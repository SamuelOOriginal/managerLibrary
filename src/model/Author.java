package model;

public class Author {

    private int id;
    private String lastName;
    private String firstNane;

    public Author(int id, String lastName, String firstNane) {
        this.id = id;
        this.lastName = lastName;
        this.firstNane = firstNane;
    }

    public Author(String lastName, String firstNane) {
        this.lastName = lastName;
        this.firstNane = firstNane;
    }
    public Author(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstNane;
    }

    public void setFirstNane(String firstNane) {
        this.firstNane = firstNane;
    }

    @Override
    public String toString() {
        return getFirstName() +" "+ getLastName();
    }
    
}
