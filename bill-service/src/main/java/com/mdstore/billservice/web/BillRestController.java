package com.mdstore.billservice.web;

import com.mdstore.billservice.DTOs.BillRequestDTO;
import com.mdstore.billservice.entities.Bill;
import com.mdstore.billservice.repository.BillRepository;
import com.mdstore.billservice.service.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillRestController {


    private final BillService billService;

    public BillRestController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/bills")
    public List<Bill> billsList(){
        return billService.getAllBills();
    }

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        return billService.findById(id);
    }

    @PostMapping("/bills")
    public Bill createBill(@RequestBody BillRequestDTO billRequestDTO){
        return billService.saveBill(billRequestDTO);
    }
}
