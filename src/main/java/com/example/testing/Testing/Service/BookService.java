package com.example.testing.Testing.Service;


import com.example.testing.Testing.Entity.Book;
import com.example.testing.Testing.Repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    private static final Logger logger = LogManager.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;


    public List<Book> show() {
        List<Book> al = new ArrayList<>();
        for (Book b : bookRepository.findAll()) {
            al.add(b);

        }
        return al;
    }

    public ResponseEntity<List<Book>> add(Book book) {
    if(book.getTitle().length()>=5)
    {
        logger.error("Error!,Title length greater than or equal to 5");
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    Book book1= bookRepository.save(book);
    logger.info("Added item is bookid:{}, title:{}",book1.getBookid(),book1.getTitle());
     List<Book>al=new ArrayList<>();
    for(Book b:bookRepository.findAll())
    {
        al.add(b);
    }

      return new ResponseEntity<>(al,HttpStatus.OK);
    }


    public Book find(int id) {
        try {
            if (bookRepository.findById(id).get() == null) {
                throw new NoSuchElementException();
            }
        }
        catch (NoSuchElementException e){
            logger.error("Error, element not found!",e);
        }

        return bookRepository.findById(id).get();
    }


    public String name(int id)
    {
        for(Book b:bookRepository.findAll())
        {
            if(b.getBookid()==id)
            {
                return b.getTitle();
            }
        }
        logger.error("ID "+id+" not found",new NoSuchElementException());
        return "ID not found";
    }

    public String delete(int id) {

        for(Book b:bookRepository.findAll())
        {
            if(b.getBookid()==id)
            {
                bookRepository.delete(b);
                return "DELETED SUCCESSFULLY";
            }
        }
        logger.error("{} not found",id);
        return "ID not found";

    }
}
