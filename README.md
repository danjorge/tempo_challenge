# Getting Started (Challenge)

### How to run the project documentation
For further initialization of this project, you will need:

* JDK 11+ (installed)
* JVM (installed)
* Clone this project using: 
```
git clone https://github.com/danjorge/tempo_challenge.git 
```  
* I advocate to use intellij IDE in this project, due it's more simple to configure to run the project
* otherwise, to run without any IDE, you can use this command in one terminal:  
```
./gradlew bootRun
```

### What was did into the project and The Approach used to solve the problem
* Imagining that the services of listing and saving users are already ready, to enrich it by giving the ability to associate team members for a role, we need to create a Role class and call it within the Users class as an object, thus initiating a relationship between the classes called object orientation.
* That done, we need to include a repository to make calls to the database, thus making it possible to save the objects.
* After that, we need to create a class where all the logic needs to be called Service. So, we created a controller to receive calls made to the service.
---
* The approach used was to include a class for the user's role and relate it to the user class itself including an attribute called role and related them using JPA and Hibernate with the appropriate annotations, thus making it possible to make a logic to include a role within a user at the time a user is being saved or when a role inclusion is requested.

### Technologies used
You can see in:
* [HELP.md](HELP.md)
