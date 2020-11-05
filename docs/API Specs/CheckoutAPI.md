# Checkout API (For Users)

## Get Checkout Detail by User

+ Endpoint : ``/api/wishlists/{id}/_checkout``
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
  "totalPrice": 50000.0,
  "totalDiscount": 0.0,
  "measurement": [{
    "chest": 120,
    "waist": 120,
    "hips": 120,
    "neckToWaist": 120,
    "inseam": 120
  }],
  "specialInstructions": "",
  "design": {
    "id" : "STE_12001_MEN_0001",
    "title": "Men's Suits",
    "image": "design/STE_12001_MEN_0001.png",
    "price": 50000.00,
    "discount": 0.0,
    "tailorId": "STE_12001",
    "tailorName": "Steven",
    "size": "S",
    "color": "Navy"
  }
}
```

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id" : "1234567890",
    "userId": "SYN_0001",
    "tailorId": "STE_12001",
    "quantity": 1,
    "totalPrice": 500000.0,
    "totalDiscount": 0.0,
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
