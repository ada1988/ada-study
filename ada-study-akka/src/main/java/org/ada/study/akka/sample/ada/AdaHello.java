package org.ada.study.akka.sample.ada;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
/**  
 * Filename: AdaHello.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月29日 <br>
 *
 *  
 */

public class AdaHello extends AbstractActor{

	@Override
	public Receive createReceive() {
		return receiveBuilder().matchEquals( AdaGreeter.Msg.Done, m -> {
			System.out.println("好的!");
			getContext().stop( self() );
		} ).build();
	}

	@Override
	public void preStart() throws Exception {
		final ActorRef ref = getContext().actorOf( Props.create( AdaGreeter.class ), "greetor" );
		ref.tell(  AdaGreeter.Msg.Greet, self() );
	}

	
	
}
