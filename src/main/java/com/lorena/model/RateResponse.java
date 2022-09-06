package com.lorena.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RateResponse implements Serializable {
    @JsonProperty("base")
    private String base;
    @JsonProperty("rates")
    private Map<String, String> rates;
}
