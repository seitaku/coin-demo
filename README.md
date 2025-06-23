# 貨幣資訊 API 接口文檔

## 基礎資訊
- **Base URL**: `http://{{host}}:{{port}}/{{api_cur}}`
- **數據格式**: `application/json; charset=utf-8`

## API 接口列表

### 1. 新增單筆貨幣資訊
- **方法**: `POST`
- **路徑**: `/addCurInfo`
- **請求範例**:
  ```json
  {
    "curCode": "GOLD",
    "nameZh": "黃金",
    "symbol": "g",
    "curType": "OTHER",
    "isActive": "Y",
    "displayScale": 4
  }


### 2. 取得單筆貨幣資訊
方法: GET

路徑: /{curCode}

參數說明:

{curCode}: 貨幣代碼 (如: GOLD)

### 3. 取得第三方貨幣資訊
方法: GET

路徑: /thirdCurInfo

### 4. 取得第三方貨幣資訊 (格式化)
方法: GET

路徑: /format

### 5. 刪除貨幣資訊
方法: DELETE

路徑: /{deleteCur}

參數說明:

{deleteCur}: 要刪除的貨幣代碼 (預設: XAU)

### 6. 更新貨幣資訊
方法: PUT

路徑: /updateCurInfo

請求範例:

json
{
  "curCode": "GOLD",
  "nameZh": "黃金",
  "symbol": "g",
  "curType": "OTHER",
  "isActive": "Y",
  "displayScale": 4
}
字段說明: 同「新增單筆貨幣資訊」

### 7. 取得所有貨幣資訊表
方法: GET

路徑: /table
