package io.seitaku.coin_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.seitaku.coin_demo.model.entity.ExchangeRateEntity;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {

}