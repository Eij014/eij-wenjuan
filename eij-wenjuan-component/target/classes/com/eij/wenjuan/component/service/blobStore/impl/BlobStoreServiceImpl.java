package com.eij.wenjuan.component.service.blobStore.impl;

import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.service.blobStore.BlobStoreService;


/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-07
 */
@Service
public class BlobStoreServiceImpl implements BlobStoreService {
    @Override
    public String storeImage(String key, byte[] screenshot) {
        boolean success = true;

        if (!success) {
            throw new RuntimeException("存储图片失败" + key);
        }
        return key;
    }

    @Override
    public byte[] getImageByKey(String key) {
        return new byte[10];
    }
}
