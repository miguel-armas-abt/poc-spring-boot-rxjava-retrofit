package com.demo.poc.entrypoint.books.service;

import com.demo.poc.commons.custom.properties.ApplicationProperties;
import com.demo.poc.entrypoint.books.dto.request.BookInsertRequestDto;
import com.demo.poc.entrypoint.books.dto.response.BookResponseDto;
import com.demo.poc.entrypoint.books.mapper.BookRequestMapper;
import com.demo.poc.entrypoint.books.mapper.BookResponseMapper;
import com.demo.poc.entrypoint.books.repository.books.*;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final ApplicationProperties properties;
  private final BookRepository bookRepository;
  private final BookRequestMapper requestMapper;
  private final BookResponseMapper responseMapper;

  public Observable<BookResponseDto> findAll(Map<String, String> headers) {
    return bookRepository.findAll(headers, properties)
        .map(responseMapper::toDto)
        .flatMapObservable(response -> Observable.fromStream(response.stream()));
  }

  public Maybe<BookResponseDto> findById(Map<String, String> headers, Long id) {
    return bookRepository.findById(headers, properties, id)
        .map(responseMapper::toDto)
        .toMaybe();
  }

  public Completable save(Map<String, String> headers, BookInsertRequestDto book) {
    return bookRepository.save(headers, properties, requestMapper.toWrapper(book))
        .ignoreElement();
  }
}
