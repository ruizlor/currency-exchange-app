package com.lorena.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ConversionResponse implements Serializable {
    @JsonProperty("query")
    private Query query;
    @JsonProperty("result")
    private String convertedAmount;
}
