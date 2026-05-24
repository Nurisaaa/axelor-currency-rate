package com.axelor.apps.currencyrates.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyRateDto {
  private String code;
  private String name;
  private Integer nominal;
  private BigDecimal rate;
  private LocalDate rateDate;

  public CurrencyRateDto(
      String code, String name, Integer nominal, BigDecimal rate, LocalDate rateDate) {
    this.code = code;
    this.name = name;
    this.nominal = nominal;
    this.rate = rate;
    this.rateDate = rateDate;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public Integer getNominal() {
    return nominal;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public LocalDate getRateDate() {
    return rateDate;
  }
}
