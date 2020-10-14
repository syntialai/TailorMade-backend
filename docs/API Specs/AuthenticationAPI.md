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
  "value": "eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczovL2lkZW50aXR5dG9vbGtpdC5nb29nbGVhcGlzLmNvbS9nb29nbGUuaWRlbnRpdHkuaWRlbnRpdHl0b29sa2l0LnYxLklkZW50aXR5VG9vbGtpdCIsImNsYWltcyI6eyJzZWxsZXJJZCI6ImFkN2NhZWYzLWMyOGEtNDViNS1iZjJkLTFmOWE1NDdjYzYxYiIsInN0b3JlTmFtZSI6IlRva28gUUEgQ00iLCJzd"
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
