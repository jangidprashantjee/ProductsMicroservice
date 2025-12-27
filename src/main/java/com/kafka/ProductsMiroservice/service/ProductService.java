package com.kafka.ProductsMiroservice.service;

import com.kafka.ProductsMiroservice.controller.CreateProductRestModel;

public interface ProductService {
      String createProduct(CreateProductRestModel product);
}
