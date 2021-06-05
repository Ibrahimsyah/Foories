/* eslint-disable max-len */
/* eslint linebreak-style: ["error", "windows"]*/
const {nanoid} = require('nanoid');
const cal = require('./cal');
// const foodsCaloriesData = require('./foodsCalories');
const listfood = require('./listfood');

const addCalHandler = (request, h) => {
  const {gender,
    weight,
    height,
    age,
    activityType} = request.payload;

  const id = nanoid(16);
  let activityValue = null;
  switch (activityType) {
    case 1:
      activityValue = 1.2;
      break;
    case 2:
      activityValue = 1.375;
      break;
    case 3:
      activityValue = 1.55;
      break;
    case 4:
      activityValue = 1.725;
      break;
    case 5:
      activityValue = 1.9;
      break;
    default:
      activityValue = 0;
      break;
  }

  const calories = (gender === 'male') ? ((10 * weight) + (6.25 * height) - (5 * age) + 5) *
    activityValue : ((10 * weight) + (6.25 * height) - (5 * age) - 161) * activityValue;

  if (!gender) {
    const response = h.response({
      status: 'fail',
      message: 'Silahkan isi gender',
    });
    response.code(400);
    return response;
  }

  const newCal = {
    id,
    gender,
    weight,
    height,
    age,
    activityType,
    calories,
  };
  cal.push(newCal);
  console.log(newCal);
  console.log(cal);
  console.log(listfood);
  const isSuccess = cal.filter((item) => item.id === id).length > 0;
  if (isSuccess) {
    const response = h.response({
      status: 'success',
      calories,
    });
    response.code(201);
    return response;
  }

  const response = h.response({
    status: 'fail',
    message: 'Calorie gagal ditambahkan',
  });
  response.code(500);
  return response;
};

const detectFoodsCalorie = (request, h) => {
  const {foods = []} = request.payload || {};
  const foodsCalories = foods.map((food) => {
    const foodCalory = listfood.find((foodCalory) => foodCalory.food == food.name) || {};
    return {
      ...food,
      calorie: foodCalory.calorie || 0,
    };
  });
  console.log(listfood);
  return h.response({
    status: 'success',
    foodsCalories,
  });
};

module.exports = {addCalHandler, detectFoodsCalorie};
