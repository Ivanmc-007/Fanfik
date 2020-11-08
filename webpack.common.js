const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
   entry: {
      index: path.join(__dirname, "src", "main", "resources", "static", "source", "js", "index.js"),
      // login: path.join(__dirname, "src", "main", "resources", "static", "js-dev", "login.js"),
      // registration: path.join(__dirname, "src", "main", "resources", "static", "js-dev", "registration.js")
   },
   module: {
      rules: [
         {
            test: /\.m?js$/,
            exclude: /(node_modules|bower_components)/,
            use: {
               loader: 'babel-loader',
               options: {
                  presets: ['@babel/preset-env']
               }
            }
         },
         {
            test: /\.(s*)css$/,
            use: ['style-loader', 'css-loader', 'sass-loader']
         }
      ]
   },
   plugins: [
      new CleanWebpackPlugin(),
   ]
};