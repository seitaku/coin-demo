package io.seitaku.coin_demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.seitaku.coin_demo.model.dto.CurForamtRateInfoDTO;
import io.seitaku.coin_demo.model.entity.CurrencyInfoEntity;
import io.seitaku.coin_demo.repository.CurrencyInfoRepository;
import io.seitaku.coin_demo.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cur-info")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyInfoRepository currencyInfoRepository;

    @GetMapping("/thirdCurInfo")
    public String getThirdCurInfo() {
        return currencyService.fetchDataCurrencyInfo();
    }

    @GetMapping("/format")
    public ResponseEntity<?> getThirdCurInfoFormat() {

        try {
            String curInfo = currencyService.fetchDataCurrencyInfo();
            CurForamtRateInfoDTO curInfoFormat = currencyService.curFormat(curInfo);
            return ResponseEntity.ok(curInfoFormat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("error format fail");
        }

    }

    @PostMapping("/addCurInfo")
    public ResponseEntity<?> createCurInfo(@RequestBody CurrencyInfoEntity curInfo) {
        try {
            CurrencyInfoEntity savedCurrency = currencyService.addCurInfo(curInfo);
            return ResponseEntity.ok(savedCurrency);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/table")
    public List<CurrencyInfoEntity> getAllCurInfo() {
        List<CurrencyInfoEntity> curInfoList = currencyInfoRepository.findAll();
        return curInfoList;
    }

    @GetMapping("/{curCode}")
    public ResponseEntity<?> getCurrencyByCode(@PathVariable String curCode) {
        Optional<CurrencyInfoEntity> curOpt = currencyInfoRepository.findById(curCode);

        if (curOpt.isPresent()) {
            return ResponseEntity.ok(curOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("currency not found");
        }
    }

    @PutMapping("/updateCurInfo")
    public ResponseEntity<?> updateCurInfo(@RequestBody CurrencyInfoEntity currencyInfo) {
        try {
            log.info("currencyInfo:{}", currencyInfo.getCurCode());

            CurrencyInfoEntity savedCurrency = currencyService.updateCurInfo(currencyInfo.getCurCode(),
                    currencyInfo);
            return ResponseEntity.ok(savedCurrency);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

    @DeleteMapping("/{curCode}")
    public ResponseEntity<String> deleteCurInfoByCode(@PathVariable String curCode) {
        Optional<CurrencyInfoEntity> curOpt = currencyInfoRepository.findById(curCode);

        if (curOpt.isPresent()) {
            currencyInfoRepository.deleteById(curCode);
            return ResponseEntity.ok("delete success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("delete currency not found");
        }
    }
}
