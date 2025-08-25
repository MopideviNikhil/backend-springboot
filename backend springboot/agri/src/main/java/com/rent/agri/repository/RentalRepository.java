package com.rent.agri.repository;

import com.rent.agri.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByUserUserId(Long userId);

    List<Rental> findByEquipmentEquipmentId(Long equipmentId);

    boolean existsByEquipmentEquipmentId(Long equipmentId);
}
