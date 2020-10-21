package service;

import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.VehicleRepository;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

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
