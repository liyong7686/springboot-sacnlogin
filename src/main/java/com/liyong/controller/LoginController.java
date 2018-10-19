package com.liyong.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liyong.until.HttpRequestUtils;
import com.liyong.until.SignUtil;

@Controller
@RequestMapping("/LoginController")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${wx_appid}")
	private String appid;
	@Value("${wx_callBack}")
	private String callBack;
	@Value("${wx_scope}")
	private String scope;
    @Value("${wx_appsecret}")
    private String appsecret;
	
	
    /** 以下代码为微信授权登陆部分---------------start---------------------------------- **/
	 @RequestMapping("/index")
     public String index(Model model) throws UnsupportedEncodingException {
		 
		 System.out.println("-------------进入登陆首页-----------  ");
		 System.out.println("--------");
		 System.out.println(callBack);
		 
        String oauthUrl ="https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        String redirect_uri = URLEncoder.encode(callBack, "utf-8");
        oauthUrl =  oauthUrl.replace("APPID",appid).replace("REDIRECT_URI",redirect_uri).replace("SCOPE",scope);
        model.addAttribute("name","hello");
        model.addAttribute("oauthUrl",oauthUrl);
        System.out.println(oauthUrl);
        return "index";
     }

	 @RequestMapping("/index1")
     public String index1(Model model) throws UnsupportedEncodingException {
        String redirect_uri = URLEncoder.encode(callBack, "utf-8"); ;
        model.addAttribute("name","自定义登陆");
        model.addAttribute("appid",appid);
        model.addAttribute("scope",scope);
        model.addAttribute("redirect_uri",redirect_uri);
        return "index1";
    }
	 
	 @RequestMapping("/callBack")
     public String callBack(String code,String state,Model model) throws Exception{
        logger.info("进入授权回调,code:{},state:{}",code,state);
        
        System.out.println("------------进入授权回调,code:{},state:{}" + code+ "-----"+state);

        //1.通过code获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APPID",appid).replace("SECRET",appsecret).replace("CODE",code);
        String tokenInfoStr =  HttpRequestUtils.httpGet(url,null,null);

        JSONObject tokenInfoObject = new JSONObject(tokenInfoStr);
        logger.info("tokenInfoObject:{}",tokenInfoObject);

        //2.通过access_token和openid获取用户信息
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        userInfoUrl = userInfoUrl.replace("ACCESS_TOKEN",tokenInfoObject.getString("access_token")).replace("OPENID",tokenInfoObject.getString("openid"));
        String userInfoStr =  HttpRequestUtils.httpGet(userInfoUrl,null,null);
        logger.info("userInfoObject:{}",userInfoStr);

        model.addAttribute("tokenInfoObject",tokenInfoObject);
        model.addAttribute("userInfoObject",userInfoStr);

        return "result";
    }
	 
	@ResponseBody
    @RequestMapping("/check")
	public String check(HttpServletResponse response,HttpServletRequest request)throws Exception{
		
		System.out.println("微信验证程序。。。。");
		
		// 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        } else{
            return "fail";
        }
	}
	
    /** 以下代码为微信授权登陆部分---------------end---------------------------------- **/

	 
}
