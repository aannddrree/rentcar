package controller;

import constant.Constants;
import model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.ClientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
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
    public Client findById(@PathVariable("id") String cpf){
        return clienteService.findByid(cpf);
    }
}
