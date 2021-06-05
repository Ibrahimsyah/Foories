/* eslint linebreak-style: ["error", "windows"]*/
const {addCalHandler, detectFoodsCalorie, getData} = require('./handler');
// const listfood = require('./listfood');
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
    handler: getData,
  },
];

module.exports = routes;
