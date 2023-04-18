package yfu.practice.springboot.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import yfu.practice.springboot.specification.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {}
