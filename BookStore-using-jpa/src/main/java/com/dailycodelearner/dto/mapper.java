package com.dailycodelearner.dto;

import com.dailycodelearner.dto.responseDto.AuthorResponseDto;
import com.dailycodelearner.dto.responseDto.BookResponseDto;
import com.dailycodelearner.dto.responseDto.CategoryResponseDto;
import com.dailycodelearner.entity.Author;
import com.dailycodelearner.entity.Book;
import com.dailycodelearner.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class mapper {

    public static BookResponseDto bookToBookResponseDto(Book book){

        BookResponseDto bookResponseDto=new BookResponseDto();
        bookResponseDto.setName(book.getName());
        bookResponseDto.setCategory(book.getCategory().getName());
        List<String> names=new ArrayList<>();
        List<Author> authors=book.getAuthors();

        for(Author a:authors){
            names.add(a.getName());
        }
        bookResponseDto.setAuthorNames(names);
        return bookResponseDto;
    }

    public static List<BookResponseDto> booksToBookResponseDtos(List<Book> books){
        List<BookResponseDto> bookResponseDtos=new ArrayList<>();
        for(Book book:books){
            bookResponseDtos.add(bookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }

    public static AuthorResponseDto authorToAuthorResponseDto(Author author){
        AuthorResponseDto authorResponseDto=new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());
        authorResponseDto.setZipcodeName(author.getZipcode().getName());
        List<String> names=new ArrayList<>();
        List<Book> books=author.getBooks();
        for(Book book:books){
            names.add(book.getName());
        }
        authorResponseDto.setBookNames(names);
        return authorResponseDto;
    }

    public static List<AuthorResponseDto> authorsToAuthorResponseDtos(List<Author> authors){
        List<AuthorResponseDto> authorResponseDtos=new ArrayList<>();
        for(Author author:authors){
            authorResponseDtos.add(authorToAuthorResponseDto(author));
        }
        return authorResponseDtos;
    }

    public static CategoryResponseDto categoryToCategoryResponseDto(Category category){
        CategoryResponseDto categoryResponseDto=new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        List<String> names=new ArrayList<>();
        List<Book> books = category.getBooks();
        for(Book book:books){
            names.add(book.getName());
        }
        categoryResponseDto.setBookNames(names);
        return categoryResponseDto;
    }

    public static List<CategoryResponseDto> categoriesToCategoryResponseDtos(List<Category> categories){
        List<CategoryResponseDto> CategoryResponseDtos=new ArrayList<>();
        for(Category category:categories){
            CategoryResponseDtos.add(categoryToCategoryResponseDto(category));
        }
        return CategoryResponseDtos;
    }
}
