package ua.lviv.EduPortal.Entities;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hometask_id", nullable = false)
    private Hometask hometask;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerFile_id", referencedColumnName = "id")
    private AnswerFile answerFile;
    @Column(nullable = true)
    private String response;
    @Column(nullable = false)
    private double mark;

    public Answer(Hometask hometask, User user) {
        this.hometask = hometask;
        this.user = user;
    }

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hometask getHometask() {
        return hometask;
    }

    public void setHometask(Hometask hometask) {
        this.hometask = hometask;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public AnswerFile getAnswerFile() {
        return answerFile;
    }

    public void setAnswerFile(AnswerFile answerFile) {
        this.answerFile = answerFile;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", hometask=" + hometask +
                ", user=" + user +
                ", response='" + response + '\'' +
                ", mark=" + mark +
                '}';
    }
}
