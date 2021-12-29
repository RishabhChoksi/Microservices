package com.springcloud.productservice.restclients;


import com.springcloud.productservice.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "API-GATEWAY-SERVICE")
public interface CouponClient {

    @GetMapping(value = "couponapi/coupons/{code}")
    public Coupon getCoupon(@PathVariable(value = "code") String code);

}
