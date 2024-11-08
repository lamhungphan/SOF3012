package com.fpoly.sof3012.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "`history`")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId", referencedColumnName = "id")
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "videoId", referencedColumnName = "id")
    private Video video;
    @CreationTimestamp // luu record moi khi co data
    private Timestamp viewdDate;

    private Boolean isLiked;

    private Timestamp likedDate;

}
