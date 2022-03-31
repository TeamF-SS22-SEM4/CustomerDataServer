set -e

mongo <<EOF
use ${MONGO_INITDB_DATABASE}

db.createUser({
  user: '${MONGO_USERNAME}',
  pwd: '${MONGO_PASSWORD}',
  roles: [{
    role: 'readWrite',
    db: '${MONGO_INITDB_DATABASE}'
  }]
});

EOF

mongo <<EOF
use ${MONGO_INITDB_DATABASE}
db.createCollection('customers');

EOF

mongo <<EOF
use ${MONGO_INITDB_DATABASE}

db.customers.insertMany([
  {"givenName":"Josie","familyName":"Stöber","birthName":"Weigand","gender":"female","birthDate":"1997-03-17","height":172,"eyecolor":"grau","email":"JosieStober1997@email.test","taxId":"85461072902","address":{"addressCountry":"DE","addressLocality":"Wuppertal","postalCode":"42289","streetAddress":"Öhder Straße","houseNumber":"31a"},"phoneNo":"+49 32 122386200","mobileNo":"+49 1577 0473623","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50220900","desc":"Hauck & Aufhäuser","bic":"HAUKDEFFXXX"},"iban":"DE87502209004192256669"},"creditCard":{"number":"5497056331911694","type":"MASTERCARD","cvc":"107"},"customerId":"4a7d1fda-e46a-4d14-b03a-60ebe8d70c63"}
]);

EOF