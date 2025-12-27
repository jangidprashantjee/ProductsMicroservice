package com.kafka.ProductsMiroservice.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.kafka.ProductsMiroservice.controller.CreateProductRestModel;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	
	private final KafkaTemplate<String , ProductCreatedEvent> kafkaTemplate;
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	@Override
	public String createProduct(CreateProductRestModel product) {
		String productId=UUID.randomUUID().toString();
		
		//Todo persist the product event to the database before publishing to kafka topic
		
		ProductCreatedEvent productCreatedEvent=
				new ProductCreatedEvent(productId,product.getName(),product.getPrice(),product.getQuantity());
		//kafkaTemplate.send("products-created-event-topic",productId,productCreatedEvent);
		// async call not wait for result ,if it fails we can't know here 
		//so we need confirmation from kafka broker that message is persisted successfully kafka topic
		
		CompletableFuture<SendResult<String,ProductCreatedEvent>> future=
				kafkaTemplate.send("products-created-event-topic",productId,productCreatedEvent); 
		// we use completable future to get the result of async call
		future.whenComplete((result,exception)->{
			if(exception==null) {
				logger.info("Message sent successfully for product id: "+productId+
						" partition: "+result.getRecordMetadata());
			}else {
				logger.error("Message sending failed for product id: "+productId,exception);
			}
		});
		logger.info("***************************");
		//future.join(); // block the thread until the async call is completed ,make it synchronous 
		return productId;
	}

}
