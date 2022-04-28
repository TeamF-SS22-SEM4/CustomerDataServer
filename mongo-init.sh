#!/usr/bin/env bash


mongo <<EOF
use ${MONGO_INITDB_DATABASE};

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
use ${MONGO_INITDB_DATABASE};

db.createCollection('customers');

EOF

mongo <<EOF
use ${MONGO_INITDB_DATABASE}

db.customers.insertMany([
{"givenName":"Josie","familyName":"Stöber","email":"JosieStober1997@email.test","address":{"addressCountry":"DE","addressLocality":"Wuppertal","postalCode":"42289","streetAddress":"Öhder Straße","houseNumber":"31a"},"phoneNo":"+49 32 122386200","mobileNo":"+49 1577 0473623","creditCard":{"number":"5497056331911694","type":"MASTERCARD","cvc":"107"},"customerId":"4a7d1fda-e46a-4d14-b03a-60ebe8d70c63"}, 
{"givenName":"Mieke","familyName":"Jochum","email":"MiekeJochum1946@email.test","address":{"addressCountry":"DE","addressLocality":"Ahlstädt","postalCode":"98553","streetAddress":"Hauptstraße","houseNumber":"4"},"phoneNo":"+49 32 142709659","mobileNo":"+49 1577 0334090","creditCard":{"number":"4129492231657419","type":"VISA","cvc":"801"},"customerId":"9d9f3f44-2b37-454c-981c-9f1a171ae2df"},
{"givenName":"Isabella","familyName":"Pabst","email":"IsabellaPabst1945@email.test","address":{"addressCountry":"DE","addressLocality":"Mönchengladbach","postalCode":"41063","streetAddress":"Marktfeldstraße","houseNumber":"131"},"phoneNo":"+49 32 176733383","mobileNo":"+49 1578 6692475","creditCard":{"number":"4555558529974113","type":"VISA","cvc":"729"},"customerId":"e8092ef9-5d49-43ed-b8c9-0e7aa7b28a51"}
]);
EOF

mongo <<EOF
use ${MONGO_INITDB_DATABASE};

db.customers.createIndex({customerId: 1});

db.customers.createIndex({ givenName: "text", familyName: "text", "address.addressCountry": "text", "address.addressLocality": "text", "address.streetAddress": "text"});
EOF
