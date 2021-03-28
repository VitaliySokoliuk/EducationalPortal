package ua.lviv.EduPortal.DTOs;

public class CourseDto {

    private int id;
    private String title;
    private String description;
    private byte[] logoPicture;
    private Boolean visibility;
    private Long likes;
    private Boolean meLiked;

    public CourseDto(int id, String title, String description, byte[] logoPicture, Long likes, Boolean meLiked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logoPicture = logoPicture;
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public CourseDto(int id, String title, String description, byte[] logoPicture, boolean visibility, Long likes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logoPicture = logoPicture;
        this.visibility = visibility;
        this.likes = likes;
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

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
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
