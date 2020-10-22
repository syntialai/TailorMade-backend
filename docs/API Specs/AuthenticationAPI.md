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
  "birthDate" : "2020-03-28",
  "role": "user",
  "gender": "female"
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
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "content": {
    "verificationCode": "123456",
    "expiresIn": 16783
  }
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
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "content": {
    "accessToken": "b3912854-5bc2-46a8-b57a-8828daf395f6",
    "tokenType": "bearer",
    "expiresIn": 16783,
    "scope": "read write trust"
  }
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

## Get Firebase Token

+ Endpoint : ``/3p/token``
+ HTTP Method : ``GET``
+ Request Header :
  + Accept : ``application/json``
+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "value": "dMqNCIBjCEk:APA91bEq-0KM_ES1EfiYyOmw7KhcqksxuDVJc-zfKLhs-cYmMEJvlKDASY-24-4ySZToYsQ-
  K6106wJ5KSNIocNLE35Jejce23RZp29vToDriu01lI1-UEW6xLbbDuAVkqsmd76zgib5"
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

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": 404,
  "errorMessage": "Not found: Cannot found user with id {id}",
  "success": false
}
```
