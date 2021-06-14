package com.kafka.consumer.models;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user-details", path = "user-details")
public interface UserDetailsRepository extends PagingAndSortingRepository<UserDetails, Long> {
    UserDetails findByUserName(@Param("username") String userName);
}
