# Tailors API

## Get Tailor by Id

+ Endpoint : ``/api/tailors/{id}``
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
    "id" : "UUID",
    "name" : "Siapa",
    "image": "user/UUID.png",
    "location": {
      "address": "Jl. Demak No. 5G/E",
      "distinct": "Medan Area",
      "city": "Medan",
      "province": "Sumatera Utara",
      "country": "Indonesia",
      "postCode": 20214
    },
    "occupation": {
      "company": "",
      "city": "",
      "job": "",
    },
    "education": {
      "school": "",
      "major": "",
      "city": "",
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

## Get Tailor Designs by Tailor Id

+ Endpoint : ``/api/tailors/{id}/designs``
+ HTTP Method : `GET`
+ Auth : Required
+ Path Variable :
  + id
+ Request Param :
  + page
  + itemPerPage
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": [{
    "id": "NAM_DES_0001_0001",
    "title": "Design ku",
    "image": "/UUID/NAM_DES_0001_0001.jpg",
  }, {
    "id": "NAM_DES_0001_0002",
    "title": "Design mu",
    "image": "/UUID/NAM_DES_0001_0002.jpg"
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

## Get Tailor Designs by Tailor Id and by Design Id

+ Endpoint : ``/api/tailors/{tailorId}/designs/{id}``
+ HTTP Method : `GET`
+ Auth : Required
+ Path Variable :
  + id
  + tailorId
+ Request Param :
  + page
  + itemPerPage
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id": "NAM_DES_0001_0001",
    "title": "Design ku",
    "image": "UUID/NAM_DES_0001_0001.jpg",
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

## Add Design by Tailor

+ Endpoint : ``/api/tailors/{id}/designs``
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

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id": "NAM_DES_0001_0001",
    "title": "Design ku",
    "image": "UUID/NAM_DES_0001_0001.jpg",
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
  "code": 400,
  "status": "BAD_REQUEST",
  "errors": {
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
    ],
    "category": [
      "NotBlank", "InvalidFormat"
    ],
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

## Edit Design by Tailor and by Id

+ Endpoint : ``/api/tailors/{tailorId}/designs/{id}``
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

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id": "NAM_DES_0001_0001",
    "title": "Design ku",
    "image": "UUID/NAM_DES_0001_0001.jpg",
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
  "code": 400,
  "status": "BAD_REQUEST",
  "errors": {
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
    ],
    "category": [
      "NotBlank", "InvalidFormat"
    ],
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

## Delete Design by Tailor and by Id

+ Endpoint : ``/api/tailors/{tailorId}/designs/{id}``
+ HTTP Method : `DELETE`
+ Auth : Required
+ Path Variable :
  + tailorId
  + id
+ Request Header :
  + Accept: `application/json`
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
