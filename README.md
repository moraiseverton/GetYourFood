# README #

# GetYourFood
SkipTheDishes São Paulo Recruiting Fair

### Running tests ###
* Open the Terminal
* Go to the location of the project
* Run the command: 

```
mvn test
```

### Running the application ###
* Open the Terminal
* Go to the location of the project
* Run the command: 

```
mvn spring-boot:run
```

### Endpoints ###
* [http://localhost:8080/api/v1/swagger-ui.html](Link URL)


### H2 Database Console ###
* On the browser, access: [http://localhost:8080/api/v1/h2-console/](Link URL)
* Fill the field JDBC URL with: **jdbc:h2:mem:testdb**
* Press **Connect** button


### Notes ###
* Feedbacks: **esdmorais@gmail.com**
* I used spring boot, H2 database and Hibernate to save some time;
* I used TDD to develop most of parts. Almost all the relevant services and repositories are tested;
* I splitted my project into pieces to help me to organize what shoud I do first, ordering by relevance:

```
- create project using spring initializr OK
- configure swagger OK
- run at least one time to see if it's working OK

Cousine OK!
01) GET /api/v1/Cousine OK
02) GET /api/v1/Cousine/search/{searchText} OK
06) GET /api/v1/Cousine/{cousineId}/stores OK

Store OK!
03) GET /api/v1/Store OK
04) GET /api/v1/Store/search/{searchText} OK
05) GET /api/v1/Store/{storeId} OK
10) GET /api/v1/Store/{storeId}/products OK

Product OK!
07) GET /api/v1/Product OK
08) GET /api/v1/Product/search/{searchText} OK
09) GET /api/v1/Product/{productId} OK

Order
13) GET /api/v1/Order/{orderId} OK (not tested)
12) POST /api/v1/Order (products are not persisting correctly and there is not test for prices and totals)
15) GET /api/v1/Order/customer 

Customer
14) POST /api/v1/Customer/auth (100 minutes!)
11) POST /api/v1/Customer OK (need to response the auth token)
```



