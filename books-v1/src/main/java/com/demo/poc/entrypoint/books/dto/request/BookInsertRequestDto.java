package com.demo.poc.entrypoint.books.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookInsertRequestDto implements Serializable {

  @NotNull
  private Long id;

  @NotEmpty
  private String title;

  @NotEmpty
  private String description;

  @NotNull
  private Integer pageCount;

  @NotEmpty
  private String excerpt;

  @NotEmpty
  private String publishDate;
}
