# effective-planning-app
>time management, to-do list, web application

## Table of contents
* [General info](#general-info)
* [Live Demo](#Live-Demo)
* [Technologies](#technologies)
* [How to use?](#How-to-use?)

## General info
>Web application
* allows you to add an unlimited number of to-do lists
* allows you to add an unlimited number of tasks
to these to-do lists
* logging in with Google or GitHub

## Live Demo
> http://effectiveplanningapplication-env.eba-xs6xeppn.eu-central-1.elasticbeanstalk.com/

## Technologies
* Spring Boot
* Spring Data JPA
* Spring Security OAuth
* MySql
* Liquibase
* Boostrap
* Thymeleaf



## How to use?
### Creating OAuth2 apps for social login
To enable social login with an OAuth2 provider, you’ll need to create an app in the OAuth2 provider’s console.
You’ll need to configure your application’s client ID and client secret (which you can obtain by registering your application with Google at (https://console.developers.google.com/)  and Github at (https://github.com/settings/apps)).
Then add the client credentials to the *application.properties* file. 
### Database configuration
In the same file you'll also need configure MySQL database. You must add username, password and url your own database.
