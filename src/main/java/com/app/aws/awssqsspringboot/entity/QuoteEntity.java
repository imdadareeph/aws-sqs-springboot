package com.app.aws.awssqsspringboot.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Builder
@Entity
@AllArgsConstructor
@Table
public class QuoteEntity {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String text;
    @Column
    private String author;
    @Column
    private String awsMessageId;
    @Column
    private Timestamp dateReceived;

    public QuoteEntity(String text, String author, String awsMessageId, Timestamp dateReceived) {
    }

    public QuoteEntity() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAwsMessageId() {
        return awsMessageId;
    }

    public void setAwsMessageId(String awsMessageId) {
        this.awsMessageId = awsMessageId;
    }

    public Timestamp getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Timestamp dateReceived) {
        this.dateReceived = dateReceived;
    }
}
