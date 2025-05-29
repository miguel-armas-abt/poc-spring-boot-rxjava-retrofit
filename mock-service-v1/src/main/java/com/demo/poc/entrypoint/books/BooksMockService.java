package com.demo.poc.entrypoint.books;

import java.util.concurrent.TimeUnit;

import com.demo.poc.commons.custom.config.MockService;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpStatusCode;

import org.springframework.stereotype.Component;

import static com.demo.poc.commons.custom.utils.DelayUtil.generateRandomDelay;
import static com.demo.poc.commons.custom.utils.HeadersGenerator.contentType;
import static com.demo.poc.commons.custom.utils.HeadersGenerator.generateTraceId;
import static com.demo.poc.commons.custom.utils.FileReader.readJson;
import static com.demo.poc.commons.custom.utils.FileReader.readRaw;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@Component
public class BooksMockService implements MockService {

  @Override
  public void loadMocks(ClientAndServer mockServer) {

    mockServer
        .when(request()
            .withMethod("GET")
            .withPath("/poc/mocks/v1/books"))
        .respond(request -> {

          long randomDelay = generateRandomDelay();
          Header traceIdHeader = generateTraceId();

          return response()
              .withStatusCode(HttpStatusCode.OK_200.code())
              .withHeader(contentType("application/json"))
              .withHeader(traceIdHeader)
              .withBody(readJson("mocks/books/Books.200.json"))
              .withDelay(TimeUnit.MILLISECONDS, randomDelay);
        });

    mockServer
        .when(request()
            .withMethod("GET")
            .withPath("/poc/mocks/v1/reactive/books"))
        .respond(request -> {

          long randomDelay = generateRandomDelay();
          Header traceIdHeader = generateTraceId();

          return response()
              .withStatusCode(HttpStatusCode.OK_200.code())
              .withHeader(contentType("application/x-ndjson"))
              .withHeader(traceIdHeader)
              .withBody(readRaw("mocks/books/Books.Reactive.200.json"))
              .withDelay(TimeUnit.MILLISECONDS, randomDelay);
        });

    mockServer
        .when(request()
            .withMethod("GET")
            .withPath("/poc/mocks/v1/books/1"))
        .respond(request -> {

          long randomDelay = generateRandomDelay();
          Header traceIdHeader = generateTraceId();

          return response()
              .withStatusCode(HttpStatusCode.OK_200.code())
              .withHeader(contentType("application/json"))
              .withHeader(traceIdHeader)
              .withBody(readJson("mocks/books/Book.200.json"))
              .withDelay(TimeUnit.MILLISECONDS, randomDelay);
        });

    mockServer
        .when(request()
            .withMethod("POST")
            .withPath("/poc/mocks/v1/books"))
        .respond(request -> {

          long randomDelay = generateRandomDelay();
          Header traceIdHeader = generateTraceId();

          return response()
              .withStatusCode(HttpStatusCode.CREATED_201.code())
              .withHeader(contentType("application/json"))
              .withHeader(traceIdHeader)
              .withDelay(TimeUnit.MILLISECONDS, randomDelay);
        });

  }
}
