# Designs API

## Get All Designs by Param

+ Endpoint : ``/api/designs``
+ HTTP Method : `GET`
+ Auth : Required
+ Request Param :
  + Title
  + page
  + itemPerPage
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": [{
    "id" : "DSGN_MEN'_0001",
    "title": "Men's Suits",
    "image": null,
    "price": 500000.0,
    "discount": 0.0,
    "active": true
  },
  {
    "id" : "DSGN_MEN'_0002",
    "title": "Men's Suits",
    "image": null,
    "price": 500000.0,
    "discount": 0.0,
    "active": true
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
    "id" : "DSGN_MEN'_0001",
    "title": "Men's Suits",
    "image": null,
    "price": 500000.0,
    "discount": 0.0,
    "tailorId": "UUID",
    "tailorName": "Steven",
    "description": "Anything",
    "size": [{
      "name": "S",
      "detail": {
        "chest": 120.0,
        "waist": 120.0,
        "hips": 120.0,
        "neckToWaist": 120.0,
        "inseam": 120.0
      }
    }, {
      "name": "M",
      "detail": {
        "chest": 140.0,
        "waist": 140.0,
        "hips": 140.0,
        "neckToWaist": 140.0,
        "inseam": 140.0
      }
    }],
    "color": [{
      "name": "Navy",
      "color": "#123456"
    }, {
      "name": "Black",
      "color": "#000000"
    }],
    "category": null,
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

## Delete Design by Id

+ Endpoint : ``/api/tailors/{tailorId}/designs/{id}``
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
