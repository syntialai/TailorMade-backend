# Orders API

## Get Orders by User

+ Endpoint : ``/api/users/{userId}/orders``
+ HTTP Method : `GET`
+ Auth : Required
+ Path Variable :
  + userId
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": [{
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "tailorId": "STE_12001",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.00,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    },
    "status": "ACCEPTED"
  }, {
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "tailorId": "STE_12001",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.00,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    },
    "status": "REJECTED"
  }, {
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "tailorId": "STE_12001",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.00,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    },
    "status": "CANCELLED"
  }],
  "paging": {
    "page": 1,
    "itemPerPage": 10,
    "totalPage": 1,
    "totalItem": 1
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

## Get Orders by User and by Status

+ Endpoint : ``/api/users/{userId}/orders``
+ HTTP Method : `GET`
+ Auth : Required
+ Path Variable :
  + userId
+ Request Param :
  + status
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": [{
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "tailorId": "STE_12001",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "specialInstructions": "",
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 50000.00,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    },
    "status": "PENDING"
  }],
  "paging": {
    "page": 1,
    "itemPerPage": 10,
    "totalPage": 1,
    "totalItem": 1
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

## Get Order by Id

+ Endpoint : ``/api/users/{userId}/orders/{id}``
+ HTTP Method : `GET`
+ Auth : Required
+ Path Variable :
  + id
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
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

## Add Order by User

+ Endpoint : ``/api/users/{userId}/orders``
+ HTTP Method : `POST`
+ Auth : Required
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
  "code": 404,
  "status": "NOT_FOUND"
}
```

## Accept Order by Tailor and by Id

+ Endpoint : ``/api/tailors/{tailorId}/orders/{id}/_accept``
+ HTTP Method : `PUT`
+ Auth : Required
+ Path Variable :
  + tailorId
  + id
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
  "code": 404,
  "status": "NOT_FOUND"
}
```

## Reject Order by Tailor and by Id

+ Endpoint : ``/api/tailors/{tailorId}/orders/{id}/_reject``
+ HTTP Method : `PUT`
+ Auth : Required
+ Path Variable :
  + tailorId
  + id
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
  "code": 404,
  "status": "NOT_FOUND"
}
```

## Delete Order by Id

+ Endpoint : ``/api/tailors/{tailorId}/orders/{id}``
+ HTTP Method : `DELETE`
+ Path Variable :
  + tailorId
  + id
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
