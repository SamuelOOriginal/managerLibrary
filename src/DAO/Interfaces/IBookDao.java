/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Interfaces;

import model.Author;
import model.Book;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabri
 */
public interface IBookDao {
    
    void insertBook(int author_id, String title, String isbn, double price, int publisher_id) throws Exception;
    
    void editBook(String title, double price, String isbn) throws SQLException;
    
    Book getBookByIsbn(String isbn) throws SQLException;
    
    List<Book> getBooksByTitle(String title) throws SQLException;

    List<Book> getAllBooks() throws Exception;
    
    void deleteBook(String isbn) throws SQLException;

}
