package model;

public class Publisher {

    private int publisher_id;
    private String name;
    private String url;

    public Publisher(int publisher_id, String name, String url) {
        this.publisher_id = publisher_id;
        this.name = name;
        this.url = url;
    }
    public Publisher(){}

    public int getPublisher_id() {
        return this.publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return getName();
    }
}
