package com.axelor.apps.currencyrates.job;

import com.axelor.apps.currencyrates.service.CurrencyRateService;
import com.google.inject.Inject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyRateJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(CurrencyRateJob.class);

    @Inject
    private CurrencyRateService service;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("CurrencyRateJob triggered");
        service.updateRates();
    }
}
