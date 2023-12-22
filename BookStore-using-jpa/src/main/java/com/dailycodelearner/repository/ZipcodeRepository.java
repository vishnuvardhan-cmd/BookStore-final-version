package com.dailycodelearner.repository;

import com.dailycodelearner.entity.Zipcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRepository extends CrudRepository<Zipcode,Long> {
}
