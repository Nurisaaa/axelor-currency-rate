package com.axelor.apps.currencyrates.web;

import com.axelor.apps.currencyrates.service.CurrencyRateService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class CurrencyRateController {

  private final CurrencyRateService service;

  @Inject
  public CurrencyRateController(CurrencyRateService service) {
    this.service = service;
  }

  public void updateRates(ActionRequest request, ActionResponse response) {
    try {
      service.updateRates();
      response.setReload(true);
      response.setInfo("Currency rates updated successfully");
    } catch (Exception e) {
      response.setError("Update failed: " + e.getMessage());
    }
  }

  public void getAllRates(ActionRequest request, ActionResponse response) {
    response.setValue("rates", service.getAllRates());
  }
}
