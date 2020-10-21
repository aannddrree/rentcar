package br.com.rentcar.controller;

import br.com.rentcar.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import br.com.rentcar.service.ClientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import br.com.rentcar.model.Client;

@Controller
public class ClientController {

    @Autowired
    private ClientService clienteService;

    @RequestMapping(value = Constants.API_CLIENT, method = RequestMethod.POST)
    public Client save(@RequestBody Client client){
        return clienteService.save(client);
    }

    @RequestMapping(value = Constants.API_CLIENT, method = RequestMethod.GET)
    public List<Client> findAll(){
        return clienteService.findAll();
    }

    @RequestMapping(value = Constants.API_CLIENT, method = RequestMethod.PUT)
    public Client update(@RequestBody Client client){
        return this.save(client);
    }

    @RequestMapping(value = Constants.API_CLIENT, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String cpf){
        clienteService.delete(cpf);
    }

    @RequestMapping(value = Constants.API_CLIENT + "/{id}", method = RequestMethod.GET)
    public Optional<Client> findById(@PathVariable("id") String cpf){
        return clienteService.findByid(cpf);
    }
}
