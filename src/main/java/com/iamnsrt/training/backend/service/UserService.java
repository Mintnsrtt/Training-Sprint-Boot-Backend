package com.iamnsrt.training.backend.service;

import com.iamnsrt.training.backend.entity.User;
import com.iamnsrt.training.backend.exception.BaseException;
import com.iamnsrt.training.backend.exception.UserException;
import com.iamnsrt.training.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }
    public Optional<User> findByEmail(String email) {

        return repository.findByEmail(email);
    }

    public User updateName(String id, String name) throws BaseException{
        Optional<User> opt = repository.findById(id);

        if (opt.isEmpty()) {
            throw UserException.notFound();
        }

        User user = opt.get();
        user.setName(name);

        return repository.save(user);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public boolean matchPassword(String rawPassword, String encodePassword) {
        return passwordEncoder.matches(rawPassword, encodePassword);
    }
    public User create(String email, String password, String name) throws BaseException {

        //validate
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }

        if (Objects.isNull(password)) {
            throw UserException.createPasswordNull();
        }

        if (Objects.isNull(name)){
            throw UserException.createNameNull();
        }

        //verify
        if (repository.existsById(email)) {
            throw UserException.createEmailDuplicated();
        }

        //save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);

        return repository.save(entity);
    }
}
