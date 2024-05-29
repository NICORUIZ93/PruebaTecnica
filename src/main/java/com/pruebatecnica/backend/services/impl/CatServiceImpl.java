package com.pruebatecnica.backend.services.impl;

import com.pruebatecnica.backend.models.Breed;
import com.pruebatecnica.backend.services.CatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CatServiceImpl implements CatService {

    @Value("${thecatapi.apiUrl}")
    private String url;

    private final RestTemplate restTemplate;

    public CatServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Breed[] getBreeds() {
        log.info("CatService::getBreeds");
        return restTemplate.getForObject(url + "breeds?limit=10&page=0", Breed[].class);
    }

    public Breed getBreedById(String breedId) {
        log.info("CatService::getBreedById - breedId: [{}] ", breedId);
        return restTemplate.getForEntity(url + "breeds/" + breedId, Breed.class).getBody();
    }

    public List<Breed> searchBreeds(String query) {
        log.info("CatService::searchBreeds - query: [{}]", query);
        return new ArrayList<>();
    }
}
