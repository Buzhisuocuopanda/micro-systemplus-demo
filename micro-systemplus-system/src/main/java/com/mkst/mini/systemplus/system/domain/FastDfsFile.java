package com.mkst.mini.systemplus.system.domain;

public class FastDfsFile extends Oss {

    public static final String STATE_SUCCESS = "SUCCESS";
    public static final String STATE_ERROR = "ERROR";

	private static final long serialVersionUID = 1L;

    private byte[] content;
    
    private String filePath;

    private String state;

    private String url;

    private String size;

    private String original;

    private String type;

    public FastDfsFile(byte[] content, String ext) {
        this.content = content;
        super.setFileExt(ext);
    }

    public FastDfsFile(byte[] content, String name, String ext) {
        this.content = content;
        super.setFileName(name);
        super.setFileExt(ext);
    }

    public FastDfsFile(byte[] content, String name, String ext, String length, SysUser author) {
        this.content = content;
        super.setFileName(name);
        super.setFileExt(ext);
        super.setFileLength(length);
        super.setCreateUser(author.getUserId().intValue());
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return filePath;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return super.getFileLength();
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOriginal() {
        return super.getFileName();
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
