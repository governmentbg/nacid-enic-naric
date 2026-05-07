#Core clients creation
Whenever new client must be created, developer must add a Base client. This Base client must hold the method signatures and the proper annotations on the methods and the parameters, etc.

Then, depending on the needs, developer can create a client that is to be used with authenticated user's access token or with client credentials access token.

1. For creation of client that uses authenticated user's access token

* Name the client as \<Something>Client
* Extend the Base client
* In the @FeignClient annotation add the following

  
    configuration = SecContextFeignConfig.class

2. For creation of client that uses client credentials access token

* Name the client as Admin\<Something>Client
* Extend the Base client
* In the @FeignClient annotation add the following


    configuration = ClientTokenFeignConfig.class

#Core clients usages

1. Using core clients with currently authenticated user's access token

To use them in this case, developer must use the clients that do not have Admin in their names. For example: 
* CountriesClient
* FileStoreClient
* etc.

Of course, this is used with oauth2 security

2. Using core clients with client credentials access token

To use such core clients use Admin<Something.>Client classes since they will get the access token using the TokenManager

For example:
* AdminCountriesClient
* etc.

For this usage it is required that the app has proper configuration imported (TokenManagerConfig)

