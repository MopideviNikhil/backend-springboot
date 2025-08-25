package com.rent.agri.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.exception.EquipmentNotFoundException;
import com.rent.agri.exception.UserNotFoundException;
import com.rent.agri.model.dto.EquipmentDto;
import com.rent.agri.model.dto.EquipmentResponse;
import com.rent.agri.model.dto.EquipmentResponseL;
import com.rent.agri.model.dto.ImageDto;
import com.rent.agri.model.entity.Equipment;
import com.rent.agri.model.entity.Image;
import com.rent.agri.model.entity.Rental;
import com.rent.agri.model.entity.User;
import com.rent.agri.repository.EquipmentRepository;
import com.rent.agri.repository.ImageRepository;
import com.rent.agri.repository.UserRepository;
import com.rent.agri.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public EquipmentResponse saveEquipment(EquipmentDto dto) {
        Equipment equipment = new Equipment();
        equipment.setTitle(dto.getTitle());
        equipment.setCategory(dto.getCategory());
        equipment.setDescription(dto.getDescription());
        equipment.setPrice(dto.getPrice());
        equipment.setLocation(dto.getLocation());
        equipment.setTime(LocalDateTime.now());

        Image image = Image.builder().build();
        equipment.setImage(saveImage(dto.getImageDto(), image));
        
        image.setEquipment(equipment);
        User user = userRepository.findById(dto.getUserId())
        		.orElseThrow(() -> new UserNotFoundException("Equipment not found with id "+dto.getUserId()));
      equipment.setUser(user);
      
        Equipment saved = equipmentRepository.save(equipment);
        return mapToDto(saved);
    }
    

    
    private Image saveImage(MultipartFile file,Image img) {
    	if (file == null || file.isEmpty()) {
    		throw new IllegalArgumentException("Uploaded file is empty or missing");
    	}
    	img.setFileName(file.getOriginalFilename());
    	img.setContentType(file.getContentType());
    	
    	try {
    		img.setImageData(file.getBytes());
    	} catch (IOException e) {
    		throw new IllegalArgumentException("Failed to read uploaded file", e);
    	}
    	
    	return img;
    }

    @Override
    public void uploadEquipmentImage(Long eqId, MultipartFile file) {
        Equipment existing = equipmentRepository.findById(eqId)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with id " + eqId));

        Image image = existing.getImage();
        if (image == null) {
            image = Image.builder().build();
        }
        image.setEquipment(existing);
        existing.setImage(saveImage(file, image));
        equipmentRepository.save(existing);
    }

    @Override
    public EquipmentResponse updateEquipment(Long equipmentId, EquipmentDto dto) {
        Equipment existing = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with id " + equipmentId));
     
        if (dto.getTitle() != null && !dto.getTitle().trim().isEmpty()) {
            existing.setTitle(dto.getTitle());
        }
        if (dto.getCategory() != null && !dto.getCategory().trim().isEmpty()) {
            existing.setCategory(dto.getCategory());
        }
        if (dto.getDescription() != null && !dto.getDescription().trim().isEmpty()) {
            existing.setDescription(dto.getDescription());
        }
        if (dto.getPrice() > 0) {
            existing.setPrice(dto.getPrice());
        }
        if (dto.getLocation() != null && !dto.getLocation().trim().isEmpty()) {
            existing.setLocation(dto.getLocation());
        }

        Equipment saved = equipmentRepository.save(existing);
        return mapToDto(saved);
    }

    @Override
    public List<Equipment> getAllEquipment() {
        List<Equipment> list = equipmentRepository.findAll();
        if (list.isEmpty()) {
            throw new EquipmentNotFoundException("No equipment found");
        }
       
        return list;
    }


    public List<EquipmentResponseL> getByEmail(String email) {
        List<Equipment> list = equipmentRepository.findByUserEmail(email);
        if (list.isEmpty()) {
            throw new EquipmentNotFoundException("No equipment found for email: " + email);
        }
        List<EquipmentResponseL> equiList = list.stream()
                                         .map(this::mapToDtoL)
                                         .collect(Collectors.toList()); // Collect to list
       return equiList;
    }
    
    @Override
    public List<EquipmentResponseL> getByLocation(String location) {
        List<Equipment> list = equipmentRepository.findByLocation(location);
        if (list.isEmpty()) {
            throw new EquipmentNotFoundException("No equipment found in location: " + location);
        }
        List<EquipmentResponseL> equiList = list.stream()
                .map(this::mapToDtoL)
                .collect(Collectors.toList()); 
        return equiList;
    }

    @Override
    public List<EquipmentResponseL> getByCategory(String category) {
        List<Equipment> list = equipmentRepository.findByCategory(category);
        if (list.isEmpty()) {
            throw new EquipmentNotFoundException("No equipment found in category: " + category);
        }
        List<EquipmentResponseL> equiList = list.stream()
                .map(this::mapToDtoL)
                .collect(Collectors.toList()); 
        return equiList;
    }

    private EquipmentResponse mapToDto(Equipment equipment) {
        return EquipmentResponse.builder()
                .equipId(equipment.getEquipmentId())
                .title(equipment.getTitle())
                .category(equipment.getCategory())
                .description(equipment.getDescription())
                .price(equipment.getPrice())
                .location(equipment.getLocation())
                .build();
    }
    
    private EquipmentResponseL mapToDtoL(Equipment equipment) {
    	return EquipmentResponseL.builder()
    			.equipId(equipment.getEquipmentId())
    			.title(equipment.getTitle())
    			.category(equipment.getCategory())
    			.description(equipment.getDescription())
    			.price(equipment.getPrice())
    			.location(equipment.getLocation())
    			.image(mapToImage(equipment.getImage()))
    			.build();
    }
    
    private ImageDto mapToImage(Image image) {
    	return ImageDto.builder()
    			.fileName(image.getFileName())
    			.contentType(image.getContentType())
    			.imageData(image.getImageData())
    			.build();
    			
    }
    
    private Equipment mapDtoToEntity(EquipmentDto dto) {
        Equipment equipment = new Equipment();
        equipment.setTitle(dto.getTitle());
        equipment.setCategory(dto.getCategory());
        equipment.setDescription(dto.getDescription());
        equipment.setPrice(dto.getPrice());
        equipment.setLocation(dto.getLocation());
        return equipment;
    }

    @Override
    @Transactional
    public void deleteEquipment(Long eqId) {
        Equipment existing = equipmentRepository.findById(eqId)
            .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with id " + eqId));

        if (existing.getImage() != null) {
            existing.getImage().setEquipment(null);
            existing.setImage(null);
        }

        existing.setUser(null);

        
        List<Rental> rentals = existing.getRentals();
        if (rentals != null) {
            rentals.stream().forEach(rental -> rental.setEquipment(null));
            rentals.clear(); 
            existing.setRentals(rentals); 
        }


        equipmentRepository.delete(existing);
    }

    
    
   
}
