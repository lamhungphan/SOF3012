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
@Table(name = "Share",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "VideoId"})}
)
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    private String emails;

    @Temporal(TemporalType.DATE)
    private Date shareDate;

    public Share(Video video, User user, String emails, Date shareDate) {
        this.video = video;
        this.user = user;
        this.emails = emails;
        this.shareDate = shareDate;
    }
}
