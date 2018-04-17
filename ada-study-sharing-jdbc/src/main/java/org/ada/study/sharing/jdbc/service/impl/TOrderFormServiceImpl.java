package org.ada.study.sharing.jdbc.service.impl;

import java.util.List;

import org.ada.study.sharing.jdbc.model.TOrderForm;
import org.ada.study.sharing.jdbc.mapper.TOrderFormMapper;
import org.ada.study.sharing.jdbc.service.TOrderFormService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单记录表 服务实现类
 * </p>
 *
 * @author czd
 * @since 2018-04-17
 */
@Service
public class TOrderFormServiceImpl extends ServiceImpl<TOrderFormMapper, TOrderForm> implements TOrderFormService {
	@Autowired
	private TOrderFormMapper orderFormMapper;
	public List<TOrderForm> queryRelation(){
		return orderFormMapper.queryRelation();
	}
	
	
	@Autowired
	private TOrderFormServiceImpl tOrderFormMapper;
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insert0() {
		try {
			TOrderForm order = new TOrderForm();
			order.setOrderNo( new Long(21) );
			tOrderFormMapper.insert( order );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
