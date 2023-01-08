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
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Book WHERE id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        return true;
    }


    public boolean save(Book book) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Book VALUES(?,?,?,?)");
            preparedStatement.setInt(1,book.getId());
            preparedStatement.setString(2,book.getName());
            preparedStatement.setString(3,book.getAuthor());
            preparedStatement.setInt(4,book.getPage());
            preparedStatement.executeUpdate();
            return true;
    }


    public Book getBook(int id) throws Exception {
        Book book = new Book();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Book  WHERE id =?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("authorName"));
            book.setId(resultSet.getInt("page"));
            return book;

        } catch (Exception e) {
            throw new Exception();

        }

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


    public boolean putBook(int id, String authorName, String name, String newParam) {
        try {
            updateByStringColumn(id, name, newParam);
            updateByStringColumn(id, authorName, newParam);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void updateByStringColumn(int id, String columnName, String newParam) throws SQLException {
        if (columnName != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Book SET columnName = ?  WHERE id = ?");
            preparedStatement.setString(1,newParam);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }

    }
}

