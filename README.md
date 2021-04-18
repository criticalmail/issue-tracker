##### From Joshua #####

I found the original project is in an older version with Spring boot 1x and Java 8.

I re-created the entire project in Spring boot 2.3.x, Java 11 and avoided flyway due to incompatibility.

I edited the SQL to make it run on the H2 database without the flyway.

The project is refactored based on the review.

OpenAPI3.0 / Swagger is available on /swagger-ui.html

	gradle --refresh-dependencies

	gradle test

	gradle bootRun
	


##### From Domain #####
#  Issue Tracker application

## Overview

The Issue tracker application is a simplified version of Atlassian Jira, or Trello board, which allows users to create
and manage the progress of issues. In the exercise, you will be asked to implement a RESTful API which provides the
required functionality.

The first part of the exercise is required, and the additional parts are optional. Please let us know when you submit your completed application how much time was spent on it.

You have been provided with a simple Spring Boot application template to get you started, although you are not required
to use it. Feel free to modify the provided tables, data as you see fit. Details are provided below for the setup,
and how to start the server.


## Exercise

### Part 1 - Core REST api
Design a RESTful API for the issue tracker system, which will provide CRUD functionality to API consumers.
Consumers should be able to complete all of the following:

1. Create a new issue.
2. Retrieve an existing issue.
3. Update an existing issue.
3. Delete an issue.


### Part 2 - Filtering and Sorting

Build on your initial implementation to support filter of issues. Consumers should be
able to complete all of the following:

- Filter by assignee, reporter and status
- Filter by date range eg tasks created between start and end date
- Sorting by created date, Ascending and Descending
- Pagination support.


### Part 3 - Comments Feature

Build on your initial implementation to support commenting on an issue. Requirements:

- An Issue can have zero or more comments
- Comments cannot be anonymous. They must have a user.
- Replying to comments is not supported. Comments on an issue cannot be nested.

### Part 4 (Optional) - Testing for extra points

Here at pricefinder testing is an important part of delivering any solution.
 
For bonus points in this test we would love to see a suite of tests to show 
that the work you have delivered is error free. Remember to test both the happy path
 as well as throwing in some unexpected and invalid data too.

## Getting Started

## SpringBoot

[Spring Boot](https://projects.spring.io/spring-boot/) is an Opinionated Java Framework for developing production-ready
Spring applications. Spring Boot favours convention over configuration and is designed to get you up and running as
quickly as possible.


[Gradle](https://gradle.org/) is the build tool for this project, and requires Java JDK to be installed. It is not
necessary to install gradle.


This project makes use of [The Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html). The
following command will run a full build of the project.

    ./gradlew build


### Running the application

This project makes use of the
[Gradle Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-gradle-plugin.html)
plugin. To start the application locally just run the following command


    ./gradew bootRun


## H2 Console

The H2 DB console can be accessed in your browser. Navigate to [/v1/h2](http://localhost:8080/v1/h2) to access the console,
and ensure the `JDBC URL` matches the one defined in the `application.yml` file: jdbc:h2:file:~/simple-ticket-db

**Note**: removing the `simple-ticket-db` file, which will be placed in your home by default, will reset the database.

## Flyway

[Flyway](https://flywaydb.org/) is a database migration tool and will initialze the H2 database with sample data. The
scripts can be found in `src/main/resources/db/migration`

Additional SQL should be placed in versioned SQL files eg `V2__new_file.sql`

## Submission
Please submit a complete copy of the source code you wrote for this test.

Upon submission, please also give us a guide to how long you spent on the test (There is no wrong answer! We iterate on this test based on the average time taken so we can provide an accurate time-frame for future candidates).

Submission can be in any format where we have access to the required files (.zip, GitHub, carrier pigeon, etc - your call!)