
package DAO;

import DAO.Interfaces.IBookDao;
import model.Book;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao{
    public BookDao() throws Exception {
    }
    private String bookPath = "files/book.csv";


    @Override
    public void insertBook(int author_id, String title, String isbn, double price, int publisher_id) throws Exception {
        try (FileWriter fw = new FileWriter(this.bookPath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(getNextBookId() + "," + author_id + "," + title + "," + isbn + "," + price + "," + publisher_id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList getAllBooks() {
        try (
                InputStream is = new FileInputStream(this.bookPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ){
            String linha;
            ArrayList<Book> bookListGlobal = new ArrayList<>();

            while ((linha = br.readLine()) != null) {

                String[] books = linha.split(",");

                int authorId = Integer.parseInt(books[1]);
                String title = books[2];
                String isbn = books[3];
                double price = Double.parseDouble(books[4]);
                int publisherId = Integer.parseInt(books[5]);

                var bookModel = new Book(authorId, title, isbn, price, publisherId);

                bookListGlobal.add(bookModel);
            }
            return bookListGlobal;
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(String isbn) throws SQLException {
        try {
            File inputFile = new File(this.bookPath);
            File tempFile = new File("files/myTempFile.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = isbn;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] books = currentLine.split(",");
                if (!books[3].equals(lineToRemove)) {
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
    }

    @Override
    public void editBook(String title, double price, String isbn) throws SQLException {
        try(InputStream is = new FileInputStream(this.bookPath);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr)){
            String linha;
            ArrayList<Book> bookListGlobal = new ArrayList<>();
            String lineToEdit = isbn;

            while ((linha = br.readLine()) != null) {

                String[] books = linha.split(",");

                int authorId = Integer.parseInt(books[1]);
                String titleBook = books[2];
                String isbnBook = books[3];
                double priceBook = Double.parseDouble(books[4]);
                int publisherId = Integer.parseInt(books[5]);

                if (isbnBook.equals(lineToEdit)) {
                    titleBook = title;
                    priceBook = price;
                    authorId = Integer.parseInt(books[1]);
                    publisherId = Integer.parseInt(books[5]);
                }

                var bookModel = new Book(authorId, titleBook, isbnBook, priceBook, publisherId);
                bookListGlobal.add(bookModel);
            }

            try (FileWriter fw = new FileWriter(this.bookPath, false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                for (Book book : bookListGlobal) {
                    out.println(getNextBookId() + "," + book.getAuthor_id() + "," + book.getTitle() + "," + book.getIsbn() + "," + book.getPrice() + "," + book.getPublisher_id());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getBookByIsbn(String isbn) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getBooksByTitle(String title) throws SQLException {
        try(
                InputStream is = new FileInputStream(this.bookPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ){
            String linha;
            List<Book> bookListGlobal = new ArrayList<>();

            while ((linha = br.readLine()) != null) {

                String[] books = linha.split(",");

                int authorId = Integer.parseInt(books[1]);
                String titleBook = books[2];
                String isbn = books[3];
                double price = Double.parseDouble(books[4]);
                int publisherId = Integer.parseInt(books[5]);

                if(titleBook.equals(title)){
                    var bookModel = new Book(authorId, titleBook, isbn, price, publisherId);
                    bookListGlobal.add(bookModel);
                }
            }
            System.out.println(bookListGlobal);
            return bookListGlobal;
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private int getNextBookId() throws IOException {
        int highestId = 0;

        try (InputStream is = new FileInputStream(this.bookPath);
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