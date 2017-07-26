package com.high.test;

import com.high.utils.HttpUtils;
import com.high.utils.TimeUtils;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by llw on 2017/7/5.
 */
public class HttpUtilTest {

    @Test
    public void test1(){
        String appId="wx18a9ddfade51c6b4";
        String appSecret="8a1b1e7b1d03ae28ff9d5aa2ab26584b";
        String jsCode="013iZid11RhAX125fPd11Lvfd11iZido";
        String grantType="authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret +"&js_code="+jsCode+ "&grant_type="+grantType;
        try {
            String response = HttpUtils.doGet(url);
            System.out.println(response);
            JSONObject jsonObject = JSONObject.fromObject(response);
            System.out.println(jsonObject.get("openid"));
            System.out.println(jsonObject.get("session_key"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 预约活动开始提醒的demo。
     * token有时间限制，需要定时获取
     * form_id限制有7天时间，且使用一次就要删除。
     */
    @Test
    public void sendMessage(){
        String appId="wx18a9ddfade51c6b4";
        String openId="ohEMN0SIphfIDrhJLp6IR6VDYsWg";
        String templateId="8-2zAj049oul2nM2leZAmQPLHQKIHxjFewvhvlbb70A";
        String page="/pages/activedetailByParticate/activedetailByParticate";
        String formId="1500342211041";
        String token="YF9m9TplA99usMv4J9W1cWSypoo1zYqiwi9L1h6ytsMe8jElrae4TizPz2_6WlLesm8TIq4mpEQ2iBjb5xpF03sPJEvjpVKRFu-I23FL-VkXVAeAJATRW";
        String url="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + token;
        JSONObject data = new JSONObject();
        data.put("touser",openId);
        data.put("template_id",templateId);
        data.put("page",page);
        data.put("form_id",formId);

        data.put("color","#ccc");
        JSONObject subData = new JSONObject();
        JSONObject keyWord1 = new JSONObject();
        keyWord1.put("value","羽毛球");
        keyWord1.put("color","#4a4a4a");

        JSONObject keyWord2 = new JSONObject();
        keyWord2.put("value","西电");
        keyWord2.put("color","#9b9b9b");

        JSONObject keyWord3 = new JSONObject();
        keyWord3.put("value", TimeUtils.formatDate(new Date()));
        keyWord3.put("color","#9b9b9b");

        JSONObject keyWord4 = new JSONObject();
        keyWord4.put("value","201612130909");
        keyWord4.put("color","#9b9b9b");

        subData.put("keyword1",keyWord1);
        subData.put("keyword2",keyWord2);
        subData.put("keyword3",keyWord3);
        subData.put("keyword4",keyWord4);
        data.put("data",subData.toString());
        try {
            HttpUtils.doPost(url,data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
