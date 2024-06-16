package br.com.hoffmann.web.client;

import br.com.hoffmann.web.client.exception.WebRequestClientException;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public abstract class WebRequestClient {

    protected WebClient client;

    protected WebRequestClient() {
        createInstanceWebClient();
    }


    protected abstract String getBaseUrl();

    private void createInstanceWebClient() {

        client = WebClient.builder()
                .baseUrl(getBaseUrl())
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(getClientConnector())
                .build();

    }

    protected <K, T> Mono<K> post(String url, T request, Class<K> clazz) throws WebRequestClientException {
        UriSpec<RequestBodySpec> uriSpec = client.post();
        return exchangeToMono(url, request, clazz, uriSpec);
    }

    protected <K, T> Mono<K> put(String url, T request, Class<K> clazz) throws WebRequestClientException {
        UriSpec<RequestBodySpec> uriSpec = client.put();
        return exchangeToMono(url, request, clazz, uriSpec);
    }

    protected <K, T> Mono<K> get(String url, Class<K> clazz) throws WebRequestClientException {
        RequestHeadersUriSpec<?> requestHeadersUriSpec = client.get();
        RequestHeadersSpec<?> uri = requestHeadersUriSpec.uri(url);
        configureHeaders(uri);
        return uri.retrieve().bodyToMono(clazz);
    }

    protected <K, T> Mono<K> delete(String url, Class<K> clazz) throws WebRequestClientException {
        RequestHeadersUriSpec<?> requestHeadersUriSpec = client.delete();
        RequestHeadersSpec<?> uri = requestHeadersUriSpec.uri(url);
        configureHeaders(uri);
        return uri.retrieve().bodyToMono(clazz);
    }

    private void configureHeaders(RequestHeadersSpec<?> uri) {
        uri.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);
        uri.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        if (hasTokenSecret()) {
            uri.header("Authorization", "Bearer " + getSecretTokenHeader());
        }
        uri.acceptCharset(StandardCharsets.UTF_8);
    }


    private <K, T> Mono<K> exchangeToMono(String url, T request, Class<K> clazz, UriSpec<RequestBodySpec> uriSpec) throws WebRequestClientException {
        RequestBodySpec bodySpec = uriSpec.uri(url);
        RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(request));
        configureHeaders(headersSpec);
        //headersSpec.retrieve();
        return headersSpec.exchangeToMono(response -> {
            if (response.statusCode().is2xxSuccessful()) {
                return response.bodyToMono(clazz);
            } else if (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
                throw new WebRequestClientException("Erro" + response.logPrefix());
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
    }

    protected ClientHttpConnector getClientConnector() {
        var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofSeconds(5))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5, TimeUnit.SECONDS)));

        return new ReactorClientHttpConnector(httpClient);
    }


    protected String getSecretTokenHeader() {
        return null;
    }

    private boolean hasTokenSecret() {
        return StringUtils.isNotEmpty(getSecretTokenHeader());
    }


}
