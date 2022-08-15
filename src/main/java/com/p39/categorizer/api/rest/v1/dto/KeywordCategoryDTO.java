package com.p39.categorizer.api.rest.v1.dto;

import java.util.List;
import java.util.Set;

public class KeywordCategoryDTO {
    private Set<String> possibleCategories;
    private List<String> urls;

    public Set<String> getPossibleCategories() {
        return possibleCategories;
    }

    public void setPossibleCategories(Set<String> possibleCategories) {
        this.possibleCategories = possibleCategories;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
