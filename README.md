# Bon Voyage

# Overview of the application
* A web application powered by RESTful backend which facilitates searching of a restaurants and near by places to hangout in a city.
* Allowing users to create custom itinerary.
* Suggesting some of the hot destinations in the USA to hangout.
* Comparing the popularity of the restaurants based on the rating and displaying the comparison in the form of bar chart using High Charts.
* Allowing users to create and download the itinerary.

# Features of the application

* Google places API.
* Google maps API.
* Password Encryption.
* PDF creation and download for the itinerary.
* High charts.
 
# Technology Stack

* Java 8
* AngularJs
* MongoDB
* Maven
* HTML 5 and Bootstrap
* JsPDF for PDF creation and downloading.

# Steps to run this web application

a) Front end accessibility

1) mvn spring-boot: run
2) http://localhost:8080/hangoutamigosapp/index.html


b) To simply access the backend APIs

1) mvn clean install
2) java -jar target demo-0.0.1-SNAPSHOT.jar


 i) http://localhost:8080/hangoutamigos/getplaces/{latitutde}/{longitude}/{radius}/type/{type} 
 http://localhost:8080/hangoutamigos/getplaces/47.6062095/-122.3320708/1000/type/restaurant
 
 ii) To get the details of a city
  http://localhost:8080/hangoutamigos/getplace/textsearch/{query}
   http://localhost:8080/hangoutamigos/getplace/textsearch/seatte
