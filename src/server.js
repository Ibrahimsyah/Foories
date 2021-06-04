/* eslint linebreak-style: ["error", "windows"]*/
const Hapi = require('@hapi/hapi');
const routes = require('./routes');
// const admin = require('firebase-admin');

const init = async () => {
  const server = Hapi.server({
    // port: 5000,
    // host: 'localhost',
    // eslint-disable-next-line max-len
    // host: process.env.NODE_ENV !== 'production' ? 'localhost' : '34.101.5.116',
    port: process.env.PORT || 8080,
    // host: '0.0.0.0',
    routes: {
      cors: {
        origin: ['*'],
      },
    },
  });

  server.route(routes);
  await server.start();
  console.log(`Server berjalan pada ${server.info.uri}`);
};

init();
