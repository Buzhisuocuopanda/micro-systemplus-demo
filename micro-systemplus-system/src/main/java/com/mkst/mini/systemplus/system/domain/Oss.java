package com.mkst.mini.systemplus.system.domain;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Lists;
import com.mkst.mini.systemplus.common.base.BaseEntity;
import com.mkst.mini.systemplus.util.FileSizeHelper;
import com.mkst.mini.systemplus.util.SysConfigUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 附件管理表 sys_oss
 * 
 * @author systemplus
 * @date 2019-03-19
 */
public class Oss extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 文件名称 */
	private String fileName;
	/** 文件后缀 */
	private String fileExt;
	/** 文件大小 */
	private String fileLength;
	/** 文件编号 */
	private String fileId;
	/** 状态 */
	private String status;
	/** 下载次数 */
	private String download;
	/** 文件存储位置 */
	private String storage;
	/** 创建者 */
	private Integer createUser;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private Integer modificationUser;
	/** 更新时间 */
	private Date modificationTime;
	/** 备注信息 */
	private String remarks;
	/** 删除标记 */
	private String deleteState;
	
	private String fileSize;

	private String filePath;

	//上传组件使用
	public String inputId;//组件id
	public String type;//files:文件，images:图片
	public String fileType;//运行上传文件类型 jpg|png|gif
	public String size; //限制文件大小，单位 KB
	public String images;//true，选择附件时只显示图片文件

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setFileExt(String fileExt) 
	{
		this.fileExt = fileExt;
	}

	public String getFileExt() 
	{
		return fileExt;
	}
	public void setFileLength(String fileLength) 
	{
		this.fileLength = fileLength;
	}

	public String getFileLength() 
	{
		return fileLength;
	}
	
	public String getFileSize() {
		if(StringUtils.isNotBlank(fileLength)){
			return 	FileSizeHelper.getHumanReadableFileSize(Long.parseLong(fileLength));
		}
		return fileSize;
	}
	public void setFileId(String fileId) 
	{
		this.fileId = fileId;
	}

	public String getFileId() 
	{
		return fileId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setDownload(String download) 
	{
		this.download = download;
	}

	public String getDownload() 
	{
		return download;
	}
	public void setStorage(String storage) 
	{
		this.storage = storage;
	}

	public String getStorage() 
	{
		return storage;
	}
	public void setCreateUser(Integer createUser) 
	{
		this.createUser = createUser;
	}

	public Integer getCreateUser() 
	{
		return createUser;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setModificationUser(Integer modificationUser) 
	{
		this.modificationUser = modificationUser;
	}

	public Integer getModificationUser() 
	{
		return modificationUser;
	}
	public void setModificationTime(Date modificationTime) 
	{
		this.modificationTime = modificationTime;
	}

	public Date getModificationTime() 
	{
		return modificationTime;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}
	public void setDeleteState(String deleteState) 
	{
		this.deleteState = deleteState;
	}

	public String getDeleteState() 
	{
		return deleteState;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileName", getFileName())
            .append("fileExt", getFileExt())
            .append("fileLength", getFileLength())
            .append("fileId", getFileId())
            .append("status", getStatus())
            .append("download", getDownload())
            .append("storage", getStorage())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("modificationUser", getModificationUser())
            .append("modificationTime", getModificationTime())
            .append("remarks", getRemarks())
            .append("deleteState", getDeleteState())
            .toString();
    }

	private static List<String> imageSuffix = Lists.newArrayList(".jpg", ".jpeg", ".png", ".bmp", ".gif", ".webp");
	private static List<String> docSuffix = Lists.newArrayList(".doc", ".docx", ".pages", ".dot", ".dotx", ".docm", ".dotm");
	private static List<String> xlsSuffix = Lists.newArrayList(".xls", ".xlsx", ".xltx", ".numbers", ".csv");
	private static List<String> pptSuffix = Lists.newArrayList(".ppt", ".pptx", ".key", ".ppsx", ".potx");
	private static List<String> txtSuffix = Lists.newArrayList(".txt", ".log", ".xml", ".json", ".js", ".css", ".html", ".sql", ".md");
	private static List<String> zipSuffix = Lists.newArrayList(".zip", ".tar", ".gz", ".jar", ".war", ".bz2", ".z");
	private static List<String> rarSuffix = Lists.newArrayList(".rar");
	private static List<String> exeSuffix = Lists.newArrayList(".exe", ".pkg", ".dmg");
	private static List<String> pdfSuffix = Lists.newArrayList(".pdf");
	private static List<String> audioSuffix = Lists.newArrayList(".mp3", ".au", ".cd", ".wma", ".ogg", ".ape", ".flac", ".aiff");
	private static List<String> videoSuffix = Lists.newArrayList(".mp4", ".avi", ".wmv", ".mpeg", ".mov", ".mkv", ".flv", ".rmvb", ".rm", ".3gp", ".ts", ".vob", ".m3u8");

	public boolean isImage() {
		return suffixIn(imageSuffix);
	}

	public boolean isDoc() {
		return suffixIn(docSuffix);
	}

	public boolean isXls() {
		return suffixIn(xlsSuffix);
	}

	public boolean isTxt() {
		return suffixIn(txtSuffix);
	}

	public boolean isPpt() {
		return suffixIn(pptSuffix);
	}

	public boolean isZip() {
		return suffixIn(zipSuffix);
	}

	public boolean isRar() {
		return suffixIn(rarSuffix);
	}

	public boolean isExe() {
		return suffixIn(exeSuffix);
	}

	public boolean isPdf() {
		return suffixIn(pdfSuffix);
	}

	public boolean isAudio() {
		return suffixIn(audioSuffix);
	}

	public boolean isVideo() {
		return suffixIn(videoSuffix);
	}

	private boolean suffixIn(List<String> suffixList) {
		String suffix = "."+getFileExt();
		return suffix != null && suffixList.contains(suffix.toLowerCase());
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getFilePath() {
		if (StringUtils.isNotEmpty(fileId)) {
			return SysConfigUtil.getKey("fastdfs_server_path")+fileId;
		}
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
