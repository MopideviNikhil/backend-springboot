package com.rent.agri.model.entity;

import java.util.List;

import com.rent.agri.model.enums.Gender;
import com.rent.agri.model.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private Integer age;

	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	    @Column(nullable = false)
	 private String phoneNumber;
	   
	    @Column(nullable = false, unique = true)
	private String email;
	    @Column(nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "addressId")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	private Image image;

	@OneToMany(mappedBy = "user")
	private List<Rental> rentals;

	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
}
