package com.eij.wenjuan.component.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.eij.wenjuan.component.bean.Image;
import com.eij.wenjuan.component.dao.ImageDao;
import com.eij.wenjuan.component.service.ImageUploadService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-07
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    private static final Logger logger = LoggerFactory.getLogger(ImageUploadServiceImpl.class);

    private static final String SERVER_NAME = "http://eij.ink:27546/image/%s";

    @Autowired
    private ImageDao imageDao;

    @Override
    public String uploadImage(String fileName, byte[] imageData) {
        String imageKey = getImageKey(fileName);
        try {
            File imageFile = new File("D:\\image\\" + imageKey);
            FileOutputStream outStream = new FileOutputStream(imageFile);
            outStream.write(imageData);
            outStream.close();
        } catch (IOException e) {
            logger.error("upload image error.msg={}", e.getMessage());
        }
        Image image = new Image();
        image.setImageKey(imageKey);
        image.setImageUrl(String.format(SERVER_NAME, imageKey));
        imageDao.insert(image);
        List<String> test = new ArrayList<>();
        test.stream();
        return  String.format(SERVER_NAME, imageKey);
    }

    private String getImageKey(String fileName) {
        String fileNameWithTime = String.join("-", fileName, String.valueOf(System.nanoTime()));
        String[] file = fileName.split("\\.");
        String type = file.length > 1 ? file[1] : "png";
        return DigestUtils.md5DigestAsHex(fileNameWithTime.getBytes()) + "." + type;
    }

    @Override
    public List<Image> getImages() {
        return imageDao.selectAll();
    }
}
