package com.myp.myproject.Model;

public class PurchaseDetail {
    private int ID;
    private int purchaseID;
    private int productID;
    private int quantity;
    private double purchasePrice;
    private String type;
    private String unit;
    private double amount;

    public PurchaseDetail(int ID, int purchaseID, int productID, int quantity, double purchasePrice, String type, String unit, double amount) {
        this.ID = ID;
        this.purchaseID = purchaseID;
        this.productID = productID;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.type = type;
        this.unit = unit;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
