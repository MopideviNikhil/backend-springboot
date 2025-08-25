package com.rent.agri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.agri.model.entity.Equipment;
import com.rent.agri.model.entity.User;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    
    List<Equipment> findByCategory(String category);

    List<Equipment> findByLocation(String location);

   Optional<User> findByUserUserId(Long userId); 

    List<Equipment> findByTitleContainingIgnoreCase(String keyword);
    
    List<Equipment> findByUserEmail(String email);
    
}
