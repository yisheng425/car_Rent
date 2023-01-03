package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.announcementService;
import com.example.demo.Controller.dto.pageDTOplus;
import com.example.demo.Entity.announcement;

@RequestMapping("/announcement")
@RestController
public class announcementController {
    @Autowired
    announcementService anService;

    @GetMapping("/GetAnnouncementByPage")
    public List<announcement> GetAnnouncementByPage(){
        return anService.getSentAnnouncementByPage(0, 50);
    }

    @PostMapping("/GetMyAnnouncement")
    public List<announcement> GetMyreleaseAnnouncement(@RequestBody pageDTOplus page){
        return anService.getMyreleaseAnnouncementByPage(page.getUser_id(), 0, 50);
    }

    @GetMapping("/GetUnreleaseAnnouncement")
    public List<announcement> GetUnreleaseAnnouncement(@RequestBody announcement an){
        return anService.getUnreleaseAnnouncementByPage(an.getUser_id());
    }

    @PostMapping("/create")
    public Integer create(@RequestBody announcement an){
        if(an.getTitle() == null)
        {
            return 500;
        }
        if(an.getContent() == null)
        {
            return 500;
        }
        if(an.getUser_id() == null)
        {
            return 500;
        }
        return anService.createAnnouncement(an);
    }

    @PostMapping("/release")
    public Integer release(@RequestBody announcement an){
        if(an.getId() == null)
        {
            return 500;
        }
        return anService.setAnnouncementState(an.getId(), 1);
    }

    @PostMapping("/drawback")
    public Integer drawback(@RequestBody announcement an){
        if(an.getId() == null)
        {
            return 500;
        }
        return anService.setAnnouncementState(an.getId(), 0);
    }

    @PostMapping("/delete")
    public Integer delete(@RequestBody announcement an){
        if(an.getId() == null)
        {
            return 500;
        }
        return anService.deleteAnnouncement(an.getId());
    }

    @PostMapping("/modify")
    public Integer modify(@RequestBody announcement an){
        if(an.getId() == null)
        {
            return 500;
        }
        if(an.getTitle() == null)
        {
            return 500;
        }
        if(an.getContent() == null)
        {
            return 500;
        }
        return anService.modify(an);
    }
}
