Install and launch mongoDB:
-
Install 

-  `brew tap mongodb/brew`
-  `brew install mongodb-community@4.2`
-  `sudo mkdir -p /data/db`
-  `sudo chown -R ' id -un ' /data/db`

Launch Mongo DB
-   `mongod` 

**Note:**
1. Robo3T is a very good GUI for mongodb - https://robomongo.org/download 

Install gradle
-
- `brew install gradle`


Start service
-

- `gradle clean`
- `gradle build`
- `gradle bootRun`

APIS
-
Refer endpoints.txt


Start service
-

- `gradle test --tests FoodbiteApiTests` or
- `gradle test --tests FoodbiteApiTests --info`
