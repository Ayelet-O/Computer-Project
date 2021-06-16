/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class ProgramFactory implements Serializable{
      public Product getShape(String productType){
      if(productType == null){
         return null;
      }		
      if(productType.equalsIgnoreCase("SOFTWARE")){
         return new SoftwareProduct();
         
      } else if(productType.equalsIgnoreCase("HARDWARE")){
         return new HardwareProduct();
      }
      
      return null;
   }
}
