# Disc-Assignment
# Shortest Path Application

This is a Spring Boot application that implements endpoints to find the shortest path between two planets, load data from Excel to a database, and perform CRUD operations on Traffic, Routes, and Planet data.

## Technical Stack

- JDK 8
- In-Memory Database (Derby)
- RESTful APIs using Jersey
- Apache POI to read Excel
- Lombok

## Implemented as part of Assignment

1. **Persist the Graph into an In-Memory Database**

    - Created tables for the three sheets provided in the Excel sheet:
        1. Planet_Names
        2. Routes
        3. Traffic

    - Columns created as per the columns in the sheet.
    - Used `@Entity` annotation to generate the table and its structure while the application starts.


**Read the File and Import it into the DB**


              Implemented teh POST API which consumes Excel and will give the message as  Data successfully loaded into Database.
       		  - API - http://localhost:8080/api/planet/data/load
       		  	Method : POST



**Expose the Database Using a RESTful Web Service**

	Implemented the REST API's CRUD operations on the ROUTES, TRAFFIC and PLANET_NAMES tables with repos 
  
     Note: Please load the data before finding the shortest distance; then, data will be available to calculate the distance.

**Implement the Algorithm to Find the Shortest Path**

- Retrieve a list of all routes from the `routesRepo` (Routes Repository). These routes represent connections between planets.
- Create an `edgeGraph`, which is a mapping of planet names to a list of edges (connections to other planets). The Routes objects are grouped by their origin planet using the `groupingBy` collector, and each group is mapped to a list of edges using the mapping function.
  - Call the `dijkstra` method with the `edgeGraph`, source, and destination as parameters to calculate the shortest distances.
    - Initialize two maps: `distances` and `shortestDistances`.
        - `distances`: Keeps track of the current shortest distances from the origin planet to all other planets.
        - Initially, all distances are set to positive infinity.
        - `shortestDistances`: Stores the shortest distance to the destination planet, if it is reachable.
    - Create a priority queue `priorityQueue` that stores planet names based on their current distances in ascending order.
    - Initialize the `distances` map by setting the distance from the origin planet to itself to 0.0 and adding origin to the `priorityQueue`.
    - While the `priorityQueue` is not empty, do the following:
        - Pop the planet with the smallest current distance (`currentPlanet`) from the `priorityQueue`.
        - For each neighbor of `currentPlanet` in the `edgeGraph`, calculate a new distance (`newDistance`) by adding the distance of the edge connecting them to the current distance of `currentPlanet`.
        - If the new distance is smaller than the current distance stored in `distances` for the neighbor, update the `distances` map with the new distance and add the neighbor to the `priorityQueue`.
    - After the algorithm completes, check if the `distances` map contains the destination planet. If it does, store the shortest distance to the destination planet in the `shortestDistances` map.
    - Return the `shortestDistances` map, which contains the shortest distance from the origin planet to the destination planet

**Expose the Algorithm Using a Document Literal Web Service**

     - Expose a document literal service:
     - Construct an XSD to constrain the usage of the service.
     - Expose the Route Request document and return a Route Response document with the hops.
     
     Note: For this XSD, we have implemented the End Points using REST APIs, and we are ignoring this as XSD is normally used for literal SOAP services.

**Create a Front End to Capture the Source and Destination and Then Print the Shortest Path**

     - Implemented HTML and JavaScript to hit the GET API to get the shortest distance and display the result in a table format.
     http://localhost:8080/index.html - UI Page URL


