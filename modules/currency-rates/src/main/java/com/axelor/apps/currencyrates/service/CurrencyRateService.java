package com.axelor.apps.currencyrates.service;

import com.axelor.apps.currencyrates.dto.CurrencyRateDto;
import java.util.List;

public interface CurrencyRateService {
  String updateRates();

  List<CurrencyRateDto> getAllRates();
}
