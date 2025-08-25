package com.rent.agri.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.exception.EmailNotFoundException;
import com.rent.agri.exception.UserNotFoundException;
import com.rent.agri.model.entity.Image;
import com.rent.agri.model.entity.User;
import com.rent.agri.repository.ImageRepository;
import com.rent.agri.repository.UserRepository;
import com.rent.agri.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	private final ImageRepository imageRepository;
	private final UserRepository userRepository;

	@Override
	public Image saveForUserEmail(String email, MultipartFile file) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
		
		Image image = user.getImage();
		if (image == null) {
			image = Image.builder().build();
		}
		Image saved = imageRepository.save(saveImage(file, image));
		user.setImage(saved); 
		userRepository.save(user);

		return saved;
	}

	public Image saveImage(MultipartFile file, Image image) {
		if (file == null || file.isEmpty()) {
		    throw new IllegalArgumentException("Uploaded file is empty or missing");
		}

		image.setFileName(file.getOriginalFilename());
		image.setContentType(file.getContentType());
		try {
			image.setImageData(file.getBytes());
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to read uploaded file bytes", e);
		}
		return image;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Image> getById(Long id) {
		return imageRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Image> getByUserEmail(String email) {
		return imageRepository.findByUserEmail(email);
	}

	@Override
	public Image updateById(Long id, MultipartFile file) {
		Image image = imageRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Image not found for id: " + id));
		return imageRepository.save(saveImage(file, image));
	}

	@Override
	public Image updateByUserEmail(String email, MultipartFile file) {
		Image image = imageRepository.findByUserEmail(email)
				.orElseThrow(() -> new EmailNotFoundException("Image not found for user email: " + email));
		return imageRepository.save(saveImage(file, image));
	}

	@Override
	public void deleteById(Long id) {
		if (!imageRepository.existsById(id)) {
			throw new IllegalArgumentException("Image not found for id: " + id);
		}
		imageRepository.deleteById(id);
	}

	@Override
	public long deleteByUserEmail(String email) {
		imageRepository.findByUserEmail(email)
				.orElseThrow(() -> new EmailNotFoundException("Image not found for user email: " + email));
		return imageRepository.deleteByUserEmail(email);

	}

}
