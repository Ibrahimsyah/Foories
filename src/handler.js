/* eslint linebreak-style: ["error", "windows"]*/
const {nanoid} = require('nanoid');
const users = require('./users');

const addUserHandler = (request, h) => {
  const {name,
    weight,
    height,
    age,
    gender,
    activityType} = request.payload;

  const id = nanoid(16);

  if (!name) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal menambahkan user. Mohon isi nama user',
    });
    response.code(400);
    return response;
  }
  const newUser = {
    id,
    name,
    weight,
    height,
    age,
    gender,
    activityType,
  };
  users.push(newUser);

  const isSuccess = users.filter((user) => user.id === id).length > 0;
  if (isSuccess) {
    const response = h.response({
      status: 'success',
      message: 'User berhasil ditambahkan',
      data: {
        userId: id,
      },
    });
    response.code(201);
    return response;
  }

  const response = h.response({
    status: 'fail',
    message: 'User gagal ditambahkan',
  });
  response.code(500);
  return response;
};

const getAllUserHandler = () => ({
  status: 'success',
  data: {
    users: users.map((user) => ({
      id: user.id,
      name: user.name,
      age: user.age,
      weight: user.weight,
      height: user.height,
      gender: user.gender,
      activityType: user.activityType,
    })),
  },
});

const getUserByIdHandler = (request, h) => {
  const {id} = request.params;
  const user = users.filter((n) => n.id === id)[0];

  if (user !== undefined) {
    return {
      status: 'success',
      data: {
        user,
      },
    };
  }
  const response = h.response({
    status: 'fail',
    meessage: 'User tidak ditemukan',
  });
  response.code(404);
  return response;
};

module.exports = {addUserHandler,
  getAllUserHandler,
  getUserByIdHandler};
