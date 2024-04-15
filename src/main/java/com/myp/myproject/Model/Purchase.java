package com.myp.myproject.Model;

import java.sql.Date;

public class Purchase {
    private Integer purchaseID;
    private Integer supplierID;
    private Date date;
    private Integer numproduct;
    private double amount;

    public Purchase(double amount, Integer numproduct, Date date, Integer supplierID, Integer purchaseID) {
        this.amount = amount;
        this.numproduct = numproduct;
        this.date = date;
        this.supplierID = supplierID;
        this.purchaseID = purchaseID;
    }

    public Integer getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(Integer purchaseID) {
        this.purchaseID = purchaseID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumproduct() {
        return numproduct;
    }

    public void setNumproduct(Integer numproduct) {
        this.numproduct = numproduct;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
