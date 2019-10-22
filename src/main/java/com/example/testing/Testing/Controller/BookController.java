package com.example.testing.Testing.Controller;


import com.example.testing.Testing.Entity.Book;
import com.example.testing.Testing.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @RequestMapping(method = RequestMethod.GET,value="/show")
    public List<Book> show()
    {
        return bookService.show();

    }

    @RequestMapping(method = RequestMethod.POST,value="/add")
    public ResponseEntity<List<Book>> add(@RequestBody Book book)
    {
        return bookService.add(book);
    }

    @RequestMapping(method = RequestMethod.GET,value="/find/{id}",produces = "application/json")
    public Book find(@PathVariable int id)
    {
        return bookService.find(id);
    }

    @RequestMapping(method = RequestMethod.GET,value="/name/{id}")
    public String name(@PathVariable  int id)
    {
        return bookService.name(id);
    }

    @RequestMapping(method = RequestMethod.POST,value="delete/{id}")
    public String delete(@PathVariable int id)
    {
        return bookService.delete(id);
    }


}
