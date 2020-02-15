# e-Repair
  
  The android application 'e-Repair' is developed to fulfill the immediate service on demand of the people to make their life easier. Whenever an individual or a family needs a home service like plumbing, AC servicing and installation, CC camera installation and repair, automobiles repair, computer repairing, carpentering and other electronic appliance repairing, they can book the required service through this android application.
  
  The main problem in the today is to find a good service professional who can give a desired service at a desired time in best cost. Currently, these kinds of service are not in a proper management. It is hard to know where the service professional can be available, even they are near us. Some professional takes more service cost and some takes less for the same kind of service. So, there is price issue. Sometimes the professional does not come at all for the services even after informing. This mean there is trust issue too.
  
  e-Repair app will help to solve all the problems that are mentioned above. It will be focused on providing the best service in best time as fast as possible. People can choose for the service and book an appointment to get a quick, trusted and reliable services.
  

# Features:
### Register and login:
-   User can register and login to the system, which will be necessary to complete booking.

### Easy Browsing of service:
-   User can browse the repair services on different category.

### Booking/Cancel appointment:
-   User can be able to book the services as per their needs and they will be able to cancel the booked service too.

### View Booking:
-   User can view the status of booked services.

### Review:
-   User can give review to the service that they have booked.

### Edit profile and change password:
-   User can edit and update their own profile and can change the password.

### Request to become professional
-   If the user is service export and want to join, they can send the request to become a service professional through the ‘Become A Professional’ form.


# Links
### Youtube Link: _https://youtu.be/ZdbTjC5sKeU_
### API Link: _https://github.com/softwarica-github/t2-backend-api-bethebikash_


# Rest Client
### Rest Client in our case is Retrofit.
Retrofit is a REST Client library used in Android and Java to create an HTTP request and also to process the HTTP response from a REST API. We can use retrofit to receive data in json structures and other than JSON too, for example SimpleXML and Jackson.

### RESTful API
A RESTful API is an application program interface (API) that uses HTTP requests to GET, PUT, POST and DELETE data.

### Use of Retrofit
To work with Retrofit you need basically three classes.

- Model class which is used to map the JSON data to
- Interfaces which defines the possible HTTP operations
- Retrofit.Builder class - Instance which uses the interface and the Builder API which allows defining the URL end point for the HTTP operation.

Every method of an interface represents one possible API call. It must have a HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL. The return value wraps the response in a Call object with the type of the expected result.
```
@GET("users")
Call<List<User>> getUsers()
```

We can use replacement blocks and query parameters to adjust the URL. A replacement block is added to the relative URL with {}. With the help of the @Path annotation on the method parameter, the value of that parameter is bound to the specific replacement block.
```
@GET("users/{id}/projects")
Call<List<Projects>> getCommitsByName(@Path("id") String id)
```

Query parameters are added with the @Query annotation on a method parameter. They are automatically added at the end of the URL.
```
@GET("users")
Call<User> getUserByGender(@Query("gender") String gender)
```

The @Body annotation on a method parameter tells Retrofit to use the object as the request body for the call.
```
@POST("users")
Call<User> postUser(@Body User user)
```
