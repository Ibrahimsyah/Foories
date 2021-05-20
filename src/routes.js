/* eslint linebreak-style: ["error", "windows"]*/

const {addUserHandler,
  getUserByIdHandler,
  getAllUserHandler} = require('./handler');

const routes = [
  {
    method: 'POST',
    path: '/users',
    handler: addUserHandler,
  },
  {
    method: 'GET',
    path: '/users',
    handler: getAllUserHandler,
  },
  {
    method: 'GET',
    path: '/users/{id}',
    handler: getUserByIdHandler,
  },
];

module.exports = routes;
