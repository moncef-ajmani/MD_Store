package com.mdstore.billservice.DTOs;

import com.mdstore.billservice.entities.ProductItem;
import com.mdstore.billservice.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class BillRequestDTO {
    private List<ProductItemRequestDTO> productItems;
    private Long userID;
}
