package com.example.springbootproject1.repository;

import com.example.springbootproject1.dto.Book;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dbase {
    private static final String URL = "jdbc:postgresql://localhost:5432/second_db";
    private static final String userName = "postgres";
    private static final String password = "rootroot";
    private static Connection connection;
    int index = 0;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(int id) throws SQLException, BookException, IOException, ClassNotFoundException {
        Statement statement = connection.createStatement();
        String SQL = "DELETE FROM Book WHERE id =" + id;
        statement.executeUpdate(SQL);
        return true;
    }


    public boolean save(Book book) throws SQLException {
            Statement statement = connection.createStatement();
           // book.setId(index);
            String SQL = "INSERT INTO book VALUES('" + book.getId() + "','" + book.getName() + "','" + book.getAuthor() + "','" + book.getPage() + "')";
            statement.executeUpdate(SQL);
            //index++;
            return true;
    }


    public Book getBook(int id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Book  WHERE id ='" + id + "'";

            ResultSet resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                Book book = new Book(resultSet);
                return book;
            }
        } catch (Exception ignored) {

        }
        return null;
    }


    public Iterable<Book> getAllBook() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Book ";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Book book = new Book(resultSet);
                books.add(book);
            }
        } catch (Exception ignored) {

        }
        return books;
    }


    public Iterable<Book> getBookByName(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Book  WHERE name LIKE ('%" + name + "%')";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                books.add(new Book(resultSet));

            }

        } catch (Exception ignored) {
        }
        return books;
    }


    public boolean putBook(int id, String authorName, String name, int pageCount, String newAuthorName, String newName, int newPageCount) {
        try {
            Statement statement = connection.createStatement();
            updateByStringColumn(id, name, newName, statement);
            updateByStringColumn(id, authorName, newAuthorName, statement);
            if (pageCount != 0) {
                String SQL = "UPDATE Book SET page = " + newPageCount + " WHERE id =" + id;
                statement.executeUpdate(SQL);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void updateByStringColumn(int id, String columnName, String newName, Statement statement) throws SQLException {
        if (columnName != null) {
            String SQL = "UPDATE Book SET " + columnName + " = '" + newName + "' WHERE id =" + id;
            statement.executeUpdate(SQL);
        }

    }
}

