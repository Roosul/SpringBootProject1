package com.example.springbootproject1.service;

import com.example.springbootproject1.dto.Book;
import com.example.springbootproject1.repository.BookException;
import com.example.springbootproject1.repository.BookRepoHashMap;
import com.example.springbootproject1.repository.Dbase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class Service {

    final BookRepoHashMap bookRepoHashMap = new BookRepoHashMap();
    final Dbase dbase = new Dbase();

    public String addBook(int id,String name, String author, int page) {
        Book book = new Book(id,name, author, page);
        try {
            if (dbase.save(book))
                return "Книга создана";
            return "Такая книга уже существует";
        } catch (Exception e) {
            return e.toString();

        }
    }

    public Book getBook(int id) throws Exception {
        Book book = dbase.getBook(id);
        if (book == null) {
            throw new Exception("Такой книги нет");
        }
        return book;

    }

    public String getAllBook() {
        return dbase.getAllBook().toString();
    }

    public String getBookByName(String name) {
        List<Book> book = (List<Book>) dbase.getBookByName(name);
        if (book == null || book.isEmpty())
            return "Такая книга не найдена";
        return book.toString();
    }

    public int deleteBook(int id) {
        try {
            if (dbase.delete(id))
                return 1;
        } catch (SQLException | ClassNotFoundException | BookException | IOException e) {
            return 0;
        }
        return 0;
    }

    public String putBook(int id, String authorName, String name, int pageCount, String newAuthorName, String newName, int newPageCount) {

        if (dbase.putBook(id, authorName, name, pageCount, newAuthorName, newName, newPageCount))
            return "Изменения приняты";
        return "Ошибка";
    }
}

