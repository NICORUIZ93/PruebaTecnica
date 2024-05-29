package com.pruebatecnica.backend.services;

import com.pruebatecnica.backend.models.Breed;

public interface CatService {
    Breed[] getBreeds();

    Breed getBreedById(String breedId);

    Breed[] searchBreeds(String searchBreed, int attachImage);
}
