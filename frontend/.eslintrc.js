module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/essential',
    'eslint:recommended'
  ],
  parserOptions: {
    parser: 'babel-eslint'
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off'
  },
  "overrides": [
    {
      "files": ["date.js"],
      "rules": {
        "no-use-before-define": ["off"],
        "max-lines-per-function": ["off"]
      }
    }
  ]
}
