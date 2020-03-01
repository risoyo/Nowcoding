package com.nowcoder.community.controller;

import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.ReturnService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.BizException;
import com.nowcoder.community.util.HostHolder;
import com.nowcoder.community.util.NowcodingErrCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ReturnService returnService;
    private final HostHolder hostholder;
    private final UserService userService;
    @Value("${community.path.upload}")
    private String path;
    @Value("${community.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Value("${community.path.avatarUpload}")
    private String avatarPath;

    private UserController(ReturnService returnService, HostHolder hostholder, UserService userService) {
        this.returnService = returnService;
        this.hostholder = hostholder;
        this.userService = userService;
    }

    @RequestMapping("/uploadHeaderImage")
    @ResponseBody//定义返回类型为自定义类型
    public ReturnMessage<?> uploadHeaderImage(@RequestParam("file") MultipartFile picture, HttpServletRequest request) {


        System.out.println(picture.getContentType());
        File filePath = new File(avatarPath);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        assert originalFileName != null;
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;

        //在指定路径下创建一个文件
        File targetFile = new File(avatarPath, fileName);

        //将文件保存到服务器指定位置
        try {
            // 存储文件
            picture.transferTo(targetFile);
            //将文件在服务器的存储路径返回
//            return returnService.success();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException(NowcodingErrCode.UPLOAD_FAIL.respCode(), NowcodingErrCode.UPLOAD_FAIL.respMessage());
        }


        //在拦截器中将用户信息存入了hostholder
        User user = hostholder.getUser();

        String headerUrl = domain + contextPath + "/img/header/" + fileName;

        // 更新当前用户头像的路径（web访问路径）
        //http:/localhost:8080/community/user/header/xxx.png
        userService.updateHeader(user.getId(), headerUrl);

        return returnService.successWithMessage(headerUrl);// 在返回的message节点中装入headerURL

    }
}
