package com.rent.agri.service;

import com.rent.agri.model.dto.RentalRequest;
import com.rent.agri.model.dto.RentalResponse;
import com.rent.agri.model.entity.Rental;

import java.util.List;

public interface RentalService {
    RentalResponse createRental(RentalRequest rental);
    List<Rental> getRentalsByUserId(Long userId);
    List<Rental> getRentalsByEquipmentId(Long equipmentId);
    void deleteRental(Long rentalId);
}
