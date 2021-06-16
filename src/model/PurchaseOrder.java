/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author student
 */
public class PurchaseOrder implements Serializable{
    private Customer orderingCustomer;
    private ArrayList<Product>productMap;
    private LocalDate orderDate;

    public PurchaseOrder() {
        this.orderDate=LocalDate.now();
        productMap=new ArrayList<Product>();
    }

    public PurchaseOrder(Customer orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    public PurchaseOrder(Customer c, ArrayList<Product> arrp, LocalDate toLocalDate) {
        orderingCustomer=c;
        this.productMap=arrp;
        this.orderDate=toLocalDate;
    }

    public Customer getOrderingCustomer() {
        return orderingCustomer;
    }

    public void setOrderingCustomer(Customer orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    public ArrayList<Product> getProductMap() {
        return productMap;
    }

    public void setProductMap(ArrayList<Product> productMap) {
        this.productMap = productMap;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "orderingCustomer=" + orderingCustomer + ", productMap=" + productMap + ", orderDate=" + orderDate + '}';
    }
    
}
