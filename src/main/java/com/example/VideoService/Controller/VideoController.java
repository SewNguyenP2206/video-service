package com.example.VideoService.Controller;


import com.example.VideoService.Config.ModelMapperObject;
import com.example.VideoService.DTO.CreateVideoDTO;
import com.example.VideoService.DTO.ResponeVideoDTO;
import com.example.VideoService.Entity.Video;
import com.example.VideoService.Repository.VideoRepository;
import com.example.VideoService.Service.ServiceOfFile;
import com.example.VideoService.Service.ServiceOfVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "http://academy.arthub.s3-website-ap-southeast-1.amazonaws.com/", maxAge = 3600)
@RestController
public class VideoController implements IVideoController {
    Path staticPath = Paths.get("static");
    Path videoPath = Paths.get("videos");

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    ServiceOfFile serviceOfFile;

    @Autowired
    ServiceOfVideo serviceOfVideo;

    @Autowired
    ModelMapperObject modelMapperObject;


    public ResponeVideoDTO fromVideoIntoResponeVideoDTO(Video video) {

        ResponeVideoDTO videoDTO = new ResponeVideoDTO();
        videoDTO.setId(video.getId());
        videoDTO.setName(video.getName());
        videoDTO.setData(video.getData());
        videoDTO.setDate(video.getDate());
        videoDTO.setScript(video.getScript());
        videoDTO.setSectionId(video.getSectionId());
        videoDTO.setTrial(video.isTrial());

        return videoDTO;
    }

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));






    @Override
    public ResponeVideoDTO createNewVideo(@RequestParam String name, @RequestParam MultipartFile data, @RequestParam String script, @RequestParam boolean isTrial, @RequestParam int sectionId) throws IOException {

        serviceOfFile.uploadFile(data);
        CreateVideoDTO createVideoDTO = new CreateVideoDTO();
        createVideoDTO.setSectionId(sectionId);
        createVideoDTO.setData(data.getOriginalFilename());
        createVideoDTO.setName(name);
        createVideoDTO.setTrial(isTrial);
        createVideoDTO.setScript(script);
        return  fromVideoIntoResponeVideoDTO(serviceOfVideo.saveVideo(createVideoDTO));
    }

    @Override
    public List<ResponeVideoDTO> findAllVideoBySectionID(int id) {
        return serviceOfVideo.findAllBySectionId(id).stream().map(video -> modelMapperObject.modelMapper().map(video, ResponeVideoDTO.class)).toList();
    }

    @Override
    public ResponeVideoDTO findVideoByID() {
        return null;
    }

    @Override
    public List<ResponeVideoDTO> findAllVideos() {
        return null;
    }

    @Override
    public int deleteVideo(int id) {
        int delete = videoRepository.deleteVideo(id);
        return delete;
    }

    @Override
    public int deleteVideosBySectionID(int sectionID) {
        return serviceOfVideo.deleteVideosBySectionID(sectionID);
    }
}
