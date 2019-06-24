package com.cancer.security.core.properties;

/**
 * @author yangpan
 * @Title: security
 * @Package com.cancer.security.core.properties
 * @Description:
 * @date 2018/7/517:31
 */
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();
    private WeChatProperties weChat = new WeChatProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public WeChatProperties getWeChat() {
        return weChat;
    }

    public void setWeChat(WeChatProperties weChat) {
        this.weChat = weChat;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
