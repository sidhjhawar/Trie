MapQuest interview problem: delta encoder.

*******

The goal of this exercise is to implement a web service which can keep track of a set of terms, and can return the terms, in a certain compressed format, when they're requested as a group.  For simplicity, you can assume that all the terms are single, simple words, in lowercase ASCII lettering.

To generate the compressed form, the service first sorts all the terms.  It then iterates over the sorted list, and for each term, outputs an optional backreference to an earlier term, plus a suffix.  The backreference is the index of the longest preceding term that's a perfect prefix of the current term.  (If no such term exists, the backreference should be set to the indicator -1.)  The suffix is the remaining substring that can be added to the prefix to reconstruct the term.

For instance, if the service were given the terms "dig", "digress", "digest", and "digresses", and was then asked for the encoded form of the set, the response would contain the following data:

-1 dig
0 est
0 ress
2 es

The original set could then be reconstructed from this data by first outputting the term "dig"; then outputting the term at index 0 plus "est" ("dig" + "est"); then outputting the term at index 0 plus "ress" ("dig" + "ress"); then outputting the term at index 2 plus "es" ("digress" + "es").  For the exact format of the response in terms of JSON arrays and objects, please see below.

Note: the service need not have a persistence layer; all the data can simply be kept in memory, to be lost when the application is shut down.

*******

This directory contains a working build.gradle for a Spring Boot project.  To run the application, simply type:

gradle bootRun

To verify that the service is running, type:

curl 'http://localhost:8080/terms'

An empty Javascript list should be returned.

*******

The service has the following endpoints:

1. Add a term:

curl -X PUT 'http://localhost:8080/terms/<term>'

* This should return an HTTP 200, and an empty response body.  On success, the term should be stored by the service.

2. Delete a term:

curl -X DELETE 'http://localhost:8080/terms/<term>'

* This call should ensure that the term is no longer stored by the service, and should return an HTTP 200.

3. Get all terms, encoded:

curl 'http://localhost:8080/terms'

* This call should return HTTP 200, plus a JSON array containing an object for each unique term currently stored by the service, in sorted order.  Each object should contain a field "backref" that contains the index of the preceding item that's a maximal prefix for the current term (or -1 if no such term exists), as well as a field "suffix" containing the substring that should be appended to the prefix to recreate the term.

*******

A sample run, using curl.  Responses are indicated as "[HTTP status code] -> [response]".

curl -v 'http://localhost:8080/terms'
200 -> []

curl -v -X PUT 'http://localhost:8080/terms/dig'
200 ->

curl -v -X PUT 'http://localhost:8080/terms/digress'
200 ->

curl -v -X PUT 'http://localhost:8080/terms/digest'
200 ->

curl -v -X PUT 'http://localhost:8080/terms/digress'
200 ->

curl -v 'http://localhost:8080/terms'
200 -> [{"backref":-1,"suffix":"dig"},{"backref":0,"suffix":"est"},{"backref":0,"suffix":"ress"}]

curl -v -X PUT 'http://localhost:8080/terms/diggers'
200 ->

curl -v -X PUT 'http://localhost:8080/terms/digester'
200 ->

curl -v -X PUT 'http://localhost:8080/terms/digests'
200 ->

curl -v -X PUT 'http://localhost:8080/terms/digs'
200 ->

curl -v -X PUT 'http://localhost:8080/terms/elephant'
200 ->

curl -v -X PUT 'http://localhost:8080/terms/digresses'
200 ->

curl -v 'http://localhost:8080/terms'
200 -> [{"backref":-1,"suffix":"dig"},{"backref":0,"suffix":"est"},{"backref":1,"suffix":"er"},{"backref":1,"suffix":"s"},{"backref":0,"suffix":"gers"},{"backref":0,"suffix":"ress"},{"backref":5,"suffix":"es"},{"backref":0,"suffix":"s"},{"backref":-1,"suffix":"elephant"}]

curl -v -X DELETE 'http://localhost:8080/terms/digest'
200 ->

curl -v 'http://localhost:8080/terms'
200 -> [{"backref":-1,"suffix":"dig"},{"backref":0,"suffix":"ester"},{"backref":0,"suffix":"ests"},{"backref":0,"suffix":"gers"},{"backref":0,"suffix":"ress"},{"backref":4,"suffix":"es"},{"backref":0,"suffix":"s"},{"backref":-1,"suffix":"elephant"}]

*******

Implementation notes:

1. Please add unit tests as appropriate.  Complete coverage isn't required, but please cover at least one bit of functionality well enough to demonstrate your knowledge of unit testing.

2. Autowired assembly of the application is preferred.

3. Keep in mind that multiple concurrent users could potentially access the application.

