package service;

import model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RentRepository;

import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

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
