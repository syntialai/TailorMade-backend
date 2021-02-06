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
    "id" : "WSLT_MEN'_0001",
    "createdAt": 1611669999000,
    "updatedAt": 1611669999000,
    "userId": "UUID",
    "tailorId": "UUID",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "design": {
      "id" : "DSGN_MEN'_0001",
      "title": "Men's Suits",
      "image": null,
      "price": 50000.0,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    },
    "status": "Accepted"
  }, {
    "id" : "WSLT_MEN'_0001",
    "createdAt": 1611669999000,
    "updatedAt": 1611669999000,
    "userId": "UUID",
    "tailorId": "UUID",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "design": {
      "id" : "DSGN_MEN'_0001",
      "title": "Men's Suits",
      "image": null,
      "price": 50000.00,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    },
    "status": "Rejected"
  }, {
    "id" : "WSLT_MEN'_0001",
    "createdAt": 1611669999000,
    "updatedAt": 1611669999000,
    "userId": "UUID",
    "tailorId": "UUID",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "design": {
      "id" : "DSGN_MEN'_0001",
      "title": "Men's Suits",
      "image": null,
      "price": 50000.00,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    },
    "status": "Incoming"
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
    "id" : "WSLT_MEN'_0001",
    "createdAt": 1611669999000,
    "updatedAt": 1611669999000,
    "userId": "UUID",
    "tailorId": "UUID",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "specialInstructions": "",
    "design": {
      "id" : "DSGN_MEN'_0001",
      "title": "Men's Suits",
      "image": null,
      "price": 50000.0,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    },
    "status": "Incoming"
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
    "id" : "WSLT_MEN'_0001",
    "createdAt": 1611669999000,
    "updatedAt": 1611669999000,
    "userId": "UUID",
    "userName": "Syntia",
    "tailorId": "UUID",
    "tailorName": "Steven",
    "quantity": 1,
    "totalPrice": 50000.0,
    "totalDiscount": 0.0,
    "measurement": [{
      "chest": 120.0,
      "waist": 120.0,
      "hips": 120.0,
      "neckToWaist": 120.0,
      "inseam": 120.0
    }],
    "specialInstructions": "",
    "design": {
      "id" : "DSGN_MEN'_0001",
      "title": "Men's Suits",
      "image": null,
      "price": 50000.0,
      "discount": 0.0,
      "tailorId": "UUID",
      "tailorName": "Steven",
      "size": "S",
      "color": "Navy"
    },
    "status": "Rejected"
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
  "userId": "UUID",
  "userName": "Syntia",
  "tailorId": "UUID",
  "tailorName": "Steven",
  "quantity": 1,
  "totalPrice": 50000.0,
  "totalDiscount": 0.0,
  "measurements": [{
    "chest": 120.0,
    "waist": 120.0,
    "hips": 120.0,
    "neckToWaist": 120.0,
    "inseam": 120.0
  }],
  "specialInstructions": "",
  "design": {
    "id" : "DSGN_MEN'_0001",
    "title": "Men's Suits",
    "image": null,
    "price": 50000.00,
    "discount": 0.0,
    "tailorId": "UUID",
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
