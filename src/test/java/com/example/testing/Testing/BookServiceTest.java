package com.example.testing.Testing;

import com.example.testing.Testing.Entity.Book;
import com.example.testing.Testing.Repository.BookRepository;
import com.example.testing.Testing.Service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    public void show()
    {
      Book book=new Book();
      book.setBookid(1);
      book.setTitle("Yaman");
      Mockito.when(bookRepository.findAll()).thenReturn((Arrays.asList(book)));
      List<Book> b=bookService.show();
      Assert.assertEquals(Arrays.asList(book),b);




    }
    @Test
   public void add()
    {
        Book book=new Book();
        book.setBookid(1);
        book.setTitle("Yama");
        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        ResponseEntity<List<Book>>b=bookService.add(book);
        Assert.assertEquals(new ResponseEntity<>(Arrays.asList(book),HttpStatus.OK),b);
    }
    @Test
    public void find()
    {
        Book book=new Book();
        book.setBookid(1);
        book.setTitle("Yaman");
        Mockito.when(bookRepository.findById(book.getBookid())).thenReturn(java.util.Optional.of(book));
        Book b= bookService.find(1);
        Assert.assertEquals(book,b);
    }
    @Test
    public void name()
    {
        Book book=new Book();
        book.setBookid(1);
        book.setTitle("Yaman");
        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        String b=bookService.name(1);
        Assert.assertEquals(book.getTitle(),b);
    }
    @Test
    public void delete()
    {
        Book book=new Book();
      book.setBookid(1);
      book.setTitle("Yaman");
      bookRepository.save(book);
      Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
      String b=bookService.delete(1);
      Assert.assertEquals("DELETED SUCCESSFULLY",b);
    }

}
//fytfyuygyyu