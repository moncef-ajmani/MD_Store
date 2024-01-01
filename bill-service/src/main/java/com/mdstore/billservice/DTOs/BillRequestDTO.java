package com.mdstore.billservice.DTOs;

import com.mdstore.billservice.entities.ProductItem;
import com.mdstore.billservice.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BillRequestDTO {
    private Date date;
    private List<ProductItemRequestDTO> productItems;
    private Long userID;
}
