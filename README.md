coupon-api
===================

This API returns a list of items that can be bought with a coupon, without exceeding its amount.

##Knapsack problem
The knapsack problem is a problem in combinatorial optimization: Given a set of items, each with a weight and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible. It derives its name from the problem faced by someone who is constrained by a fixed-size knapsack and must fill it with the most valuable items. [Wikipedia](https://en.wikipedia.org/wiki/Knapsack_problem)

The API premise is based on the knapsack problem but with a little twist: instead of take care of the value and weight, we have to worry about the item prices and also return the selected items.

### Usage 
Build the artifact and docker image run:
```
mvn clean install
```

Build a runnable java artifact without creating a docker image
```
mvn clean package
```

### Run docker image
```
docker run stefy421/coupon-api
```

## Generalities
The project includes:

- Java documentation into PROJECT_ROOT_PATH/javadoc.
- Swagger documentation `http://localhost:8080/swagger-ui/`
- Actuator for checking health of application `http://localhost:8080/actuator/health`


### API Request
##### local: `http://localhost:8080/coupon/`
#### AWS: `http://ec2-18-212-190-42.compute-1.amazonaws.com:8888/coupon/`
Body `application/json`
```
{
    "item_ids":["MCO569635558",
                "MCO613591599",
                "MCO593323015",
                "MCO596414800",
                "MCO564169608",
                "MCO453857398"],
    "amount": 200000
}
```

### API response
Body `application/json`
```
{
    "item_ids": [
        "MCO453857398",
        "MCO593323015",
        "MCO613591599"
    ],
    "total": 198700.0
}
```
When the coupon amount sent is not enough to buy at least one item, the API reponse with 404 - NOT FOUND

