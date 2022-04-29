package org.acme;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Student extends PanacheEntity{
    public String name;
    public int sorce;
    
    public Student(){}

    public  Student(String name , int sorce){
        this.name = name;
        this.sorce = sorce;
    }
}
