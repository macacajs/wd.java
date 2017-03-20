# wd.java

 [ ![Download](https://api.bintray.com/packages/xudafeng/maven/macacaclient/images/download.svg)](https://bintray.com/xudafeng/maven/macacaclient/_latestVersion)
 [![Build Status](https://travis-ci.org/macacajs/wd.java.svg)](https://travis-ci.org/macacajs/wd.java)

Java Client binding for Macaca

## Intro

WD.java is a Java WebDriver client implemented most of the APIs in the [WebDriver Protocol](https://www.w3.org/TR/webdriver/).
It was originally designed for [Macaca](//macacajs.github.io) (A Node.js powered WebDriver server), but also available for any other implementation of WebDriver server such as Selenium, Appium and etc.

## Document

[javadoc](//macacajs.github.io/wd.java/)

## Sample

[sample-java](https://github.com/macaca-sample/sample-java)

## ChangeLog

Details changes for each release are documented in the [HISTORY.md](HISTORY.md).

## Deploy

```shell
$ mvn -s settings.xml clean source:jar deploy
```

## Generate Log

```shell
$ make doc
```

## License

The MIT License (MIT)
