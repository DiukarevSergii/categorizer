package com.p39.categorizer.api.rest.v1.service;

import com.p39.categorizer.KeywordCategoryManager;
import org.ahocorasick.trie.PayloadEmit;
import org.ahocorasick.trie.PayloadTrie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Parsing input text and return matched categories
 *
 * <p></p>Service reuse Aho-Corasick search</p>
 * <p>Most free-text searching is based on Lucene-like approaches, where the search text is parsed into its various </p>
 * <p>components. For every keyword a lookup is done to see where it occurs. When looking for a couple of keywords this </p>
 * <p>approach is great, but when searching for 100,000 words, the approach is quite slow (for example, checking against a </p>
 * <p>dictionary).</p>
 * <p>The algorithm is O(n). No matter how many keywords are given, or how large the search text is, the performance will decline linearly.</p>
 *
 * <p><a> href="https://github.com/robert-bor/aho-corasick"</a></p>
 */
@Service
public class CategorizerSerice implements Categorizer<String, Set<String>> {

    private Logger logger = LogManager.getLogger(CategorizerSerice.class);

    @Autowired
    private KeywordCategoryManager keywordCategoryManager;

    private PayloadTrie<String> trie;

    @Override
    public Set<String> categorize(String input) {
        logger.debug("Input text: " + input);

        logger.info("Parsing text...");
        Collection<PayloadEmit<String>> payloadEmits = trie.parseText(input);
        logger.info("Text parsed successfully");

        logger.info("Categorizing text...");
        Set<String> categorizedText = payloadEmits
                .stream()
                .map(PayloadEmit::getPayload)
                .collect(Collectors.toSet());
        logger.debug("Categorized text: " + categorizedText);
        return categorizedText;
    }

    @PostConstruct
    protected void postConstruct() {
        PayloadTrie.PayloadTrieBuilder<String> trieBuilder =
                PayloadTrie
                        .<String>builder()
                        .ignoreCase()
                        .onlyWholeWords();

        keywordCategoryManager.getCategoryKeywordsMapping()
                .forEach((category, keywords) -> {
                    if (Objects.isNull(keywords) || keywords.isEmpty()) {
                        throw new IllegalArgumentException("Keywords were not found for this category: " + category);
                    }
                    keywords.forEach(
                            keyword -> trieBuilder.addKeyword(keyword, category)
                    );

                });
        this.trie = trieBuilder.build();
    }
}
