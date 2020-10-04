# Carts API

## Get Cart Items by User

+ Endpoint : ``/user/{userId}/cart``
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
    "totalPrice": 50000,
    "totalDiscount": 0,
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
      "price": 500000,
      "discount": 0,
      "tailorId": "STE_12001",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    }
  }, {
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "userName": "Syntia",
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "quantity": 1,
    "totalPrice": 50000,
    "totalDiscount": 0,
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
      "price": 500000,
      "discount": 0,
      "tailorId": "STE_12001",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    }
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

## Get Cart Item by Id

+ Endpoint : ``/user/{userId}/cart/{id}``
+ HTTP Method : `GET`
+ Path Variable :
  + userId
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
    "totalPrice": 50000,
    "totalDiscount": 0,
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
      "price": 500000,
      "discount": 0,
      "tailorId": "STE_12001",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    }
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

## Add Cart Item by User

+ Endpoint : ``/user/{userId}/cart/add``
+ HTTP Method : `POST`
+ Path Variable :
  + userId
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Body :

```json
{
  "userName": "Syntia",
  "tailorId": "STE_12001",
  "tailorName": "Steven",
  "quantity": 1,
  "totalPrice": 50000,
  "totalDiscount": 0,
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
    "price": 500000,
    "discount": 0,
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "size": "S",
    "color": "Navy"
  }
}
```

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

## Add Cart Item Quantity by User and by Id

+ Endpoint : ``/user/{userId}/cart/{id}/add``
+ HTTP Method : `PUT`
+ Path Variable :
  + userId
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

## Reduce Cart Item Quantity by User and by Id

+ Endpoint : ``/user/{userId}/cart/{id}/reduce``
+ HTTP Method : `PUT`
+ Path Variable :
  + userId
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

## Delete Cart Item by Id

+ Endpoint : ``/user/{userId}/cart/{id}/delete``
+ HTTP Method : `DELETE`
+ Path Variable :
  + userId
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
