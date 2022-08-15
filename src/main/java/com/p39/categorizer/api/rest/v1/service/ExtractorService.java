package com.p39.categorizer.api.rest.v1.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class ExtractorService implements Extractor<String, String> {

    Logger logger = LogManager.getLogger(ExtractorService.class);

    /**
     * Brings the text of a URL
     *
     * @param url - a URL link
     * @return Extracted text from the URL
     */
    @NotNull
    @Override
    public String extract(String url) {
        logger.info("Extract text from URL: " + url);
        try {
            Document document = Jsoup.connect(url).get();
            return document.body().text();
        } catch (Exception e) {
            String errorMessage = "Unable to parse the web page: " + e;
            logger.error(errorMessage, e);
            throw new IllegalStateException(errorMessage, e);
        }
    }
}
