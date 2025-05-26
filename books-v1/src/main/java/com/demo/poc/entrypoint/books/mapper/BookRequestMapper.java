package com.demo.poc.entrypoint.books.mapper;

import com.demo.poc.entrypoint.books.dto.request.BookInsertRequestDto;
import com.demo.poc.entrypoint.books.repository.books.wrapper.BookInsertRequestWrapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookRequestMapper {

  BookInsertRequestWrapper toWrapper(BookInsertRequestDto book);
}
