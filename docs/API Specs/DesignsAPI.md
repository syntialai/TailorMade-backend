# Designs API

## Get All Designs by Tailor

+ Endpoint : ``/tailor/{id}/designs``
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
  "content": [{
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "design/STE_12001_MEN_0001.png",
    "price": 500000.0,
    "discount": 0.0,
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "description": "",
    "size": [{
      "S": true,
      "detail": {
        "chest": 120,
        "waist": 120,
        "hips": 120,
        "neckToWaist": 120,
        "inseam": 120
      }
    }, {
      "M": false,
      "detail": {
        "chest": 140,
        "waist": 140,
        "hips": 140,
        "neckToWaist": 140,
        "inseam": 140
      }
    }],
    "color": [{
      "name": "Navy",
      "color": "#123456"
    }, {
      "name": "Black",
      "color": "#000000"
    }],
    "category": ["winter"],
    "active": true
  }, {
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "design/STE_12001_MEN_0001.png",
    "price": 500000.0,
    "discount": 0.0,
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "description": "",
    "size": [{
      "S": true,
      "detail": {
        "chest": 120,
        "waist": 120,
        "hips": 120,
        "neckToWaist": 120,
        "inseam": 120
      }
    }, {
      "M": false,
      "detail": {
        "chest": 140,
        "waist": 140,
        "hips": 140,
        "neckToWaist": 140,
        "inseam": 140
      }
    }],
    "color": [{
      "name": "Navy",
      "color": "#123456"
    }, {
      "name": "Black",
      "color": "#000000"
    }],
    "category": ["winter"],
    "active": true
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
  "errorMessage": "Not Found: Cannot find tailor with id STE_12001.",
  "success": false
}
```

## Get All Designs by Param

+ Endpoint : ``/design``
+ HTTP Method : `GET`
+ Request Header :
  + Accept: `application/json`
+ Request Param :
  + Title
  + Category
  + Price
  + Location
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "content": [{
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "design/STE_12001_MEN_0001.png",
    "price": 500000.0,
    "discount": 0.0,
    "active": true
  },
  {
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "design/STE_12001_MEN_0001.png",
    "price": 500000.0,
    "discount": 0.0,
    "active": true
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
  "errorMessage": "Not Found: Cannot find tailor with id STE_12001.",
  "success": false
}
```

## Get Design by Id

+ Endpoint : ``/design/{id}``
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
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "design/STE_12001_MEN_0001.png",
    "price": 500000.0,
    "discount": 0.0,
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "description": "",
    "size": [{
      "S": true,
      "detail": {
        "chest": 120,
        "waist": 120,
        "hips": 120,
        "neckToWaist": 120,
        "inseam": 120
      }
    }, {
      "M": false,
      "detail": {
        "chest": 140,
        "waist": 140,
        "hips": 140,
        "neckToWaist": 140,
        "inseam": 140
      }
    }],
    "color": [{
      "name": "Navy",
      "color": "#123456"
    }, {
      "name": "Black",
      "color": "#000000"
    }],
    "category": ["winter"],
    "active": true
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
  "errorMessage": "Not Found: Cannot find design with id STE_12001_MEN_0001.",
  "success": false
}
```

## Add Design by Tailor

+ Endpoint : ``/tailor/{id}/design/add``
+ HTTP Method : `POST`
+ Path Variable :
  + id
+ Request Body :

```json
{
  "title": "Men's Suits",
  "image": "design/STE_12001_MEN_0001.png",
  "price": 500000.0,
  "discount": 0.0,
  "tailorId": "STE_12001",
  "tailorName": "Steven",
  "description": "",
  "size": [{
    "S": true,
    "detail": {
      "chest": 120,
      "waist": 120,
      "hips": 120,
      "neckToWaist": 120,
      "inseam": 120
    }
  }, {
    "M": false,
    "detail": {
      "chest": 140,
      "waist": 140,
      "hips": 140,
      "neckToWaist": 140,
      "inseam": 140
    }
  }],
  "color": [{
    "name": "Navy",
    "color": "#123456"
  }, {
    "name": "Black",
    "color": "#000000"
  }],
  "category": ["winter"]
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
  "errorMessage": "Bad Request: Invalid Request Format.",
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
  "errorMessage": "Not Found: Cannot find tailor with id STE_12001.",
  "success": false
}
```

## Edit Design by Tailor and by Id

+ Endpoint : ``/tailor/{tailorId}/design/{id}/edit``
+ HTTP Method : `PUT`
+ Path Variable :
  + tailorId
  + id
+ Request Body :

```json
{
  "title": "Men's Suits",
  "image": "design/STE_12001_MEN_0001.png",
  "price": 500000.0,
  "discount": 0.0,
  "tailorId": "STE_12001",
  "tailorName": "Steven",
  "description": "",
  "size": [{
    "S": true,
    "detail": {
      "chest": 120,
      "waist": 120,
      "hips": 120,
      "neckToWaist": 120,
      "inseam": 120
    }
  }, {
    "M": false,
    "detail": {
      "chest": 140,
      "waist": 140,
      "hips": 140,
      "neckToWaist": 140,
      "inseam": 140
    }
  }],
  "color": [{
    "name": "Navy",
    "color": "#123456"
  }, {
    "name": "Black",
    "color": "#000000"
  }],
  "category": ["winter"]
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

## Delete Design by Tailor and by Id

+ Endpoint : ``/tailor/{tailorId}/design/{id}/delete``
+ HTTP Method : `DELETE`
+ Path Variable :
  + tailorId
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
  "errorMessage": "Not Found: Cannot find design with id STE-0001.",
  "success": false
}
```
