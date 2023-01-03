package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.Entity.announcement;
import com.example.demo.mapper.announcementMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class announcementService {
    @Autowired
    private announcementMapper a_mapper;

    /*
     * 功能：查询已发布的公告
     * 返回：查询结果Lists
     */
    public List<announcement> getSentAnnouncementByPage(int pageNum, int pageSize){
        return a_mapper.getSentAnnouncement(pageNum * pageSize, pageSize);
    }

    /*
     * 功能：查询某位管理人员未发布的公告
     * 返回：查询结果Lists
     */
    public List<announcement> getUnreleaseAnnouncementByPage(int user_id){
        return a_mapper.getUnSentAnnouncement(user_id);
    }

    /*
     * 功能：查询某位管理人员已发布的公告
     * 返回：查询结果Lists
     */
    public List<announcement> getMyreleaseAnnouncementByPage(int user_id, int pageNum, int pageSize){
        return a_mapper.getMySentAnnouncement(pageNum * pageSize, pageSize, user_id);
    }

    /*
     * 功能：修改未发布的公告
     * 返回：1：修改成功；0修改失败；500参数错误
     */
    public int modify(announcement ann){
        return a_mapper.updateInfo(ann);
    }

    /*
     * 功能：修改公告状态
     * 返回：1：修改成功；0修改失败；500参数错误
     */
    public int setAnnouncementState(int announcement_id, int state){
        return a_mapper.updateState(announcement_id, state);
    }

    /*
     * 功能：添加新的公告
     * 返回：1添加成功，0添加失败，500参数错误
     */
    public int createAnnouncement(announcement ann){
        if(ann.getTitle() != null && ann.getContent() != null && ann.getUser_id() != null){
            return a_mapper.insert(ann);
        }
        return 500;
    }

    /*
     * 功能：删除未发布的公告
     * 返回：1删除成功，0删除失败，500参数错误
     */
    public int deleteAnnouncement(int announcement_id){
        return a_mapper.deleteById(announcement_id);
    }
}
