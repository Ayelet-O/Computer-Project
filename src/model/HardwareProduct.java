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
enum TypeP{
HARDWARE, SOFTWARE 
}
public class HardwareProduct extends Product implements Serializable{
    private int warrantyPeriod;
    private TypeP kind;

    public HardwareProduct(int warrantyPeriod, long id, String name, String description, float pricePerUnit) {
        super(id, name, description, pricePerUnit);
        this.warrantyPeriod = warrantyPeriod;
        
    }

    public HardwareProduct() {
        
    }

    public TypeP getKind() {
        return kind;
    }

    public void setKind(TypeP kind) {
        this.kind = kind;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    @Override
    public float getPrice() {
        if(this.warrantyPeriod>20)
            return super.getPricePerUnit()+150;
        return super.getPricePerUnit();
    }
    
}
