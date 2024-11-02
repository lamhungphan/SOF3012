package com.fpoly.sof3012.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Videos")
public class Video {
    @Id
    private String id;
    private String title;
    private String poster;
    private String description;
    private Boolean active;
    private Integer views;

    @OneToMany(mappedBy = "video")
    private List<Favorite> favorites;
}
