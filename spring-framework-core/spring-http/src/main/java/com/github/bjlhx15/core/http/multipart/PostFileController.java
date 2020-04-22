package com.github.bjlhx15.core.http.multipart;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping(value = "/multipart", method = RequestMethod.POST)
public class PostFileController {


    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "2000");
        map.put("size", file.getBytes().length);
        return map;
    }


    @RequestMapping("/uploadFileParam")
    @ResponseBody
    public Object uploadFileParam(@RequestParam("file") MultipartFile file, String msg) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "2000");
        map.put("size", file.getBytes().length);
        map.put("msg", msg);
        return map;
    }
    /**
     * 1、获取 流 方式处理
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadHandler1")
    @ResponseBody
    public Object uploadHandler1(@RequestParam("file") MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "2000");
        map.put("size", file.getBytes().length);
        //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
        InputStream is = file.getInputStream();
        return map;
    }

    /**
     * 2、使用file.Transto 来保存上传的文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadHandler2")
    @ResponseBody
    public Object uploadHandler2(@RequestParam("file") MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "2000");
        map.put("size", file.getBytes().length);
        File newFile = new File("path");
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        return map;
    }

    /**
     * 3、使用HttpServletRequest 自己写
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadHandler3")
    @ResponseBody
    public Object uploadHandler3(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "2000");

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = "E:/springUpload" + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        return map;
    }

}
