/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Product;
import model.PurchaseOrder;

/**
 *
 * @author student
 */
public class Backend_DAO_List implements Backend, Serializable {

    private Map<Long, Customer> Customers;
    private Set<Product> Products;
    private List<PurchaseOrder> PurchaseOrders;
    private static Backend_DAO_List bdl_singleton;

    public Map<Long, Customer> getCustomers() {
        return Customers;
    }

    public void setCustomers(Map<Long, Customer> Customers) {
        this.Customers = Customers;
    }

    public Set<Product> getProducts() {
        return Products;
    }

    public void setProducts(Set<Product> Products) {
        this.Products = Products;
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return PurchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrder> PurchaseOrders) {
        this.PurchaseOrders = PurchaseOrders;
    }

    public static Backend_DAO_List get_bdl_singleton() {
        if (bdl_singleton == null) {
            bdl_singleton = new Backend_DAO_List();
        }
        return bdl_singleton;
    }

    private Backend_DAO_List() {
        Customers = new HashMap<>();
        Products = new HashSet<>();
        PurchaseOrders = new ArrayList<>();
        Customers.put((long) 222222222, new Customer(222222222, "aaaaa", "AAAAAA"));
        Customers.put((long) 333333333, new Customer(333333333, "bbbbb", "BBBBBB"));
        Customers.put((long) 555555555, new Customer(555555555, "ccccc", "CCCCCC"));
        Customers.put((long) 888888888, new Customer(888888888, "ddddd", "DDDDDD"));
        Products.add(new Product((long) 11111, "a", "asasas", (float) 3454) {
            @Override
            public float getPrice() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    /**
     *
     * @param customer
     * @throws Exception add customers
     */
    @Override
    public void AddCustomer(Customer c) throws Exception {
        Customers.put(c.getId(), c);
    }

    /**
     *
     * @param product
     * @throws Exception add product
     */
    @Override
    public void AddProduct(Product c) throws Exception {
        Products.add(c);
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public HashMap<Long, Customer> getAllCustomers() throws Exception {
        return (HashMap<Long, Customer>) Customers;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public HashSet<Product> getAllProducts() throws Exception {
        return (HashSet<Product>) Products;
    }

    /**
     *
     * @param po
     */
    @Override
    public void PlaceOrder(PurchaseOrder po) {
        PurchaseOrders.add(po);
    }

    /**
     *
     * @param c
     * @throws Exception
     */
    @Override
    public void RemoveProduct(Product c) throws Exception {
        Iterator<Product> product = Products.iterator();
        while (product.hasNext()) {
            if (product.next().equals(c)) {
                Products.remove(c);
            }
        }
    }

    /**
     *
     * @param c
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<PurchaseOrder> getCustomersOrders(Customer c) throws Exception {
        ArrayList<PurchaseOrder> Pmap = new ArrayList<PurchaseOrder>();
        //Iterator<PurchaseOrder> pOrder = PurchaseOrders.iterator();
        for (PurchaseOrder p : PurchaseOrders) {
            if (p.getOrderingCustomer().equals(c)) {
                //Pmap = p.getProductMap();
                break;
            }
        }
        return Pmap;
    }

    /**
     *
     * @param products
     * @return
     * @throws Exception
     */
    @Override
    public Float CalcProductsTotalCost(Product[] products) throws Exception {
        float sum = 0;
        for (int i = 0; i < products.length; i++) {
            sum += products[i].getPricePerUnit();
        }
        return sum;
    }

    public void savaDataToFile() throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data"));
        oos.writeObject(Customers);
        oos.writeObject(Products);
        oos.writeObject(PurchaseOrders);
        oos.close();

    }

    public void loadDataFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        ois = new ObjectInputStream(new FileInputStream("data"));
        Customers = (Map<Long, Customer>) ois.readObject();
        Products = (Set<Product>) ois.readObject();
        PurchaseOrders = (List<PurchaseOrder>) ois.readObject();
        ois.close();
    }
}
