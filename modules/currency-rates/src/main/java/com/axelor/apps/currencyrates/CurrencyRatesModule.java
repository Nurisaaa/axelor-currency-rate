package com.axelor.apps.currencyrates;

import com.axelor.app.AxelorModule;
import com.axelor.apps.currencyrates.service.CurrencyRateService;
import com.axelor.apps.currencyrates.service.CurrencyRateServiceImpl;

public class CurrencyRatesModule extends AxelorModule {
  @Override
  protected void configure() {
    bind(CurrencyRateService.class).to(CurrencyRateServiceImpl.class);
  }
}
