package com.example.VideoService.Service;


import com.example.VideoService.DTO.CreateVideoDTO;
import com.example.VideoService.Entity.Video;
import com.example.VideoService.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfVideo implements IVideoService {


    @Autowired
    VideoRepository videoRepository;


    @Override
    public Video saveVideo(CreateVideoDTO dto) {
        Video video = new Video();
        video.setName(dto.getName());
        video.setSectionId(dto.getSectionId());
        video.setData(dto.getData());
        video.setScript(dto.getScript());
        video.setTrial(dto.isTrial());
        return videoRepository.save(video) ;
    }


    public int DeleteVideoByCourseID(int id)
    {
      return videoRepository.deleteVideosBySectionID(id);
    }





    @Override
    public List<Video> getVideos() {

        return null;
    }

    @Override
    public int deleteVideosBySectionID(int sectionID) {
        return videoRepository.deleteVideosBySectionID(sectionID);
    }

    @Override
    public List<Video> findAllBySectionId(int sectionId) {
        return videoRepository.findAllBySectionId(sectionId);
    }
}
