//package com.hanchen.utils;
//
//import java.util.Base64;
//
//public class EncUtil {
//
//
//    /**
//     * 加密
//     *
//     * @param data
//     * @param appId
//     * @param appSecret
//     * @return
//     * @throws Exception
//     */
//    public static String encrypt(String data, String appId, String appSecret) throws Exception {
//        //加密流程
//        //用appId加密appSecret获取新秘钥
//        byte[] appSecretEncData = EasyGmUtils.sm4Encrypt(appId.substring(0, 16).getBytes("UTF-8"), appSecret.getBytes("UTF-8"));
//        //新秘钥串
//        byte[] secKey = Hex.toHexString(appSecretEncData).toUpperCase().substring(0, 16).getBytes("UTF-8");
//        //加密0数据
//        String encryptDataStr = Hex.toHexString(EasyGmUtils.sm4Encrypt(secKey, data.getBytes("UTF-8"))).toUpperCase();
//        return encryptDataStr;
//    }
//
//    /**
//     * 解密
//     *
//     * @param data
//     * @param appId
//     * @param appSecret
//     * @return
//     * @throws Exception
//     */
//    public static String decrypt(String data, String appId, String appSecret) throws Exception {
//        byte[] appSecretEncDataDecode = EasyGmUtils.sm4Encrypt(appId.substring(0, 16).getBytes("UTF-8"), appSecret.getBytes("UTF-8"));
//        byte[] secKeyDecode = Hex.toHexString(appSecretEncDataDecode).toUpperCase().substring(0, 16).getBytes("UTF-8");
//        String decryptDataStr = new String(EasyGmUtils.sm4Decrypt(secKeyDecode, Hex.decode(data)));
//        return decryptDataStr;
//    }
//
//    /**
//     * 签名
//     *
//     * @param jsonObject
//     * @param appSecret
//     * @param privateKey
//     * @return
//     * @throws Exception
//     */
//    public static String sign(JSONObject jsonObject, String appSecret, String privateKey) throws Exception {
//        // 获取签名串
//        byte[] signText = SignUtil.getSignText(jsonObject, appSecret).getBytes("UTF-8");
//        byte[] userId = appSecret.getBytes();
//        byte[] prvkey = Base64.getDecoder().decode(privateKey);
//        String responseSign = Base64.getEncoder().encodeToString(EasyGmUtils.signSm3WithSm2(signText, userId, prvkey));
//        return responseSign;
//    }
//
////    /**
////     * 验签
////     *
////     * @param jsonObject
////     * @param appSecret
////     * @param publicKey
////     * @param responseSign
////     * @return
////     * @throws Exception
////     */
////    public static boolean verify(JSONObject jsonObject, String appSecret, String publicKey, String responseSign) throws Exception {
////        //验签
////        byte[] msg = SignUtil.getSignText(jsonObject, appSecret).getBytes("UTF-8");
////        byte[] userIdDecode = appSecret.getBytes("UTF-8");
////        byte[] pubkey = Base64.getDecoder().decode(publicKey);
////        byte[] signData = Base64.getDecoder().decode(responseSign);
////        return EasyGmUtils.verifySm3WithSm2(msg, userIdDecode, signData, pubkey);
////    }
//
//    public static void main(String[] args) throws Exception {
//        //应用ID
//        String appId = "57E2D561E97141A3871DEFB410ADD920";
//        //应用秘钥
//        String appSecret = "F30D0D261BA04063A0BCAEF19F8ADBCC";
//        //应用私钥
//        String privateKey = "AKyhfvPbT+tIJHJkZSDILrw7t+FOG1U58UcupYYTzCOj";
//        //平台公钥（为了测试用例能验签成功，平台公钥当前赋值为应用公钥的值，应用公钥存放于处方中心不对外提供，实际上拿到的平台公钥是用于验签处方中心返回的数据，不能用于此demo）
//        String publicKey = "BIFd2+2CgjuPAj5FMj5L/L3azTWu86suPtlIJkCo8zjQ44R7SQUUkTgZGdVelRQCM5pW+x9tZGzDPaUNbfD499w=";
//
//        JSONObject requestData = new JSONObject();
//        requestData.put("appId", appId);
//        requestData.put("encType", "SM4");
//        requestData.put("signType", "SM2");
//        requestData.put("timestamp", System.currentTimeMillis());
//
//        JSONObject data = new JSONObject();
//        data.put("userName", "张三");
//        //加密
//        String encData = EncUtil.encrypt(data.toJSONString(), appId, appSecret);
//        System.out.println("加密：" + encData);
//        JSONObject signDto = JSON.parseObject(JSON.toJSONString(requestData));
//        signDto.put("data", data);
//        //加签
//        String signData = EncUtil.sign(signDto, appSecret, privateKey);
//        System.out.println("加签：" + signData);
//        //报文
//        requestData.put("encData", encData);
//        requestData.put("signData", signData);
//        System.out.println("报文：" + JSON.toJSONString(requestData));
//        //解密
//        String decData = EncUtil.decrypt(encData, appId, appSecret);
//        System.out.println("解密：" + decData);
//        //验签
//        boolean isVerify = EncUtil.verify(signDto, appSecret, publicKey, signData);
//        System.out.println("验签：" + isVerify);
//    }
//}
