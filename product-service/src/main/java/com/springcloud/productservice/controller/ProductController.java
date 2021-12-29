package com.springcloud.productservice.controller;

import com.springcloud.productservice.model.Coupon;
import com.springcloud.productservice.model.Product;
import com.springcloud.productservice.repository.ProductRepository;
import com.springcloud.productservice.restclients.CouponClient;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productapi")
@RefreshScope
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponClient couponClient;

    @Value("${com.rishabh.springcloud.prop}")
    private String prop;

    @PostMapping(value = "/products")
    @Retry(name = "product-api", fallbackMethod = "handleError")
    public Product create(@RequestBody Product product) {
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return productRepository.save(product);
    }

    @GetMapping(value = "/prop")
    public String getProp() {
        return this.prop;
    }

    public Product handleError(Product product, Exception exception) {
        System.out.println("Inside handle error-" + exception.getMessage());
        return product;
    }
}
