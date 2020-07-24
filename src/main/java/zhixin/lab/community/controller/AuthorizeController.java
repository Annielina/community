package zhixin.lab.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zhixin.lab.community.dto.AccessTokenDTO;
import zhixin.lab.community.dto.GithubUser;
import zhixin.lab.community.provider.GithubProvider;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    //用autowired注解，自动的把spring容器中实例好的对象加载到当前类中，不需要像过去一样new一个对象

    @Value("${github.client.id}")  //去配置文件中读，找到这个key，赋值给clientId
    private String clientId;

    @Value("${github.client.secret}")  //去配置文件中读，找到这个key，赋值给clientId
    private String clientSecret;

    @Value("${github.redirect.url}")  //去配置文件中读，找到这个key，赋值给clientId
    private String redirectUrl;

    @GetMapping("/callback")   //github回调回来的地址是localhost/callback 并且携带了code和state
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);//把请求中的code拿出来，放到封装的类中
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName()); //打印看看成功没有

        return "index";
    }
}
