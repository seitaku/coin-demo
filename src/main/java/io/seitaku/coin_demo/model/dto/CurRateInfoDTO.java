package io.seitaku.coin_demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurRateInfoDTO {
    @JsonProperty("code")
    private String code;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("rate")
    private String rate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("rate_float")
    private double rateFloat;
}
