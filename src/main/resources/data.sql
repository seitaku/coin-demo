INSERT INTO CURRENCY_INFO (CUR_CODE, NAME_ZH, SYMBOL, CUR_TYPE, IS_ACTIVE, DISPLAY_SCALE) VALUES
    ('USD', '美元', '&dollar;', 'ISO_CURRENCY', 'Y', 3),
    ('EUR', '歐元', '&euro;', 'ISO_CURRENCY', 'Y', 3),
    ('JPY', '日元', '&yen;', 'ISO_CURRENCY', 'Y', 0),
    ('GBP', '英鎊', '&pound;', 'ISO_CURRENCY', 'Y', 2),
    ('BTC', '比特幣', NULL, 'CRYPTO_CURRENCY', 'Y', 8),
    ('ETH', '以太幣', NULL, 'CRYPTO_CURRENCY', 'N', 2),
    ('CNY', '人民幣', '&yen;', 'ISO_CURRENCY', 'Y', 2),
    ('TWD', '新台幣', 'NTD;', 'ISO_CURRENCY', 'Y', 2),
    ('GOLD', '黃金', 'g', 'OTHER', 'Y', 4),
    ('XAU', '黃金', 'XAU', 'ISO_CURRENCY', 'Y', 4);