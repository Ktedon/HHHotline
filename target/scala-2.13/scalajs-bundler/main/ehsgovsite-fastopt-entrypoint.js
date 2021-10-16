module.exports = {
  "require": (function(x1) {
    return {
      "react-dom": require("react-dom"),
      "resources/index.css": require("resources/index.css"),
      "react": require("react"),
      "react-proxy": require("react-proxy"),
      "resources/bootstrapfile.css": require("resources/bootstrapfile.css")
    }[x1]
  })
}