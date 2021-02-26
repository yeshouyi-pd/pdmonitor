module.exports = {
  devServer: {
    disableHostCheck: true,
    port: 80,
    //https: true,
  },
  publicPath: process.env.NODE_ENV === 'production'
      ? '/'
      : '/'

};
