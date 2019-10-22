package com.example.testing.Testing.Service;


import com.example.testing.Testing.Entity.Book;
import com.example.testing.Testing.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

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
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    bookRepository.save(book);
     List<Book>al=new ArrayList<>();
    for(Book b:bookRepository.findAll())
    {
        al.add(b);
    }
      return new ResponseEntity<>(al,HttpStatus.OK);
    }


    public Book find(int id) {
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
        return null;
    }

    public String delete(int id) {

        for(Book b:bookRepository.findAll())
        {
            if(b.getBookid()==id)
            {
                bookRepository.delete(b);
            }
        }
        return "DELETED SUCCESSFULLY";

    }
}
