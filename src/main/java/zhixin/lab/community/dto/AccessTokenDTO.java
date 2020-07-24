package zhixin.lab.community.dto;

public class AccessTokenDTO {
    //当参数数量超过两个时，最好将数据封装，而不是都写在请求地址栏中
    //dto包里面都放的数据
    private  String client_id;
    private  String client_secret;
    private  String code;
    private  String redirect_url;
    private  String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
