package br.com.rentcar.service;

import br.com.rentcar.jdbc.VehicleDB;
import br.com.rentcar.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.rentcar.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    //don't use spring data !
    //@Autowired
    //private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleDB vehicleRepository;

    public Vehicle save(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }
    public List<Vehicle> findAll(){
        return (List<Vehicle>) vehicleRepository.findAll();
    }
    public Vehicle findByid(int id){
        return vehicleRepository.findOne(id);
    }
    public void delete(int id){
        vehicleRepository.delete(id);
    }
}
