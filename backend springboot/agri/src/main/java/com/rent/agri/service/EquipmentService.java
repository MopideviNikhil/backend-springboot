package com.rent.agri.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.model.dto.EquipmentDto;
import com.rent.agri.model.dto.EquipmentResponse;
import com.rent.agri.model.dto.EquipmentResponseL;
import com.rent.agri.model.entity.Equipment;

public interface EquipmentService {
    EquipmentResponse saveEquipment(EquipmentDto equipmentDto);

	void uploadEquipmentImage(Long eqId, MultipartFile file);

	EquipmentResponse updateEquipment(Long equipmentId, EquipmentDto dto);


	List<EquipmentResponseL> getByEmail(String email);

	List<EquipmentResponseL> getByLocation(String location);

	List<EquipmentResponseL> getByCategory(String category);


	List<Equipment> getAllEquipment();

	void deleteEquipment(Long eqId);


}
