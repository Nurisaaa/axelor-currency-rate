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
