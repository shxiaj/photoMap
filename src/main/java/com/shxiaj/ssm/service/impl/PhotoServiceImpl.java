package com.shxiaj.ssm.service.impl;

import com.shxiaj.ssm.mapper.PhotoMapper;
import com.shxiaj.ssm.pojo.Photo;
import com.shxiaj.ssm.service.PhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service(value = "photoService")
public class PhotoServiceImpl implements PhotoService {
    @Resource(name = "photoMapper")
    private PhotoMapper photoMapper;

    @Override
    public byte[] download(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        is.close();
        return bytes;
    }

    @Override
    public void upload(MultipartFile file, String photoPath) throws IOException {
        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf("."));

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSS");

        filename = "image-" + ft.format(date) + fileType;
        File folder = new File(photoPath);
        if (!folder.exists()) folder.mkdir();
        String finalPath = photoPath + File.separator + filename;
        file.transferTo(new File(finalPath));

        Photo photo = new Photo(null, filename, date);
        photoMapper.insert(photo);
    }

    @Override
    public List<Photo> selectAll() {
        return photoMapper.selectAll();
    }


    @Override
    public void deleteById(Integer id, String photoPath) {
        Photo photo = photoMapper.selectByPrimaryKey(id);
        String finalPath = photoPath + File.separator + photo.getFilename();
        File file = new File(finalPath);
        if (file.exists()) file.delete();
        photoMapper.deleteByPrimaryKey(id);
    }
}
