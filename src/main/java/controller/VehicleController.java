package controller;

import constant.Constants;
import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.VehicleService;
import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.POST)
    public Vehicle save(@RequestBody Vehicle client){
        return vehicleService.save(client);
    }

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.GET)
    public List<Vehicle> findAll(){
        return vehicleService.findAll();
    }

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.PUT)
    public Vehicle update(@RequestBody Vehicle client){
        return this.save(client);
    }

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        vehicleService.delete(id);
    }

    @RequestMapping(value = Constants.API_VEHICLE + "/{id}", method = RequestMethod.GET)
    public Vehicle findById(@PathVariable("id") int id){
        return vehicleService.findByid(id);
    }
}
