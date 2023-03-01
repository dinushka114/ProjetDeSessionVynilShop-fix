/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vynilshop.model;

public class Order {
    
    private int id;
    private int orderId;
    private int productId;
    private int uid;
    private int qunatity;
    private String date;

    public Order() {
    }

    public Order(int productId, int uid, int qunatity, String date) {
        this.productId = productId;
        this.uid = uid;
        this.qunatity = qunatity;
        this.date = date;
    }
    
     public Order(int productId, int uid, int qunatity) {
        this.productId = productId;
        this.uid = uid;
        this.qunatity = qunatity;
    }

    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQunatity() {
        return qunatity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
    

    
}
