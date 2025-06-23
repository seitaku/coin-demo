package io.seitaku.coin_demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurExchangeRateTimeDTO {
    @JsonProperty("updated")
    private String updated;

    @JsonProperty("updatedISO")
    private String updatedISO;

    @JsonProperty("updateduk")
    private String updatedUk;
}
