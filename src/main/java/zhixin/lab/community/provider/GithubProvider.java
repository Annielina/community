package zhixin.lab.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import zhixin.lab.community.dto.AccessTokenDTO;
import zhixin.lab.community.dto.GithubUser;

import java.io.IOException;

@Component
public class GithubProvider {
    //返回access token
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        //获取accesstoken需要的数据封装到了AccessTokenDTO类中，直接把类传进来就可以了

        //做post请求 okhttp
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));  //类转json 记得加依赖fastjaon
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //string 打印出来这样：access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer
            String[] split = string.split("&");
            String token = split[0].split("=")[1];

            return token;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public GithubUser getUser(String accessToken){
        //get请求 用什么请求方式是github官方文档告诉的
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();

        try {
            Response response = client.newCall(request).execute();
            //拿到的是json格式
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            //把string的json对象，自动转换解析为java类对象GithubUser

            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
