package com.high.utils;

import com.high.entity.Activity;
import net.sf.json.JSONObject;

import java.io.IOException;

/**
 * Created by llw on 2017/7/5.
 */
public class WeChatUtils {

    public static final String APP_ID ="wx18a9ddfade51c6b4";
    public static String APP_SECRET ="8a1b1e7b1d03ae28ff9d5aa2ab26584b";
    public static String token;
    /**
     * 查询用户的openId
     * @param code
     * @return
     */
    public static String getOpedId(String code){
        String grantType="authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APP_ID + "&secret=" + APP_SECRET +"&js_code="+code+ "&grant_type="+grantType;
        try {
            String response = HttpUtils.doGet(url);
            System.out.println(response);
            JSONObject jsonObject = JSONObject.fromObject(response);
            if(jsonObject.get("errcode")!=null){
                System.out.println(jsonObject.get("errcode"));
            }
            System.out.println(jsonObject.get("openid"));
            System.out.println(jsonObject.get("session_key"));
            return jsonObject.get("openid").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户的token信息
     * @return
     */
    public static String getToken(){
        String grantType="client_credential";
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type="+grantType+"&appid="+ APP_ID +"&secret="+ APP_SECRET;
        try {
            String response = HttpUtils.doGet(url);
            System.out.println(response);
            JSONObject jsonObject = JSONObject.fromObject(response);
            if(jsonObject.get("errcode")!=null){
                System.out.println(jsonObject.get("errcode"));
            }
            token = jsonObject.get("access_token").toString();
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 个体活动参与者发送活动开始提醒
     * @param activity 要开始的活动
     * @param formId formID用来发送模板消息
     * @param toUser 要讲模板消息发送给该用户
     * @throws IOException
     */
    public static void sendActivityStartRemind(Activity activity, String formId,String toUser) throws IOException {
        String templateId="8-2zAj049oul2nM2leZAmQPLHQKIHxjFewvhvlbb70A";
        String page="/pages/activedetailByParticate/activedetailByParticate?activityId="+activity.getActivityId();
        String url="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + token;
        JSONObject data = new JSONObject();
        data.put("touser",toUser);
        data.put("template_id",templateId);
        data.put("page",page);
        data.put("form_id",formId);

        JSONObject subData = new JSONObject();
        JSONObject keyWord1 = new JSONObject();
        keyWord1.put("value",activity.getContent());
        keyWord1.put("color","#4a4a4a");

        JSONObject keyWord2 = new JSONObject();
        keyWord2.put("value",activity.getActivityLocation().getLocationDescription());
        keyWord2.put("color","#9b9b9b");

        JSONObject keyWord3 = new JSONObject();
        keyWord3.put("value", TimeUtils.formatDate(activity.getStartTime()));
        keyWord3.put("color","#9b9b9b");

        JSONObject keyWord4 = new JSONObject();
        keyWord4.put("value",activity.getComment());
        keyWord4.put("color","#9b9b9b");

        subData.put("keyword1",keyWord1);
        subData.put("keyword2",keyWord2);
        subData.put("keyword3",keyWord3);
        subData.put("keyword4",keyWord4);
        data.put("data",subData.toString());
        data.put("color","#ccc");
        HttpUtils.doPost(url,data);
    }
}
