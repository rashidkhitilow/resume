/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

/**
 *
 * @author rashid.khitilov
 */
public class Country {
    private int id;
    private String name;
    private String nationality;

    public Country() {
    }

    public Country(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return name;
    }

    public void setCountryName(String name) {
        this.name = name;
    }

    public String getNationalityName() {
        return nationality;
    }

    public void setNationalityName(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name=" + name + ", nationality=" + nationality + '}';
    }
    
    
}
