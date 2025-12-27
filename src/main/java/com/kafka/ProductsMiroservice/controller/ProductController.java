package com.kafka.ProductsMiroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.ProductsMiroservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products") // http://localhost:<port no>/products
@RequiredArgsConstructor
public class ProductController {
	  
	 private final ProductService productService;
	 @PostMapping
	 public ResponseEntity<String> createProducts(@RequestBody CreateProductRestModel product) {
	     String productId = productService.createProduct(product);  
		 return ResponseEntity.status(HttpStatus.CREATED).body(productId);
	    }
}
