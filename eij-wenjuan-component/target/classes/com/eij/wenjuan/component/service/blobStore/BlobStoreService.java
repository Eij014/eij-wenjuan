package com.eij.wenjuan.component.service.blobStore;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-07
 */
public interface BlobStoreService {
    String storeImage(String key, byte[] screenshot);

    byte[] getImageByKey(String key);
}
