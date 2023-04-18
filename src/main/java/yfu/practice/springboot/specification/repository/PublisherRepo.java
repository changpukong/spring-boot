package yfu.practice.springboot.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yfu.practice.springboot.specification.entity.Publisher;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, String> {}
