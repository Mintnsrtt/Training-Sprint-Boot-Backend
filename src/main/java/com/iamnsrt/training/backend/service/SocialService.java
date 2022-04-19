package com.iamnsrt.training.backend.service;

import com.iamnsrt.training.backend.entity.Social;
import com.iamnsrt.training.backend.entity.User;
import com.iamnsrt.training.backend.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SocialService {

    private final SocialRepository repository;

    public SocialService(SocialRepository repository) {
        this.repository = repository;
    }

    public Optional<Social> findByUser(User user) {

        return repository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok) {
        Social entity = new Social();

        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);

        return repository.save(entity);
    }
}
