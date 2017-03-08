package cn.icedoge.dao;

import cn.icedoge.model.SidebarMenu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Trialiet on 2017-3-6.
 */
@Repository
public interface SidebarMenuDao {
    @Select("SELECT * FROM sidebar_menu")
    List<SidebarMenu> getAllSidebarMenu();

    @Select("SELECT * FROM sidebar_menu WHERE sidebar_menu_id = #{id}")
    SidebarMenu getSidebarMenuById(int id);

    @Select("SELECT sidebar_menu_name FROM sidebar_menu WHERE sidebar_menu_id = #{id}")
    String getSidebarMenuName(int id);
}
