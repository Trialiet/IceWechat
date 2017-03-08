package cn.icedoge.wechat.menu;

import cn.icedoge.wechat.WechatResponse;

import java.util.Set;

/**
 * Created by Trialiet on 2016/10/19.
 */
public class Menu extends WechatResponse{
    private Set<Button> button;

    public void addButton(Button sub_button){
        if (button.size() < 3){
            button.add(sub_button);
        }
    }

    public Set<Button> getButton() {
        return button;
    }

    public void setButton(Set<Button> button) {
        this.button = button;
    }
}
