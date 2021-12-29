package com.springcloud.couponservice.controller;

import com.springcloud.couponservice.model.Coupon;
import com.springcloud.couponservice.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponController {

    @Autowired
    private CouponRepository couponRepository;

    @PostMapping(value = "/coupons")
    public Coupon create(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @GetMapping(value = "/coupons/{code}")
    public Coupon getCoupon(@PathVariable(value = "code") String code) {
        return couponRepository.findByCode(code);
    }
}
