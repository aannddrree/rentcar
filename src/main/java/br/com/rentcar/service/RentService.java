package br.com.rentcar.service;

import br.com.rentcar.jdbc.RentDB;
import br.com.rentcar.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.rentcar.repository.RentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    //don't use spring data !
    //@Autowired
    //private RentRepository rentRepository;

    @Autowired
    private RentDB rentRepository;

    public Rent save(Rent rent){
        return rentRepository.save(rent);
    }
    public List<Rent> findAll(){
        return (List<Rent>) rentRepository.findAll();
    }
    public Rent findByid(int id){
        return rentRepository.findOne(id);
    }
    public void delete(int id){
        rentRepository.delete(id);
    }
}
