package org.acme;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.sql.rowset.serial.SerialBlob;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@Entity
public class Film implements Serializable{
    @Id
     private int id;
     private int price;
     private int sit;
     private String name;
     private String time;

     public void setPrice(int price){
         this.price = price;
     }
     public void setSit(int sit){
        this.sit = sit;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }


    public int getPrice(){
        return price;
    }
    public int getSit(){
        return sit;
    }
    public String getTime(){
        return time;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
}
