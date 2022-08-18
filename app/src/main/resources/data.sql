INSERT INTO FOOD(name, calorie, description, price, category)
SELECT "name", CONVERT("calorie",INT), "description", CONVERT("price", INT), "category"
FROM CSVREAD('input\\foods.csv', 'name, calorie, description, price, category', NULL);

INSERT INTO CUSTOMER(email, password, balance, name, id)
SELECT "email", "password", CONVERT("balance", DECIMAL(19,2)), "name", CONVERT("id",INT)
FROM CSVREAD('input\\customers.csv', 'email, password, id, name, balance', NULL);
