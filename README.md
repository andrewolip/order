# ORDER API ( SpringBoot + Docker + Kafka + PostgreSQL )

It manages the orders received from ExternalProductA, calculates the total price of the products within each order, and then produces a new message to ExternalProductB.

# JDK 21.0.5 temurin

# Kafka UI can be accessed by
http://localhost:8090/ui

# PgAdmin
http://localhost:5050/

# Json sample to be consumed by Kafka
[
   {
      "orderId":1,
      "customer":"Customer1",
      "status":"PENDING",
      "products":[
         {
            "productId":1,
            "name":"Food",
            "category":"Book",
            "price":100,
            "quantity":10
         },
         {
            "productId":2,
            "name":"Java",
            "category":"Book",
            "price":10,
            "quantity":10
         }
      ]
   },
   {
      "orderId":1,
      "customer":"Customer1",
      "status":"PENDING",
      "products":[
         {
            "productId":1,
            "name":"Food",
            "category":"Book",
            "price":100,
            "quantity":10
         },
         {
            "productId":2,
            "name":"Java",
            "category":"Book",
            "price":10,
            "quantity":10
         }
      ]
   },
   {
      "orderId":2,
      "customer":"Customer2",
      "status":"PENDING",
      "products":[
         {
            "productId":1,
            "name":"Food",
            "category":"Book",
            "price":100,
            "quantity":5
         },
         {
            "productId":2,
            "name":"Java",
            "category":"Book",
            "price":10,
            "quantity":10
         }
      ]
   }
]
  
]
