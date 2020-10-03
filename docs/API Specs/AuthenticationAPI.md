# Authentication API

## Sign Up

+ Endpoint : ``/user/sign-up``
+ HTTP Method : `POST`
+ Request Body :

```json
{
  "name" : "Syntia",
  "email" : "syntia@gmail.com",
  "phoneNumber" : "+6281990333364",
  "birthDate" : "2020-03-28"
}
```

+ Request Header :
  + Accept: `application/json`
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true
}
```

+ Response Body (Fail) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": 400,
  "errorMessage": "Bad Request: Invalid request format.",
  "success": false
}
```

## Get Verification Code

+ Endpoint : ``/verify-phone``
+ HTTP Method : ``POST``
+ Request Body :

```json
{
  "phoneNumber": "+6281990333364",
}
```

+ Request Header :
  + Accept : ``application/json``
+ Response Body (Success) :

```json
{
  "verificationCode": "123456",
  "expiresIn": 16783
}
```

+ Response Body (Fail) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": 400,
  "errorMessage": "Bad Request: Invalid number format",
  "success": false
}
```

## Log In

+ Endpoint : ``/login``
+ HTTP Method : ``POST``
+ Request Body :

```json
{
  "phoneNumber": "+6281990333364",
  "verificationCode": "123456",
}
```

+ Request Header :
  + Accept : ``application/json``
+ Response Body (Success) :

```json
{
  "accessToken": "b3912854-5bc2-46a8-b57a-8828daf395f6",
  "tokenType": "bearer",
  "expiresIn": 16783,
  "scope": "read write trust"
}
```

+ Response Body (Fail) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": 401,
  "errorMessage": "Unauthorized: You are not allowed to access.",
  "success": false
}
```
