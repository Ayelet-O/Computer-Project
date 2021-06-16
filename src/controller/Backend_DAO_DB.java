/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import model.Customer;
import model.HardwareProduct;
import model.Product;
import model.PurchaseOrder;
import model.SoftwareProduct;

/**
 *
 * @author user
 */
public class Backend_DAO_DB implements Backend, Serializable {

    private static Backend_DAO_DB bdl_singleton;

    public static Backend_DAO_DB get_bdl_singleton() {
        if (bdl_singleton == null) {
            bdl_singleton = new Backend_DAO_DB();
        }
        return bdl_singleton;
    }

    private Backend_DAO_DB() {

    }

    @Override
    public void AddCustomer(Customer c) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/computerShopDB", "Ayelet", "123");
        PreparedStatement Statement = connection.prepareStatement("insert into CUSTOMERS values (?,?,?)");
        Statement.setLong(1, c.getId());
        Statement.setString(2, c.getName());
        Statement.setString(3, c.getAddrsess());
        Statement.execute();
        Statement.close();

    }

    @Override
    public void AddProduct(Product c) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/computerShopDB", "Ayelet", "123");
        PreparedStatement Statement = connection.prepareStatement("insert into PRODUCTS values (?,?,?,?,?,?)");
        Statement.setLong(1, c.getId());
        Statement.setString(2, c.getName());
        Statement.setString(3, c.getDescription());
        Statement.setFloat(4, c.getPricePerUnit());
        if (c instanceof SoftwareProduct) {
            Statement.setString(5, "SoftwareProduct");
            Statement.setInt(6, ((SoftwareProduct) c).getNumberOfHours());
        } else {
            Statement.setString(5, "HardwareProduct");
            Statement.setInt(6, ((HardwareProduct) c).getWarrantyPeriod());
        }
        Statement.execute();
        Statement.close();
    }

    @Override
    public HashMap<Long, Customer> getAllCustomers() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/computerShopDB", "Ayelet", "123");
        HashMap<Long, Customer> map = new HashMap();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from CUSTOMERS");
        while (rs.next()) {
            Customer c = new Customer();
            c.setId(rs.getLong(1));
            c.setName(rs.getString(2));
            c.setAddrsess(rs.getString(3));
            map.put(c.getId(), c);
        }
        return map;
    }

//    public void CloseConnection() {
//        try {
//            connection.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Backend_DAO_DB.class.getName()).log(Level.SEVERE, null, ex);
//        }
    @Override
    public HashSet<Product> getAllProducts() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/computerShopDB", "Ayelet", "123");
        HashSet set = new HashSet();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from PRODUCTS");
        while (rs.next()) {
            Product c;

            if (rs.getString(5).equals("SoftwareProduct")) {
                c = new SoftwareProduct(rs.getInt(6), rs.getLong(1), rs.getString(2), rs.getString(3), rs.getFloat(4));
            } else {
                c = new HardwareProduct(rs.getInt(6), rs.getLong(1), rs.getString(2), rs.getString(3), rs.getFloat(4));
            }
            set.add(c);
        }
        return set;
    }

    @Override
    public void PlaceOrder(PurchaseOrder po) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/computerShopDB", "Ayelet", "123");
        PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO ORDERS (CUSTOMER_ID, DATE_ORDER) VALUES (?,?)");
        prepareStatement.setLong(1, po.getOrderingCustomer().getId());
        prepareStatement.setDate(2, Date.valueOf(LocalDate.now()));
        prepareStatement.execute();
        //prepareStatement.close();
        Statement createStatement1 = connection.createStatement();
        ResultSet executeQuery1 = createStatement1.executeQuery("SELECT MAX(ID) FROM ORDERS");
        PreparedStatement prepareStatement1 = connection.prepareStatement("insert into PRODUCTINORDERS values(?,?)");
        boolean next = executeQuery1.next();
        long aLong = executeQuery1.getLong(1);
        for (Product p : po.getProductMap()) {
                prepareStatement1.setLong(1, aLong);
                prepareStatement1.setLong(2, p.getId());
                prepareStatement1.execute();
        }
    }

    @Override
    public void RemoveProduct(Product c) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/computerShopDB", "Ayelet", "123");
        PreparedStatement Statement = connection.prepareStatement("DELETE FROM PRODUCTS WHERE ID = ?");
        Statement.setLong(1, c.getId());
        //pstmt.executeUpdate();
        Statement.execute();
        Statement.close();
    }

    /**
     *
     * @param c
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<PurchaseOrder> getCustomersOrders(Customer c) throws Exception {
        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/computerShopDB", "Ayelet", "123");
        PreparedStatement GET_FROM_ORDERS = connection.prepareStatement("SELECT * FROM ORDERS WHERE CUSTOMER_ID=?");
        GET_FROM_ORDERS.setLong(1, c.getId());
        ResultSet ID_ORDERS = GET_FROM_ORDERS.executeQuery();
        PreparedStatement GET_FROM_PRODUCT_IN_ORDERS = connection.prepareStatement("SELECT * FROM PRODUCTINORDERS WHERE ID=?");
        PreparedStatement GET_FROM_PRODUCT = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE ID=?");
        while (ID_ORDERS.next()) {
            ArrayList<Product> arrp = new ArrayList<>();
            GET_FROM_PRODUCT_IN_ORDERS.setLong(1, ID_ORDERS.getLong(1));
            ResultSet IDRODUCTINORDERS = GET_FROM_PRODUCT_IN_ORDERS.executeQuery();
            while (IDRODUCTINORDERS.next()) {
                GET_FROM_PRODUCT.setLong(1, IDRODUCTINORDERS.getLong(2));
                ResultSet IDPRODUCT = GET_FROM_PRODUCT.executeQuery();
                IDPRODUCT.next();
                Product p;
                if (IDPRODUCT.getString(5).compareTo("HardwareProduct") == 0) {
                    p = new HardwareProduct(IDPRODUCT.getInt(6), IDPRODUCT.getLong(1),
                            IDPRODUCT.getString(2), IDPRODUCT.getString(3), IDPRODUCT.getFloat(4));
                } else {
                    p = new SoftwareProduct(IDPRODUCT.getInt(6), IDPRODUCT.getLong(1),
                            IDPRODUCT.getString(2), IDPRODUCT.getString(3), IDPRODUCT.getFloat(4));
                }
                arrp.add(p);
            }
            PurchaseOrder purchaseOrder = new PurchaseOrder(c, arrp, ID_ORDERS.getDate(3).toLocalDate());
            purchaseOrders.add(purchaseOrder);
        }
        return purchaseOrders;
    }

    @Override
    public Float CalcProductsTotalCost(Product[] products) throws Exception {
        Float sum = new Float(0);
        for (int i = 0; i < products.length; i++) {
            sum += products[i].getPrice();
        }
        return sum;
    }

    @Override
    protected void finalize() throws Throwable {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/computerShopDB", "Ayelet", "123");
        connection.close();
    }
}
