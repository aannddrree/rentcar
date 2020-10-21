package service;

import model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClientRepository;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

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
