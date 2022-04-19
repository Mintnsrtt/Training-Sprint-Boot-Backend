package com.iamnsrt.training.backend.service;

import com.iamnsrt.training.backend.entity.Address;
import com.iamnsrt.training.backend.entity.Social;
import com.iamnsrt.training.backend.entity.User;
import com.iamnsrt.training.backend.repository.AddressRepository;
import com.iamnsrt.training.backend.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {

        this.repository = repository;
    }

    public List<Address> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Address create(User user, String line1, String line2, String zipCode) {
        Address entity = new Address();

        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipCode(zipCode);

        return repository.save(entity);
    }
}
