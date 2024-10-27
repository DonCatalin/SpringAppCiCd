package com.example.actionwithspring.book;

import com.example.actionwithspring.BookController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@WebMvcTest(controllers = {BookController.class})
public class BookControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;


    @Test
    public void whenGetAllBooks_thenControlFlowCorrect() throws Exception {
        Mockito.when(bookService.getBookList()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/book").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }


    @Test
    public void whenAddBook_thenControlFlowCorrect() throws Exception {
        Mockito.doNothing().when(bookService).addBook(Mockito.any());
        Book book = Book.builder().id("1").colour("red").name("bookName").build();
        String reqBody = new ObjectMapper().writeValueAsString(book);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/book").content(reqBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(this.bookService, Mockito.times(1)).addBook(Mockito.any());


    }

}
