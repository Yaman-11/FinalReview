package com.example.testing.Testing;

import com.example.testing.Testing.Controller.BookController;
import com.example.testing.Testing.Entity.Book;
import com.example.testing.Testing.Service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {
    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void show() throws Exception {
        Book book=new Book();
        book.setBookid(1);
        book.setTitle("Yaman");
        Mockito.when(bookService.show()).thenReturn(Arrays.asList(book));
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/show");
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("[{\"bookid\":1,\"title\":\"Yaman\"}]",result.getResponse().getContentAsString());
    }

    @Test
    public void add() throws Exception {
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/add")
                .content("{\"bookid\":1,\"title\":\"Yaman\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

    }
    @Test
    public void find() throws Exception {
        Book book=new Book();
        book.setBookid(1);
        book.setTitle("Yaman");
        Mockito.when(bookService.find(book.getBookid())).thenReturn(book);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/find/1");
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("{\"bookid\":1,\"title\":\"Yaman\"}",result.getResponse().getContentAsString());
        }
        @Test
        public void name() throws Exception {
            Book book=new Book();
            book.setBookid(1);
            book.setTitle("Yaman");
            Mockito.when(bookService.name(book.getBookid())).thenReturn(book.getTitle());
            RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/name/1");
            MvcResult result=mockMvc.perform(requestBuilder).andReturn();
            Assert.assertEquals(book.getTitle(),result.getResponse().getContentAsString());
    }
    @Test
    public void delete() throws Exception {
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/delete/1");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

    }

}






