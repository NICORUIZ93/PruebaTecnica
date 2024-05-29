package com.pruebatecnica.backend.services.impl;

import com.pruebatecnica.backend.config.CatApiException;
import com.pruebatecnica.backend.models.Breed;
import com.pruebatecnica.backend.models.Weight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CatServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CatServiceImplTest {

    @Autowired
    private CatServiceImpl catServiceImpl;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testGetBreeds() {
        // Arrange
        Breed breed = createSampleBreed();
        when(restTemplate.getForObject(Mockito.any(), Mockito.<Class<Breed[]>>any(), isA(Object[].class)))
                .thenReturn(new Breed[]{breed});

        Breed[] actualBreeds = catServiceImpl.getBreeds();

        verify(restTemplate).getForObject(eq("${thecatapi.apiUrl}breeds?limit=10&page=0"), isA(Class.class), isA(Object[].class));
        assertEquals(1, actualBreeds.length);
    }

    @Test
    void testGetBreeds_CatApiException() {
        when(restTemplate.getForObject(Mockito.any(), Mockito.<Class<Breed[]>>any(), isA(Object[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(CatApiException.class, () -> catServiceImpl.getBreeds());
        verify(restTemplate).getForObject(eq("${thecatapi.apiUrl}breeds?limit=10&page=0"), isA(Class.class), isA(Object[].class));
    }

    @Test
    void testGetBreedById() {
        Breed breed = createSampleBreed();
        ResponseEntity<Breed> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(breed);
        when(restTemplate.getForEntity(Mockito.any(), Mockito.<Class<Breed>>any(), isA(Object[].class)))
                .thenReturn(responseEntity);

        Breed actualBreed = catServiceImpl.getBreedById("42");

        verify(restTemplate).getForEntity(eq("${thecatapi.apiUrl}breeds/42"), isA(Class.class), isA(Object[].class));
        assertEquals(breed, actualBreed);
    }

    @Test
    void testGetBreedById_BreedNotFoundException() {
        when(restTemplate.getForEntity(Mockito.any(), Mockito.<Class<Breed>>any(), isA(Object[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(CatApiException.class, () -> catServiceImpl.getBreedById("42"));
        verify(restTemplate).getForEntity(eq("${thecatapi.apiUrl}breeds/42"), isA(Class.class), isA(Object[].class));
    }

    @Test
    void testSearchBreeds() {
        Breed breed = createSampleBreed();
        when(restTemplate.getForObject(Mockito.any(), Mockito.<Class<Breed[]>>any(), isA(Object[].class)))
                .thenReturn(new Breed[]{breed});

        Breed[] actualBreeds = catServiceImpl.searchBreeds("Query", 1);

        verify(restTemplate).getForObject(eq("${thecatapi.apiUrl}breeds/search?q=Query&attach_image=1"), isA(Class.class), isA(Object[].class));
        assertEquals(1, actualBreeds.length);
    }

    @Test
    void testSearchBreeds_NullResponse() {
        when(restTemplate.getForObject(Mockito.any(), Mockito.<Class<Breed[]>>any(), isA(Object[].class)))
                .thenReturn(null);

        assertThrows(CatApiException.class, () -> catServiceImpl.searchBreeds("Query", 1));
        verify(restTemplate).getForObject(eq("${thecatapi.apiUrl}breeds/search?q=Query&attach_image=1"), isA(Class.class), isA(Object[].class));
    }

    @Test
    void testSearchBreeds_EmptyResponse() {
        when(restTemplate.getForObject(Mockito.any(), Mockito.<Class<Breed[]>>any(), isA(Object[].class)))
                .thenReturn(new Breed[]{});

        assertThrows(CatApiException.class, () -> catServiceImpl.searchBreeds("Query", 1));
        verify(restTemplate).getForObject(eq("${thecatapi.apiUrl}breeds/search?q=Query&attach_image=1"), isA(Class.class), isA(Object[].class));
    }

    private Breed createSampleBreed() {
        Breed.BreedBuilder builder = Breed.builder()
                .id("42")
                .name("Name")
                .weight(new Weight("Imperial", "Metric"))
                .cfaUrl("https://example.org/example")
                .vetstreetUrl("https://example.org/example")
                .vcahospitalsUrl("https://example.org/example")
                .temperament("Temperament")
                .origin("Origin")
                .countryCodes("GB")
                .countryCode("GB")
                .description("Description")
                .lifeSpan("Life Span")
                .indoor(1)
                .lap(1)
                .altNames("Alt Names")
                .adaptability(1)
                .affectionLevel(1)
                .childFriendly(3)
                .dogFriendly(3)
                .energyLevel(1)
                .grooming(1)
                .healthIssues(1)
                .intelligence(1)
                .sheddingLevel(1)
                .socialNeeds(1)
                .strangerFriendly(3)
                .vocalisation(1)
                .experimental(1)
                .hairless(1)
                .natural(1)
                .rare(1)
                .rex(1)
                .suppressedTail(1)
                .shortLegs(1)
                .wikipediaUrl("https://example.org/example")
                .hypoallergenic(1)
                .referenceImageId("42");
        return builder.build();
    }
}
