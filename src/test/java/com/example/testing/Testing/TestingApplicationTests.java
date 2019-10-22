package com.example.testing.Testing;

import com.example.testing.Testing.Entity.Book;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TestingApplicationTests {
@Autowired
private TestRestTemplate testRestTemplate;

private HttpHeaders httpHeaders;
@Test
public void show()
{
   String response=  testRestTemplate.getForObject("/show",String.class);
   DocumentContext documentContext=JsonPath.parse(response);
   int n=documentContext.read("$.length()");
   Assert.assertEquals(5,n);
}

@Test
public void add()
{
	Book book=new Book();
	book.setTitle("Nikhil");
	book.setBookid(6);
	HttpEntity<Book> httpEntity=new HttpEntity<Book>(book,httpHeaders);
    ResponseEntity<String> response=testRestTemplate.exchange(createUrl("/add")
			,HttpMethod.POST,httpEntity,String.class);
    Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
}
@Test
public void find()
{
	HttpEntity<String> httpEntity=new HttpEntity<String>(null,httpHeaders);
	ResponseEntity<String> response=testRestTemplate.exchange(createUrl("/find/4")
			,HttpMethod.GET,httpEntity,String.class);
	Assert.assertEquals("{\"bookid\":4,\"title\":\"Deepali\"}",response.getBody());
}
@Test
public void name()
{
	HttpEntity<String> httpEntity=new HttpEntity<String>(null,httpHeaders);
	ResponseEntity<String> response=testRestTemplate.exchange(createUrl("/name/3"),HttpMethod.GET,httpEntity,String.class);
	Assert.assertEquals("Dilip",response.getBody());

}
@Test
public void delete()
{
	HttpEntity<String> httpEntity=new HttpEntity<String>(null,httpHeaders);
	ResponseEntity<String> response=testRestTemplate.exchange(createUrl("/delete/3"),HttpMethod.POST,httpEntity,String.class);
	Assert.assertEquals("DELETED SUCCESSFULLY",response.getBody());
}


public String createUrl(String url)
{
	return "http://localhost:8080"+url;
}


}