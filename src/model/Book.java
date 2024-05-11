package model;

import java.util.List;

public class Book {
    private int id;
    private int author_id;
    private String title;
    private String isbn;
    private double price;
    private int publisher_id;

    public Book(int authorId, String title, String isbn, double price) {
        author_id = authorId;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
    }
    
    public Book(int authorId, String title, String isbn, double price, int publisher_id) {
        author_id = authorId;
        this.title = title;
       this.isbn = isbn;
       this.price = price;
       this.publisher_id = publisher_id;
    }

    public Book(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    @Override
    public String toString() {
        return getAuthor_id() + " "+ getTitle() +" " + getIsbn() +" "+ getPrice() +" "+ getPublisher_id();
    }
}
