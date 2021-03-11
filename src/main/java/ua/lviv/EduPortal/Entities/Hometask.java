package ua.lviv.EduPortal.Entities;

import javax.persistence.*;

@Entity
@Table(name = "hometask")
public class Hometask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String task;
    @Column(name = "max_mark", nullable = false)
    private int maxMark;

    public Hometask(String task, int maxMark) {
        this.task = task;
        this.maxMark = maxMark;
    }

    public Hometask() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    @Override
    public String toString() {
        return "Hometask{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", maxMark=" + maxMark +
                '}';
    }
}
