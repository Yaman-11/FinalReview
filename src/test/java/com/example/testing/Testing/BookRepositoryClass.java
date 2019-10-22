package com.example.testing.Testing;


import com.example.testing.Testing.Entity.Book;
import com.example.testing.Testing.Repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryClass {
    @Autowired
     private BookRepository bookRepository;

    @Test
    public void show()
    {
      List<Book> b= (List<Book>) bookRepository.findAll();
      Assert.assertNotNull(b);
    }

    @Test
    public void add()
    {
       Book book=new Book();;
       book.setBookid(1);
       book.setTitle("Yaman");
       Book b=bookRepository.save(book);
       bookRepository.findAll();
       Assert.assertEquals(book.getBookid(),b.getBookid());
       Assert.assertEquals(book.getTitle(),b.getTitle());
    }
    @Test
    public void find()
    {
        Book book=new Book();
        book.setBookid(1);
        book.setTitle("Yaman");
        bookRepository.save(book);
        Book b= bookRepository.findById(1).get();
        Assert.assertEquals(book.getTitle(),b.getTitle());
        Assert.assertEquals(book.getBookid(),b.getBookid());
    }
    @Test
    public void delete()
    {
         Book book=new Book();
         book.setBookid(1);
         book.setTitle("Yaman");
         Book b=bookRepository.save(book);
         bookRepository.delete(b);
         Assert.assertEquals(0,bookRepository.count());

    }


}
