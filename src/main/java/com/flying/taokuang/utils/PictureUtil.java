package com.flying.taokuang.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flying.taokuang.aip.baidu.HttpUtil;

import java.net.URLEncoder;

/**
 * 通用文字识别，百度云
 * @author NNShadow
 * @date 2019/9/22 10:50
 */
public class PictureUtil {

    /**
     * img 需要 base64 转码
     * @param imgStr
     * @return
     */
    public static String getStudentId(String imgStr){
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        try {
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间，客户端可自行缓存，过期后重新获取。
             */
            String accessToken = AccessTokenUtil.getAuth();//#####调用鉴权接口获取的token#####
            String result = HttpUtil.post(otherHost, accessToken, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回学生学号
     * @param result 识别的 json 数据
     * @return
     */
    public String get(String result){
//        图片路径
//        String filePath = "C:\\Users\\Secluded Wind\\Desktop\\test.png";
//        将图片转为 Base64
//        byte[] imgData = FileUtil.readFileByBytes(filePath);
//        String imgStr = Base64Util.encode(imgData);
//        System.out.println(imgStr);
//        String s = PictureUtil.getStudentId(imgStr);
//        String result = "{\"log_id\": 6880705468944884534, \"words_result_num\": 11, \"words_result\": [{\"location\": {\"width\": 192, \"top\": 687, \"height\": 61, \"left\": 696}, \"words\": \"聂鹏远\"}, {\"location\": {\"width\": 114, \"top\": 802, \"height\": 58, \"left\": 689}, \"words\": \"性别:\"}, {\"location\": {\"width\": 58, \"top\": 855, \"height\": 53, \"left\": 692}, \"words\": \"男\"}, {\"location\": {\"width\": 100, \"top\": 944, \"height\": 41, \"left\": 692}, \"words\": \"学院\"}, {\"location\": {\"width\": 274, \"top\": 965, \"height\": 31, \"left\": 1363}, \"words\": \"www.cumt.edu.cn\"}, {\"location\": {\"width\": 490, \"top\": 986, \"height\": 58, \"left\": 689}, \"words\": \"计算机科学与技术学院\"}, {\"location\": {\"width\": 113, \"top\": 1077, \"height\": 43, \"left\": 691}, \"words\": \"学号:\"}, {\"location\": {\"width\": 240, \"top\": 1051, \"height\": 56, \"left\": 1362}, \"words\": \"学生校园卡\"}, {\"location\": {\"width\": 259, \"top\": 1124, \"height\": 51, \"left\": 688}, \"words\": \"08182871\"}, {\"location\": {\"width\": 320, \"top\": 1116, \"height\": 55, \"left\": 1365}, \"words\": \"卡号:113309\"}, {\"location\": {\"width\": 279, \"top\": 1411, \"height\": 20, \"left\": 1634}, \"words\": \"https://blog.csdnnet/weixin_43820352\"}]}\n";
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray array = jsonObject.getJSONArray("words_result");
        JSONObject number = (JSONObject) array.get(8);
        String studentId = (String) number.get("words");
        return studentId;
    }
}
