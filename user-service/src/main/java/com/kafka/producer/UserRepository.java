package com.kafka.producer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "user",path = "user")
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    User findByUserName(@Param("username") String userName);
}
