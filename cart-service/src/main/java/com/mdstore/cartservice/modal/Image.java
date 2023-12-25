package com.mdstore.cartservice.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
public class Image {
    private Long id;
    private String imageUrl;
    private Product product;
    private boolean primaryImage;
}
