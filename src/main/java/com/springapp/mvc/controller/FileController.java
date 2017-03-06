package com.springapp.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/18.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    /** 下载模板，没有再生成，读取的是现成的模板文件 */
    @RequestMapping(value="/template")
    public String downTemplate(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath("");
            File file = new File(path + "/template/template.rar");
            if (file.exists()) {
                InputStream is = new FileInputStream(file);
                OutputStream os = response.getOutputStream();

                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", "attachment;filename=template.rar");

                int actual = 0;
                int bytesRead = 0;
                int len = (int) file.length();
                byte[] buffer = new byte[len];

                while ((bytesRead < len) && (actual != -1)) {
                    actual = is.read(buffer, bytesRead, len - bytesRead);
                    bytesRead += actual;
                }
                os.write(buffer);
                os.flush();
                os.close();
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 这种写法不能捕获文件超大异常！ */
    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> upload2(ModelMap model, @RequestParam("file")MultipartFile file, DefaultMultipartHttpServletRequest request, HttpServletResponse response) {
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("flag", "2");

        try {
            if (file != null && !file.isEmpty()) {

            } else {
                returnMap.put("msg", "文件为空");
                return returnMap;
            }

            String filename = file.getOriginalFilename();
            String path = request.getSession().getServletContext().getRealPath("/uploadFile") + File.separator + filename;
            File target = new File(path);
            target.getParentFile().mkdirs();

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target));
            BufferedInputStream in = new BufferedInputStream(file.getInputStream());
            byte[] data = new byte[1024];
            while (in.read(data) != -1) {
                out.write(data);
            }
            out.flush();
            out.close();
            in.close();

            returnMap.put("flag", "1");
            returnMap.put("msg", "上传成功");

            return returnMap;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MaxUploadSizeExceededException) {
                returnMap.put("msg", "文件已超过最大值15M");
                return returnMap;
            } else {
                returnMap.put("msg", "文件上传发生错误");
                return returnMap;
            }
        }
    }

    @RequestMapping(value = "/interUpload")
    public String interUpload(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "/upload/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> upload(ModelMap model, DefaultMultipartHttpServletRequest request, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mt = new MediaType("text", "html", Charset.forName("utf-8"));
        headers.setContentType(mt);
        ResponseEntity<Map<String, String>> re = null;

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("flag", "2");// 表示失败

        try {
            MultipartFile file = request.getFile("file");
            if (file != null && !file.isEmpty()) {

            } else {
                resultMap.put("msg", "文件为空");
                re = new ResponseEntity<Map<String, String>>(resultMap, headers, HttpStatus.OK);
                return re;
            }

            String filename = file.getOriginalFilename();
            String path = request.getSession().getServletContext().getRealPath("/uploadFile") + File.separator + filename;
            /**
             * getRealPath()参数必须加/，表示相对于项目的绝对路径（即在项目根目录下生成该保存上传文件的目录），
             * 否则会在tomcat/bin目录下生成一个名字是null的目录，上传的文件跑到这了。
             */
            File target = new File(path);
            target.getParentFile().mkdirs();

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target));
            BufferedInputStream in = new BufferedInputStream(file.getInputStream());
            byte[] data = new byte[1024];
            while (in.read(data) != -1) {
                out.write(data);
            }
            out.flush();
            out.close();
            in.close();

            resultMap.put("flag", "1");
            resultMap.put("msg", "上传成功");

            re = new ResponseEntity<Map<String, String>>(resultMap, headers, HttpStatus.OK);
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MaxUploadSizeExceededException) {
                resultMap.put("msg", "文件已超过最大值15M");
                re = new ResponseEntity<Map<String, String>>(resultMap, headers, HttpStatus.OK);
                return re;
            } else {
                resultMap.put("msg", "文件上传发生错误");
                re = new ResponseEntity<Map<String, String>>(resultMap, headers, HttpStatus.OK);
                return re;
            }
        }
    }
}
