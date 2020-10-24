package br.com.rentcar.model;

import br.com.rentcar.util.Fuel;
import br.com.rentcar.util.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Vehicle {

    public Vehicle(){}

    public Vehicle(int id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(length = 30)
    @NotBlank
    private String brand;
    @Column(length = 100)
    @NotBlank
    private String name;
    @Min(value = 2000, message = "Tem que ser maior que 2000")
    @Max(value = 2100, message = "Tem que ser menor que 2100")
    private int year;
    @Min(value = 2000, message = "Tem que ser maior que 2000")
    @Max(value = 2100, message = "Tem que ser menor que 2100")
    private int model;
    private Fuel fuel;
    private double dailyValue;
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public double getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(double dailyValue) {
        this.dailyValue = dailyValue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
