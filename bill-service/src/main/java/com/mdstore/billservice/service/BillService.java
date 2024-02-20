package com.mdstore.billservice.service;

import com.mdstore.billservice.DTOs.BillRequestDTO;
import com.mdstore.billservice.DTOs.ProductItemRequestDTO;
import com.mdstore.billservice.entities.Bill;
import com.mdstore.billservice.entities.ProductItem;
import com.mdstore.billservice.feignClient.PaymentRestClient;
import com.mdstore.billservice.feignClient.ProductRestClient;
import com.mdstore.billservice.feignClient.UserRestClient;
import com.mdstore.billservice.repository.BillRepository;
import com.mdstore.billservice.repository.ProductItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final ProductRestClient productRestClient;
    private final PaymentRestClient paymentRestClient;
    private final UserRestClient userRestClient;
    private final ProductItemRepository productItemRepository;


    public BillService(BillRepository billRepository, ProductRestClient productRestClient, PaymentRestClient paymentRestClient, UserRestClient userRestClient, ProductItemRepository productItemRepository) {
        this.billRepository = billRepository;
        this.productRestClient = productRestClient;
        this.paymentRestClient = paymentRestClient;
        this.userRestClient = userRestClient;
        this.productItemRepository = productItemRepository;
    }

    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

    public Bill findById(Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setUser(userRestClient.getuserById(bill.getUserID()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(productRestClient.getProductById(pi.getProductID()));
        });
        return bill;
    }

    public Bill saveBill(BillRequestDTO billRequestDTO){
        Bill bill = new Bill();
        bill.setUserID(billRequestDTO.getUserID());
        List<ProductItem> productItems = new ArrayList<>();
        for(ProductItemRequestDTO productItemRequestDTO: billRequestDTO.getProductItems()){
            ProductItem  productItem = ProductItem.builder()
                    .productID(productItemRequestDTO.getProductID())
                    .bill(bill)
                    .quantity(productItemRequestDTO.getQuantity())
                    .price((float)productRestClient.getProductById(productItemRequestDTO.getProductID()).getPrice())
                    .build();
            productItems.add(productItem);
        }
        System.out.println(productItems);
        bill.setProductItems(productItems);
        return billRepository.save(bill);

    }
}
