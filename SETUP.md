# Setup

This application uses MongoDB for persistence.

By default, this application expects a MongoDb instance on localhost and on standard port.
This can be changed in [customerServer.conf](./customerServer.conf)

Note: Setup is only described for an Ubuntu System (specifically Ubuntu 20.04.3)

## Setup MongoDB instance

First to install MongoDB Community Edition:

1. Install gnupg
> sudo apt-get install gnupg

2. Import public GPG key of MongoDB
> wget -qO - https://www.mongodb.org/static/pgp/server-5.0.asc | sudo apt-key add -

3. Create list file
> echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/5.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-5.0.list

4. Install MongoDB package
> sudo apt-get install -y mongodb-org

5. Verify installation
> mongod --version
 
You should see something like this
> db version v5.0.6<br>
> Build info: ...

### Start MongoDB

To start the server run:
> sudo systemctl start mongod

Optionally to always start MongoDB when the system boots, run:
> sudo systemctl enable mongod

## Insert data from JSON-File

1. Install Tool to import JSON to MongoDB
> sudo apt install mongo-tools

2. Import JSON-File
> mongoimport --jsonArray --db <DATABASE-NAME> --collection <COLLECTION-NAME> --file persons.json

## Create ID's and Index
The project uses UUID's for Entity-identities, execute the following commands in `mongosh`
to create a new field with a UUID for all customer records.

Switch to the customer database

```use customerDb```

Insert UUID's

```
db.customers.aggregate([
    {$addFields: {
        customerId: {
            $function: {
                body: 'return UUID().toString().split(\'"\')[1]',
                args: [],
                lang: "js"
            }
        }
    }}, {
        $out: "customers"
    }
])
```

Create index on UUID's for faster searches

```db.customers.createIndex({customerId: 1})```