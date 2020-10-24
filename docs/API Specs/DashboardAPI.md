# Dashboard API (For Users)

## Get Tailors

Based on proximity with user

+ Endpoint : ``/api/dashboard/tailors``
+ HTTP Method : `GET`
+ Required: Auth
+ Request Param :
  + lat
  + lon
  + page
  + itemPerPage
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": [{
    "id": "UUID",
    "name": "Name",
    "image": "",
    "location": {
      "city": "Medan",
      "country": "Indonesia"
    },
    "designs": [{
      "id": "NAM_DES_0001_0001",
      "image": "/UUID/NAM_DES_0001_0001.jpg",
    }, {
      "id": "NAM_DES_0001_0002",
      "image": "/UUID/NAM_DES_0001_0002.jpg",
    }],
  },
  {
    "id": "UUID",
    "name": "Name",
    "image": "",
    "location": {
      "city": "Medan",
      "country": "Indonesia"
    },
    "designs": [{
      "id": "NAM_DES_0001_0001",
      "image": "/UUID/NAM_DES_0001_0001.jpg",
    }, {
      "id": "NAM_DES_0001_0002",
      "image": "/UUID/NAM_DES_0001_0002.jpg",
    }],
  }],
  "paging": {
    "page": 1,
    "itemPerPage": 10,
    "totalPage": 50
  }
}
```
