/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author student
 */
public class SoftwareProduct extends Product implements Serializable{
    private int numberOfUsers;

    public SoftwareProduct(int numberOfHours, long id, String name, String description, float pricePerUnit) {
        super(id, name, description, pricePerUnit);
        this.numberOfUsers = numberOfHours;
    }

    public SoftwareProduct() {
       
    }

    public int getNumberOfHours() {
        return numberOfUsers;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfUsers  = numberOfHours;
    }
    
    @Override
    public float getPrice() {
          if(this.numberOfUsers>200){
                return super.getPricePerUnit()+100;
          }
          return super.getPricePerUnit();
    }
    
}
