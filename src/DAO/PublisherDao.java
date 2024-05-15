/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.Interfaces.IBookDao;
import DAO.Interfaces.IPublisherDao;
import model.Book;
import model.Publisher;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    @Override
    public void editPublisher(String name, String url, int publisher_id) throws SQLException {
        try(InputStream is = new FileInputStream(this.publisherPath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr)){
            String linha;
            List<Publisher> publisherListGlobal = new ArrayList<>();
            String lineToEdit = String.valueOf(publisher_id);

            while ((linha = br.readLine()) != null) {

                String[] publishers = linha.split(",");

                int publisherId = Integer.parseInt(publishers[0]);
                String namePublisher = publishers[1];
                String urlPublisher = publishers[2];

                if(publisherId == publisher_id){
                    namePublisher = name;
                    urlPublisher = url;
                }

                publisherListGlobal.add(new Publisher(publisherId, namePublisher, urlPublisher));
            }

            File inputFile = new File(this.publisherPath);
            File tempFile = new File(this.publisherPath);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                for (Publisher p : publisherListGlobal) {
                    writer.write(p.getPublisher_id() + "," + p.getName() + "," + p.getUrl() + System.getProperty("line.separator"));
                }
                writer.close();
                inputFile.delete();
                tempFile.renameTo(inputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Publisher getPublisherById(int publisher_id) throws SQLException {


        return null;
    }

    @Override
    public List<Publisher> getPublisherByName(String name) throws SQLException {
        try(InputStream is = new FileInputStream(this.publisherPath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr)){
            String linha;
            List<Publisher> publisherListGlobal = new ArrayList<>();

            while ((linha = br.readLine()) != null) {

                String[] publishers = linha.split(",");

                int publisherId = Integer.parseInt(publishers[0]);
                String namePublisher = publishers[1];
                String urlPublisher = publishers[2];

                if(namePublisher.equals(name)){
                    publisherListGlobal.add(new Publisher(publisherId, namePublisher, urlPublisher));
                }
            }
            return publisherListGlobal;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public void deletePublisher(int publisher_id) throws SQLException {
        try {

            String lineToRemove = String.valueOf(publisher_id);
            String currentLine;

            File inputFile = new File(this.publisherPath);
            File tempFile = new File("files/publisherTemp.csv");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                while ((currentLine = reader.readLine()) != null) {
                    String[] publishers = currentLine.split(",");
                    if (!publishers[0].equals(lineToRemove)) {
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                }
                writer.close();
                reader.close();
                inputFile.delete();
                tempFile.renameTo(inputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
   }



    @Override
    public int getByPublishNameReturnId(String fName) {
        try(InputStream is = new FileInputStream(this.publisherPath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
        ) {
            String linha;
            while ((linha = br.readLine()) != null){

                String[] publishers = linha.split(",");

                int id = Integer.parseInt(publishers[0]);
                String name = publishers[1];

                if (name.equals(fName)){
                    return id;
                }
            }
            return -1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

}
