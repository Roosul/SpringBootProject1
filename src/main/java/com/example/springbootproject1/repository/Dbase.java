package com.example.springbootproject1.repository;

import com.example.springbootproject1.dto.Book;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class Dbase implements BookRepoInterface {
    private static String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static String userName = "postgres";
    private static String password = "rootroot";
    private static final HashMap<Integer, Book> books = new HashMap<>();
    int index = 0;

    private static Connection connection;



    static {
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean save(Book book) throws BookException, IOException, ClassNotFoundException, SQLException {
        Statement statement = connection.createStatement();
        String SQL = "INSERT INTO Book VALUES('"+book.getName()+"','"+book.getAuthor()+"','"+book.getPage()+"')";
        statement.executeUpdate(SQL);
        return true;
    }

    @Override
    public boolean delete(String name) {
        return false;
    }

    @Override
    public String getBook(int id) throws IOException, ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public String getAllBook() throws IOException, ClassNotFoundException, SQLException {
        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM Book";
        ResultSet resultSet = statement.executeQuery(SQL);
        while (resultSet.next()) {
            Book book = new Book();
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setPage(resultSet.getInt("id"));
            books.put(index, book);
            index++;

        }
        StringBuilder q = new StringBuilder();
        for (int j = 0; j <= index - 1; j++)
            if(books.get(j)!=null)
                q.append(books.get(j).toString());
        index = 0;
        return q.toString();
    }

    @Override
    public Book getBookByName(String name) throws BookException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getIdByName(String name) {
        return 0;
    }

    @Override
    public String putBook(int id, String author, String name) throws BookException {
        return null;
    }
}
