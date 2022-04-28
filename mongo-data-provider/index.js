//TODO remove this and dependency
require('dotenv').config({path: '../.env'});

const {MongoClient} = require('mongodb');

//TODO check if mongoDB is ready
const ADMIN_USERNAME = encodeURIComponent(process.env.MONGO_INITDB_ROOT_USERNAME);
const ADMIN_PASSWORD = encodeURIComponent(process.env.MONGO_INITDB_ROOT_PASSWORD);
const DATABASE = process.env.MONGO_INITDB_DATABASE;
const USERNAME = encodeURIComponent(process.env.MONGO_USERNAME);
const PASSWORD = encodeURIComponent(process.env.MONGO_PASSWORD);
//
const uri = `mongodb://${ADMIN_USERNAME}:${ADMIN_PASSWORD}@local_mongo`;


const client = new MongoClient(uri);

async function populate_db() {
    try {
        await client.connect();
        const db = client.db(DATABASE);
        const admindb = db.admin()
        admindb.addUser(USERNAME, PASSWORD, {roles: [{role: 'readWrite', db: DATABASE}]})

        const customerCollection = db.collection("customers");
        // Query for a movie that has the title 'Back to the Future'
        const customers = [
            {"givenName":"Josie","familyName":"Stöber","email":"JosieStober1997@email.test","address":{"addressCountry":"DE","addressLocality":"Wuppertal","postalCode":"42289","streetAddress":"Öhder Straße","houseNumber":"31a"},"phoneNo":"+49 32 122386200","mobileNo":"+49 1577 0473623","creditCard":{"number":"5497056331911694","type":"MASTERCARD","cvc":"107"},"customerId":"4a7d1fda-e46a-4d14-b03a-60ebe8d70c63"},
            {"givenName":"Aiden","familyName":"Sorg","email":"AidenSorg1936@email.test","address":{"addressCountry":"DE","addressLocality":"Burg Stargard","postalCode":"17094","streetAddress":"Zum Born","houseNumber":"3a"},"phoneNo":"+49 32 184390873","mobileNo":"+49 175 38272955","creditCard":{"number":"4282794345551500","type":"VISA","cvc":"264"},"customerId":"3ae6ee19-3243-4b19-851f-4d4d7c16e058"},
            {"givenName":"Isa","familyName":"Friese","email":"IsaFriese1999@email.test","address":{"addressCountry":"DE","addressLocality":"Berlin","postalCode":"12526","streetAddress":"Atlantisring","houseNumber":"20"},"phoneNo":"+49 32 133668245","mobileNo":"+49 1514 7775046","creditCard":{"number":"MASTERCARD5837316433","type":" S.A.","cvc":"839"},"customerId":"d76eab62-e670-42ef-835a-b9becfc536be"},
            {"givenName":"Adelina","familyName":"Landmann","email":"AdelinaLandmann1987@email.test","address":{"addressCountry":"DE","addressLocality":"Drochtersen","postalCode":"21706","streetAddress":"Wegefährels","houseNumber":"19"},"phoneNo":"+49 32 169210115","mobileNo":"+49 1525 7759780","creditCard":{"number":"4461487249042709","type":"VISA","cvc":"230"},"customerId":"d24c06e3-c7f9-4d9f-84e2-224ad44221cb"},
            {"givenName":"Erva","familyName":"Grewe","email":"ErvaGrewe2001@email.test","address":{"addressCountry":"DE","addressLocality":"Augsburg","postalCode":"86199","streetAddress":"Bürgermeister-Miehle-Straße","houseNumber":"35"},"phoneNo":"+49 32 197177484","mobileNo":"+49 1521 7200786","creditCard":{"number":"4666508746370732","type":"VISA","cvc":"632"},"customerId":"d99ead66-b234-41fa-8aee-d479da39727a"},
            {"givenName":"Mathea","familyName":"Steinhauer","email":"MatheaSteinhauer1936@email.test","address":{"addressCountry":"DE","addressLocality":"Glonn","postalCode":"85625","streetAddress":"Wiesmühlstraße","houseNumber":"25"},"phoneNo":"+49 32 180406216","mobileNo":"+49 1575 4066142","creditCard":{"number":"5274273208519455","type":"MASTERCARD","cvc":"358"},"customerId":"1ef37b9b-0eff-44e2-87a4-9239e0ba4509"},
            {"givenName":"Poyraz","familyName":"Stürmer","email":"PoyrazSturmer1970@email.test","address":{"addressCountry":"DE","addressLocality":"Crimmitschau","postalCode":"08451","streetAddress":"Lindenstraße","houseNumber":"18"},"phoneNo":"+49 32 187158777","mobileNo":"+49 1516 8282998","creditCard":{"number":"5256279156833667","type":"MASTERCARD","cvc":"720"},"customerId":"73c36ac1-25f5-4064-87b4-fe56034e5f83"},
            {"givenName":"Jonas","familyName":"Niebuhr","email":"JonasNiebuhr1982@email.test","address":{"addressCountry":"DE","addressLocality":"Burgdorf","postalCode":"31303","streetAddress":"Moorstraße","houseNumber":"45"},"phoneNo":"+49 32 177232485","mobileNo":"+49 1521 2704970","creditCard":{"number":"4412614992618415","type":"VISA","cvc":"981"},"customerId":"e02e192b-f60c-4ffe-8421-a66f3861d20f"}
        ];

        const result = await customerCollection.insertMany(customers);
        console.log(result)
        console.log("Successfully inserted data")
    } catch (e) {
        console.log(e)
    } finally {
        // Ensures that the client will close when you finish/error
        await client.close();
    }
}
populate_db()