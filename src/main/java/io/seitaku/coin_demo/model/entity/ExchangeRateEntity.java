package io.seitaku.coin_demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "exchange_rate")
public class ExchangeRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BASE_CURRENCY")
    private Currency baseCurrency;

    @Column(name = "TARGET_CURRENCY")
    private Currency targetCurrency;

    @Column(nullable = false, precision = 20, scale = 6)
    private BigDecimal rate;

    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;

    @PreUpdate
    public void preUpdate() {
        this.updatedTime = LocalDateTime.now();
    }

}