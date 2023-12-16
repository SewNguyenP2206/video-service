package com.example.VideoService.Service;



import com.example.VideoService.DTO.CreateVideoDTO;
import com.example.VideoService.Entity.Video;

import java.util.List;

public interface IVideoService {

    Video saveVideo(CreateVideoDTO dto) ;

    List<Video> getVideos();

    int deleteVideosBySectionID(int sectionID);

    List<Video> findAllBySectionId(int sectionId);


}
