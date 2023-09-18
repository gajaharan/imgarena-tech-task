# imgarena-tech-task

## Technologies
* Gradle 
* Kotlin 1.8
* Java 17
* Spring Boot 2.7.16
* JPA with H2 database
* Testing - Junit, Mockito, MockMvc, Integration Test using H2 database

## Build and Run

### How to build:
`./mvnw clean build`

### How to run tests:
`./gradlew clean test`


### How to run
* Run as a Spring Boot local application
* `./gradlew clean build bootRun`

## Overview

### Summary
| Endpoint                  | method | Status                                           | Notes                                   |
|:--------------------------|:-------|:-------------------------------------------------|:----------------------------------------|
| `/api/v1/golf/tournament` | POST   | Response code 201 with Location header, 400, 500 | Need to use x-data-src-id in the header |
|                           |        |                                                  | with data-source-one or data-source-two |

### Example POST request
```
-- header: ' "x-data-src-id": "data-source-one" '

{
	"tournamentId": "174638",
	"tournamentName": "Women's Open Championship",
	"forecast": "fair",
	"courseName": "Sunnydale Golf Course",
	"countryCode": "GB",
	"startDate": "09/07/21",
	"endDate": "13/07/21",
	"roundCount": "4"
}
```

### Usage for new data sources: 
1. create a new package under data (data source name).
2. Create a new class and extend `Converter` interface. Implement interface functions in `Converter file`.
3. Create a new data cass that extends `Source` interface in `Converter file`.
3. Add test to `data` package. Update `GolfTournamentIntegrationTest`.


### Assumptions
* Used Spring Boot 2 instead of version 3 as this was the version I used two years ago. Past two years used a different framework http4k.
* Use HTTP headers to define the data source (API demands of the source for the provider) e.g. x-data-src-id.
* Use the strategy pattern to define a "data source jackson converters" to allow for additional data sources to be accommodated in the future with minimal change to the existing codebase.
* A single common table that will have the base set of data as mandatory columns. Other columns like forecast and playerCount could be stored optionally. Or maybe put it into specific tables e.g. data_source_1_table and data_source_2_table. Not sure how scalable that will be.

### Further Improvements
* Handle missing data source properties like forecast and playerCount to the database.
* Implement GET endpoint to retrieve data source based on external data source id.
