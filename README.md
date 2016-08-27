[![Build Status](https://travis-ci.org/kost/webshell-portlet.png)](https://travis-ci.org/kost/webshell-portlet)
[![Coverage Status](https://coveralls.io/repos/kost/webshell-portlet/badge.png?branch=master)](https://coveralls.io/r/kost/webshell-portlet?branch=master)

webshell-portlet
================

Web shell as Portlet (useful for Websphere Portal, JBoss Portal, etc.).

Portable Web shell across different "portal" implementations which are JSR 168 or JSR 268 compatible.

Includes:
- JBoss Portal
- IBM WebSphere Portal
- etc

Prerequists
================

In order to build from source, you'll need:
- Java
- Maven

Building
================

Go to project directory and run mvn:

    mvn package

Tested platforms
================

So, far it has been tested on:
- JBoss Portal 2.7.2
- IBM WebSphere Portal 7
