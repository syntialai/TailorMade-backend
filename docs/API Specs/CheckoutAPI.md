# Checkout API (For Users)

## Checkout Item by User

+ Endpoint : ``/api/user/{userId}/wishlists/{id}/_checkout``
+ HTTP Method : `POST`
+ Auth : Required
+ Request Body :

```json
{
  "specialInstructions": "",
  "measurements": {
    "chest": 120.0,
    "waist": 120.0,
    "hips": 120.0,
    "neckToWaist": 120.0,
    "inseam": 120.0
  }
}
```

+ Response Body (Success) :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id" : "ORDR_SYNT_0001",
    "userId": "UUID",
    "tailorId": "STE_12001",
    "quantity": 1,
    "totalPrice": 500000.0,
    "totalDiscount": 0.0,
    "design": {
      "id" : "STE_12001_MEN_0001",
      "title": "Men's Suits",
      "image": null,
      "price": 500000.0,
      "discount": 0.0,
      "size": "S",
      "color": "Navy",
      "tailorId": "UUID",
      "tailorName": "Steven"
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
