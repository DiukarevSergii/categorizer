package com.categorizer.api.rest.v1.controller;

import com.categorizer.api.rest.v1.service.Extractor;
import com.categorizer.api.rest.v1.dto.KeywordCategoryDTO;
import com.categorizer.api.rest.v1.service.Categorizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class CategorizerController {

    private Logger logger = LogManager.getLogger(CategorizerController.class);

    @Autowired
    private Extractor<String, String> extractor;

    @Autowired
    private Categorizer<String, Set<String>> categorizer;

    @PostMapping("/categorize")
    public Map<String, Set<String>> categorize(
            @Valid @RequestBody KeywordCategoryDTO requestDTO
            ) {
        Map<String, Set<String>> result = new HashMap<>();
        requestDTO
                .getUrls()
                .parallelStream()
                .forEach(url -> {
                    // extract text in a similar way as we did in Part 1
                    String text = extractor.extract(url);
                    // parsing input and searching for match categories
                    Set<String> matchedCategoriesSet = categorizer.categorize(text);
                    // creating a new instance to avoid modification of original object
                    Set<String> possibleCategoriesDTO = new HashSet<>(requestDTO.getPossibleCategories());
                    possibleCategoriesDTO.retainAll(matchedCategoriesSet);
                    result.put(url,possibleCategoriesDTO);
                });
        return  result;
    }

}
