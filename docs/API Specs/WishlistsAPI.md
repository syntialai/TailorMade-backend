# Wishlists API

## Get Wishlist Items by User

+ Endpoint : ``/api/wishlists``
+ HTTP Method : `GET`
+ Auth : Required
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
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 500000.0,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    }
  }, {
    "id" : "1234567890",
    "createdAt": "2019-08-23T04:22:26.690+0000",
    "updatedAt": "2019-08-23T04:22:26.690+0000",
    "userId": "SYN_0001",
    "tailorId": "STE_12001",
    "quantity": 1,
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 500000.0,
      "discount": 0.0,
      "size": "S",
      "color": "Navy"
    }
  }]
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

+ Endpoint : ``/api/wishlists/{id}``
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
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": "design/STE_12001_MEN_0001.png",
      "price": 500000.0,
      "discount": 0.0,
      "size": "S",
      "sizeDetail": {
        "chest": 120,
        "waist": 120,
        "hips": 120,
        "neckToWaist": 120,
        "inseam": 120
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

+ Endpoint : ``/api/wishlists``
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
  "design": {
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "design/STE_12001_MEN_0001.png",
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
  "code": 201,
  "status": "CREATED"
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
        "NotBlank", "InvalidFormat"
      ],
      "image": [
        "NotBlank", "InvalidType"
      ],
      "price": [
        "NotBlank"
      ],
      "size": [
        "NotBlank"
      ],
      "color": [
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

+ Endpoint : ``/api/wishlists/{id}/_edit-quantity``
+ HTTP Method : `PUT`
+ Auth : Required
+ Path Variable :
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
      "NotBlank", "InvalidFormat"
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

+ Endpoint : ``/api/wishlists/{id}``
+ HTTP Method : `DELETE`
+ Auth : Required
+ Path Variable :
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
