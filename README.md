# imgarena-tech-task


1. Use HTTP headers to define the data source (API demands of the source from the consumer) e.g. x-data-src-id
2. This will allow us to define a single endpoint (/v1/golf/tournament) and accept the data source from the header and request string
3. Use the strategy pattern to define a "data source jackson deserializer" interface to allow for additional sources to be accommodated in the future with minimal change to the existing codebase.
4. Use a map collection of object mappers when deserializing the data source based on x-data-src-id key
5. Use a single common table that will have a base set of data as mandatory columns. Other columns like forecast and playerCount could be stored optionally. Or maybe put it into specific tables e.g. data_source_1_table and data_source_2_table. Not sure how scalable that will be. TBC the latter part.
6. If I have time I will implement a GET endpoint to query the database and return the appropriate data source based on the external source id.