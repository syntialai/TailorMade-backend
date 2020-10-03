# Users API

## Get Users by Role

+ Endpoint : ``/users/{role}``
+ HTTP Method : `GET`
+ Path Variable :
  + role
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
  "content": [{
    "id" : "SYN_0001",
    "name" : "Syntia",
    "email" : "syntia@gmail.com",
    "phoneNumber" : "+6281990333364",
    "birthDate" : "2020-03-28",
    "image": "user/SYN_0001.png",
    "role" : "Customer",
    "gender": "female",
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
    "designs": null
  }, {
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
  }]
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

## Get User by Id

+ Endpoint : ``/user/{id}``
+ HTTP Method : `GET`
+ Path Variable :
  + id
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
    "designs": [{
      "id" : "STE_12001_MEN_0001",
      "image": "design/STE_12001_MEN_0001.png",
    }]
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
