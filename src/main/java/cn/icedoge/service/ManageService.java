package cn.icedoge.service;

import cn.icedoge.dao.SidebarMenuDao;
import cn.icedoge.dao.UserConfigDao;
import cn.icedoge.model.SidebarMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Trialiet on 2017-3-6.
 */
@Service
public class ManageService {
    @Autowired
    private UserConfigDao userConfigDao;
    @Autowired
    private SidebarMenuDao sidebarMenuDao;

    public List getSidebarMenu(){
        List sidebarMenu = sidebarMenuDao.getAllSidebarMenu();
        return sidebarMenu;
    }

    public SidebarMenu getSidebarMenuById(int id){
        return sidebarMenuDao.getSidebarMenuById(id);
    }

    public String test(){
        return userConfigDao.getToken("icedog");
    }
}
