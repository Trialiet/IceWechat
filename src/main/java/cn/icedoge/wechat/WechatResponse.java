package cn.icedoge.wechat;

/**
 * Created by Trialiet on 2016/10/19.
 */
public class WechatResponse {
    private Long errcode;
    private String errmsg;

    public void setErrcode(Long errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Long getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
