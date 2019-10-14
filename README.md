# OrganizationsAndUsers


### Database Schema
![DB Relations](https://github.com/Yunukas/OrganizationsAndUsers/blob/master/db_relation.png)


### Project Structure
![Project Structure](https://github.com/Yunukas/OrganizationsAndUsers/blob/master/organizationDemoDiagram.png)


## Resources
#### This applications comes with 2 common resources:
/users

/organizations


## Routes
#### Below HTTP methods are supported.

GET		/users

GET		/organizations

GET		/users/<userId>
  
GET		/users/<userId>/organizations
  
GET		/organizations/<organizationId>
  
GET		/organizations/<organizationId>/users
  
POST	/users

POST	/organizations

PUT		/organizations/<organizationId>

PUT		/organizations/<organizationId>/users/add?id=<userId>

DELETE	/users/<userId>

DELETE	/organizations/<organizationId>/users/delete?id=<userId>
