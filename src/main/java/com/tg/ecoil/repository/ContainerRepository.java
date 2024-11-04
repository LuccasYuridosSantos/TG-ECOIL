package com.tg.ecoil.repository;

import com.tg.ecoil.model.dto.Container;
import com.tg.ecoil.model.dto.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends MongoRepository<Container, String> {

}
