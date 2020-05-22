package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static sun.nio.ch.IOStatus.EOF;

/**
 * created by houchangchang on 2020/5/20
 */
@Controller
@RequestMapping("files")
public class uploadController {

    @RequestMapping(value = "download")
    public void download(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String basePath = request.getSession().getServletContext().getRealPath("/files/download");
        //读取文件内容
        FileInputStream is = new FileInputStream(new File(basePath, filename));
//        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));
        response.setHeader("content-disposition", "inline;fileName=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        System.out.println("duwnload文件------------");
//        int n = 0;
//        byte[] buffer = new byte[1024 * 4];
//        while (EOF != (n = is.read(buffer))) {
//            os.write(buffer, 0, n);
//        }
//        is.close();
//        os.close();
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String upload(MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        System.out.println("路径 = " + request.getSession().getServletContext().getRealPath("/"));//获取项目根路径
        System.out.println("文件名 = " + multipartFile.getOriginalFilename());
        System.out.println("文件类型 = " + multipartFile.getContentType());
        System.out.println("文件大小 = " + multipartFile.getSize());
        String realPath = request.getSession().getServletContext().getRealPath("/files");
        File file = new File(realPath, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if(!file.exists()) file.mkdirs();
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String newName = UUID.randomUUID().toString().replaceAll("-", "") + new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()) + "." + extension;
        multipartFile.transferTo(new File(file, newName));
        return "redirect:/upload.jsp";
    }

}
