package org.ada.study.netty.custom.entity;

/**
 * Filename: LuckHeader.java <br>
 *
 * Description: Header : 协议头部，放置一些Meta信息。 Content : 应用之间交互的信息主体。 <br>
 * 其中Version,Content-Length,SessionId就是Header信息，Content就是交互的主体
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年9月25日 <br>
 *
 * 
 */
// 消息的头部
public class LuckHeader {
	// 协议版本
	private int		version;
	// 消息内容长度
	private int		contentLength;
	// 服务名称
	private String	sessionId;

	public LuckHeader(int version, int contentLength, String sessionId) {
		this.version = version;
		this.contentLength = contentLength;
		this.sessionId = sessionId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
