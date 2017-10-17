package org.ada.study.tools.task.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**  
 * Filename: NamedThreadFactory.java  <br>
 *
 * Description:   线程工厂类，统一管理命名 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年7月3日 <br>
 *
 *  
 */

public class NamedThreadFactory implements ThreadFactory{
	private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

	private final AtomicInteger mThreadNum = new AtomicInteger(1);

	private final String mPrefix;

	private final boolean mDaemo;

	private final ThreadGroup mGroup;

	public NamedThreadFactory()
	{
		this("miduo-pool-" + POOL_SEQ.getAndIncrement(),false);
	}

	public NamedThreadFactory(String prefix)
	{
		this(prefix,false);
	}

	public NamedThreadFactory(String prefix,boolean daemo)
	{
		mPrefix = prefix + "-thread-";
		mDaemo = daemo;
        SecurityManager s = System.getSecurityManager();
        mGroup = ( s == null ) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
	}

	public Thread newThread(Runnable runnable)
	{
		String name = mPrefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(mGroup,runnable,name,0);
        ret.setDaemon(mDaemo);
        return ret;
	}

	public ThreadGroup getThreadGroup()
	{
		return mGroup;
	}
}
