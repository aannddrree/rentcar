package br.com.rentcar.service;

import br.com.rentcar.jdbc.ClientDB;
import br.com.rentcar.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.rentcar.repository.ClientRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    //don't use spring data !
    //@Autowired
    //private ClientRepository clientRepository;

    @Autowired
    private ClientDB clientRepository;

    public Client save(Client client){
        return clientRepository.save(client);
    }
    public List<Client> findAll(){
        return (List<Client>) clientRepository.findAll();
    }
    public Client findByid(String id){
        return clientRepository.findOne(id);
    }
    public void delete(String id){
        clientRepository.delete(id);
    }
}
