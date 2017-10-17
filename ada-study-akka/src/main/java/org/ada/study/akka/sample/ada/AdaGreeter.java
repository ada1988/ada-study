package org.ada.study.akka.sample.ada;

import akka.actor.AbstractActor;

/**  
 * Filename: AdaGreeter.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月29日 <br>
 *
 *  
 */

public class AdaGreeter extends AbstractActor{
	public static enum Msg{
		Greet,Done
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().matchEquals( Msg.Greet, m ->{
			System.out.println("已约，歇了吧!");
			sender().tell( Msg.Done, self() );
		} ).build();
	}
}
