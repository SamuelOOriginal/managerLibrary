/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.Interfaces.IAuthorDao;
import model.Author;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDao implements IAuthorDao {
    private String authorPath = "files/author.csv";

    public AuthorDao() throws Exception {
    }

    @Override
    public ArrayList getAllAuthors(){
        try(InputStream is = new FileInputStream(this.authorPath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
        ) {
            String linha;
            ArrayList<Author> authorListGlobal = new ArrayList<>();

            while ((linha = br.readLine()) != null){

                String[] authors = linha.split(",");

                int id = Integer.parseInt(authors[0]);
                String lastName = authors[1];
                String firstNane = authors[2];

                var authorModel = new Author(id,lastName, firstNane);

                authorListGlobal.add(authorModel);
            }
            return authorListGlobal;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void insertAuthor(String fName, String name) throws SQLException {
        try (FileWriter fw = new FileWriter(this.authorPath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(getNextAuthorId() + "," +fName + "," + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getNextAuthorId() throws IOException {
        int highestId = 0;

        try (InputStream is = new FileInputStream(this.authorPath);
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] authorData = line.split(",");
                if (authorData.length >= 1) {
                    try {
                        int currentId = Integer.parseInt(authorData[0]);
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
    public void editAuthor(String name, String fName, int id) throws Exception {

    }

    @Override
    public Author getAuthorById(int id) throws SQLException {
        return null;
    }

    @Override
    public void deleteAuthor(int author_id) throws SQLException {
    }
    
    @Override
    public void deleteRelationAuthorBooks(int author_id) throws SQLException {

    }
}
