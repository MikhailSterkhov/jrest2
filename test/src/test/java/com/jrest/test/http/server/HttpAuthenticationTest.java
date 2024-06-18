package com.jrest.test.http.server;

import com.jrest.http.server.authentication.AuthorizationController;
import com.jrest.http.server.authentication.AuthenticatorContainer;
import com.jrest.http.server.authentication.token.TokenGenerator;
import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpMethod;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.authentication.ApprovalResult;
import com.jrest.mvc.model.authentication.Authentication;
import com.jrest.mvc.model.authentication.Token;
import com.jrest.mvc.model.authentication.defaults.HttpBasicAuthenticator;
import com.jrest.mvc.model.authentication.defaults.HttpBearerAuthenticator;

public class HttpAuthenticationTest {

    private static final Token.UsernameAndPassword BASIC_CREDENTIALS = Token.UsernameAndPassword.of("admin", "admin");
    private static final String BEARER_API_TOKEN = TokenGenerator.defaults(30).generate();

    private final AuthorizationController authorizationController = new AuthorizationController();
    private final AuthenticatorContainer authenticatorContainer = new AuthenticatorContainer();

    private void registerAuthenticators() {
        authenticatorContainer.add(Authentication.BASIC, HttpBasicAuthenticator.of(BASIC_CREDENTIALS));
        authenticatorContainer.add(Authentication.BEARER, HttpBearerAuthenticator.single(BEARER_API_TOKEN));
    }

    private HttpRequest newUnauthorizedRequest() {
        return HttpRequest.builder()
                .method(HttpMethod.GET)
                .headers(Headers.newHeaders())
                .url("/index.html")
                .build();
    }

    private HttpRequest newCorrectlyBasicRequest() {
        return HttpRequest.builder()
                .url("/index.html")
                .method(HttpMethod.GET)
                .headers(Headers.newHeaders()
                        .add(Headers.Def.AUTHORIZATION, Authentication.BASIC.format(BASIC_CREDENTIALS)))
                .build();
    }

    private HttpRequest newCorrectlyBearerRequest() {
        return HttpRequest.builder()
                .url("/index.html")
                .method(HttpMethod.GET)
                .headers(Headers.newHeaders()
                        .add(Headers.Def.AUTHORIZATION, Authentication.BEARER.format(BEARER_API_TOKEN)))
                .build();
    }

    private HttpRequest newWrongBasicRequest() {
        return HttpRequest.builder()
                .url("/index.html")
                .method(HttpMethod.GET)
                .headers(Headers.newHeaders()
                        .add(Headers.Def.AUTHORIZATION, Authentication.BASIC.format("user:pass")))
                .build();
    }

    private HttpRequest newWrongBearerRequest() {
        return HttpRequest.builder()
                .url("/index.html")
                .method(HttpMethod.GET)
                .headers(Headers.newHeaders()
                        .add(Headers.Def.AUTHORIZATION, Authentication.BEARER.format("123")))
                .build();
    }

    private void test(HttpRequest httpRequest) {
        System.out.println("Authorize " + httpRequest);

        if (!authorizationController.hasAuthentication(httpRequest)) {
            System.out.println("  -> not found");
            return;
        }

        Authentication authentication = authorizationController.findAuthentication(httpRequest);
        ApprovalResult result = authorizationController.authenticate(httpRequest, authentication,
                authenticatorContainer.getAuthenticators(authentication));

        System.out.println("  -> result: " + result);
    }

    public static void main(String[] args) {
        HttpAuthenticationTest test = new HttpAuthenticationTest();

        test.registerAuthenticators();

        test.test(test.newUnauthorizedRequest());
        test.test(test.newCorrectlyBasicRequest());
        test.test(test.newCorrectlyBearerRequest());
        test.test(test.newWrongBasicRequest());
        test.test(test.newWrongBearerRequest());
    }
}
