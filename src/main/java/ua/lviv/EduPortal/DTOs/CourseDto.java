package ua.lviv.EduPortal.DTOs;

import ua.lviv.EduPortal.Entities.Chapter;
import ua.lviv.EduPortal.Entities.User;

public class CourseDto {

    private int id;
    private String title;
    private String description;
    private byte[] logoPicture;
    private Boolean paid;
    private Double price;
    private Long likes;
    private Boolean meLiked;
    private User author;
    private Chapter chapter;

    public CourseDto(int id, String title, String description, byte[] logoPicture, Boolean paid,
                     Double price, Long likes, Boolean meLiked, User author, Chapter chapter) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logoPicture = logoPicture;
        this.paid = paid;
        this.price = price;
        this.likes = likes;
        this.meLiked = meLiked;
        this.author = author;
        this.chapter = chapter;
    }

    public CourseDto(int id, String title, String description, byte[] logoPicture,
                     Boolean paid, Double price, Long likes, User author, Chapter chapter) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logoPicture = logoPicture;
        this.paid = paid;
        this.price = price;
        this.likes = likes;
        this.author = author;
        this.chapter = chapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getLogoPicture() {
        return logoPicture;
    }

    public void setLogoPicture(byte[] logoPicture) {
        this.logoPicture = logoPicture;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    public void setMeLiked(Boolean meLiked) {
        this.meLiked = meLiked;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}
