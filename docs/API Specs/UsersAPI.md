# Users API

## Sign Up

+ Endpoint : ``/api/users/_sign-up``
+ HTTP Method : `POST`
+ Request Body :

```json
{
  "name" : "Syntia",
  "email" : "syntia@gmail.com",
  "password": "passworedq13213",
  "birthDate" : "2020-03-28",
  "gender": "female"
}
```

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK"
}
```

+ Response Body (Fail) :

```json
{
  "code": 400,
  "status": "BAD_REQUEST",
  "errors": {
    "password": [
      "Required", "InvalidFormat"
    ],
    "username": [
      "Required", "InvalidFormat", "NotAvailable"
    ],
  },
}
```

## Log In

+ Endpoint : ``/api/users/_login``
+ HTTP Method : ``POST``
+ Request Body :

```json
{
  "username": "example@mail.com",
  "password": "123456"
}
```

+ Request Header :
  + Accept : ``application/json``
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "token": {
      "access": "b3912854-5bc2-46a8-b57a-8828daf395f6",
      "refresh": "b3912854-5bc2-46a8-b57a-8828daf395f6"
    }
  }
}
```

+ Response Body (Fail) :

```json
{
  "code": 401,
  "status": "UNAUTHORIZED"
}
```

## Get Access Token

+ Endpoint : ``/api/users/_refresh-token``
+ HTTP Method : ``POST``
+ Request Body :

```json
{
  "refreshToken": "b3912854-5bc2-46a8-b57a-8828daf395f6"
}
```

+ Request Header :
  + Accept : ``application/json``
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "token": {
      "access": "b3912854-5bc2-46a8-b57a-8828daf395f6",
      "refresh": "b3912854-5bc2-46a8-b57a-8828daf395f6"
    }
  }
}
```

+ Response Body (Fail) :

```json
{
  "code": 401,
  "status": "UNAUTHORIZED"
}
```

## Get Firebase Token

+ Endpoint : ``/api/users/_get-firebase-token``
+ HTTP Method : ``GET``
+ Request Header :
  + Accept : ``application/json``
+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "token": "dMqNCIBjCEk:APA91bEq-0KM_ES1EfiYyOmw7KhcqksxuDVJc-zfKLhs-cYmMEJvlKDASY-24-4ySZToYsQ-
    K6106wJ5KSNIocNLE35Jejce23RZp29vToDriu01lI1-UEW6xLbbDuAVkqsmd76zgib5"
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

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": 404,
  "errorMessage": "Not found: Cannot found user with id {id}",
  "success": false
}
```

## Get User by Id

+ Endpoint : ``/api/users/{id}``
+ HTTP Method : `GET`
+ Required : Auth
+ Path Variable :
  + id
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id" : "UUID",
    "name" : "Siapa",
    "image": "user/UUID.png",
    "location": {
      "address": "Jl. Demak No. 5G/E",
      "distinct": "Medan Area",
      "city": "Medan",
      "province": "Sumatera Utara",
      "country": "Indonesia",
      "postCode": 20214
    },
    "occupation": {
      "company": "",
      "city": "",
      "job": "",
    },
    "education": {
      "school": "",
      "major": "",
      "city": "",
    }
  }
}
```

+ Response Body (Fail) :

```json
{
  "code": 401,
  "status": "UNAUTHORIZED"
}
```

```json
{
  "code": 404,
  "status": "NOT_FOUND"
}
```

## Get User by Phone Number

+ Endpoint : ``/user/phoneNumber/{phoneNumber}``
+ HTTP Method : `GET`
+ Path Variable :
  + phoneNumber
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "content": {
    "id" : "STE_12001",
    "name" : "Steven R",
    "email" : "stevenR@gmail.com",
    "phoneNumber" : "+6281990333364",
    "birthDate" : "1999-10-15",
    "image": "user/STE_12001.png",
    "role" : "Tailor",
    "gender": "male",
    "address": "Jl. Demak No. 5G/E, Medan Area, Kota Medan, Sumatera Utara 20214",
    "location": "Kota Medan, Indonesia",
    "occupation": {
      "company": "",
      "city": "",
      "job": "",
    },
    "education": {
      "school": "",
      "major": "",
      "city": "",
    },
    "designs": []
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

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": 404,
  "errorMessage": "Not Found: Cannot find user with role Tailor.",
  "success": false
}
```

## Edit User by Id

+ Endpoint : ``/user/{id}/edit``
+ HTTP Method : `PUT`
+ Path Variable :
  + id
+ Request Body :

```json
{
  "name" : "Steven R",
  "email" : "stevenR@gmail.com",
  "phoneNumber" : "+6281990333364",
  "birthDate" : "1999-10-15",
  "image": "user/STE_12001.png",
  "role" : "Tailor",
  "gender": "male",
  "address": "Jl. Demak No. 5G/E, Medan Area, Kota Medan, Sumatera Utara 20214",
  "location": "Kota Medan, Indonesia",
  "occupation": {
    "company": "",
    "city": "",
    "job": "",
  },
  "education": {
    "school": "",
    "major": "",
    "city": "",
  },
  "designs": []
}
```

+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
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
  "errorMessage": "Bad Request: Duplicate data.",
  "success": false
}
```

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
  "errorMessage": "Not Found: Cannot find user with id STE-0001.",
  "success": false
}
```
