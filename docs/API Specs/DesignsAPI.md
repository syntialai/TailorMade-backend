# Designs API

## Get All Designs by Param

+ Endpoint : ``/api/designs``
+ HTTP Method : `GET`
+ Auth : Required
+ Request Param :
  + Title
  + Category
  + Price
  + Location
  + page
  + itemPerPage
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": [{
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "UUID/STE_12001_MEN_0001.png",
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
  }],
  "paging": {
    "page": 1,
    "itemPerPage": 10,
    "totalPage": 50
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

## Get Design by Id

+ Endpoint : ``/api/designs/{id}``
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
