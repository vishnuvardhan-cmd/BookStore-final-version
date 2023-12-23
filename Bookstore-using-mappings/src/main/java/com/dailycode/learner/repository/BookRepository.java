package com.dailycode.learner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dailycode.learner.entity.Book;
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
