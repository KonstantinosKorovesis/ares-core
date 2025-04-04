package com.spacex.marsops.arescore.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.spacex.marsops.arescore.model.Resource;

@Repository
public interface AresCoreRepository extends CrudRepository<Resource, Long> {
}