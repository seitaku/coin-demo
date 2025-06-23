package io.seitaku.coin_demo.model.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurForamtRateInfoDTO {
    @JsonProperty("updatedLocal")
    private String updatedLocal;

    @JsonProperty("bpiInfo")
    private Map<String, String> bpiInfo;

}
