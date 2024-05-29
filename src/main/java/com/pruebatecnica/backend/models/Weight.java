package com.pruebatecnica.backend.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Weight {
    private String imperial;
    private String metric;
}
