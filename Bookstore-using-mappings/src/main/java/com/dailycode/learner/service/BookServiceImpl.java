package com.dailycode.learner.service;

import com.dailycode.learner.dto.mapper;
import com.dailycode.learner.dto.requestdto.BookRequestDto;
import com.dailycode.learner.dto.responsedto.BookResponseDto;
import com.dailycode.learner.entity.Author;
import com.dailycode.learner.entity.Book;
import com.dailycode.learner.entity.Category;
import com.dailycode.learner.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book=new Book();
        book.setName(bookRequestDto.getName());
        if(bookRequestDto.getAuthorIds().isEmpty()){
            throw new IllegalArgumentException("book should contain atleast one author");
        }
        else{
            List<Author> authors=new ArrayList<>();
            for (Long authorId:
                 bookRequestDto.getAuthorIds()) {
                authors.add(authorService.getAuthor(authorId));
            }
            book.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId()==null){
            throw new IllegalArgumentException("category id can't be null");
        }
        Category category=categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);
        Book book1=bookRepository.save(book);
        return mapper.bookToBookResponseDto(book1);
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        Book book=getBook(bookId);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public Book getBook(Long bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(()->
                new IllegalArgumentException("can't find a book with Id: "+bookId));
        return book;
    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books= StreamSupport.stream(bookRepository.findAll().spliterator(),false).collect(Collectors.toList());
        return mapper.booksToBookResponseDtos(books);
    }

    @Override
    public BookResponseDto deleteBook(Long bookId) {
        Book book=getBook(bookId);
        bookRepository.delete(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        Book bookToEdit=getBook(bookId);
        bookToEdit.setName(bookRequestDto.getName());
        if(!bookRequestDto.getAuthorIds().isEmpty()){
            List<Author> authors=new ArrayList<>();
            for (Long authorId:
                    bookRequestDto.getAuthorIds()) {
                authors.add(authorService.getAuthor(authorId));
            }
            bookToEdit.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId()!=null){
            bookToEdit.setCategory(categoryService.getCategory(bookRequestDto.getCategoryId()));
        }
//        bookRepository.save()
        return mapper.bookToBookResponseDto(bookToEdit);
    }

    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        Book book=getBook(bookId);
        Author author=authorService.getAuthor(authorId);
        if(author.getBooks().contains(author)){
            throw new IllegalArgumentException("this author is already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book=getBook(bookId);
        Author author=authorService.getAuthor(authorId);
        if(!(author.getBooks().contains(book))){
            throw new IllegalArgumentException("author doesn't contain a book with this name");
        }
        author.removeBook(book);
        book.deleteAuthor(author);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book=getBook(bookId);
        Category category=categoryService.getCategory(categoryId);
        if(Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("book has already a category");
        }
        book.setCategory(category);
        category.addBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book=getBook(bookId);
        Category category=categoryService.getCategory(categoryId);
        if(!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("book doesn't have a category to remove");
        }
        book.setCategory(null);
        category.removeBook(book);
        return mapper.bookToBookResponseDto(book);
    }
}
