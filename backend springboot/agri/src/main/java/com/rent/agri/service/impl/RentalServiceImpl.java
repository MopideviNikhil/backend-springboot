package com.rent.agri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.agri.exception.EquipmentNotFoundException;
import com.rent.agri.model.dto.RentalRequest;
import com.rent.agri.model.dto.RentalResponse;
import com.rent.agri.model.entity.Equipment;
import com.rent.agri.model.entity.Payment;
import com.rent.agri.model.entity.Rental;
import com.rent.agri.repository.EquipmentRepository;
import com.rent.agri.repository.RentalRepository;
import com.rent.agri.service.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private EquipmentRepository equipmentRepository;
	

	@Override
	public RentalResponse createRental(RentalRequest req) {
		Equipment existing = equipmentRepository.findById(req.getEqupId())
				.orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with id " + req.getEqupId()));
	     if (req.getStartTime() == null || req.getEndTime() == null) {
	            throw new IllegalArgumentException("Start time and End time are required for booking");
	        }

	        long hours = java.time.Duration.between(req.getStartTime(), req.getEndTime()).toHours();
	        if (hours <= 0) {
	            throw new IllegalArgumentException("End time must be after Start time");
	        }

	        double totalPrice = existing.getPrice() * hours;
	        
	        Rental rental=Rental.builder()
	        		.startTime(req.getStartTime())
	        		.endTime(req.getEndTime())
	        		.build();

	         Payment payment = Payment.builder().build();
	         payment.setPrice(totalPrice);
	        rental.setPayment(payment);
	        rental.setEquipment(existing);
	        
	        rental.setUser(existing.getUser());
	        
	        Rental saved = rentalRepository.save(rental);
	      
	        		
		return mapToDto(saved);
	}

	@Override
	public List<Rental> getRentalsByUserId(Long userId) {
		return rentalRepository.findByUserUserId(userId);
	}

	@Override
	public List<Rental> getRentalsByEquipmentId(Long equipmentId) {
		return rentalRepository.findByEquipmentEquipmentId(equipmentId);
	}

	@Override
	public void deleteRental(Long rentalId) {
	}

	
	private RentalResponse mapToDto(Rental rental) {
		Rental existing = rentalRepository.findById(rental.getRentalId())
				.orElseThrow(() -> new RuntimeException("Rental not found with ID: " + rental.getRentalId()));
		
		return RentalResponse.builder()
				.RenId(rental.getRentalId())
				.startTime(rental.getStartTime())
				.endTime(rental.getEndTime())
				.price(existing.getPayment().getPrice())
				.build();
	}
	
}
