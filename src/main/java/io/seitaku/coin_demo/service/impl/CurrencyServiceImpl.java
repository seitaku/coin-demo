package io.seitaku.coin_demo.service.impl;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.seitaku.coin_demo.Context;
import io.seitaku.coin_demo.model.dto.CurExchangeRateInfoDTO;
import io.seitaku.coin_demo.model.dto.CurForamtRateInfoDTO;
import io.seitaku.coin_demo.model.dto.CurRateInfoDTO;
import io.seitaku.coin_demo.model.entity.CurrencyInfoEntity;
import io.seitaku.coin_demo.repository.CurrencyInfoRepository;
import io.seitaku.coin_demo.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyInfoRepository currencyInfoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public CurrencyInfoEntity addCurInfo(CurrencyInfoEntity curInfo) {
        if (!StringUtils.hasLength(curInfo.getCurCode())) {
            throw new IllegalArgumentException("add fail lost currency code");
        }

        String curCode = curInfo.getCurCode();
        CurrencyInfoEntity oriCurData = currencyInfoRepository.findById(curCode).orElse(null);
        if (oriCurData != null) {
            throw new IllegalArgumentException("currency info is exist");
        }

        return currencyInfoRepository.save(curInfo);
    }

    @Transactional
    public CurrencyInfoEntity updateCurInfo(String curCode, CurrencyInfoEntity newCurrencyInfo) {
        CurrencyInfoEntity oriCurData = currencyInfoRepository.findById(curCode)
                .orElseThrow(() -> new IllegalArgumentException("currency not found with code: " + curCode));

        if (oriCurData.equals(newCurrencyInfo)) {
            throw new IllegalArgumentException("currency information is identical - no update required");
        }

        BeanUtils.copyProperties(newCurrencyInfo, oriCurData, "curCode");
        return currencyInfoRepository.save(oriCurData);
    }

    public String fetchDataCurrencyInfo() {
        ResponseEntity<String> response = restTemplate.getForEntity(Context.COINDESK_INFO_URL, String.class);
        return response.getBody();
    }

    public CurForamtRateInfoDTO curFormat(String curInfoJson) {
        CurExchangeRateInfoDTO dto = new CurExchangeRateInfoDTO();
        try {
            dto = new ObjectMapper().readValue(curInfoJson, CurExchangeRateInfoDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("JSON format fail: " + e.getMessage(), e);
        }

        String updatedLocal = getLocalTime(dto);

        Map<String, CurRateInfoDTO> bpi = dto.getBpi();
        List<String> curCodes = new ArrayList<>(bpi.keySet());
        List<CurrencyInfoEntity> curCodeInfos = currencyInfoRepository.findByCurCodeIn(curCodes);

        CurForamtRateInfoDTO result = new CurForamtRateInfoDTO();
        Map<String, String> bpiInfo = new HashMap<>();

        bpi.forEach((currencyCode, rateInfo) -> {
            CurrencyInfoEntity curInfo = curInfo = curCodeInfos.stream()
                    .filter(info -> info.getCurCode().equals(currencyCode))
                    .findFirst()
                    .orElse(null);

            bpiInfo.put(currencyCode, convertToInfo(rateInfo, curInfo));
        });

        result.setUpdatedLocal(updatedLocal);
        result.setBpiInfo(bpiInfo);

        log.info("result : {}", result);

        return result;
    }

    private String getLocalTime(CurExchangeRateInfoDTO dto) {
        String timeStr = dto.getTime().getUpdated();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss z", Locale.ENGLISH);
        ZonedDateTime utcTime = ZonedDateTime.parse(timeStr, inputFormatter);
        ZonedDateTime taiwanTime = utcTime.withZoneSameInstant(ZoneId.of(Context.ZoneId_Taipei));
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日 a h:mm:ss（台灣時間）",
                Locale.TRADITIONAL_CHINESE);
        return taiwanTime.format(outputFormatter);
    }

    private static String convertToInfo(CurRateInfoDTO rateInfo, CurrencyInfoEntity curInfo) {
        return String.format(
                "%s (%s) 匯率: %s (符號: %s)",
                curInfo.getNameZh() != null ? curInfo.getNameZh() : "",
                rateInfo.getCode(),
                rateInfo.getRate(),
                rateInfo.getSymbol() != null ? StringEscapeUtils.unescapeHtml4(rateInfo.getSymbol()) : "");
    }

}