package com.kafka.ProductsMiroservice.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCreatedEvent {
	
	private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    
    
    
    
}
