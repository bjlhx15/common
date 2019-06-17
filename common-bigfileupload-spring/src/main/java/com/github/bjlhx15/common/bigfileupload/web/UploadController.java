package com.github.bjlhx15.common.bigfileupload.web;


import com.github.bjlhx15.common.bigfileupload.bean.FileBean;
import com.github.bjlhx15.common.bigfileupload.bean.FileUploadBean;
import com.github.bjlhx15.common.bigfileupload.utils.FileUtilsHelper;
import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// 一个http上传大文件实践
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static String finalDirPath = "/Users/lihongxu6/tmp/upload/";

    private Map<String, FileBean> fileMap = Collections.synchronizedMap(new HashMap<String, FileBean>());

    @RequestMapping(value = "upload.do")
    public void upload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean multipartContent = ServletFileUpload.isMultipartContent(req);
        // 是文件上传请求
        if (multipartContent) {
            // 获取请求长度
            int length = req.getIntHeader("Content-Length");
            logger.info("用户请求的长度为:{}", length);

            // 创建工厂（这里用的是工厂模式）
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //获取汽车零件清单与组装说明书（从ServletContext中得到上传来的数据）
            //ServletContext servletContext = this.getServletConfig().getServletContext();
            ServletContext servletContext = req.getServletContext();
            // 临时文件目录
//            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            File repository = (File) servletContext.getAttribute(ApplicationContext.TEMPDIR);
            //工厂把将要组装的汽车的参数录入工厂自己的系统，因为要根据这些参数开设一条生产线（上传来的文件的各种属性）
            factory.setRepository(repository);
            //此时工厂中已经有了汽车的组装工艺、颜色等属性参数（上传来的文件的大小、文件名等）
            //执行下面的这一行代码意味着根据组装工艺等开设了一条组装生产线
            ServletFileUpload upload = new ServletFileUpload(factory);

            List<FileItem> fileItems;
            try {
                fileItems = upload.parseRequest(req);
            } catch (FileUploadException e) {
                logger.error("解析请求出现异常", e);
                return;
            }
            FileUploadBean param = new FileUploadBean(fileItems, logger);
            logger.info("文件信息为:{}", param.toString());

            // 然后存储
            String fileName = param.getName();
            String uploadDirPath = finalDirPath + param.getMd5();
            String tempFileName = fileName + "_" + param.getChunk() + "_tmp";
            Path tmpDir = Paths.get(uploadDirPath);
            if (!Files.exists(tmpDir)) {
                synchronized (UploadController.class) {
                    if (!Files.exists(tmpDir)) {
                        Files.createDirectory(tmpDir);
                    }
                }
            } else {
                // 文件夹已存在, 先检测是否完成收集
                // 1.检查是否有文件,有进入2, 没有进3
                Path localPath = Paths.get(uploadDirPath, fileName);

                // 2.检查md5值是否匹配, 应该建立数据库,存储文件信息才是更快 更好的解决办法.
                // 2.1.若匹配直接返回成功.
                // 2.2 若不成功,删除源文件再次读取
                if (Files.exists(localPath)) {
                    String nowMd5 = DigestUtils.md5Hex(Files.newInputStream(localPath, StandardOpenOption.READ));
                    if (StringUtils.equals(param.getMd5(), nowMd5)) {
                        // 比较相等,那么直接返回成功.
                        logger.info("已检测到重复文件{},并且比较md5相等,已直接返回", fileName);
                        return;
                    } else {
                        // 删除
                        logger.info("已经存在的文件的md5不匹配上传上来的文件的md5,删除后重新下载");
                        Files.delete(localPath);
                    }
                }
                // 3. 直接写入到具体目录下.
            }


            //写入该分片数据
            // 0.读取上传文件到数组
            // 1.写到本地
            // 1.记录分片数,检查分片数
            // 2.当对应的md5读取数量达到对应的文件后,合并文件
            // 3.删除临时文件
            Path path = Paths.get(uploadDirPath, tempFileName);
            //文件上传时,获取是否有分片,如果有直接返回.
            if (!Files.exists(path)) {
                // 不存在.
                byte[] fileData = FileUtilsHelper.read(param.getFile(), 2048);
                try {
                    Files.write(path, fileData, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
                } catch (IOException e) {
                    // 删除上传的文件
                    Files.delete(path);
                    throw e;
                }
                FileUtilsHelper.authorizationAll(path);
            }

            FileBean fileBean;
            if (fileMap.containsKey(param.getMd5())) {
                fileBean = fileMap.get(param.getMd5());
            } else {
                fileBean = new FileBean(param.getName(), param.getChunks(), param.getMd5());
                fileMap.put(param.getMd5(), fileBean);
            }
            //Stream.iterate(0, (value) -> value).limit(param.getChunk()).forEach(System.out::println);
            fileBean.addIndex(param.getChunk());

            if (fileBean.isLoadComplate()) {
//                // 先查看目录下的文件数是否满足条件
//                int count = 0;
//                // 遍历一层文件
//                try(DirectoryStream<Path> paths = Files.newDirectoryStream(tmpDir)) {
//                    for(Path entry : paths) {
//                        count++;
//                    }
//                }
//                if (count != fileBean.getChunks()) {
//                    logger.info("大小与目录下的文件数不符,不能合并.{}", count);
//                    return;
//                }
                // 合并文件..
                Path realFile = Paths.get(uploadDirPath, fileBean.getName());
                realFile = Files.createFile(realFile);
                // 设置权限
                FileUtilsHelper.authorizationAll(realFile);
                for (int i = 0; i < fileBean.getChunks(); i++) {
                    // 获取每个分片
                    tempFileName = fileName + "_" + i + "_tmp";
                    Path itemPath = Paths.get(uploadDirPath, tempFileName);
                    byte[] bytes = Files.readAllBytes(itemPath);
                    Files.write(realFile, bytes, StandardOpenOption.APPEND);
                    //写完后删除掉临时文件.
                    Files.delete(itemPath);
                }
                logger.info("合并文件{}成功", fileName);
            }
        }
    }

    @RequestMapping("/upload2.do")
    public void bigFile(HttpServletRequest request, HttpServletResponse response, String guid, String md5, Integer chunk, MultipartFile file, Integer chunks) {
        try {
            //guid=md5;
            logger.warn("md5:" + md5);
            if (file.isEmpty() || file.getSize() == 0) {
                return;
            }
            //秒传
            if (secondUploadExist(md5, file.getOriginalFilename())) {
                logger.warn("秒传");
                return;
            }
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            chunk = chunk == null ? 0 : chunk;
            if (isMultipart) {
                // 临时目录用来存放所有分片文件
                String tempFileDir = finalDirPath + guid;
                File parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
                Path partPath = Paths.get(tempFileDir, guid + "_" + chunk + ".part");
                // 断点续传 文件上传时,获取是否有分片,如果有直接返回.
                if (!Files.exists(partPath)) {
                    File tempPartFile = new File(parentFileDir, guid + "_" + chunk + ".part");
                    FileUtils.copyInputStreamToFile(file.getInputStream(), tempPartFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 合并文件
     *
     * @param guid
     * @param fileName
     * @throws Exception
     */
    @RequestMapping("/merge.do")
    @ResponseBody
    public Object mergeFile(String guid, String md5, String fileName) {
        Map<Object, Object> result = new HashMap<>();
        result.put("code", 200);
        // 得到 destTempFile 就是最终的文件
        //guid=md5;
        if (secondUploadExist(md5, fileName)) {
            logger.warn("秒传");
            return result;
        }
        try {
            File parentFileDir = new File(finalDirPath + guid);
            if (parentFileDir.isDirectory()) {
                File destTempFile = new File(finalDirPath + "/" + md5, fileName);
                if (!destTempFile.exists()) {
                    //先得到文件的上级目录，并创建上级目录，在创建文件,
                    destTempFile.getParentFile().mkdir();
                    try {
                        //创建文件
                        destTempFile.createNewFile(); //上级目录没有创建，这里会报错
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(parentFileDir.listFiles().length);
                for (int i = 0; i < parentFileDir.listFiles().length; i++) {
                    File partFile = new File(parentFileDir, guid + "_" + i + ".part");
                    FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
                    //遍历"所有分片文件"到"最终文件"中
                    FileUtils.copyFile(partFile, destTempfos);
                    destTempfos.close();
                }
                // 删除临时目录中的分片文件
                FileUtils.deleteDirectory(parentFileDir);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 400);
            return result;
        }
        return result;
    }

    private boolean secondUploadExist(String md5, String filename) {
        Path path = Paths.get(finalDirPath + md5, filename);
        return Files.exists(path);
    }

}
