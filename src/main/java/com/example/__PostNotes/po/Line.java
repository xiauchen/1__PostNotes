package com.example.__PostNotes.po;

import javax.persistence.*;

@Entity
@Table(name = "p_line")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="context")
    private String context;

    @ManyToOne
    @JoinColumn(name="title_id")
    private Title title;
//    private Long titleId;

    public Line() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", title=" + title +
                '}';
    }
}
