# Orders API

## Get Orders by User

+ Endpoint : ``/orders``
+ HTTP Method : `GET`
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "content": [{
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "userName": "Syntia",
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "measurement": [{
      "chest": 120,
      "waist": 120,
      "hips": 120,
      "neckToWaist": 120,
      "inseam": 120
    }],
    "specialInstructions": "",
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.00,
      "discount": 0.0,
      "tailorId": "STE_12001",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    },
    "status": "ACCEPTED"
  }, {
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "userName": "Syntia",
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "measurement": [{
      "chest": 120,
      "waist": 120,
      "hips": 120,
      "neckToWaist": 120,
      "inseam": 120
    }],
    "specialInstructions": "",
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.00,
      "discount": 0.0,
      "tailorId": "STE_12001",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    },
    "status": "REJECTED"
  }, {
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "userName": "Syntia",
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "measurement": [{
      "chest": 120,
      "waist": 120,
      "hips": 120,
      "neckToWaist": 120,
      "inseam": 120
    }],
    "specialInstructions": "",
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.00,
      "discount": 0.0,
      "tailorId": "STE_12001",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    },
    "status": "CANCELLED"
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

## Get Orders by User and by Status

+ Endpoint : ``/orders/{status}``
+ HTTP Method : `GET`
+ Path Variable :
  + category
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "content": [{
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "userName": "Syntia",
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "measurement": [{
      "chest": 120,
      "waist": 120,
      "hips": 120,
      "neckToWaist": 120,
      "inseam": 120
    }],
    "specialInstructions": "",
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.00,
      "discount": 0.0,
      "tailorId": "STE_12001",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    },
    "status": "PENDING"
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

## Get Order by Id

+ Endpoint : ``/order/{id}``
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
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "userName": "Syntia",
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "measurement": [{
      "chest": 120,
      "waist": 120,
      "hips": 120,
      "neckToWaist": 120,
      "inseam": 120
    }],
    "specialInstructions": "",
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.0,
      "discount": 0.0,
      "tailorId": "STE_12001",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    },
    "status": "REJECTED"
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

## Add Order by User

+ Endpoint : ``/order/add``
+ HTTP Method : `POST`
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Body :

```json
{
  "userId": "SYN_0001",
  "userName": "Syntia",
  "tailorId": "STE_12001",
  "tailorName": "Steven",
  "quantity": 1,
  "totalPrice": 50000.0,
  "totalDiscount": 0.0,
  "measurement": [{
    "chest": 120,
    "waist": 120,
    "hips": 120,
    "neckToWaist": 120,
    "inseam": 120
  }],
  "specialInstructions": "",
  "design": {
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "design/STE_12001_MEN_0001.png",
    "price": 50000.00,
    "discount": 0.0,
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "size": "S",
    "color": "Navy"
  }
}
```

+ Request Param :
  + userId
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

## Accept Order by Tailor and by Id

+ Endpoint : ``/order/{id}/accept``
+ HTTP Method : `PUT`
+ Path Variable :
  + id
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Param :
  + tailorId
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

## Reject Order by Tailor and by Id

+ Endpoint : ``/order/{id}/reject``
+ HTTP Method : `PUT`
+ Path Variable :
  + id
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Param :
  + tailorId
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

## Delete Order by Id

+ Endpoint : ``/order/{id}/delete``
+ HTTP Method : `DELETE`
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
