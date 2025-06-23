package io.seitaku.coin_demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "CURRENCY_INFO")
public class CurrencyInfoEntity {

    @Id
    @Column(name = "CUR_CODE", length = 10)
    private String curCode;

    @Column(name = "NAME_ZH", nullable = false, columnDefinition = "VARCHAR(64) CHARACTER SET utf8")
    private String nameZh;

    @Column(name = "SYMBOL", length = 10)
    private String symbol;

    @Enumerated(EnumType.STRING)
    @Column(name = "CUR_TYPE", nullable = false)
    private CurrencyType curType;

    @Column(name = "IS_ACTIVE", nullable = false, length = 1)
    private String isActive;

    @Column(name = "DISPLAY_SCALE")
    private Integer displayScale;

    public enum CurrencyType {
        ISO_CURRENCY,
        CRYPTO_CURRENCY,
        OTHER
    }

}
