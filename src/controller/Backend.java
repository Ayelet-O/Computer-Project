/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import model.Customer;
import model.Product;
import model.PurchaseOrder;

/**
 *
 * @author student
 */
public interface Backend {
    void AddCustomer(Customer c) throws  Exception;
    void AddProduct(Product c) throws  Exception;
    HashMap<Long, Customer> getAllCustomers()throws Exception;
    HashSet <Product> getAllProducts()throws  Exception;
    void PlaceOrder(PurchaseOrder po)throws  Exception;
    void RemoveProduct(Product c) throws  Exception;
    ArrayList<PurchaseOrder>getCustomersOrders(Customer c)throws  Exception;
    Float CalcProductsTotalCost(Product [] products)throws  Exception;

}
