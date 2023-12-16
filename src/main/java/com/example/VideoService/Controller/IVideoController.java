package com.example.VideoService.Controller;


import com.example.VideoService.DTO.ResponeVideoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/video")
public interface IVideoController {

    @PostMapping("/add")
    public ResponeVideoDTO createNewVideo(String name, MultipartFile data, String script, boolean isTrial, int sectionId  ) throws IOException;

    @GetMapping("/findAllVideoBySectionID/{id}")
    public List<ResponeVideoDTO> findAllVideoBySectionID(@PathVariable int id);

    public ResponeVideoDTO findVideoByID();

    public List<ResponeVideoDTO> findAllVideos();

    @PostMapping("/delete")
    public int deleteVideo(int id);

    @GetMapping("/deleteVideosBySectionID/{id}")
    int deleteVideosBySectionID( @PathVariable int sectionID);
}
