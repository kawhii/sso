# Example CASified Java Web Application

This is sample java web application that exercises the CAS protocol features via the Java CAS Client.

Configure
---------

- Adjust the url endpoints of the CAS server and 
the application server in the [`web.xml`](https://github.com/UniconLabs/cas-sample-java-webapp/blob/master/src/main/webapp/WEB-INF/web.xml) file.

## Build

* Create a Java keystore at `/etc/cas/jetty/thekeystore` with the password `changeit`.
* Import your server certificate inside this keystore.

```bash
mvn clean package jetty:run-forked
```

The application will be available on:
```bash
http://localhost:9080/sample
```
and
```bash
https://localhost:9443/sample
```

 
## Testing High Availability

Assuming you have deployed CAS on two nodes, you can use the sample application to make sure all nodes are properly
sharing the ticket state. To do this, in the `web.xml` file ensure that:

- The `casServerLoginUrl` of the `CAS Authentication Filter` points to CAS node 1 (i.e `https://cas1.sso.edu:8443/cas/login`).
- The `casServerUrlPrefix` of the `CAS Validation Filter` points to CAS node 2 (i.e `https://cas2.sso.edu:8443/cas`)
- For both of the above filters, the `serverName` should always point to the location where *this sample application* is deployed.


Deploy the application and test. You may also want to reverse the order of CAS 
nodes 1 and 2 in the above configuration, redeploy and test again.

> Alternatively, one could test distributed CAS nodes without any client application 
set up using [this](https://github.com/UniconLabs/duct) small command line utility




