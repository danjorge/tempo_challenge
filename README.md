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
* otherwise, to run without any IDE, you can use this command in one terminal (root directory of this project):  
```
./gradlew bootRun
```
---
### What was done into the project, and The Approach used to solve the problem
* Imagining that the services of listing and saving users are already ready, to enrich them by giving the possibility of associating team members for a role, we need to create a Role class, an intermediate class called UserTeam with a primary key composed of users and teams for a role, so it is possible to search for a role for a member and a member for a role, also including the call to the Role class within that intermediate table.
* That done, we need to include a repository to make calls to the database, thus making it possible to save the objects.
* After that, we need to create a class where all the logic needs to be called Service. So, we created a controller to receive calls made to the service.
---

### Technologies used
You can see in:
* [HELP.md](HELP.md)
