/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.Interfaces.IBookDao;
import DAO.Interfaces.IPublisherDao;
import database.ConectionDB;
import model.Book;
import model.Publisher;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class PublisherDao implements IPublisherDao{

    IBookDao bookDao;

    public PublisherDao() throws Exception {
    }
    
    public PublisherDao(BookDao _bookDao) throws Exception {
    
        bookDao = _bookDao;
    }

    private String publisherPath = "files/publisher.csv";

    @Override
    public void insertPublisher(String name, String url){
        try(FileWriter fw = new FileWriter(this.publisherPath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(getNextPublisherId() + "," + name + "," + url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getNextPublisherId() throws IOException {
        int highestId = 0;

        try (InputStream is = new FileInputStream(this.publisherPath);
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length >= 1) {
                    try {
                        int currentId = Integer.parseInt(bookData[0]);
                        highestId = Math.max(highestId, currentId);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing ID: " + line);
                    }
                }
            }
        }

        return highestId + 1;
    }

    @Override
    public void editPublisher(String name, String url, int publisher_id) throws SQLException {


    }

    @Override
    public Publisher getPublisherById(int publisher_id) throws SQLException {


        return null;
    }

    @Override
    public List<Publisher> getAllPublishers() throws Exception {
        try(InputStream is = new FileInputStream(this.publisherPath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr)){
            String linha;
            List<Publisher> publisherListGlobal = new ArrayList<>();

            while ((linha = br.readLine()) != null) {

                String[] publishers = linha.split(",");

                int publisherId = Integer.parseInt(publishers[0]);
                String name = publishers[1];
                String url = publishers[2];

                publisherListGlobal.add(new Publisher(publisherId, name, url));
            }
            return publisherListGlobal;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Publisher> getPublisherByName(String name) throws SQLException {

//        if(name == null || name.equals("") || name.trim().equals(""))
//        {
//            try {
//                return this.getAllPublishers();
//            } catch (Exception ex) {
//                System.out.println("Erro interno, não foi possivel carregar nenhuma editora");
//            }
//        }
//
//        List<Publisher> publishers = new ArrayList<>();
//        String sql = "SELECT * FROM Publishers WHERE name LIKE ?";
//
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//        try {
//            pstm = conexao.prepareStatement(sql);
//            pstm.setString(1, "%"+name+"%");
//
//            rs = pstm.executeQuery();
//
//            Publisher publisher = null;
//            while (rs.next()) {
//                String names = rs.getString("name");
//                String url = rs.getString("url");
//                int publisherP = rs.getInt("publisher_id");
//
//                publishers.add(new Publisher(publisherP, names, url));
//
//            }
//            if(publishers.size() == 0){
//                JOptionPane.showMessageDialog(null, "Não foi encontrada nenhuma editora com o nome: " + name);
//            }
//            return publishers;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            if(!pstm.isClosed())
//                pstm.close();
//        }
        return null;
        

    }
    
    @Override
    public void deletePublisher(int publisher_id) throws SQLException {

   }

    public void deleteRelationPublisherBooks(int publisher_id) throws Exception{

            try {
                List<Book> books = getBooksWithRelationalPublisher(publisher_id);

                for(Book b: books){
                    bookDao.deleteBook(b.getIsbn());
                }

                System.out.println("Relação excluída com sucesso!!!");
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }

    private List<Book> getBooksWithRelationalPublisher(int publisher_id) throws Exception {
        return null;
    }

}
