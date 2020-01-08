package com.example.__PostNotes.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* */
@Entity
@Table(name="p_title")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createTime")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updateTime")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deadTime")
    private Date deadTime;
    //Persist,Remove,Merge,Detach,Refresh,All
    @OneToMany(mappedBy = "title",cascade=CascadeType.ALL)
    private List<Line> line = new ArrayList<>();

    @Column(name="life")
    private boolean life;

    public Title() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public List<Line> getLine() {
        return line;
    }

    public void setLine(List<Line> line) {
        this.line = line;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deadTime=" + deadTime +
                ", life=" + life +
                '}';
    }
}
