# OrganizationsAndUsers
This is a Spring Boot project that constructs end points to create/manipulate Organizations and Users and show the relationship between these two resources.

### Database Schema
##### H2 Database is used
![DB Relations](https://github.com/Yunukas/OrganizationsAndUsers/blob/master/db_relation.png)


### Project Structure
![Project Structure](https://github.com/Yunukas/OrganizationsAndUsers/blob/master/organizationDemoDiagram.png)


## Resources
#### This applications comes with 2 common resources:
/users

/organizations


## Routes
#### Below HTTP methods are supported.

GET		    /users

GET		    /organizations

GET		    /users/{userId}
  
GET		    /users/{userId}/organizations
  
GET		    /organizations/{organizationId}
  
GET		    /organizations/{organizationId}/users
  
POST	    /users

POST	    /organizations

PUT		    /users/{userId}

PUT		    /organizations/{organizationId}

PUT		    /organizations/{organizationId}/users/add?id={userId}

DELETE    /users/{userId}

DELETE	  /organizations/{organizationId}/users/delete?id={userId}
