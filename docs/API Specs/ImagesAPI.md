# Upload Image to Cloud Bucket API (For Tailors)

## Upload Image

+ Endpoint : ``/api/upload/{filePath}``
+ HTTP Method : `POST`
+ Auth : Required
+ Path Variable :
  + filePath
+ Request Header :
  + Accept: `multipart/form-data`
  + Content-Type: `multipart/form-data`
+ Request Part :
  + file
+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "imageUrl": null
  }
}
```

```json
{
  "code": 401,
  "status": "UNAUTHORIZED"
}
```
