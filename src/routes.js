/* eslint linebreak-style: ["error", "windows"]*/
const admin = require('firebase-admin');
const {addCalHandler, detectFoodsCalorie} = require('./handler');
const listfood = require('./listfood');

const serviceAccount = require('./key.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});
const db = admin.firestore();

const routes = [
  {
    method: 'POST',
    path: '/calorie',
    handler: addCalHandler,
  },
  {
    method: 'POST',
    path: '/calories',
    handler: detectFoodsCalorie,
  },
  {
    method: 'GET',
    path: '/',
    handler: async (request, h) => {
      const query = db.collection('food');
      const snapshot = await query.orderBy('food').get();
      snapshot.docs.forEach((doc) => {
        // console.log(doc.data());
        listfood.push(doc.data());
      });
      // const data = snapshot.docs.map((doc) => doc.data());
      // listfood.push(data);
      console.log(listfood);
      const response = h.response({
        listfood,
      });
      return response;
    },
  },
];

module.exports = routes;
