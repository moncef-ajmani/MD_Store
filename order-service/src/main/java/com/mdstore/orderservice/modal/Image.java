package com.mdstore.orderservice.modal;

import lombok.Data;

@Data
public class Image {
    private Long id;
    private String imageUrl;
    private Product product;
    private boolean primaryImage;
}
