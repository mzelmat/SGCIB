# Bank-account

You can launch up Eureka at http://localhost:8761

Connect Account and Operation Microservices with Eureka.

Currently you have the following services up and running

Account Micro Service (Account-Service) on 8100
One or Two instances of Operation MicroService on 8000 and 8001

# Request 1 : 
http://localhost:8100/accounts/75000000001/history

# Response : 
[{"id":1,"accountDate":[2017,10,10],"amount":2500,"operationType":"DEPOSIT","accountNumber":75000000001}]

# Request 2 : 
http://localhost:8000/operations/75000000001/history

# Response : 
[{"id":1,"accountDate":[2017,10,10],"amount":2500,"operationType":"DEPOSIT","accountNumber":75000000001}]
