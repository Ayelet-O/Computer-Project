/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagerguiview;

import controller.Backend_DAO_DB;
import controller.Backend_DAO_List;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import model.Customer;

/**
 *
 * @author user
 */
public class AddNewCustomerForm extends JFrame {

    private JButton jButtonOK;
    private JLabel jLabelID;
    private JLabel jLabelName;
    private JLabel jLabelAddress;
    private JTextField jTextFieldID;
    private JTextField jTextFieldName;
    private JTextField jTextFieldAddress;

    public AddNewCustomerForm() {
        jButtonOK = new JButton("OK");
        jLabelID = new JLabel("ID:");
        jLabelName = new JLabel("Name:");
        jLabelAddress = new JLabel("Address:");
        jTextFieldID = new JTextField();
        jTextFieldName = new JTextField();
        jTextFieldAddress = new JTextField();
        this.add(jButtonOK);
        this.add(jLabelID);
        this.add(jLabelName);
        this.add(jLabelAddress);
        this.add(jTextFieldID);
        this.add(jTextFieldName);
        this.add(jTextFieldAddress);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contPage = getContentPane();
        SpringLayout layout = new SpringLayout();
        contPage.setLayout(layout);
        jTextFieldID.setPreferredSize(new Dimension(100, 30));
        jTextFieldName.setPreferredSize(new Dimension(100, 30));
        jTextFieldAddress.setPreferredSize(new Dimension(100, 30));
        jButtonOK.setPreferredSize(new Dimension(370, 50));
        jLabelID.setFont(jLabelID.getFont().deriveFont(16.0f));
        jLabelName.setFont(jLabelName.getFont().deriveFont(16.0f));
        jLabelAddress.setFont(jLabelAddress.getFont().deriveFont(16.0f));
        jButtonOK.setFont(jButtonOK.getFont().deriveFont(20.0f));
        this.setTitle("ADD A NEW CUSTOMER");

        layout.putConstraint(SpringLayout.EAST, jLabelID, 350, SpringLayout.WEST, contPage);
        layout.putConstraint(SpringLayout.NORTH, jLabelID, 60, SpringLayout.NORTH, contPage);
        layout.putConstraint(SpringLayout.EAST, jLabelName, 350, SpringLayout.WEST, contPage);
        layout.putConstraint(SpringLayout.NORTH, jLabelName, 90, SpringLayout.NORTH, contPage);
        layout.putConstraint(SpringLayout.EAST, jLabelAddress, 350, SpringLayout.WEST, contPage);
        layout.putConstraint(SpringLayout.NORTH, jLabelAddress, 120, SpringLayout.NORTH, contPage);
        layout.putConstraint(SpringLayout.WEST, jButtonOK, 12, SpringLayout.WEST, contPage);
        layout.putConstraint(SpringLayout.NORTH, jButtonOK, 170, SpringLayout.NORTH, contPage);

        layout.putConstraint(SpringLayout.EAST, jTextFieldID, 250, SpringLayout.WEST, contPage);
        layout.putConstraint(SpringLayout.NORTH, jTextFieldID, 60, SpringLayout.NORTH, contPage);
        layout.putConstraint(SpringLayout.EAST, jTextFieldName, 250, SpringLayout.WEST, contPage);
        layout.putConstraint(SpringLayout.NORTH, jTextFieldName, 90, SpringLayout.NORTH, contPage);
        layout.putConstraint(SpringLayout.EAST, jTextFieldAddress, 250, SpringLayout.WEST, contPage);
        layout.putConstraint(SpringLayout.NORTH, jTextFieldAddress, 120, SpringLayout.NORTH, contPage);
        this.setSize(420, 280);
        jTextFieldID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        jButtonOK.addActionListener((ActionEvent e) -> {
            try {
                if (jTextFieldName.getText().isEmpty()) {
                    throw new Exception("please enter your name");
                }
                if (jTextFieldID.getText().isEmpty()) {
                    throw new Exception("please enter your id");
                }
                if (jTextFieldID.getText().length() < 9 || jTextFieldID.getText().length() > 9) {
                    throw new Exception("the id you enterd is invalid. try again");
                }
                JOptionPane.showMessageDialog(AddNewCustomerForm.this, "the customer addad succsesfully");
                Customer c = new Customer();
                c.setName(jTextFieldName.getText());
                c.setId(Long.parseLong(jTextFieldID.getText()));
                c.setAddrsess(jTextFieldAddress.getText());
                Backend_DAO_DB.get_bdl_singleton().AddCustomer(c);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AddNewCustomerForm.this, ex);
            }
        });
    }

}
