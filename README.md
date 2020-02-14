# Shop Service
A Example RESTful web service for managing persons. 

Check [person-management-ui repo](https://github.com/arpanps/person-management-ui) for basic UI which consumes Person service Microservice APIs.

Service uses Java - Spring ecosystem

## Running Service
### Docker

You need to have [Docker installed](https://docs.docker.com/v17.09/engine/installation/)

Following command will pull and run the image on machine. This will run person-management service with default configuration 

`docker run -p 8080:8080 arpanps/microservice-person-management:0.0.1-SNAPSHOT`

The API documentations will be available at the path /api-docs

`http://localhost:8080/person-management/api-docs/`

Interact with application API specification and exercise the endpoints via Swagger UI. Access Swagger UI using below link

`http://localhost:8080/person-management/swagger-ui/index.html?url=/person-management/api-docs`

### For Development
* Prerequisite - git, java, maven 

* Clone Repo

	`git clone https://github.com/arpanps/person-management.git`

* Go inside project directory

	`cd person-management`

* Build Project with Maven

	`mvn install`

* Run Service using Maven  
 
	`mvn spring-boot:run`

#### Publishing Docker Image

* Package project

	`mvn clean package`

* Build the docker image (Use your username instead of arpanps)

	`docker build -t arpanps/microservice-person-management -f etc/docker/Dockerfile .`

* Login with your Docker Id
	
	`docker login`
	
* Tag the image

	`docker tag arpanps/microservice-person-management arpanps/microservice-person-management:0.0.1-SNAPSHOT`

* Push the image to docker hub

	`docker push arpanps/microservice-person-management:0.0.1-SNAPSHOT`



### Technology

* [OpenAPI](https://swagger.io/docs/specification/about/) - OpenAPI Specification (formerly Swagger Specification) is an API description format for REST APIs. An OpenAPI file allows you to describe your entire API

* [Swagger UI](https://swagger.io/tools/swagger-ui/) - Swagger UI allows anyone — be it development team or end consumers — to visualize and interact with the API’s resources without having any of the implementation logic in place. 

* [Spring Boot](https://projects.spring.io/spring-boot/) - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.

* [Apache Derby](https://db.apache.org/derby/derby_downloads.html) - Currently service uses derby, embedded database, for package storage. Derby is very helpful during the development phase, because they are lightweight, fast, quick start time, improve testability, ease of configuration, it lets developer focus more on the development.

* [Hibernate](http://hibernate.org/) - Hibernate is the most popular ORM framework

* [Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html) - Actuator includes a number of additional features to help you monitor and manage your application when it’s pushed to production, including useful metric, dump and health endpoints.

* [Docker](https://www.docker.com/) - Docker is a tool designed to make it easier to create, deploy, and run applications by using containers


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#production-ready)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)





/********
1. Create a REST API which stores, updates, retrieves and deletes Person entities

Using Java, create a REST API which stores, updates, retrieves and deletes Person entities such as those represented in the JSON below:



{

"person": [

  {

    "first_name": "John",

    "last_name": "Wick",

    "age": "29",

    "favourite_colour": "red",

    "hobby": ["shopping", "football"]

  },

  {

    "first_name": "Sarah",

    "last_name": "Raven",

    "age": "54",

    "favourite_colour": "blue",

    "hobby": ["chess"]

  }

]

}

You may use an in-memory database as a proof of concept for this test.



Additionally, pick at least one of the problems from the list below and solve it in your code:





Create a web front end that interacts with your API (using technology of your choice)
Create a command line client that interacts with your API (using technology of your choice)
Secure your API with authentication method of your choice
Ensure that your solution can be (additionally) tested without the need to install it locally.


Do include sufficient details on how to build, test, deploy and run your code.



Publish your answer in GitHub. Your answer to this question should be presented as just one GitHub repository link.

If you think it is more appropriate to create more repositories (e.g. for the front end and the API code), reference those repositories in your main repository.

Feel free to use the Vagrantfile from this repository https://github.com/joanmarcriera/vagrant-file-for-java-apps  to ensure we are able to check your code by having the same environment conditions as you do.



****/
