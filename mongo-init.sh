#!/usr/bin/env bash
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
db.createCollection('customers')

EOF

mongo <<EOF
use ${MONGO_INITDB_DATABASE}

db.customers.insertMany([
  {"givenName":"Josie","familyName":"Stöber","birthName":"Weigand","gender":"female","birthDate":"1997-03-17","height":172,"eyecolor":"grau","taxId":"85461072902","address":{"addressCountry":"DE","addressLocality":"Wuppertal","postalCode":"42289","streetAddress":"Öhder Straße","houseNumber":"31a"},"phoneNo":"+49 32 122386200","mobileNo":"+49 1577 0473623","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50220900","desc":"Hauck & Aufhäuser","bic":"HAUKDEFFXXX"},"iban":"DE87502209004192256669"},"creditCard":{"number":"5497056331911694","type":"MASTERCARD","cvc":"107"},"customerId":"4a7d1fda-e46a-4d14-b03a-60ebe8d70c63"},
  {"givenName":"Ilaria","familyName":"Weinrich","gender":"female","birthDate":"1957-05-27","height":173,"eyecolor":"grau","taxId":"40238659167","address":{"addressCountry":"DE","addressLocality":"Berlin","postalCode":"13127","streetAddress":"Straße 180","houseNumber":"74"},"phoneNo":"+49 32 135691703","mobileNo":"+49 160 50862416","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50230700","desc":"Bankhaus Metzler Frankfurt","bic":"METZDEFFXXX"},"iban":"DE84502307008739443334"},"creditCard":{"number":"4461358355597762","type":"VISA","cvc":"311"},"customerId":"809225a6-dbb8-4f95-96a4-05b8c7ae2501"},
  {"givenName":"Clemens","familyName":"Bach","gender":"male","birthDate":"1948-10-20","height":160,"eyecolor":"blau","taxId":"94762150343","address":{"addressCountry":"DE","addressLocality":"Bad Münstereifel","postalCode":"53902","streetAddress":"Ännchengasse","houseNumber":"9"},"phoneNo":"+49 32 111301389","mobileNo":"+49 179 99793376","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50220707","desc":"CAIXABANK Zndl Frankfurt","bic":"CAIXDEFFXXX"},"iban":"DE72502207076270180802"},"creditCard":{"number":"4336880329538104","type":"VISA","cvc":"638"},"customerId":"c5b9f660-df62-4fd8-9191-7a23d02d06ec"},
  {"givenName":"Quentin","familyName":"Rumpf","gender":"male","birthDate":"1961-09-01","height":179,"eyecolor":"braun","taxId":"52384170629","address":{"addressCountry":"DE","addressLocality":"Borna","postalCode":"04552","streetAddress":"Roßmarktsche Straße","houseNumber":"8"},"phoneNo":"+49 32 127626404","mobileNo":"+49 160 99735933","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50050000","desc":"Ld Bk Hess-Thür Gz Ffm","bic":"HELADEFFXXX"},"iban":"DE76500500002338620616"},"creditCard":{"number":"5425033002297767","type":"MASTERCARD","cvc":"366"},"customerId":"1790e972-4ecd-40a2-90c9-c983c57284dd"},
  {"givenName":"Damian","familyName":"Nagel","birthName":"Schmoll","gender":"male","birthDate":"1981-10-23","height":182,"eyecolor":"grau","taxId":"67251038980","address":{"addressCountry":"DE","addressLocality":"Dresden","postalCode":"01109","streetAddress":"Am Wasserwerk","houseNumber":"19h"},"phoneNo":"+49 32 115999058","mobileNo":"+49 1521 8171033","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50320000","desc":"VTB Bank (Europe) Ffm","bic":"OWHBDEFFXXX"},"iban":"DE47503200008099228838"},"creditCard":{"number":"5476298957510856","type":"MASTERCARD","cvc":"275"},"customerId":"8a8fb179-48cb-4474-b344-d31862c29515"},
  {"givenName":"Helene","familyName":"Fix","gender":"female","birthDate":"1985-01-01","height":175,"eyecolor":"grün","taxId":"40257918685","address":{"addressCountry":"DE","addressLocality":"München","postalCode":"81547","streetAddress":"Säbener Straße","houseNumber":"31"},"phoneNo":"+49 32 123301426","mobileNo":"+49 173 23578057","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50050000","desc":"Ld Bk Hess-Thür Gz Ffm","bic":"HELADEFFXXX"},"iban":"DE02500500008443577781"},"creditCard":{"number":"4321101547152405","type":"VISA","cvc":"517"},"customerId":"f199ba5a-8499-4e4f-b993-eae4508f9d29"},
  {"givenName":"Zainab","familyName":"Bier","gender":"female","birthDate":"1928-01-24","height":150,"eyecolor":"blau","taxId":"51637490800","address":{"addressCountry":"DE","addressLocality":"Windeck","postalCode":"51570","streetAddress":"Holzgasse","houseNumber":"1"},"phoneNo":"+49 32 188786695","mobileNo":"+49 1526 0199270","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50220707","desc":"CAIXABANK Zndl Frankfurt","bic":"CAIXDEFFXXX"},"iban":"DE55502207076557794838"},"creditCard":{"number":"4412645004635880","type":"VISA","cvc":"455"},"customerId":"c441882b-b153-4e42-b893-9d4ab095bcf5"},
  {"givenName":"Eda","familyName":"Bastian","gender":"female","birthDate":"1980-05-20","height":181,"eyecolor":"braun","taxId":"42015937846","address":{"addressCountry":"DE","addressLocality":"Itzehoe","postalCode":"25524","streetAddress":"Dietrich-Bonhoeffer-Straße","houseNumber":"21"},"phoneNo":"+49 32 158962978","mobileNo":"+49 170 24076071","bankAccount":{"bank":{"city":"Frankfurt am Main","bankCode":"50220900","desc":"Hauck & Aufhäuser","bic":"HAUKDEFFXXX"},"iban":"DE28502209001259651881"},"creditCard":{"number":"5425882081317221","type":"MASTERCARD","cvc":"387"},"customerId":"cdf30959-b13d-4d66-a528-fad14f455966"}
]);
EOF

mongo <<EOF
use ${MONGO_INITDB_DATABASE}

db.customers.createIndex({customerId: 1});
EOF
