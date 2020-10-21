package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private Client client;
    private Vehicle vehicle;
    private String rentDate;
    private double valueRent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public double getValueRent() {
        return valueRent;
    }

    public void setValueRent(double valueRent) {
        this.valueRent = valueRent;
    }
}
