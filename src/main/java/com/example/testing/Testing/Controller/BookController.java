package com.example.testing.Testing.Controller;


import com.example.testing.Testing.Entity.Book;
import com.example.testing.Testing.Service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class BookController {
    private static final Logger logger = LogManager.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @RequestMapping(method = RequestMethod.GET,value="/show",produces = "application/json")
    public List<Book> show()
    {
        logger.info("Request called for /show");
        return bookService.show();

    }

    @RequestMapping(method = RequestMethod.POST,value="/add",produces = "application/json")
    public ResponseEntity<List<Book>> add(@RequestBody Book book)
    {
        return bookService.add(book);
    }

    @RequestMapping(method = RequestMethod.GET,value="/find/{id}",produces = "application/json")
    public Book find(@PathVariable int id)
    {
        logger.info("Request called for /find/{}",id);
        return bookService.find(id);

    }

    @RequestMapping(method = RequestMethod.GET,value="/name/{id}")
    public String name(@PathVariable  int id, HttpServletResponse response)
    {
        logger.info("Request called for /name/{}",id);
        if(bookService.name(id).equals("ID not found")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
            return bookService.name(id);
    }

    @RequestMapping(method = RequestMethod.POST,value="delete/{id}")
    public String delete(@PathVariable int id, HttpServletResponse response)
    {
        logger.info("Request called for /delete/{}",id);
        if(bookService.delete(id).equals("ID not found")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return bookService.delete(id);
    }


}
