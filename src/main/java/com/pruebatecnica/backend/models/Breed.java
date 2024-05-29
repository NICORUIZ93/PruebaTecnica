package com.pruebatecnica.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Breed {
    private Weight weight;
    private String id;
    private String name;

    @JsonProperty("cfa_url")
    private String cfaUrl;

    @JsonProperty("vetstreet_url")
    private String vetstreetUrl;

    @JsonProperty("vcahospitals_url")
    private String vcahospitalsUrl;

    private String temperament;
    private String origin;

    @JsonProperty("country_codes")
    private String countryCodes;

    @JsonProperty("country_code")
    private String countryCode;

    private String description;

    @JsonProperty("life_span")
    private String lifeSpan;

    private int indoor;
    private int lap;

    @JsonProperty("alt_names")
    private String altNames;

    private int adaptability;

    @JsonProperty("affection_level")
    private int affectionLevel;

    @JsonProperty("child_friendly")
    private int childFriendly;

    @JsonProperty("dog_friendly")
    private int dogFriendly;

    @JsonProperty("energy_level")
    private int energyLevel;

    private int grooming;

    @JsonProperty("health_issues")
    private int healthIssues;

    private int intelligence;

    @JsonProperty("shedding_level")
    private int sheddingLevel;

    @JsonProperty("social_needs")
    private int socialNeeds;

    @JsonProperty("stranger_friendly")
    private int strangerFriendly;

    private int vocalisation;

    private int experimental;

    private int hairless;

    private int natural;

    private int rare;

    private int rex;

    @JsonProperty("suppressed_tail")
    private int suppressedTail;

    @JsonProperty("short_legs")
    private int shortLegs;

    @JsonProperty("wikipedia_url")
    private String wikipediaUrl;

    private int hypoallergenic;

    @JsonProperty("reference_image_id")
    private String referenceImageId;
}

