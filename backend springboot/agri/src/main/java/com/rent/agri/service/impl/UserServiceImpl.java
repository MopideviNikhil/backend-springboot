package com.rent.agri.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.exception.AddressNotFoundException;
import com.rent.agri.exception.EmailNotFoundException;
import com.rent.agri.exception.ImageNotFoundException;
import com.rent.agri.exception.PasswordMismatchException;
import com.rent.agri.exception.UserNotFoundException;
import com.rent.agri.model.dto.AddressRequest;
import com.rent.agri.model.dto.AddressResponse;
import com.rent.agri.model.dto.CreateAccount;
import com.rent.agri.model.dto.CreateAccountResponse;
import com.rent.agri.model.dto.EditProfile;
import com.rent.agri.model.dto.Login;
import com.rent.agri.model.entity.Address;
import com.rent.agri.model.entity.Image;
import com.rent.agri.model.entity.User;
import com.rent.agri.repository.AddressRepository;
import com.rent.agri.repository.ImageRepository;
import com.rent.agri.repository.UserRepository;
import com.rent.agri.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	private final AddressRepository addressRepository;
	
	@Autowired
	private final ImageRepository imageRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	
	@Override
	public CreateAccountResponse saveUser(CreateAccount request) {
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setAge(request.getAge());
		user.setGender(request.getGender());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setEmail(request.getEmail());
		user.setRole(request.getRole());
		user.setPassword(request.getPassword());

		Image image = user.getImage();
		if (image == null)
			image = Image.builder().build();

		user.setImage(saveImage(request.getImageDto(), image));
		
		String text="You have successfully created you account";
		sendEmail(request.getEmail(), "Agricultural Rental Message", text);
		User saved = userRepository.save(user);
		return mapToEntity(saved);
	}
	
	private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(sender);//project
        msg.setTo(sender);//user
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }

	private Image saveImage(MultipartFile file, Image img) {
		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("Uploaded file is empty or missing");
		}
		img.setFileName(file.getOriginalFilename());
		img.setContentType(file.getContentType());
		try {
			img.setImageData(file.getBytes());
		} catch (IOException e) {
			throw new IllegalArgumentException("Uploaded file is empty or missing");
		}
		return img;
	}

	public void uploadProfile(Long userId, MultipartFile file) throws IOException {
		User existing = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("Uploaded file is empty or missing");
		}

		Image image = existing.getImage();
		if (image == null)
			image = Image.builder().build();

		existing.setImage(saveImage(file, image));
		userRepository.save(existing);
	}

	@Override
	public CreateAccountResponse updateUser(Long userId, EditProfile dto) {
		User existing = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

		if (dto.getFirstName() != null && !dto.getFirstName().trim().isEmpty()) {
			existing.setFirstName(dto.getFirstName());
		}
		if (dto.getLastName() != null && !dto.getLastName().trim().isEmpty()) {
			existing.setLastName(dto.getLastName());
		}
		if (dto.getAge() > 0) {
			existing.setAge(dto.getAge());
		}
		if (dto.getGender() != null ) {
			existing.setGender(dto.getGender());
		}
		if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().trim().isEmpty()) {
			existing.setPhoneNumber(dto.getPhoneNumber());
		}

		User savedUser = userRepository.save(existing);
		return mapToEntity(savedUser);
	}

	@Override
	public CreateAccountResponse getUserById(Long userId) {
		User existing = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

		return mapToEntity(existing);
	}

	@Override
	public CreateAccountResponse getUserByEmail(String email) {
		User existing = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with email " + email));

		return mapToEntity(existing);
	}

	@Override
	public List<CreateAccountResponse> getAllUsers() {
		 List<CreateAccountResponse> userList = userRepository.findAll().stream().map(this::mapToEntity).toList();
		 if(userList.isEmpty())
			 throw new UserNotFoundException("no users");
		return userList;
	}

	@Override
	@Transactional
	public void deleteUserById(Long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

	    Address address = user.getAddress(); 
	    if (address != null) {
	        user.setAddress(null);
	        addressRepository.delete(address);
	    }

	    Image image = user.getImage(); 
	    if (image != null) {
	        user.setImage(null);
	        imageRepository.delete(image);
	    }

	    userRepository.delete(user);
	}


	@Override
	@Transactional
	public void deleteUserByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("No user found with email: " + email));
		
		Address address = user.getAddress(); 
	    if (address != null) {
	        user.setAddress(null);
	        addressRepository.delete(address);
	    }

	    Image image = user.getImage(); 
	    if (image != null) {
	        user.setImage(null);
	        imageRepository.delete(image);
	    }

	    userRepository.delete(user);
	}

	@Override
	public CreateAccountResponse login(Login login) {
		User user = userRepository.findByEmail(login.getEmail())
				.orElseThrow(() -> new EmailNotFoundException("Email not found: " + login.getEmail()));

		if (!user.getPassword().equals(login.getPassword())) {
			throw new PasswordMismatchException("Password does not match for email: " + login.getEmail());
		}
		return mapToEntity(user);
	}

	@Override
	public void updatePassword(Login login) {
		User existing = userRepository.findByEmail(login.getEmail())
				.orElseThrow(() -> new UserNotFoundException("Email not found: " + login.getEmail()));
		if (login.getPassword() != null && !login.getPassword().trim().isEmpty()) {
			existing.setPassword(login.getPassword());
		}
	}

	public void otp(String email) {
		sendEmail(email,"Agricultural Renatal", "Your OTP is "+new Random().nextInt(999999));
	}

	public void verifyOtp(int otp) {

	}

	@Override
	public Image getUserImageById(Long userId) {
		User existing = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
		Image image = existing.getImage();
		if (image == null)
			throw new ImageNotFoundException("image not found with id " + userId);
		return image;
	}

	@Override
	public Image getUserImageByEmail(String email) {
		User existing = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + email));
		Image image = existing.getImage();
		if (image == null)
			throw new ImageNotFoundException("image not found with id " + email);
		return image;
	}

	private CreateAccountResponse mapToEntity(User user) {
		return CreateAccountResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName())
				.age(user.getAge()).gender(user.getGender()).email(user.getEmail()).phoneNumber(user.getPhoneNumber())
				.build();
	}

	@Override
	public AddressResponse saveAddress(AddressRequest req) {
		User existing = userRepository.findByEmail(req.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found with email " + req.getEmail()));

		Address address = Address.builder().houseNumber(req.getHouseNumber()).street(req.getStreet())
				.landmark(req.getLandmark()).village(req.getVillage()).pincode(req.getPincode()).mandal(req.getMandal())
				.district(req.getDistrict()).state(req.getState()).build();


		existing.setAddress(address);
		Address saved = userRepository.save(existing).getAddress();

		return mapToAddressResponse(saved);
	}

	@Transactional
	@Override
	public AddressResponse updateAddress(AddressRequest req) {
		User user = userRepository.findByEmail(req.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found with email " + req.getEmail()));

		Address address = Optional.ofNullable(user.getAddress()).orElseThrow(
				() -> new AddressNotFoundException("Address not found for user with email " + req.getEmail()));


		if (req.getHouseNumber() != null && !req.getHouseNumber().trim().isEmpty()) {
			address.setHouseNumber(req.getHouseNumber());
		}

		if (req.getStreet() != null && !req.getStreet().trim().isEmpty()) {
			address.setStreet(req.getStreet());
		}

		if (req.getLandmark() != null && !req.getLandmark().trim().isEmpty()) {
			address.setLandmark(req.getLandmark());
		}

		if (req.getVillage() != null && !req.getVillage().trim().isEmpty()) {
			address.setVillage(req.getVillage());
		}

		if (req.getPincode() != null && !req.getPincode().trim().isEmpty()) {
			address.setPincode(req.getPincode());
		}

		if (req.getMandal() != null && !req.getMandal().trim().isEmpty()) {
			address.setMandal(req.getMandal());
		}

		if (req.getDistrict() != null && !req.getDistrict().trim().isEmpty()) {
			address.setDistrict(req.getDistrict());
		}

		if (req.getState() != null && !req.getState().trim().isEmpty()) {
			address.setState(req.getState());
		}

		return mapToAddressResponse(address);
	}

	private AddressResponse mapToAddressResponse(Address address) {
		return AddressResponse.builder().addressId(address.getAddressId()).houseNumber(address.getHouseNumber())
				.street(address.getStreet()).landmark(address.getLandmark()).village(address.getVillage())
				.pincode(address.getPincode()).mandal(address.getMandal()).district(address.getDistrict())
				.state(address.getState()).build();
	}

}
