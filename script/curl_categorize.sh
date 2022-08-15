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