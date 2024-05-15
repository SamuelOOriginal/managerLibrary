/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package View;

import model.Author;
import model.Book;
import model.Publisher;

import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author gabri
 */
public interface View {
    public Author getAddAuthor();
    
    public Author getAuthorSelected();
    
    public Author getSearchAuthors();
    
    public Author getEditAuthors();
    
    public int getDeleteAuthor();
    
    public void addActionListnerAuthor(ActionListener al);
    
    public void editActionListnerAuthor(ActionListener al);
    
    public void excludeActionListnerAuthor(ActionListener al);
    
    public void addExistentAuthorsToListActionListner(ActionListener al);
    
    public void removeExistentAuthorsToListActionListner(ActionListener al);
    
    public Book getAddBook() throws Exception;
    
    public String getSearchBooks();
    
    public Book getEditBooks();
    
    public String getDeleteBook();
    
    public void addActionListnerBook(ActionListener al);
    
    public void editActionListnerBook(ActionListener al);
    
    public void excludeActionListnerBook(ActionListener al);
    
    public Publisher getAddPublisher();
    
    public String getListPublishers();
    
    public Publisher getPublisherSelected();
    
    public Publisher getEditPublishers();
    
    public int getDeletePublishers();
    
    public String getSearchPublishers();
    
    public void addActionListnerPublisher(ActionListener al);
    
    public void editActionListnerPublisher(ActionListener al);
    
    public void excludeActionListnerPublisher(ActionListener al);

    public void atualizaTextoListaAutores(List<Author> authors);

    public void initVisualComponents(List<Book> books, List<Publisher> publishers, List<Author> authors);
    
    public void refreshVisualComponents(List<Book> books, List<Publisher> publishers, List<Author> authors);


    public void searchActionListnerPublisher(ActionListener al);

    public void searchActionListnerBook(ActionListener al);

    public void searchActionListnerAutor(ActionListener al);

}
