# Wishlists API

## Get Wishlist Items by User

+ Endpoint : ``/api/user/{userId}/wishlists``
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
    "id" : "WSLT_USER_0001",
    "createdAt": 1611669999000,
    "updatedAt": 1611669999000,
    "userId": "UUID",
    "tailorId": "UUID",
    "quantity": 1,
    "design": {
      "id" : "DSGN_MEN_0001",
      "title": "Men's Suits",
      "image": null,
      "price": 500000.0,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    }
  }, {
    "id" : "WSLT_USER_0002",
    "createdAt": 1611669999000,
    "updatedAt": 1611669999000,
    "userId": "UUID",
    "tailorId": "UUID",
    "quantity": 1,
    "design": {
      "id" : "DSGN_MEN_0002",
      "title": "Men's Suits",
      "image": null,
      "price": 500000.0,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    }
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

## Get Wishlists Item by Id

+ Endpoint : ``/api/user/{userId}/wishlists/{id}``
+ HTTP Method : `GET`
+ Auth : Required
+ Path Variable :
  + userId
  + id
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id" : "WSLT_USER_0001",
    "createdAt": 1611669999000,
    "updatedAt": 1611669999000,
    "userId": "UUID",
    "userName": "useria",
    "tailorId": "UUID",
    "tailorName": "Steven",
    "quantity": 1,
    "design": {
      "id" : "DSGN_MEN_0001",
      "title": "Men's Suits",
      "image": null,
      "price": 500000.0,
      "discount": 0.0,
      "size": "S",
      "sizeDetail": {
        "chest": 120.0,
        "waist": 120.0,
        "hips": 120.0,
        "neckToWaist": 120.0,
        "inseam": 120.0
      },
      "color": "Navy"
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

## Add Wishlists Item by User

+ Endpoint : ``/api/user/{userId}/wishlists``
+ HTTP Method : `POST`
+ Auth : Required
+ Path Variable :
  + userId
+ Request Body :

```json
{
  "userId": "UUID",
  "userName": "User",
  "tailorId": "UUID",
  "tailorName": "Steven",
  "quantity": 1,
  "design": {
    "id" : "DSGN_MEN_0001",
    "title": "Men's Suits",
    "image": null,
    "price": 500000.0,
    "discount": 0.0,
    "size": "S",
    "color": "Navy"
  }
}
```

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "wishlistId": "WLST_USER_0001"
  }
}
```

+ Response Body (Fail) :

```json
{
  "code": 400,
  "status": "BAD_REQUEST",
  "errors": {
    "design": {
      "title": [
        "NotBlank"
      ],
      "image": [
        "NotBlank"
      ],
      "price": [
        "Positive"
      ],
      "discount": [
        "Positive"
      ],
      "description": [
        "NotBlank"
      ]
    }
  }
}

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

## Edit Wishlists Item Quantity by User and by Id

+ Endpoint : ``/api/user/{userId}/wishlists/{id}/_edit-quantity``
+ HTTP Method : `PUT`
+ Auth : Required
+ Path Variable :
  + userId
  + id
+ Request Body :

```json
{
  "quantity": 1
}
```

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id": "wishlistId",
    "quantity": 1
  }
}
```

+ Response Body (Fail) :

```json
{
  "code": 400,
  "status": "BAD_REQUEST",
  "errors": {
    "quantity": [
      "Positive"
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

## Delete Wishlists Item by Id

+ Endpoint : ``/api/user/{userId}/wishlists/{id}``
+ HTTP Method : `DELETE`
+ Auth : Required
+ Path Variable :
  + userId
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
