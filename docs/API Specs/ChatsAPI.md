# CHats API

## Get Chats by User

+ Endpoint : ``/chats``
+ HTTP Method : `GET`
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "content": [{
    "id": "generate-id",
    "code": "d3b133d2-8293-49e3-9dee-722cd3becd62",
    "identifierType": "Tailor",
    "identifierCode": "STE_12001",
    "text": "Barang ready!",
    "userId": "STE_12001",
    "createdBy": "Steven",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "updatedBy": "Steven",
    "updatedDate": "2020-10-05T08:30:23.279+00:00",
    "hasSeen": false
  }, {
    "id": "generate-id",
    "code": "d3b133d2-8293-49e3-9dee-722cd3becd62",
    "identifierType": "User",
    "identifierCode": "SYN_0001",
    "text": "Barang ready!",
    "userId": "SYN_0001",
    "createdBy": "Syntia",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "updatedBy": "Steven",
    "updatedDate": "2020-10-05T08:30:23.279+00:00",
    "hasSeen": true
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
  "errorMessage": "Not Found: Cannot find chat with user id STE_12001.",
  "success": false
}
```

## Get Chats by User and by Id

+ Endpoint : ``/chats/{id}``
+ HTTP Method : `GET`
+ Path Variable :
  + id
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "content": [{
    "code": "d3b133d2-8293-49e3-9dee-722cd3becd62",
    "identifierType": "Tailor",
    "identifierCode": "STE_12001",
    "text": "Barang ready!",
    "userId": "SYN_0001",
    "createdBy": "Steven",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "updatedBy": "Steven",
    "updatedDate": "2020-10-05T08:30:23.279+00:00"
  }, {
    "code": "d3b133d2-8293-49e3-9dee-722cd3becd62",
    "identifierType": "Tailor",
    "identifierCode": "STE_12001",
    "text": "Barang ready!",
    "userId": "SYN_0001",
    "createdBy": "Syntia",
    "createdDate": "2020-10-05T08:29:01.809+00:00",
    "updatedBy": "Steven",
    "updatedDate": "2020-10-05T08:30:23.279+00:00"
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
  "errorMessage": "Not Found: Cannot find chat with user id STE_12001.",
  "success": false
}
```

## Add Chat by User

+ Endpoint : ``/chat``
+ HTTP Method : `POST`
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Body :

```json
{
  "userId": "SYN_0001"
}
```

+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "value": "generate-id"
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
  "errorMessage": "Not Found: Cannot find chat with user id STE_12001.",
  "success": false
}
```

## Add Chat by User and by Id

+ Endpoint : ``/chat/{id}``
+ HTTP Method : `POST`
+ Path Variable :
  + id
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Body :

```json
{
  "text": "Barang ready"
}
```

+ Request Param :
  + userId
+ Response Body (Success) :

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": null,
  "errorMessage": "",
  "success": true,
  "value": "Barang ready"
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
  "errorMessage": "Not Found: Cannot find chat with user id STE_12001.",
  "success": false
}
```

## Delete Chat by User and by Id

+ Endpoint : ``/chat/{id}``
+ HTTP Method : `DELETE`
+ Path Variable :
  + id
+ Request Header :
  + Accept: `application/json`
  + Authorization : `bearer b3912854-5bc2-46a8-b57a-8828daf395f6`
+ Request Param :
  + userId
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
  "errorCode": 401,
  "errorMessage": "Unauthorized: You are not allowed to access.",
  "success": false
}
```

```json
{
  "timestamp": "2019-08-23T04:22:26.690+0000",
  "errorCode": 404,
  "errorMessage": "Not Found: Cannot find chat with user id STE_12001.",
  "success": false
}
```
