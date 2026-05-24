package com.axelor.apps.currencyrates.service;

import com.axelor.apps.currencyrates.db.CurrencyRate;
import com.axelor.apps.currencyrates.db.repo.CurrencyRateRepository;
import com.axelor.apps.currencyrates.dto.CurrencyRateDto;
import com.axelor.db.Query;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyRateServiceImpl implements CurrencyRateService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyRateServiceImpl.class);
    private static final String NBKR_URL = "https://www.nbkr.kg/XML/daily.xml";
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final CurrencyRateRepository repository;

    @Inject
    public CurrencyRateServiceImpl(CurrencyRateRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public String updateRates() {
        log.info("Rate update started");
        try {
            String xml = HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder().uri(URI.create(NBKR_URL)).GET().build(),
                        HttpResponse.BodyHandlers.ofString()
                    )
                    .body();

            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes()));

            LocalDate date = LocalDate.parse(
                    doc.getDocumentElement().getAttribute("Date"), DATE_FMT
            );

            NodeList list = doc.getElementsByTagName("Currency");
            log.info("Parse: date={}, count={}", date, list.getLength());

            for (int i = 0; i < list.getLength(); i++) {
                Element el = (Element) list.item(i);
                String code = el.getAttribute("ISOCode");

                Optional<CurrencyRate> existing = Optional.ofNullable(
                    Query.of(CurrencyRate.class)
                        .filter("self.code = :code AND self.rateDate = :date")
                        .bind("code", code)
                        .bind("date", date)
                        .fetchOne()
                );

                CurrencyRate entity = existing.orElseGet(CurrencyRate::new);
                entity.setCode(code);
                entity.setName(code);
                entity.setNominal(Integer.parseInt(
                        el.getElementsByTagName("Nominal").item(0).getTextContent()));
                entity.setRate(new BigDecimal(
                        el.getElementsByTagName("Value").item(0).getTextContent().replace(",", ".")));
                entity.setRateDate(date);

                repository.save(entity);
                log.info("Saved: {} = {}", code, entity.getRate());
            }

            log.info("Rate update finished: {} currency {}", list.getLength(), date);
            return "Successfully rate updated";

        } catch (Exception e) {
            log.error("Rate update failed: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CurrencyRateDto> getAllRates() {
        List<CurrencyRate> all = repository.all().fetch();
        List<CurrencyRateDto> result = new ArrayList<>();
        for (CurrencyRate c : all) {
            result.add(new CurrencyRateDto(c.getCode(), c.getName(), c.getNominal(), c.getRate(), c.getRateDate()));
        }
        return result;
    }
}
