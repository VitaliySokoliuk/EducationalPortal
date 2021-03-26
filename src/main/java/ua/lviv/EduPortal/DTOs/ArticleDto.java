package ua.lviv.EduPortal.DTOs;

public class ArticleDto {

    private int id;
    private String title;
    private String description;
    private byte[] logoPicture;
    private Long likes;
    private Boolean meLiked;

    public ArticleDto(int id, String title, String description, byte[] logoPicture, Long likes, Boolean meLiked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logoPicture = logoPicture;
        this.likes = likes;
        this.meLiked = meLiked;
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

    public boolean getMeLiked() {
        return meLiked;
    }

    public void setMeLiked(boolean meLiked) {
        this.meLiked = meLiked;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}
