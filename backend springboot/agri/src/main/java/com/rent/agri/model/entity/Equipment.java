package com.rent.agri.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;
    @Column(nullable = false)
    private String title;
    private String category;
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String location;
    private LocalDateTime time;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "equipment")
    private List<Review> reviews;

    @OneToMany(mappedBy = "equipment")
    private List<Rental> rentals;

    @OneToOne(mappedBy = "equipment", cascade = CascadeType.ALL)
    private Image image;
}
