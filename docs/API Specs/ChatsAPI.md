# Chats API

## Get Chats

+ Endpoint : ``/api/chats``
+ HTTP Method : `GET`
+ Auth : Required
+ Request Param :
  + page
  + itemPerPage
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": [{
    "id": "ChatId",
    "userId": "STE_12001",
    "text": "Barang ready!",
    "createdBy": "Steven",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "updatedBy": "Steven",
    "updatedDate": "2020-10-05T08:30:23.279+00:00",
    "hasSeen": false
  }, {
    "id": "ChatId",
    "userId": "SYN_0001",
    "text": "Barang ready!",
    "createdBy": "Syntia",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "updatedBy": "Steven",
    "updatedDate": "2020-10-05T08:30:23.279+00:00",
    "hasSeen": true
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

## Get Unread Count from Chats

+ Endpoint : ``/api/chats``
+ HTTP Method : `GET`
+ Auth : Required
+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "unreadCount": 3
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

## Get Chats by User and by Id

+ Endpoint : ``/api/chats/{id}``
+ HTTP Method : `GET`
+ Auth : Required
+ Path Variable :
  + id
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": [{
    "text": "Barang ready!",
    "image": "",
    "createdBy": "Steven",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "hasSeen": false
  }, {
    "text": "Barang ready!",
    "image": "",
    "createdBy": "Syntia",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "hasSeen": true
  }],
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

## Add Chat by User

+ Endpoint : ``/api/chats``
+ HTTP Method : `POST`
+ Auth : Required
+ Request Body :

```json
{
  "userId": "UUID"
}
```

+ Response Body (Success) :

```json
{
  "code": 201,
  "status": "CREATED",
  "data": {
    "id": "ChatId",
    "userId": "SYN_0001",
    "createdBy": "Steven",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "updatedBy": "Steven",
    "updatedDate": "2020-10-05T08:30:23.279+00:00",
    "hasSeen": false
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

## Add Chat by User and by Id

+ Endpoint : ``/api/chats/{id}``
+ HTTP Method : `POST`
+ Auth : Required
+ Path Variable :
  + id
+ Request Body :

```json
{
  "text": "Barang ready"
}
```

+ Response Body (Success) :

```json
{
  "code": 201,
  "status": "CREATED",
  "data": {
    "text": "Barang ready!",
    "image": "",
    "createdBy": "Steven",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "hasSeen": false
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

## Delete Chat by User and by Id

+ Endpoint : ``/api/chats/{id}``
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
