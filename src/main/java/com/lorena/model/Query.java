package com.lorena.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
public class Query implements Serializable {

    @JsonProperty
    private String from;
    @JsonProperty
    private String to;
    @JsonProperty
    private String amount;

}
