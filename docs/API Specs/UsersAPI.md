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
    "email": [
      "Required", "Email", "NotAvailable"
    ],
    "name": [
      "Required", "InvalidFormat"
    ],
    "password": [
      "Required", "InvalidFormat"
    ],
    "birthDate": [
      "Required", "InvalidFormat"
    ],
    "gender": [
      "Required"
    ]
  },
}
```

## Sign In

+ Endpoint : ``/api/users/_sign-in``
+ HTTP Method : ``POST``
+ Request Body :

```json
{
  "username": "example@mail.com",
  "password": "123456"
}
```

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
+ Auth : Required
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
  "code": 401,
  "status": "UNAUTHORIZED"
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

## Edit User Basic Information by Id

+ Endpoint : ``/api/users/{id}/_update-basic-info``
+ HTTP Method : `PUT`
+ Auth : Required
+ Path Variable :
  + id
+ Request Body :

```json
{
  "name" : "Steven R",
  "phoneNumber" : "+6281990333364",
  "birthDate" : "1999-10-15",
  "location": {
    "address": "Jl. Demak No. 5G/E",
    "distinct": "Medan Area",
    "city": "Medan",
    "province": "Sumatera Utara",
    "country": "Indonesia",
    "postCode": 20214
  }
}
```

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "name" : "Steven R",
    "phoneNumber" : "+6281990333364",
    "birthDate" : "1999-10-15",
    "location": {
      "address": "Jl. Demak No. 5G/E",
      "distinct": "Medan Area",
      "city": "Medan",
      "province": "Sumatera Utara",
      "country": "Indonesia",
      "postCode": 20214
    }
  }
}
```

+ Response Body (Fail) :

```json
{
  "code": 400,
  "status": "BAD_REQUEST",
  "errors": {
    "phoneNumber": [
      "Required", "InvalidFormat"
    ],
    "name": [
      "Required", "InvalidFormat"
    ],
    "birthDate": [
      "Required", "InvalidFormat"
    ],
    "location": [
      "Required"
    ]
  }
}
```

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

## Edit User Additional Information by Id

+ Endpoint : ``/api/users/{id}/_update-more-info``
+ HTTP Method : `PUT`
+ Auth : Required
+ Path Variable :
  + id
+ Request Body :

```json
{
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
```

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
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

## Sign Out

+ Endpoint : ``/api/users/_sign-out``
+ HTTP Method : ``POST``
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
  "code": 401,
  "status": "UNAUTHORIZED"
}
```

```json
{
  "code": 500,
  "status": "INTERNAL_SERVER_ERROR"
}
```
