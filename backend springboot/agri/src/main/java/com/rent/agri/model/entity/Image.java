package com.rent.agri.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String fileName;
    private String contentType;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @ManyToOne
    private Equipment equipment;

//    @JsonIgnore
    @OneToOne(mappedBy = "image",cascade = CascadeType.ALL)
    private User user;
}
