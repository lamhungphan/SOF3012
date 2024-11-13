package com.fpoly.sof3012.dao;

import com.fpoly.sof3012.entity.Video;

import java.util.List;

public interface VideoDao {
    List<Video> findVideoByKeyword(String keyword);

    List<Video> getFavoriteVideo(Object userId);

}
