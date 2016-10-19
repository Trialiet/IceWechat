package cn.icedoge.model.wechat.json;

/**
 * Created by Trialiet on 2016/10/19.
 */
public class WechatResponse {
    private Long errcode;
    private String errmsg;

    public Long getErrcode() {
        return errcode;
    }

    public void setErrcode(Long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
