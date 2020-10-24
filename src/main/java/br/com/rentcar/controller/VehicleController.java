package br.com.rentcar.controller;

import br.com.rentcar.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.rentcar.service.VehicleService;
import java.util.List;

import br.com.rentcar.model.Vehicle;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.POST)
    @ResponseBody
    public Vehicle save(@RequestBody Vehicle client){
        return vehicleService.save(client);
    }

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.GET)
    @ResponseBody
    public List<Vehicle> findAll(){
        return vehicleService.findAll();
    }

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.PUT)
    @ResponseBody
    public Vehicle update(@RequestBody Vehicle client){
        return this.save(client);
    }

    @RequestMapping(value = Constants.API_VEHICLE + "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") int id){
        vehicleService.delete(id);
    }

    @RequestMapping(value = Constants.API_VEHICLE + "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Vehicle findById(@PathVariable("id") int id){
        return vehicleService.findByid(id);
    }
}
