package org.ada.study.rpc.frame.common.msg;
/**  
 * Filename: RpcResponse.java  <br>
 *
 * Description:  响应实体 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月17日 <br>
 *
 *  
 */

public class RpcResponse {
	private String requestId;
    private Throwable error;
    private Object result;
    private Boolean isError;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Throwable getError() {
		return error;
	}
	public void setError(Throwable error) {
		this.error = error;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Boolean isError(){
		return isError;
	}
}
