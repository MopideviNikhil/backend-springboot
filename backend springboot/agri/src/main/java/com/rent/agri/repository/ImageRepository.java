package com.rent.agri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.agri.model.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByUserEmail(String email);

    long deleteByUserEmail(String email);

    boolean existsByUserEmail(String email);
}
