package com.p39.categorizer;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "app-configuration")
public class KeywordCategoryManager {

    @NotEmpty
    private Map<String, List<String>> categoryKeywordsMapping;

    public Map<String, List<String>> getCategoryKeywordsMapping() {
        return categoryKeywordsMapping;
    }

    public void setCategoryKeywordsMapping(Map<String, List<String>> categoryKeywordsMapping) {
        this.categoryKeywordsMapping = categoryKeywordsMapping;
    }
}
