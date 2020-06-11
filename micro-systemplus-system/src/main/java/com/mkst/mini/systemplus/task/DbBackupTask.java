package com.mkst.mini.systemplus.task;

import com.mkst.mini.systemplus.common.config.Global;
import com.mkst.mini.systemplus.common.factory.AsyncFactory;
import com.mkst.mini.systemplus.framework.manager.AsyncManager;
import com.mkst.mini.systemplus.quartz.task.RyTask;
import com.mkst.mini.systemplus.system.service.ISysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库备份任务
 *
 * @author liuzhiping
 */
@Component("dbBackupTask")
public class DbBackupTask {
    private static final Logger log = LoggerFactory.getLogger(DbBackupTask.class);
    @Autowired
    DbParamConfig dbParamConfig;
    @Autowired
    ISysConfigService configService;
    private static String mysqlBasedir = null;

    private void initMysqlDir(){
        if(mysqlBasedir==null){
            mysqlBasedir = configService.getMysqlBasedir();
        }
    }

    public void backupDB() {
        log.info("------------------执行定时任务------------Systemplus.backupDB");
        try {
            initMysqlDir();
            //备份数据库
            backup(Global.getDbBackupPath());

            //默认清理操作30天的数据库备份文件
            final Integer days=30;
            final String folder= Global.getDbBackupPath();
            AsyncManager.me().execute(AsyncFactory.cleanOutDateBackupFile(days,folder));

        } catch (Exception e) {
            log.error("执行数据库备份时发生异常："+e.getMessage());
        }
    }
    /**
     * 备份数据库操作
     *
     * @param fileBasePath 文保存件路径
     * @return
     */
    private void backup(String fileBasePath) throws IOException {
        Long starttime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        String name = dbParamConfig.getDatabaseName()+"_"+sdf.format(new Date())+".sql";

        File sqlFile = BatCommandUtil.createFile(fileBasePath+File.separator+ name);
        StringBuilder cmdStr = new StringBuilder();
        // 构建数据库备份参数
        cmdStr.append(" -u").append(dbParamConfig.getUsername())
                //注意：如果数据库密码为空，导不出来
                .append(" -p").append(dbParamConfig.getPassword())
                .append(" -h").append(dbParamConfig.getHost())
                .append(" -P").append(dbParamConfig.getPort())
                .append(" ").append(dbParamConfig.getDatabaseName())
                .append(" ").append("--hex-blob")//使用十六进制符号转储二进制字符序列，防止乱码
                .append(" > ").append(sqlFile.getPath());
        if (isWindows()) {
            BatCommandUtil.writeAndExcute(fileBasePath+File.separator+"backupmysql.bat",new StringBuilder()
                    .append("cd /d ").append(mysqlBasedir).append("bin").append("\r\n")
                    .append("mysqldump").append(cmdStr.toString()).toString());
            Long endtime = System.currentTimeMillis();
            Long distance = endtime - starttime;
            log.info("【" + dbParamConfig.getDatabaseName() + "】备份成功，耗时：" + distance + "ms");
        } else {
            BatCommandUtil.writeAndExcute(fileBasePath+File.separator+"backupmysql.sh",new StringBuilder()
                    .append("cd ").append(mysqlBasedir).append("bin").append("\r\n")
                    .append("mysqldump").append(cmdStr.toString()).toString());
            Long endtime = System.currentTimeMillis();
            Long distance = endtime - starttime;
            log.info("【" + dbParamConfig.getDatabaseName() + "】备份成功，耗时：" + distance + "ms");

            /*try {
                Runtime rt = Runtime.getRuntime();
                Process process = rt.exec(new StringBuilder().append(mysqlBasedir).append("bin").append(File.separator).append("mysqldump").append(cmdStr.toString()).toString());
                int exitVal = process.waitFor();
                if(exitVal==0){
                    Long endtime = System.currentTimeMillis();
                    Long distance = endtime - starttime;
                    log.info("【" + dbParamConfig.getDatabaseName() + "】备份成功，耗时：" + distance + "ms");
                }else{
                    InputStream is = process.getErrorStream();
                    if (is != null) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(is, "utf-8"));
                        String line;
                        StringBuilder sb = new StringBuilder();
                        while ((line = in.readLine()) != null) {
                            sb.append(line);
                        }
                        log.error("数据库备【" + dbParamConfig.getDatabaseName() + "】份失败\r\n" + sb.toString());
                    }
                }
            } catch (InterruptedException e) {
                log.error("数据库备份异常");
                e.printStackTrace();
            }*/
            //throw new RuntimeException("暂时只支持windows系统！");
        }
    }


    private static boolean isWindows() {
        return System.getProperty("os.name").indexOf("Windows") != -1;
    }
}
