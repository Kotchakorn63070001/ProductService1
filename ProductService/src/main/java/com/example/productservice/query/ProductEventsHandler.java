package com.example.productservice.query;

import com.example.productservice.core.ProductEntity;
import com.example.productservice.core.data.ProductRepository;
import com.example.productservice.core.event.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

// รอฟัง เมื่อมี product ใหม่เข้ามา
@Component
public class ProductEventsHandler {
    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(event, entity); // copy จาก event มาใส่ใน entity
        productRepository.save(entity);
    }
}
