package io.seitaku.coin_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.seitaku.coin_demo.model.entity.CurrencyInfoEntity;

@Repository
public interface CurrencyInfoRepository extends JpaRepository<CurrencyInfoEntity, String> {

    List<CurrencyInfoEntity> findByCurCodeIn(List<String> curCodes);

}