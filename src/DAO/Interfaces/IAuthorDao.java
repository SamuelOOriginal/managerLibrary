/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Interfaces;

import model.Author;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabri
 */
public interface IAuthorDao {
    
    void insertAuthor(String name, String fName) throws SQLException;
    
    void editAuthor(String name, String fName, int author_id) throws SQLException;
    
    Author getAuthorById(int id) throws SQLException;

    List<Author> getAllAuthors() throws Exception;
    
    void deleteAuthor(int author_id) throws SQLException;
    
   void deleteRelationAuthorBooks(int author_id) throws SQLException;

   Author getAuthorByNames(String fName, String lName);

    int getByNameReturnId(String fName);
}
