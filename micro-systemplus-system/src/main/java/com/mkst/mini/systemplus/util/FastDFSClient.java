package com.mkst.mini.systemplus.util;

import com.mkst.mini.systemplus.framework.util.SpringUtils;
import com.mkst.mini.systemplus.system.domain.FastDfsFile;
import com.mkst.mini.systemplus.system.service.IOssService;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FastDFSClient {

    private static IOssService ossService  =   SpringUtils.getBean(IOssService.class);

    private static String classPath = SysConfigUtil.getKey("fdfs_client_path");

    private static final String CONFIG_FILENAME = classPath + File.separator + "fdfs_client.conf";
    private static final String GROUP_NAME = "group1";
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient storageClient = null;

    static{
        try {
            ClientGlobal.init(CONFIG_FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
    public FastDFSClient() throws Exception {
        trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient = new StorageClient(trackerServer, storageServer);
    }

    /**
     * <strong>方法概要： 文件上传</strong> <br>
     * <strong>创建时间： 2018年5月9日11:18:12</strong> <br>
     *  synchronized 多文件上传时报io异常。采用锁解决该问题
     *
     * @param file
     * @return uploadResults
     * @author liuzhiping
     */
    public FastDfsFile upload(FastDfsFile file, NameValuePair[] valuePairs) {
        try {
            String[] results = storageClient.upload_file(GROUP_NAME,file.getContent(),file.getFileExt(),valuePairs);
            file.setFileId(results[0]+"/"+results[1]);
            file.setStatus("1");
            file.setRemarks("fastDfs上传");
            ossService.insertOss(file);
            file.setFilePath(SysConfigUtil.getKey("fastdfs_server_path") + file.getFileId());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return file;
        }
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @param fileName 文件名
     * @return
     */
    public  String[] uploadFile(File file, String fileName) {
        return uploadFile(file,fileName,null);
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @param fileName 文件名
     * @param metaList 文件元数据
     * @return
     */
    public  String[] uploadFile(File file, String fileName, Map<String,String> metaList) {
        try {
            byte[] buff = IOUtils.toByteArray(new FileInputStream(file));
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<String,String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name,value);
                }
            }
            return storageClient.upload_file(GROUP_NAME,buff,fileName,nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件元数据
     * @param fileId 文件ID
     * @return
     */
    public Map<String,String> getFileMetadata(String groupname,String fileId) {
        try {
            NameValuePair[] metaList = storageClient.get_metadata(groupname,fileId);
            if (metaList != null) {
                HashMap<String,String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(),metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     * @param fileId 文件ID
     * @return 删除失败返回-1，否则返回0
     */
    public int deleteFile(String groupname,String fileId) {
        try {
            return storageClient.delete_file(groupname,fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 下载文件
     * @param fileId 文件ID（上传文件成功后返回的ID）
     * @param outFile 文件下载保存位置
     * @return
     */
    public  int downloadFile(String groupName,String fileId, File outFile) {
        FileOutputStream fos = null;
        try {
            byte[] content = storageClient.download_file(groupName,fileId);
            fos = new FileOutputStream(outFile);
            InputStream ips = new ByteArrayInputStream(content);
            IOUtils.copy(ips,fos);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        FastDFSClient client = new FastDFSClient();
        File file = new File("D:\\test.jpeg");
        String[] result = client.uploadFile(file, "jpeg");
        System.out.println(result.length);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
