/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Interfaces;

import model.Author;
import model.Publisher;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gabri
 */
public interface IPublisherDao {
    
    void insertPublisher(String name, String url);
    
    void editPublisher(String name, String url, int publisher_id) throws SQLException;
    
    Publisher getPublisherById(int id) throws SQLException;
    
    List<Publisher> getPublisherByName(String name) throws SQLException;
    
    List<Publisher> getAllPublishers() throws Exception;
    
    void deletePublisher(int publisher_id) throws SQLException;

    int getByPublishNameReturnId(String fName);
}
