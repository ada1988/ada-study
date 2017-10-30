package org.ada.study.storm.mysql.handler;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ada.study.storm.mysql.common.UserRelationGlobal;
import org.ada.study.storm.mysql.cover.IFieldValueCovert;
import org.ada.study.storm.mysql.cover.PostParamCover;
import org.ada.study.storm.mysql.cover.SessionIdCover;
import org.ada.study.storm.mysql.em.UserRelationDbFieldsEM;
import org.apache.storm.jdbc.common.Column;
import org.apache.storm.tuple.Tuple;


/**  
 * Filename: UserRelationUrlHandler.java  <br>
 *
 * Description: 用户与请求关系处理  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年10月30日 <br>
 *
 *  
 */

@SuppressWarnings("rawtypes")
public class UserRelationUrlHandler implements IUrlHandler<Tuple, List<Column>>{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static final String MOBILE_PARAM_NAME = "mobile";
	
	public Map<String,IFieldValueCovert> paramCovers = new HashMap<String,IFieldValueCovert>();
	
	{
		/**
		 * 装载字段处理器
		 */
		for(UserRelationDbFieldsEM em:UserRelationDbFieldsEM.values()){
			if(em == UserRelationDbFieldsEM.user_mobile)
				paramCovers.put( em.getFieldName(), new PostParamCover(MOBILE_PARAM_NAME) );
			if(em == UserRelationDbFieldsEM.session_id)
				paramCovers.put( em.getFieldName(), new SessionIdCover() );
			if(em == UserRelationDbFieldsEM.session_version)
					paramCovers.put( em.getFieldName(), new SessionVersionCover() );
		}
	}
	
	@Override
	public List<Column> handler(Tuple req) {
		List<Column> columns = new ArrayList<Column>();
		
		IFieldValueCovert cover = null;
		String value = null;
		int[] preIndexs = null;
		for(UserRelationDbFieldsEM em:UserRelationDbFieldsEM.values()){
			
			value = null;
			cover = paramCovers.get( em.getFieldName() );
			preIndexs = em.getPreIndexs();
			for(int preIndex:preIndexs){
				if(null==value)
					value = req.getString( preIndex );
				else
					value = value+"###"+req.getString( preIndex );
			}
			if(null != cover){
				value = cover.valueCover( value );
			}
			columns.add( new Column( em.getFieldName(), value, Types.VARCHAR ) );
		}
		
		//存放全局变量，供下一节点处理
		UserRelationGlobal.setSessionIdAndMobile( getColumnValueByFieldName(columns,UserRelationDbFieldsEM.session_id), getColumnValueByFieldName( columns,UserRelationDbFieldsEM.user_mobile) );
		return columns;
	}
	
	/**
	 * 通过列名称，获取列值
	 * @param columns
	 * @param em
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年10月30日
	 */
	public String getColumnValueByFieldName(List<Column> columns,UserRelationDbFieldsEM em){
		String result = null;
		for(Column col:columns){
			if(col.getColumnName().equals( em.getFieldName() )) return col.getVal().toString();
		}
		return result;
	}
	/**
	 * session version
	 * 处理 
	 */
	private static class SessionVersionCover implements IFieldValueCovert{
		private final String SessionV1 = "1";
		private final String SessionV2 = "2";
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;

		@Override
		public String valueCover(String original) {
			String[] sessions = original.split( "###" );
			if(null!=sessions[0] && !"".equals( sessions[0] ) && !"".equals( sessions[0] ))
				return SessionV1;
			else
				return SessionV2;
		}
		
	}

}
