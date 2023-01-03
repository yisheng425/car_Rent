package com.example.demo.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.aliyun.tea.*;

@Service
public class messageService {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    
    /*
     * 功能：用来查询用户账户表中，是否已经存在该手机号码
     * 返回：6位验证码，代表成功，-1代表失败
     */
    public int SendVericode(String PhoneNumber) throws Exception
    {
        com.aliyun.dysmsapi20170525.Client client = messageService.createClient("LTAI5tEfBLNtFkzLmZXpzUK9", "joEWkCfCB6kYbTftWyGCCTBLE3lH3F");

        Random random = new Random();
        int code = random.nextInt() % 10000;

        String code_ = String.valueOf(code);

        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("唐奥翔的博客")
                .setTemplateCode("SMS_262215035")
                .setPhoneNumbers("13593315783")
                .setTemplateParam("{\"code\":\"" + code_ +"\"}");
                com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
                try {
                    // 复制代码运行请自行打印 API 的返回值
                    client.sendSmsWithOptions(sendSmsRequest, runtime);
                } catch (TeaException error) {
                    // 如有需要，请打印 error
                    com.aliyun.teautil.Common.assertAsString(error.message);
                } catch (Exception _error) {
                    TeaException error = new TeaException(_error.getMessage(), _error);
                    // 如有需要，请打印 error
                    com.aliyun.teautil.Common.assertAsString(error.message);
                }  
        //send verucode to PhoneNumber ，return code or -1
        return code;
    }
}
