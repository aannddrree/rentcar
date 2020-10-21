package controller;

import constant.Constants;
import model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.RentService;
import java.util.List;

@RestController
public class RentController {

    @Autowired
    private RentService rentService;

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.POST)
    public Rent save(@RequestBody Rent rent){
        return rentService.save(rent);
    }

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.GET)
    public List<Rent> findAll(){
        return rentService.findAll();
    }

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.PUT)
    public Rent update(@RequestBody Rent rent){
        return this.save(rent);
    }

    @RequestMapping(value = Constants.API_VEHICLE, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        rentService.delete(id);
    }

    @RequestMapping(value = Constants.API_VEHICLE + "/{id}", method = RequestMethod.GET)
    public Rent findById(@PathVariable("id") int id){
        return rentService.findByid(id);
    }
}
