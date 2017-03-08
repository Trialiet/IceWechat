package cn.icedoge.model;

/**
 * Created by Trialiet on 2017-3-6.
 */
public class SidebarMenu {
    private int sidebar_menu_id;
    private String sidebar_menu_name;
    private String sidebar_menu_icon;
    private String sidebar_menu_sref;

    public SidebarMenu(){

    }

    public SidebarMenu(int sidebar_menu_id, String sidebar_menu_name) {
        this.sidebar_menu_id = sidebar_menu_id;
        this.sidebar_menu_name = sidebar_menu_name;
    }

    public int getSidebar_menu_id() {
        return sidebar_menu_id;
    }

    public void setSidebar_menu_id(int sidebar_menu_id) {
        this.sidebar_menu_id = sidebar_menu_id;
    }

    public String getSidebar_menu_name() {
        return sidebar_menu_name;
    }

    public void setSidebar_menu_name(String sidebar_menu_name) {
        this.sidebar_menu_name = sidebar_menu_name;
    }

    @Override
    public String toString() {
        return "SidebarMenu{" +
                "sidebar_menu_id=" + sidebar_menu_id +
                ", sidebar_menu_name='" + sidebar_menu_name + '\'' +
                '}';
    }

    public String getSidebar_menu_icon() {
        return sidebar_menu_icon;
    }

    public void setSidebar_menu_icon(String sidebar_menu_icon) {
        this.sidebar_menu_icon = sidebar_menu_icon;
    }

    public String getSidebar_menu_sref() {
        return sidebar_menu_sref;
    }

    public void setSidebar_menu_sref(String sidebar_menu_sref) {
        this.sidebar_menu_sref = sidebar_menu_sref;
    }
}
