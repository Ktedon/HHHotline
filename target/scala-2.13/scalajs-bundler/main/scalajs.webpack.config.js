module.exports = {
  "entry": {
    "ehsgovsite-fastopt": ["/home/bradly/Desktop/ehsgovsite/target/scala-2.13/scalajs-bundler/main/ehsgovsite-fastopt-entrypoint.js"]
  },
  "output": {
    "path": "/home/bradly/Desktop/ehsgovsite/target/scala-2.13/scalajs-bundler/main",
    "filename": "[name]-library.js",
    "library": "ScalaJSBundlerLibrary",
    "libraryTarget": "var"
  },
  "mode": "development",
  "devtool": "source-map",
  "module": {
    "rules": [{
      "test": new RegExp("\\.js$"),
      "enforce": "pre",
      "use": ["source-map-loader"]
    }]
  }
}