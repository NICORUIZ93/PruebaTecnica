package com.pruebatecnica.backend.controllers;

import com.pruebatecnica.backend.models.Breed;
import com.pruebatecnica.backend.services.CatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pruebatecnica.backend.constans.CoreRouter.ThecatAPI.*;

@Slf4j
@RequestMapping(ROOT)
@RestController
public class CatApiController {

    private final CatService catService;

    public CatApiController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping(BREEDS)
    public Breed[] getBreeds() {
        log.info("CatApiController::getBreeds");
        return catService.getBreeds();
    }

    @GetMapping(BREED_BY_ID)
    public Breed getBreedById(@PathVariable String breedId) {
        log.info("CatApiController::getBreedById - breedId: [{}] ", breedId);
        return catService.getBreedById(breedId);
    }

    @GetMapping(SEARCH_BREEDS)
    public List<Breed> searchBreeds(@PathVariable String searchBreed) {
        log.info("CatApiController::searchBreeds - searchBreed: [{}]", searchBreed);
        return catService.searchBreeds(searchBreed);
    }
}
