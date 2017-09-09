package org.ada.study.tools.model.state;
/**  
 * Filename: MsgCode.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2015年8月12日 <br>
 *
 *  
 */

public class MsgHelp {
	public enum ResultCodes{
		//必填项为空
		ADA_NULL,
		//字段不符合标准
		ADA_LENGTH,
		//操作成功
		ADA_SUCCESS,
		//操作失败
		ADA_ERROR,
		//调用方法错误
		ADA_FUNCTION_ERROR,
		//组合错误
		ADA_MORE_ERROR,
		//系统内部错误
		ADA_IN_ERROR,
		//系统内部错误
		ADA_OUT_ERROR
	}
	
	public final static String ADA_NULL = "外部错误，必填字段不可为空!";
	
	public final static String ADA_LENGTH = "外部错误，录入长度不符合标准!";
	
	public final static String ADA_SUCCESS = "操作成功!";
	
	public final static String ADA_ERROR = "未知原因,操作失败!";

	public static final String ADA_IN_FUNCTION_ERROR = "系统内部，方法调用错误!";
	
	public static final String ADA_IN_UPDATE_KEY_ERROR = "系统内部，没有该唯一键的数据!";
	
	public static final String ADA_OUT_UPDATE_EXIST_ERROR = "外部错误，关联其他数据，不可修改!";
	
	public static final String ADA_OUT_UPDATE_NOT_EXIST_ERROR = "外部错误，系统不存在该父编码!";
	
	public static final String ADA_OUT_UPDATE_UNIQUE_ERROR = "外部错误，唯一键重复!";
	
	public static final String ADA_IN_MODE_ERROR = "状态模式中，系统具体业务操作异常:";
	
	/**
	 * 装载返回值的实体
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 * @author: CZD  
	 * @Createtime: 2015年8月13日
	 */
	public static ResultBean loadResult(MsgHelp.ResultCodes code,String msg,Object data){
		ResultBean bean = new ResultBean();
		bean.setCode(code.toString());
		bean.setMgs(msg);
		bean.setData(data);
		return bean;
	}
	public static ResultBean loadResult(MsgHelp.ResultCodes code,String msg,boolean check){
		ResultBean bean = new ResultBean();
		bean.setCode(code.toString());
		bean.setMgs(msg);
		bean.setFail(check);
		return bean;
	}
	public static void main(String[] args) {
		System.out.println(MsgHelp.ResultCodes.ADA_ERROR.toString());
	}
}
