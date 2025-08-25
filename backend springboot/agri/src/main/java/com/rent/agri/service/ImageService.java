package com.rent.agri.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.model.entity.Image;

public interface ImageService {

    Image saveForUserEmail(String email, MultipartFile file);

    Optional<Image> getById(Long id);

    Optional<Image> getByUserEmail(String email);

    Image updateById(Long id, MultipartFile file);

    Image updateByUserEmail(String email, MultipartFile file);

    void deleteById(Long id);

    long deleteByUserEmail(String email);
}
