package com.ellen.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
public class Stuff implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "Stuff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Max(value = 99, message = "illegal quantity: >99")
    @Min(value = 0, message = "illegal quantity: <0 ")
    private Integer quantity;

    public Stuff(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
