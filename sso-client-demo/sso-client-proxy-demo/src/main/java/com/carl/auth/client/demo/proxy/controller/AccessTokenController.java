/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.client.demo.proxy.controller;

import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Carl
 * @date 2017/10/21
 * @since
 */
@RestController
@RequestMapping("/token")
public class AccessTokenController {
    private OAuth2AccessTokenExtractor tokenExtractor = OAuth2AccessTokenExtractor.instance();

    @Autowired
    private RestTemplate restTemplate;

    /**
     * qq代理转发
     *
     * @return
     */
    @RequestMapping(value = "/qq", produces = {"application/json"})
    public Object qq(
            @RequestParam(OAuthConstants.CLIENT_ID) String client_id,
            @RequestParam(OAuthConstants.CLIENT_SECRET) String client_secret,
            @RequestParam(OAuthConstants.CODE) String code,
            @RequestParam(OAuthConstants.REDIRECT_URI) String redirect_uri,
            @RequestParam(OAuthConstants.GRANT_TYPE) String authorization_code,
            HttpServletResponse response) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add(OAuthConstants.CLIENT_ID, client_id);
        map.add(OAuthConstants.CLIENT_SECRET, client_secret);
        map.add(OAuthConstants.CODE, code);
        map.add(OAuthConstants.REDIRECT_URI, redirect_uri);
        map.add(OAuthConstants.GRANT_TYPE, authorization_code);

        System.out.println(map);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> resp = restTemplate.exchange("https://graph.qq.com/oauth2.0/token", HttpMethod.POST, request, String.class);

        //todo 注意异常处理
        System.out.println(resp.getBody());

        response.setContentType("application/json");
        OAuth2AccessToken token = tokenExtractor.extract(new Response(
                resp.getStatusCodeValue(),
                resp.toString(),
                resp.getHeaders().toSingleValueMap(),
                resp.getBody(), null
        ));

        //返回结果
        Map<String, Object> res = new HashMap<>();
        res.put("access_token", token.getAccessToken());
        res.put("token_type", token.getTokenType());
        res.put("expires_in", token.getExpiresIn());
        res.put("refresh_token", token.getRefreshToken());
        res.put("error_description", token.getTokenType());
        res.put("scope", token.getScope());
        return res;
    }
}
