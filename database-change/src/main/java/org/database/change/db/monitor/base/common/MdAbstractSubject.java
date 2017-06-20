package org.database.change.db.monitor.base.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Filename: MdAbstractSubject.java <br>
 *
 * Description: 抽象主题 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年4月19日 <br>
 *
 * 
 */

public abstract class MdAbstractSubject<T> implements MdSubject<T> {
	// 观察者集合
	private List<MdObservable<T>>	obServables	= new ArrayList<MdObservable<T>>();

	@Override
	public void subscribe(MdObservable<T> obServable) {
		if (!obServables.contains( obServable ))
			obServables.add( obServable );
	}

	@Override
	public void unSubscribe(MdObservable<T> obServable) {
		if (obServables.contains( obServable ))
			obServables.remove( obServable );
	}

	@Override
	public void notifies(T metadata) {
		// 副本，避免修改
		List<MdObservable<T>> dest = Collections.unmodifiableList( obServables );
		for (MdObservable<T> observable : dest) {
			observable.callBack( metadata );
		}
	}
}
