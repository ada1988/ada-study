package org.ada.study.sharing.jdbc.web;


import java.math.BigDecimal;
import java.util.List;

import org.ada.study.sharing.jdbc.model.TOrderForm;
import org.ada.study.sharing.jdbc.model.TblOrderDetail;
import org.ada.study.sharing.jdbc.service.ITransactionService;
import org.ada.study.sharing.jdbc.service.TOrderFormService;
import org.ada.study.sharing.jdbc.service.TblOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 订单记录表 前端控制器
 * </p>
 *
 * @author czd
 * @since 2018-04-17
 */
@Controller
@RequestMapping("/tOrderForm")
public class TOrderFormController {
	@Autowired
	private TOrderFormService orderFormService;
	@Autowired
	private TblOrderDetailService orderDetailService;
	/**
	 * 插入数据
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("insert")
	public @ResponseBody String insert(){
		for(int i=0;i<10;i++){
			TOrderForm order = new TOrderForm();
			order.setOrderNo( new Long(i) );
			order.setLinkUser( "ada" );
			order.setVersion( 0 );
			orderFormService.insert( order );
			TblOrderDetail orderDetail = new TblOrderDetail();
			orderDetail.setOrderNo( new Long(i) );
			orderDetail.setProductName( "product" );
			orderDetail.setVersion( 0 );
			orderDetailService.insert( orderDetail );
		}
		return "success";
	}
	/**
	 * 更新数据
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("update")
	public @ResponseBody String update(){
		for(int i=0;i<10;i++){
			TOrderForm order = new TOrderForm();
			order.setOrderNo( new Long(i) );
			EntityWrapper<TOrderForm> w = new EntityWrapper<TOrderForm>();
			w.where( "order_no={0}", new Long(i) );
			TOrderForm o = orderFormService.selectOne( w );
			o.setDiscountAmount( new BigDecimal( "2.3" ) );
			o.setVersion( null );
			Boolean is = orderFormService.update( o, w );
			if(is){
				System.out.println("操作成功");
			}else{
				System.out.println("操作失败");
			}
		}
		return "success";
	}
	/**
	 * 更新数据(乐观锁)  不支持
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("updateVersion")
	public @ResponseBody String updateVersion(){
		for(int i=0;i<10;i++){
			TOrderForm order = new TOrderForm();
			order.setOrderNo( new Long(i) );
			EntityWrapper<TOrderForm> w = new EntityWrapper<TOrderForm>();
			w.where( "order_no={0}", new Long(i) );
			TOrderForm o = orderFormService.selectOne( w );
			w.and( "version", i );
			o.setDiscountAmount( new BigDecimal( "2.3" ) );
			o.setVersion( i );
			Boolean is = orderFormService.update( o, w );
			if(is){
				System.out.println("操作成功");
			}else{
				System.out.println("操作失败");
			}
		}
		return "success";
	}
	/**
	 * 查询数据
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("select")
	public @ResponseBody Page<TOrderForm> select(){
		Page<TOrderForm> pageInfo = orderFormService.selectPage( new Page<TOrderForm>( 1, 10 ) );
		return pageInfo;
	}
	/**
	 * 关联查询数据
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("selectRelation")
	public @ResponseBody List<TOrderForm> selectRelation(){
		List<TOrderForm> pageInfo = orderFormService.queryRelation();
		return pageInfo;
	}
	
	@Autowired
	private ITransactionService transactionService;
	/**
	 * 事物测试
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("transaction0")
	public @ResponseBody String transaction0(){
		transactionService.insert0();
		return "success";
	}
	/**
	 * 事物测试
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("transaction1")
	public @ResponseBody String transaction1(){
		transactionService.insert1();
		return "success";
	}
	/**
	 * 事物测试
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("transaction2")
	public @ResponseBody String transaction2(){
		transactionService.insert2();
		return "success";
	}
	/**
	 * 事物测试
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年4月17日
	 */
	@RequestMapping("transaction3")
	public @ResponseBody String transaction3(){
		transactionService.insert3();
		return "success";
	}
}
