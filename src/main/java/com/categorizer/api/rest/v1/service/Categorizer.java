package com.categorizer.api.rest.v1.service;

public interface Categorizer<T, S> {
    S categorize(T t);
}
