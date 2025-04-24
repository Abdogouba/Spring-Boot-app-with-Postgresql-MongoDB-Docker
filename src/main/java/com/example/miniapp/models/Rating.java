package com.example.miniapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ratings")
public class Rating {

    @Id
    private String id;

    private Long entityId;

    private String entityType;

    private Integer score;

    private String comment;

    private LocalDateTime ratingDate;

    public Rating(Long entityId, String entityType, Integer score, String comment, LocalDateTime ratingDate) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
        this.comment = comment;
        this.ratingDate = ratingDate;
    }

}

