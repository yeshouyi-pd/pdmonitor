module.exports = {
  devServer: {
    disableHostCheck: true,
    port: 9099,
    //https: true,
  },
  publicPath: process.env.NODE_ENV === 'production'
      ? '/'
      : '/'

};
