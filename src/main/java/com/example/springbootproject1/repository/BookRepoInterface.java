package com.example.springbootproject1.repository;

import com.example.springbootproject1.dto.Book;


import java.io.IOException;
import java.sql.SQLException;

public interface BookRepoInterface {

    boolean save(Book book) throws BookException, IOException, ClassNotFoundException, SQLException;

    boolean delete(int id) throws SQLException, BookException, IOException, ClassNotFoundException;

    String getBook(int id) throws IOException, ClassNotFoundException, SQLException;

    String getAllBook() throws IOException, ClassNotFoundException, SQLException;

    String getBookByName(String name) throws BookException, IOException, ClassNotFoundException, SQLException;

    int getIdByName(String name);

    String putBook(int id, String authorName, String name,int pageCount,String newAuthorName,String newName,int newPageCount) throws SQLException;

}
