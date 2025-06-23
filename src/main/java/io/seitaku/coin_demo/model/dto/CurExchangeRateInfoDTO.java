package io.seitaku.coin_demo.model.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurExchangeRateInfoDTO {
    @JsonProperty("time")
    private CurExchangeRateTimeDTO time;

    @JsonProperty("disclaimer")
    private String disclaimer;

    @JsonProperty("chartName")
    private String chartName;

    @JsonProperty("bpi")
    private Map<String, CurRateInfoDTO> bpi;

}
