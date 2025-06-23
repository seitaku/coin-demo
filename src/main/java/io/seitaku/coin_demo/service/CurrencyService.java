package io.seitaku.coin_demo.service;

import org.springframework.stereotype.Service;

import io.seitaku.coin_demo.model.dto.CurForamtRateInfoDTO;
import io.seitaku.coin_demo.model.entity.CurrencyInfoEntity;

@Service
public interface CurrencyService {

    public CurrencyInfoEntity addCurInfo(CurrencyInfoEntity curInfo);

    public CurrencyInfoEntity updateCurInfo(String code, CurrencyInfoEntity newSymbol);

    public String fetchDataCurrencyInfo();

    public CurForamtRateInfoDTO curFormat(String curInfoStr);

}