# ORDER API ( SpringBoot and Kafka )

It manages the orders received from ExternalProductA, calculates the total price of the products within each order, and then produces a new message to ExternalProductB.


# Json sample to be consumed by kafka

[
  {
		"orderId": 1,
  	"customer": "Customer1",
  	"status": "PENDING",
  	"products": [
  		{
				"productId": 1,
  			"name": "Food",
  			"category": "Book",
  			"price": 100,
  			"quantity": 10
  		},
  		{
				"productId": 2,
  			"name": "Java",
  			"category": "Book",
  			"price": 10,
  			"quantity": 10
  		}
  	]
   },
	{
		"orderId": 1,
  	"customer": "Customer1",
  	"status": "PENDING",
  	"products": [
  		{
				"productId": 1,
  			"name": "Food",
  			"category": "Book",
  			"price": 100,
  			"quantity": 10
  		},
  		{
				"productId": 2,
  			"name": "Java",
  			"category": "Book",
  			"price": 10,
  			"quantity": 10
  		}
  	]
   },
   {
		"orderId": 2,
  	"customer": "Customer2",
  	"status": "PENDING",
  	"products": [
  		{
				"productId": 1,
  			"name": "Food",
  			"category": "Book",
  			"price": 100,
  			"quantity": 5
  		},
  		{
				"productId": 2,
  			"name": "Java",
  			"category": "Book",
  			"price": 10,
  			"quantity": 10
  		}
  	]
   }
  
]
