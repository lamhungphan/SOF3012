package com.fpoly.sof3012.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Favorites",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "VideoId"})}
)
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VideoId") // ten khoa ngoai trong table Favorites
    private Video video;

    @ManyToOne
    @JoinColumn(name = "UserId") // ten khoa ngoai trong table Favorites
    private User user;

    @Temporal(TemporalType.DATE) // chi lay ngay trong java .util.Date
    private Date likeDate;

    public Favorite(Video video, User user, Date likeDate) {
        this.video = video;
        this.user = user;
        this.likeDate = likeDate;
    }
    public Favorite(Video video){
        this.video = video;
    }
}
