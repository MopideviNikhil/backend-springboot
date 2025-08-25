package com.rent.agri.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rent.agri.model.dto.AddressRequest;
import com.rent.agri.model.dto.AddressResponse;
import com.rent.agri.model.dto.CreateAccount;
import com.rent.agri.model.dto.CreateAccountResponse;
import com.rent.agri.model.dto.EditProfile;
import com.rent.agri.model.dto.ImageDto;
import com.rent.agri.model.dto.Login;
import com.rent.agri.model.entity.Image;
import com.rent.agri.service.UserService;
import com.rent.agri.util.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated	
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET,
		RequestMethod.PUT })
public class UserController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<CreateAccountResponse>>> getAllUsers() {
		List<CreateAccountResponse> users = userService.getAllUsers();

		return ResponseEntity
				.ok(ApiResponse.<List<CreateAccountResponse>>builder().message("All users fetched successfully")
						.success(true).status(HttpStatus.OK.value()).data(users).build());
	}


	@PostMapping
	public ResponseEntity<ApiResponse<CreateAccountResponse>> createUser(@Valid @ModelAttribute CreateAccount dto) {
		CreateAccountResponse saved = userService.saveUser(dto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.<CreateAccountResponse>builder().message("User created successfully").success(true)
						.status(HttpStatus.CREATED.value()).data(saved).build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CreateAccountResponse>> getUserById(@PathVariable 
            @Min(value = 1, message = "User ID must be greater than 0") Long id) {
		CreateAccountResponse user = userService.getUserById(id);
		return ResponseEntity.ok(ApiResponse.<CreateAccountResponse>builder().message("User fetched successfully")
				.success(true).status(HttpStatus.OK.value()).data(user).build());
	}

	@GetMapping("/by-email")
	public ResponseEntity<ApiResponse<CreateAccountResponse>> getUserByEmail(@RequestParam 
	        @NotBlank(message = "Email is required") 
	        @Email(message = "Invalid email format")  String email) {
		CreateAccountResponse user = userService.getUserByEmail(email);
		return ResponseEntity
				.ok(ApiResponse.<CreateAccountResponse>builder().message("User fetched successfully by email")
						.success(true).status(HttpStatus.OK.value()).data(user).build());
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<CreateAccountResponse>> login(@RequestBody Login login) {
		CreateAccountResponse loggedInUser = userService.login(login);
		return ResponseEntity.ok(ApiResponse.<CreateAccountResponse>builder().message("Login successful").success(true)
				.status(HttpStatus.OK.value()).data(loggedInUser).build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CreateAccountResponse>> updateUser(@PathVariable 
            @Min(value = 1, message = "User ID must be greater than 0") Long id,
			@RequestBody EditProfile dto) {
		CreateAccountResponse updated = userService.updateUser(id, dto);
		return ResponseEntity.ok(ApiResponse.<CreateAccountResponse>builder().message("User updated successfully")
				.success(true).status(HttpStatus.OK.value()).data(updated).build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteUserById(@PathVariable 
            @Min(value = 1, message = "User ID must be greater than 0") Long id) {
		System.out.println(id+"  hii hello");
		userService.deleteUserById(id);
		return ResponseEntity.ok(ApiResponse.<Void>builder().message("User deleted successfully").success(true)
				.status(HttpStatus.OK.value()).build());
	}

	@DeleteMapping("/by-email")
	public ResponseEntity<ApiResponse<Void>> deleteUserByEmail(@RequestParam 
	        @NotBlank(message = "Email is required") 
	        @Email(message = "Invalid email format") String email) {
		userService.deleteUserByEmail(email);
		return ResponseEntity.ok(ApiResponse.<Void>builder().message("User deleted successfully by email").success(true)
				.status(HttpStatus.OK.value()).build());
	}

	@GetMapping("image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable 
            @Min(value = 1, message = "User ID must be greater than 0") Long id) {
		Image image = userService.getUserImageById(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
				.contentType(MediaType.parseMediaType(image.getContentType())).body(image.getImageData());
	}

	@GetMapping("image-i/{id}")
	public ResponseEntity<ApiResponse<ImageDto>> getImageI(@PathVariable 
            @Min(value = 1, message = "User ID must be greater than 0") Long id) {
		Image image = userService.getUserImageById(id);
		 ImageDto dto = new ImageDto();
		    dto.setFileName(image.getFileName());
		    dto.setContentType(image.getContentType());
		    dto.setImageData(image.getImageData());
		return ResponseEntity.ok(ApiResponse.<ImageDto>builder().message("User address saved successfully").success(true)
				.status(HttpStatus.OK.value()).data(dto).build());
	}



	@GetMapping("image-e/{email}")
	public ResponseEntity<byte[]> getImage(@PathVariable 
	        @NotBlank(message = "Email is required") 
	        @Email(message = "Invalid email format") String email) {
		Image image = userService.getUserImageByEmail(email);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
				.contentType(MediaType.parseMediaType(image.getContentType())).body(image.getImageData());
	}

	@PostMapping("/address")
	public ResponseEntity<ApiResponse<AddressResponse>> saveAddress(@Valid @RequestBody AddressRequest dto) {
		AddressResponse saved = userService.saveAddress(dto);
		return ResponseEntity.ok(ApiResponse.<AddressResponse>builder().message("User address saved successfully")
				.success(true).status(HttpStatus.OK.value()).data(saved).build());
	}

	@PutMapping("/addressup")
	public ResponseEntity<ApiResponse<AddressResponse>> updateAddress(@Valid @RequestBody AddressRequest dto) {
		AddressResponse saved = userService.updateAddress(dto);
		return ResponseEntity.ok(ApiResponse.<AddressResponse>builder().message("User address updated successfully")
				.success(true).status(HttpStatus.OK.value()).data(saved).build());
	}
}
