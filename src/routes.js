/* eslint linebreak-style: ["error", "windows"]*/

const {addCalHandler, detectFoodsCalorie} = require('./handler');


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
];

module.exports = routes;
