package com.dailycode.learner.service;

import com.dailycode.learner.dto.mapper;
import com.dailycode.learner.dto.requestdto.AuthorRequestDto;
import com.dailycode.learner.dto.responsedto.AuthorResponseDto;
import com.dailycode.learner.entity.Author;
import com.dailycode.learner.entity.Zipcode;
import com.dailycode.learner.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author=new Author();
        author.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipcodeId()==null){
            throw new IllegalArgumentException("zipcode can't be null");
        }
        Zipcode zipcode=zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
        author.setZipcode(zipcode);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors=new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);
//        StreamSupport.stream(authorRepository.findAll().spliterator(),false).collect(Collectors.toList());
        return mapper.authorsToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        Author author=getAuthor(authorId);

        return mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public Author getAuthor(Long authorId) {
        Author author=authorRepository.findById(authorId).get();
        return author;
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
        Author author=getAuthor(authorId);
        authorRepository.delete(author);

        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author authorToEdit=getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipcodeId()!=null){
            Zipcode zipcode=zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
            authorToEdit.setZipcode(zipcode);
        }
        return mapper.authorToAuthorResponseDto(authorToEdit);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId) {
        Author author=getAuthor(authorId);
        Zipcode zipcode=zipcodeService.getZipcode(zipcodeId);
        if(Objects.nonNull(author.getZipcode())){
            throw new RuntimeException("author already has a zipcode");
        }
        author.setZipcode(zipcode);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId) {
        Author author=getAuthor(authorId);
        author.setZipcode(null);
        return mapper.authorToAuthorResponseDto(author);
    }
}
