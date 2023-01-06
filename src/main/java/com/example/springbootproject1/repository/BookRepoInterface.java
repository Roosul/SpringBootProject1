package com.example.springbootproject1.repository;

import com.example.springbootproject1.dto.Book;


import java.io.IOException;
import java.sql.SQLException;

public interface BookRepoInterface {

    boolean save(Book book) throws BookException, IOException, ClassNotFoundException, SQLException;

    boolean delete(String name);

    String getBook(int id) throws IOException, ClassNotFoundException, SQLException;

    String getAllBook() throws IOException, ClassNotFoundException, SQLException;

    Book getBookByName(String name) throws BookException, IOException, ClassNotFoundException;

    int getIdByName(String name);

    String putBook(int id, String author, String name) throws BookException;
}
