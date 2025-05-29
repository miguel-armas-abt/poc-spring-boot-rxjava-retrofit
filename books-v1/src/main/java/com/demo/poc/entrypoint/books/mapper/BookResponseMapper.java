package com.demo.poc.entrypoint.books.mapper;

import com.demo.poc.entrypoint.books.dto.response.BookResponseDto;
import com.demo.poc.entrypoint.books.repository.books.wrapper.BookResponseWrapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookResponseMapper {

  BookResponseDto toDto(BookResponseWrapper book);
}
