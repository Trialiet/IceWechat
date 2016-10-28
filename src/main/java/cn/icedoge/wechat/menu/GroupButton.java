package cn.icedoge.wechat.menu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Trialiet on 2016/10/19.
 */
public class GroupButton extends Button {
    private Set<Button> sub_button = new HashSet<>();

    public void addSub_button(Button button){
        if (sub_button.size() < 5)
            sub_button.add(button);
    }

    public Set<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(Set<Button> sub_button) {
        this.sub_button = sub_button;
    }
}
