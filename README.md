
# Categorizer

A system that categorizes web pages based on a keyword category.
Categorized mechanizm based on Aho-Corasick Search library 




## API Reference

#### Categorize all items

```http
  POST /api/v1/categorize
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `possibleCategories` | `string[]` | **Required**. List of expected categorizes |
| `urls` | `string[]` | **Required**.List of urls for the seach

Takes all urls, parse and returns the result.


## How to run

To build this project run:

```bash
  gradle build
  java -jar build/libs/categorizer-0.0.1-SNAPSHOT.jar
```

Request:

```bash
curl --location --request POST 'http://localhost:8080/api/v1/categorize' \
--header 'Content-Type: application/json' \
--data-raw '{
    "possibleCategories": [
        "Star Wars",
        "Star Wars",
        "Star Wars",
        "Star Wars",
        "Basketball",
        "Peer39"
    ],
    "urls": [
        "http://www.starwars.com",
        "https://www.imdb.com/find?q=star+wars&ref_=nv_sr_sm",
        "https://edition.cnn.com/sport",
        "https://www.peer39.com/",
        "https://www.adexchanger.com/online-advertising/peer39-has-a-new-marketplace-for-unconventional-contextual-targeting-data/",
        "https://martechseries.com/content/peer39-adds-experian-to-its-contextual-data-marketplace/",
        "https://pitchbook.com/profiles/company/52202-26"
        ]
}'
```
