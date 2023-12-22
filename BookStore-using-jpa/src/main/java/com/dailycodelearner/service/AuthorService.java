package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestDto.AuthorRequestDto;
import com.dailycodelearner.dto.responseDto.AuthorResponseDto;
import com.dailycodelearner.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);
    public List<AuthorResponseDto> getAuthors();

    public AuthorResponseDto getAuthorById(Long authorId);
    public Author getAuthor(Long authorId);

    public AuthorResponseDto deleteAuthor(Long authorId);
    public AuthorResponseDto editAuthor(Long authorId,AuthorRequestDto authorRequestDto);
    public AuthorResponseDto addZipcodeToAuthor(Long authorId,Long zipcodeId);
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId);
}
