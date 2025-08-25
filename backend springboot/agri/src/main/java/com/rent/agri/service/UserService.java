package com.rent.agri.service;

import java.util.List;

import com.rent.agri.model.dto.AddressRequest;
import com.rent.agri.model.dto.AddressResponse;
import com.rent.agri.model.dto.CreateAccount;
import com.rent.agri.model.dto.CreateAccountResponse;
import com.rent.agri.model.dto.EditProfile;
import com.rent.agri.model.dto.Login;
import com.rent.agri.model.entity.Image;

public interface UserService {
    CreateAccountResponse saveUser(CreateAccount data);
    CreateAccountResponse updateUser(Long id, EditProfile dto);
    CreateAccountResponse getUserById(Long id);
    CreateAccountResponse getUserByEmail(String email);
    List<CreateAccountResponse> getAllUsers();
    void deleteUserById(Long id);
    void deleteUserByEmail(String email);
	CreateAccountResponse login(Login login);
	void updatePassword(Login login);
	Image getUserImageById(Long userId);
	Image getUserImageByEmail(String email);
	AddressResponse saveAddress(AddressRequest req);
	AddressResponse updateAddress(AddressRequest req);
}
