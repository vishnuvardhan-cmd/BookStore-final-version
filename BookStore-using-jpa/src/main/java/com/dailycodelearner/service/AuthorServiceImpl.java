package com.dailycodelearner.service;

import com.dailycodelearner.dto.mapper;
import com.dailycodelearner.dto.requestDto.AuthorRequestDto;
import com.dailycodelearner.dto.responseDto.AuthorResponseDto;
import com.dailycodelearner.entity.Author;
import com.dailycodelearner.entity.Zipcode;
import com.dailycodelearner.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ZipcodeService zipcodeService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
    }

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author=new Author();
        author.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipcodeId()==null){
            throw new IllegalArgumentException("Author needs a zipcode");
        }
        Zipcode zipcode =zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
        author.setZipcode(zipcode);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {

        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return mapper.authorsToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {

        return mapper.authorToAuthorResponseDto(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(()->new IllegalArgumentException("Author was not" +
                "found with give authorId: "+authorId));
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
        Author author=getAuthor(authorId);
        authorRepository.delete(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        return null;
    }

    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId) {
        return null;
    }

    @Override
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId) {
        return null;
    }
}
