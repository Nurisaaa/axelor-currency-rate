/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2026 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
