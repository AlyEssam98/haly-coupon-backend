package com.haly.coupon.model;

public class Coupon {
    private String productId;
    private String code;
    private String description;
    private double percentage;

    public Coupon(String productId, String code, String description, double percentage) {
        this.productId = productId;
        this.code = code;
        this.description = description;
        this.percentage = percentage;

    }

    public String getProductId() { return productId; }
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public double getPercentage() { return percentage; }


    public void setProductId(String productId) { this.productId = productId; }
    public void setCode(String code) { this.code = code; }
    public void setDescription(String description) { this.description = description; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

}
