package br.com.rentcar.controller;

import br.com.rentcar.constant.Constants;
import br.com.rentcar.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.rentcar.service.RentService;
import java.util.List;

@Controller
public class RentController {

    @Autowired
    private RentService rentService;

    @RequestMapping(value = Constants.API_RENT, method = RequestMethod.POST)
    @ResponseBody
    public Rent save(@RequestBody Rent rent){
        return rentService.save(rent);
    }

    @RequestMapping(value = Constants.API_RENT, method = RequestMethod.GET)
    @ResponseBody
    public List<Rent> findAll(){
        return rentService.findAll();
    }

    @RequestMapping(value = Constants.API_RENT, method = RequestMethod.PUT)
    @ResponseBody
    public Rent update(@RequestBody Rent rent){
        return this.save(rent);
    }

    @RequestMapping(value = Constants.API_RENT + "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") int id){
        rentService.delete(id);
    }

    @RequestMapping(value = Constants.API_RENT + "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Rent findById(@PathVariable("id") int id){
        return rentService.findByid(id);
    }
}
