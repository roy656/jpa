package com.personal.jpa.repository;

import com.personal.jpa.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("데미안");
        book.setAuthor("누군가");

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }
}