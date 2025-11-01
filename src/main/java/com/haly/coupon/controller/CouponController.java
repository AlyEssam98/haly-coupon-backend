package com.haly.coupon.controller;

import com.haly.coupon.model.Coupon;
import com.haly.coupon.service.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coupons")
@CrossOrigin(origins = "*")
public class CouponController {

    private final CouponService service;

    public CouponController(CouponService service) {
        this.service = service;
    }

    // ✅ Get all products with their coupons
    @GetMapping
    public Map<String, List<Coupon>> getAllCoupons() {
        return service.findAll();
    }

    // ✅ Get coupons for a specific product
    @GetMapping("/{productId}")
    public List<Coupon> getCouponsByProduct(@PathVariable String productId) {
        return service.findById(productId);
    }
}
