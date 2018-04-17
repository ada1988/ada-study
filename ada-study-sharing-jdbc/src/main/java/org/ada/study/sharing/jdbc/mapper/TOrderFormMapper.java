package org.ada.study.sharing.jdbc.mapper;

import java.util.List;

import org.ada.study.sharing.jdbc.model.TOrderForm;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 订单记录表 Mapper 接口
 * </p>
 *
 * @author czd
 * @since 2018-04-17
 */
public interface TOrderFormMapper extends BaseMapper<TOrderForm> {
	public List<TOrderForm> queryRelation();
}