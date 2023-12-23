package com.dailycode.learner.service;

import com.dailycode.learner.dto.requestdto.BookRequestDto;
import com.dailycode.learner.dto.responsedto.BookResponseDto;
import com.dailycode.learner.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public BookResponseDto addBook(BookRequestDto bookRequestDto);
    public BookResponseDto getBookById(Long bookId);
    public Book getBook(Long bookId);
    public List<BookResponseDto> getBooks();
    public BookResponseDto deleteBook(Long bookId);
    public BookResponseDto editBook(Long bookId,BookRequestDto bookRequestDto);
    public BookResponseDto addAuthorToBook(Long bookId,Long authorId);
    public BookResponseDto deleteAuthorFromBook(Long bookId,Long authorId);
    public BookResponseDto addCategoryToBook(Long bookId,Long categoryId);

    public BookResponseDto removeCategoryFromBook(Long bookId,Long categoryId);
}
