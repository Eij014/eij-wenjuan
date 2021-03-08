package com.eij.wenjuan.component.service;

import java.util.List;

import com.eij.wenjuan.component.bean.Image;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-07
 */
public interface ImageUploadService {

    String uploadImage(String fileName, byte[] imageData);

    List<Image> getImages();
}
