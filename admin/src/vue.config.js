module.exports = {
  devServer: {
    disableHostCheck: true,
    port: 8080,
    //https: true,
  },
  publicPath: process.env.NODE_ENV === 'production'
      ? '/'
      : '/'

};
