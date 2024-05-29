package com.pruebatecnica.backend.services;

import com.pruebatecnica.backend.models.Breed;

import java.util.List;

public interface CatService {
    Breed[] getBreeds();

    Breed getBreedById(String breedId);

    List<Breed> searchBreeds(String searchBreed);
}
