package com.shxiaj.ssm.service;

import com.shxiaj.ssm.pojo.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PhotoService {
    byte[] download(String path) throws IOException;
    void upload(MultipartFile file, String photoPath) throws IOException;
    List<Photo> selectAll();
    void deleteById(Integer id, String realPath);

}
