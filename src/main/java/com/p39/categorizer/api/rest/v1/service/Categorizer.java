package com.p39.categorizer.api.rest.v1.service;

public interface Categorizer<T, S> {
    S categorize(T t);
}
