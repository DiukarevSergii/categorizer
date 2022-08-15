package com.p39.categorizer.api.rest.v1.service;

public interface Extractor<T, S>{
    T extract(S s);
}
