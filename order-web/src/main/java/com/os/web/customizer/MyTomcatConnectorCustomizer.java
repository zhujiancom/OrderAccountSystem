package com.os.web.customizer;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(ServerProperties.class)
public class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
    @Autowired
    private ServerProperties serverProperties;

    @Value("${server.port}")
    private int httpsServerPort;

    @Override
    public void customize(Connector connector) {
        connector.setPort(httpsServerPort);
        connector.setSecure(true);
        connector.setScheme("https");

        connector.setAttribute("SSLEnabled", true);
        connector.setAttribute("sslProtocol", "TLS");
        connector.setAttribute("protocol", "org.apache.coyote.http11.Http11Protocol");
        connector.setAttribute("clientAuth", false);
        connector.setAttribute("keystoreFile", serverProperties.getSsl().getKeyStore());
        connector.setAttribute("keystoreType", serverProperties.getSsl().getKeyStoreType());
        connector.setAttribute("keystorePass", serverProperties.getSsl().getKeyPassword());
        connector.setAttribute("keystoreAlias", serverProperties.getSsl().getKeyAlias());
        connector.setAttribute("keyPass", serverProperties.getSsl().getKeyPassword());
    }
}
