package com.sepfort.letters.repositories;

import com.sepfort.letters.models.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingTestRepository extends JpaRepository<Hello, Integer> {
}
