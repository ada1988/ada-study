package org.ada.study.cache.business.service.impl;

import java.util.Arrays;
import java.util.List;

import org.ada.study.cache.business.entity.FundBean;
import org.ada.study.cache.business.service.IFundDbService;
import org.springframework.stereotype.Component;

/**
 * Filename: FundServiceImpl.java <br>
 *
 * Description: 数据库数据，业务实现 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年6月18日 <br>
 *
 * 
 */
@Component("fundDbService")
public class FundDbServiceImpl implements IFundDbService {

	private List<FundBean>	dbFunds	= Arrays.asList( new FundBean( "001", "基金 001 名称" ),new FundBean( "002", "基金 002 名称" ), new FundBean( "003", "基金 003 名称" ) );

	public FundBean queryDetailById(String id) {
		if (dbFunds.contains( id )) {
			for (FundBean fund : dbFunds) {
				if (fund.getId().equals( id ))
					return fund;
			}
		}
		return null;
	}

	@Override
	public List<FundBean> queryAllFund() {
		return dbFunds;
	}

}
