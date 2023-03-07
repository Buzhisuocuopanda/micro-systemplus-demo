/*
package com.mkst.mini.systemplus.system.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import cn.hutool.core.convert.Convert;
import com.mkst.mini.systemplus.common.constant.Constants;
import com.mkst.mini.systemplus.framework.util.ServletUtils;
import com.mkst.mini.systemplus.system.domain.FastDfsFile;
import com.mkst.mini.systemplus.system.domain.SysUser;
import com.mkst.mini.systemplus.util.SysConfigUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.mkst.mini.systemplus.common.annotation.Log;
import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.common.enums.BusinessType;
import com.mkst.mini.systemplus.common.utils.ExcelUtil;
import com.mkst.mini.systemplus.framework.web.page.TableDataInfo;
import com.mkst.mini.systemplus.system.domain.Oss;
import com.mkst.mini.systemplus.system.service.IOssService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * 附件管理 信息操作处理
 *
 * @author systemplus
 * @date 2019-03-19
 *//*

@Controller
@RequestMapping("/system/oss")
public class OssController extends BaseController
{
    private String prefix = "system/oss";

	@Autowired
	private IOssService ossService;

	@RequiresPermissions("system:oss:view")
	@GetMapping()
	public String oss()
	{
	    return prefix + "/oss";
	}

	*/
/**
	 * 查询附件管理列表
	 *//*

	@RequiresPermissions("system:oss:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Oss oss)
	{
		startPage();
		oss.setCreateUser(Convert.toInt(this.getUserId()));
        List<Oss> list = ossService.selectOssList(oss);
		return getDataTable(list);
	}

	@RequiresPermissions("system:oss:list")
	@GetMapping("/files")
	public String files(Oss oss, ModelMap mmap)
	{
		startPage();
		List<Oss> list = ossService.selectOssList(oss);
		mmap.put("pageNum", ServletUtils.getParameterToInt(Constants.PAGE_NUM)!=null?ServletUtils.getParameterToInt(Constants.PAGE_NUM):1);
		mmap.put("files",getDataTable(list));
		return prefix + "/files";
	}


	*/
/**
	 * 导出附件管理列表
	 *//*

	@RequiresPermissions("system:oss:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Oss oss)
    {
    	List<Oss> list = ossService.selectOssList(oss);
        ExcelUtil<Oss> util = new ExcelUtil<Oss>(Oss.class);
        return util.exportExcel(list, "oss");
    }

	*/
/**
	 * 新增附件管理
	 *//*

	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	*/
/**
	 * 新增保存附件管理
	 *//*

	@RequiresPermissions("system:oss:add")
	@Log(title = "附件管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Oss oss)
	{
		return toAjax(ossService.insertOss(oss));
	}

	*/
/**
	 * 修改附件管理
	 *//*

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Oss oss = ossService.selectOssById(id);
		mmap.put("oss", oss);
	    return prefix + "/edit";
	}

	*/
/**
	 * 修改保存附件管理
	 *//*

	@RequiresPermissions("system:oss:edit")
	@Log(title = "附件管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Oss oss)
	{
		return toAjax(ossService.updateOss(oss));
	}

	*/
/**
	 * 删除附件管理
	 *//*

	@RequiresPermissions("system:oss:remove")
	@Log(title = "附件管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(ossService.deleteOssByIds(ids));
	}

	*/
/**
	 * 附件上传及选择附件
	 *//*

	@GetMapping("/ossSelect")
	public String ossSelect(ModelMap mmap)
	{
		String fastdfsServerPath = SysConfigUtil.getKey("fastdfs_server_path");
		mmap.put("fastdfsServerPath", fastdfsServerPath);
		return prefix + "/ossSelect";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public FastDfsFile uploadFile(HttpServletRequest request) throws IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			String filename = request.getParameter("filename");// 修改文件名

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();
				String originalFilename = mf.getOriginalFilename();
				String newFileName = null; */
/* 最后合并后的新文件名 *//*

				if (StringUtils.isNotEmpty(filename)) {
					filename = URLDecoder.decode(request.getParameter("filename"), "utf-8");
					newFileName = filename.concat(".").concat(FilenameUtils.getExtension(originalFilename));
				} else {
					newFileName = originalFilename;
				}

				// 获取文件名称和后缀名
				String ext = FilenameUtils.getExtension(originalFilename);
				SysUser user = this.getSysUser();
				FastDfsFile file = new FastDfsFile(mf.getBytes(), newFileName, ext, String.valueOf(mf.getSize()), user);
				NameValuePair[] meta_list = new NameValuePair[4];
				meta_list[0] = new NameValuePair("fileName", file.getFileName());
				meta_list[1] = new NameValuePair("fileLength", file.getFileLength());
				meta_list[2] = new NameValuePair("fileExt", ext);
				meta_list[3] = new NameValuePair("fileAuthor", user.getUserName());
				FastDFSClient fastDFSClient = null;
				try {
					fastDFSClient = new FastDFSClient();
					FastDfsFile uploadFile = fastDFSClient.upload(file, meta_list);
					if (null != uploadFile && uploadFile.getFileId() != null) {
						uploadFile.setState(FastDfsFile.STATE_SUCCESS);
						uploadFile.setType(mf.getContentType());
					} else {
						uploadFile.setState(FastDfsFile.STATE_ERROR);
					}
					return uploadFile;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			FastDfsFile uploadFile = new FastDfsFile(null,"");
			uploadFile.setState(FastDfsFile.STATE_ERROR);
			return uploadFile;
		}
		return null;
	}


}
*/
