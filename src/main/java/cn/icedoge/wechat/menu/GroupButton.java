package cn.icedoge.wechat.menu;

/**
 * Created by Trialiet on 2016/10/19.
 */
public class GroupButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
