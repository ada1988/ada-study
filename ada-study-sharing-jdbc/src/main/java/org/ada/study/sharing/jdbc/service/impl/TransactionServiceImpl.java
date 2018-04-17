package org.ada.study.sharing.jdbc.service.impl;

import org.ada.study.sharing.jdbc.mapper.TblOrderDetailMapper;
import org.ada.study.sharing.jdbc.model.TOrderForm;
import org.ada.study.sharing.jdbc.service.ITransactionService;
import org.ada.study.sharing.jdbc.service.TOrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**  
 * Filename: TransactionServiceImpl.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2018年4月17日 <br>
 *
 *  
 */
@Service
public class TransactionServiceImpl implements ITransactionService{
	@Autowired
	private TOrderFormServiceImpl tOrderFormMapper;
	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insert0() {
		TOrderForm order = new TOrderForm();
		order.setOrderNo( new Long(31) );
		tOrderFormMapper.insert( order );
		
	}

	/**
	 * 同一个class中事物配置失效
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert1() {
		TOrderForm order = new TOrderForm();
		order.setOrderNo( new Long(31) );
		tOrderFormMapper.insert( order );
		insert0();
	}

	@Autowired
	private TOrderFormService orderFormService;
	/**
	 * 全部回滚
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert2() {
		TOrderForm order = new TOrderForm();
		order.setOrderNo( new Long(25) );
		tOrderFormMapper.insert( order );
		orderFormService.insert0();
	}
	/**
	 * 部分回滚
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert3() {
		orderFormService.insert0();
		TOrderForm order = new TOrderForm();
		order.setOrderNo( new Long(25) );
		tOrderFormMapper.insert( order );
	}
}
