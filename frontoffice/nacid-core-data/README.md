#Security configuration in applications

##Resource server configuration
1. Simple resource server configuration

* application.yml configuration

Add the following configuration to where the configuration of the application is read from (config store for example)

    spring:
        security:
            oauth2:
                resourceserver:
                    jwt:
                        issuer-uri: <issuer URI, for example: http://192.168.3.84:8480/auth/realms/NACID>
                        jwk-set-uri: <jwk set URI, for example: http://192.168.3.84:8480/auth/realms/NACID/protocol/openid-connect/certs>

* java Spring configuration

Add Spring configuration in order for security to be properly configured

For example:

        @EnableWebSecurity
        @EnableGlobalMethodSecurity(prePostEnabled = true)
        public class AppSecurityConfig {

            @Configuration
            @Import(ResourceServerDefaultSecurityConfig.class)
            public static class SecurityConfig {}
        }

2. Configuration for apps that also need to use client credentials and not the credentials of the currently logged-in user

This is the case for example when automatic processes must be running and calling the core api or when the core api must be called with elevated privileges.

* application.yml

Add the following configuration to where the configuration of the application is read from (config store for example)

    keycloak:
        client:
            id: <client id>
            secret:<client secret>
        provider:
            base-uri: <the base URI, for example: http://192.168.3.84:8480>
            realm.name: <the realm name, for example: NACID>

* java Spring config

Must add the following import to a config class

    @Configuration
    @Import(TokenManagerConfig.class)

This will add a TokenManager bean to the context and this token manager has method to get the Oauth2 access token

3. A combination of both

The two configurations can be combined so that in some cases the currently logged-in user credentials are used and in other cases - the client credentials.

