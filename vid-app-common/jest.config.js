module.exports = {
  verbose: true,
  roots: [
    "<rootDir>/src/main/webapp/app"
  ],
  modulePaths: [
    "<rootDir>/src/main/webapp/app/vid/external"
  ],
  setupFilesAfterEnv: ["<rootDir>/test-config.js"],
  collectCoverage: true,
  collectCoverageFrom: [
    "src/**/*.js",
    "!**/node_modules/**",
    "!**/vendor/**"
  ]
};