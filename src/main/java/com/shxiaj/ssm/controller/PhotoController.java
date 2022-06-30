package com.shxiaj.ssm.controller;

import com.shxiaj.ssm.pojo.FileParam;
import com.shxiaj.ssm.pojo.Photo;
import com.shxiaj.ssm.service.PhotoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class PhotoController {
    @Resource(name = "photoService")
    private PhotoService photoService;

    @RequestMapping(name = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile photo, HttpSession session) throws IOException {
        String realPath = session.getServletContext().getRealPath(FileParam.PATH);
        photoService.upload(photo, realPath);
        System.out.println(realPath);
        return "redirect:/photoList";
    }

    @RequestMapping(value = "/photoList", method = RequestMethod.GET)
    public String photoList(Model model, HttpServletRequest request) {
        // 图片相对根路径
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + FileParam.PATH + "/";
        List<Photo> photos = photoService.selectAll();
        model.addAttribute("photos", photos);
        model.addAttribute("basePath", basePath);
        //System.out.println(contextPath);
        return "photoList";
    }

    @RequestMapping(value = "/photo/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable Integer id, HttpSession session) {
        String realPath = session.getServletContext().getRealPath(FileParam.PATH);
        photoService.deleteById(id,realPath);
        return "redirect:/photoList";
    }
    //@RequestMapping(value = "testDown", method = RequestMethod.GET)
    //public ResponseEntity<byte[]> testDown(HttpSession session) throws IOException {
    //    ServletContext servletContext = session.getServletContext();
    //    String contextPath = servletContext.getContextPath();
    //    System.out.println(contextPath);
    //    String realPath = servletContext.getRealPath("/static/img/1.jpg");
    //    System.out.println(realPath);
    //    byte[] bytes = photoService.download(realPath);
    //    //创建HttpHeaders对象设置响应头信息
    //    HttpHeaders headers = new HttpHeaders();
    //    //设置要下载方式以及下载文件的名字
    //    headers.add("Content-Disposition", "attachment;filename=1.jpg");
    //    //设置响应状态码
    //    HttpStatus statusCode = HttpStatus.OK;
    //    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
    //    return responseEntity;
    //}


    //@RequestMapping(value = "/uploadphoto", method = RequestMethod.GET)
    //public String toPhoto() {
    //    return "uploadphoto";
    //}
}
