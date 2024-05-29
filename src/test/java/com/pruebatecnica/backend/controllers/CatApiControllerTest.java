package com.pruebatecnica.backend.controllers;

import com.pruebatecnica.backend.models.Breed;
import com.pruebatecnica.backend.models.Weight;
import com.pruebatecnica.backend.services.CatService;
import com.pruebatecnica.backend.services.impl.CatServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CatApiController.class, CatService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CatApiControllerTest {
    @Autowired
    private CatApiController catApiController;

    @MockBean
    private CatService catService;

    /**
     * Method under test: {@link CatApiController#getBreeds()}
     */
    @Test
    void testGetBreeds() throws RestClientException {

        RestTemplate restTemplate = mock(RestTemplate.class);
        Breed.BreedBuilder vocalisationResult = Breed.builder()
                .adaptability(1)
                .affectionLevel(1)
                .altNames("Alt Names")
                .cfaUrl("https://example.org/example")
                .childFriendly(3)
                .countryCode("GB")
                .countryCodes("GB")
                .description("The characteristics of someone or something")
                .dogFriendly(3)
                .energyLevel(1)
                .experimental(1)
                .grooming(1)
                .hairless(1)
                .healthIssues(1)
                .hypoallergenic(1)
                .id("42")
                .indoor(1)
                .intelligence(1)
                .lap(1)
                .lifeSpan("Life Span")
                .name("Name")
                .natural(1)
                .origin("Origin")
                .rare(1)
                .referenceImageId("42")
                .rex(1)
                .sheddingLevel(1)
                .shortLegs(1)
                .socialNeeds(1)
                .strangerFriendly(3)
                .suppressedTail(1)
                .temperament("Temperament")
                .vcahospitalsUrl("https://example.org/example")
                .vetstreetUrl("https://example.org/example")
                .vocalisation(1);
        Weight weight = Weight.builder().imperial("Imperial").metric("Metric").build();
        Breed buildResult = vocalisationResult.weight(weight).wikipediaUrl("https://example.org/example").build();
        when(restTemplate.getForObject(Mockito.<String>any(), Mockito.<Class<Breed[]>>any(), isA(Object[].class)))
                .thenReturn(new Breed[]{buildResult});

        Breed[] actualBreeds = (new CatApiController(new CatServiceImpl(restTemplate))).getBreeds();

        verify(restTemplate).getForObject(eq("nullbreeds?limit=10&page=0"), isA(Class.class), isA(Object[].class));
        assertEquals(1, actualBreeds.length);
    }

    @Test
    void testGetBreedById() throws Exception {
        Breed.BreedBuilder vocalisationResult = Breed.builder()
                .adaptability(1)
                .affectionLevel(1)
                .altNames("Alt Names")
                .cfaUrl("https://example.org/example")
                .childFriendly(3)
                .countryCode("GB")
                .countryCodes("GB")
                .description("The characteristics of someone or something")
                .dogFriendly(3)
                .energyLevel(1)
                .experimental(1)
                .grooming(1)
                .hairless(1)
                .healthIssues(1)
                .hypoallergenic(1)
                .id("42")
                .indoor(1)
                .intelligence(1)
                .lap(1)
                .lifeSpan("Life Span")
                .name("Name")
                .natural(1)
                .origin("Origin")
                .rare(1)
                .referenceImageId("42")
                .rex(1)
                .sheddingLevel(1)
                .shortLegs(1)
                .socialNeeds(1)
                .strangerFriendly(3)
                .suppressedTail(1)
                .temperament("Temperament")
                .vcahospitalsUrl("https://example.org/example")
                .vetstreetUrl("https://example.org/example")
                .vocalisation(1);
        Weight weight = Weight.builder().imperial("Imperial").metric("Metric").build();
        Breed buildResult = vocalisationResult.weight(weight).wikipediaUrl("https://example.org/example").build();
        when(catService.getBreedById(Mockito.<String>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cats/consult/breeds/{breedId}", "42");

        MockMvcBuilders.standaloneSetup(catApiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"weight\":{\"imperial\":\"Imperial\",\"metric\":\"Metric\"},\"id\":\"42\",\"name\":\"Name\",\"temperament\":\"Temperament"
                                        + "\",\"origin\":\"Origin\",\"description\":\"The characteristics of someone or something\",\"indoor\":1,\"lap\":1,"
                                        + "\"adaptability\":1,\"grooming\":1,\"intelligence\":1,\"vocalisation\":1,\"experimental\":1,\"hairless\":1,\"natural"
                                        + "\":1,\"rare\":1,\"rex\":1,\"hypoallergenic\":1,\"cfa_url\":\"https://example.org/example\",\"vetstreet_url\":\"https"
                                        + "://example.org/example\",\"vcahospitals_url\":\"https://example.org/example\",\"country_codes\":\"GB\",\"country"
                                        + "_code\":\"GB\",\"life_span\":\"Life Span\",\"alt_names\":\"Alt Names\",\"affection_level\":1,\"child_friendly\":3,"
                                        + "\"dog_friendly\":3,\"energy_level\":1,\"health_issues\":1,\"shedding_level\":1,\"social_needs\":1,\"stranger"
                                        + "_friendly\":3,\"suppressed_tail\":1,\"short_legs\":1,\"wikipedia_url\":\"https://example.org/example\",\"reference"
                                        + "_image_id\":\"42\"}"));
    }

    @Test
    void testSearchBreeds() throws RestClientException {
        RestTemplate restTemplate = mock(RestTemplate.class);
        Breed.BreedBuilder vocalisationResult = Breed.builder()
                .adaptability(1)
                .affectionLevel(1)
                .altNames("Alt Names")
                .cfaUrl("https://example.org/example")
                .childFriendly(3)
                .countryCode("GB")
                .countryCodes("GB")
                .description("The characteristics of someone or something")
                .dogFriendly(3)
                .energyLevel(1)
                .experimental(1)
                .grooming(1)
                .hairless(1)
                .healthIssues(1)
                .hypoallergenic(1)
                .id("42")
                .indoor(1)
                .intelligence(1)
                .lap(1)
                .lifeSpan("Life Span")
                .name("Name")
                .natural(1)
                .origin("Origin")
                .rare(1)
                .referenceImageId("42")
                .rex(1)
                .sheddingLevel(1)
                .shortLegs(1)
                .socialNeeds(1)
                .strangerFriendly(3)
                .suppressedTail(1)
                .temperament("Temperament")
                .vcahospitalsUrl("https://example.org/example")
                .vetstreetUrl("https://example.org/example")
                .vocalisation(1);
        Weight weight = Weight.builder().imperial("Imperial").metric("Metric").build();
        Breed buildResult = vocalisationResult.weight(weight).wikipediaUrl("https://example.org/example").build();
        when(restTemplate.getForObject(Mockito.<String>any(), Mockito.<Class<Breed[]>>any(), isA(Object[].class)))
                .thenReturn(new Breed[]{buildResult});

        Breed[] actualSearchBreedsResult = (new CatApiController(new CatServiceImpl(restTemplate))).searchBreeds("foo", 1);

        verify(restTemplate).getForObject(eq("nullbreeds/search?q=foo&attach_image=1"), isA(Class.class),
                isA(Object[].class));
        assertEquals(1, actualSearchBreedsResult.length);
    }

}
