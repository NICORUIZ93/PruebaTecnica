package com.pruebatecnica.backend.services.impl;

import com.pruebatecnica.backend.config.CatApiException;
import com.pruebatecnica.backend.models.Breed;
import com.pruebatecnica.backend.services.CatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

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
        try {
            return restTemplate.getForObject(url + "breeds?limit=10&page=0", Breed[].class);
        } catch (HttpClientErrorException e) {
            throw new CatApiException("Error al obtener las razas de gatos", e);
        } catch (UnknownHttpStatusCodeException e) {
            throw new CatApiException("Error desconocido al obtener las razas de gatos", e);
        }
    }

    public Breed getBreedById(String breedId) {
        log.info("CatService::getBreedById - breedId: [{}] ", breedId);
        try {
            return restTemplate.getForEntity(url + "breeds/" + breedId, Breed.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CatApiException("No se encontró la raza de gato con el ID: " + breedId);
            } else {
                throw new CatApiException("Error al obtener la raza de gato", e);
            }
        } catch (UnknownHttpStatusCodeException e) {
            throw new CatApiException("Error desconocido al obtener la raza de gato", e);
        }
    }

    public Breed[] searchBreeds(String query, int attachImage) {
        log.info("CatService::searchBreeds - query: [{}]", query);
        try {
            Breed[] breeds = restTemplate.getForObject(url + "breeds/search?q=" + query + "&attach_image=" + attachImage, Breed[].class);

            if (breeds == null || breeds.length == 0) {
                throw new CatApiException("No se encontraron razas de gatos para la consulta: " + query);
            }

            return breeds;
        } catch (HttpClientErrorException e) {
            throw new CatApiException("Error al buscar razas de gatos", e);
        } catch (UnknownHttpStatusCodeException e) {
            throw new CatApiException("Error desconocido al buscar razas de gatos", e);
        }
    }
}
