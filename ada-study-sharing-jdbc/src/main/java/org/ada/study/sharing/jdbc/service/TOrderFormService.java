package org.ada.study.sharing.jdbc.service;

import java.util.List;

import org.ada.study.sharing.jdbc.model.TOrderForm;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单记录表 服务类
 * </p>
 *
 * @author czd
 * @since 2018-04-17
 */
public interface TOrderFormService extends IService<TOrderForm> {
	public List<TOrderForm> queryRelation();
	public void insert0();
}
