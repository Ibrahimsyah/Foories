/* eslint linebreak-style: ["error", "windows"]*/

const {addCalHandler} = require('./handler');


const routes = [
  {
    method: 'POST',
    path: '/calorie',
    handler: addCalHandler,
  },
];

module.exports = routes;
