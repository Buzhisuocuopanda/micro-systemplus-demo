package com.mkst.mini.systemplus.system.web;


import com.google.common.collect.Lists;
import com.mkst.mini.systemplus.common.annotation.Log;
import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.common.config.Global;
import com.mkst.mini.systemplus.common.enums.BusinessType;
import com.mkst.mini.systemplus.common.utils.file.ReadDirector;
import com.mkst.mini.systemplus.framework.web.page.TableDataInfo;
import com.mkst.mini.systemplus.task.DbParamConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库备份与恢复操作
 *
 * @author wujiyue
 * @date 2019-11-04
 */
@Controller
@RequestMapping("/system/backup")
public class BackupController extends BaseController {

    @Autowired
    DbParamConfig dbParamConfig;

    private String prefix = "system/backup";

    @RequiresPermissions("system:backup")
    @GetMapping()
    public String backup(ModelMap modelMap) {
        return prefix + "/backup";
    }
    @PostMapping("/files")
    @ResponseBody
    public TableDataInfo files() throws Exception
    {
        String folder= Global.getDbBackupPath();
        List<String> fs= ReadDirector.getFiles(folder);

        List<Map<String,Object>> files= Lists.newArrayList();
        Map<String,Object> temp=null;
        int i=0;
        String name="";
        String hourSeconds="";
        for(String s:fs){
            if(s.endsWith(".sql")){
                i++;
                temp = new HashMap<>();
                temp.put("rowNum", i);
                name = s;
                temp.put("name", name);
                name = name.replace(dbParamConfig.getDatabaseName()+"_", "");
                hourSeconds = name.substring(11, 19);
                hourSeconds = hourSeconds.replace("_", ":");
                name = name.substring(0, 10);

                name = name.replace("_", "-");
                name = name + " " + hourSeconds;
                temp.put("time", name);
                files.add(temp);
            }
        }
        return getDataTable(files);
    }

    /**
     * 删除备份文件
     */
    @RequiresPermissions("system:backup:delete")
    @Log(title = "删除备份文件", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String name) {
        String dir= Global.getDbBackupPath();
        String filePath=dir+File.separator+name;
        File file=new File(filePath);
        if(file.exists()){
            file.delete();
        }
        return success();
    }


}
