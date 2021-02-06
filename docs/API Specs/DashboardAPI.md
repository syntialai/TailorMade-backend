# Dashboard API (For Users)

## Get Tailors

+ Endpoint : ``/api/dashboard/tailors``
+ HTTP Method : `GET`
+ Required: Auth
+ Request Param :
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
    "image": null,
    "location": {
      "city": "Medan",
      "country": "Indonesia"
    },
    "designs": [{
      "id": "DSGN_NAME_0001",
      "title": "Name 1",
      "image": null
    }, {
      "id": "DSGN_NAME_0002",
      "title": "Name 2",
      "image": null
    }]
  },
  {
    "id": "UUID",
    "name": "Name",
    "image": null,
    "location": {
      "city": "Medan",
      "country": "Indonesia"
    },
    "designs": [{
      "id": "DSGN_NAME_0001",
      "title": "Name 1",
      "image": null
    }, {
      "id": "DSGN_NAME_0002",
      "title": "Name 2",
      "image": null
    }]
  }],
  "paging": {
    "page": 1,
    "itemPerPage": 10,
    "totalPage": 50
  }
}
```
