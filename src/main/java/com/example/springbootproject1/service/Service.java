package com.example.springbootproject1.service;
import com.example.springbootproject1.dto.Book;
import com.example.springbootproject1.repository.BookException;
import com.example.springbootproject1.repository.BookRepoHashMap;

import java.io.IOException;

public class Service {

    final BookRepoHashMap bookRepoHashMap = new BookRepoHashMap();

    public String addBook(String name, String author, int page)  {
        Book book = new Book(name, author, page);
        try {
            if(bookRepoHashMap.save(book))
                return "Книга создана";
            return "Такая книга уже существует";
        } catch (Exception e) {
            return e.toString();

        }


    }

    public String getBook(int id) {

        try {
            return bookRepoHashMap.getBook(id);
        }
        catch (RuntimeException e){
            return "Такой книги нет";
        } catch (IOException | ClassNotFoundException e) {
            return e.toString();
        }
    }

    public String getAllBook() throws IOException, ClassNotFoundException {

        return bookRepoHashMap.getAllBook();
    }

    public String getBookByName(String name) {

        try {
            Book book = bookRepoHashMap.getBookByName(name);
            if (book == null)
                return "Такая книга не найдена";
            return book.toString();
        } catch (IOException | ClassNotFoundException | BookException e) {
            throw new RuntimeException(e);
        }

    }

    public String deleteBook(String name) {
        if (bookRepoHashMap.delete(name))
            return "Книга удалена";
        return "Такой книги с таким названием нет";

    }

    public String putBook(int id, String author, String name) throws BookException {

        return bookRepoHashMap.putBook(id, author, name);
    }
}

