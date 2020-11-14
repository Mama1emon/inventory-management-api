# SimpleInventoryManagementAPI
## Rest API with Spring Framework (H2, Spring Data)  
Write a simple inventory management micro-service
## Тестовое задание:
### Product details:
* Name
* Brand
* Price
* Quantity
### URL
  - `/add + {JSON request body}` Enter new product  
  Example: /add +  
  {  
    "name": "Classic",
    "brand": "Adidas",  
    "price": "3000",  
    "quantity": "1"  
  }  
  - `/get?name=*&brand=*` Find product by name/brand  
  Example: /get?brand=Nike or /get?name=ZIG&brand=Reebok
  - `/update?name=*&brand=* + {request body}` Update/remove product  
  Example: /update?name=YEEZY&brand=Adidas +  
  {  
  "quantity": 3  
  }  
  - `/get/leftovers` Get all leftovers(leftovers means product which quantity is less than 5)
  
  SQL script: data.sql
