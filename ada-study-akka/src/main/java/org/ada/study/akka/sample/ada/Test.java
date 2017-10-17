package org.ada.study.akka.sample.ada;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;

/**  
 * Filename: Test.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月29日 <br>
 *
 *  
 */

public class Test {
	public static void main(String[] args) {
		ActorSystem sys = ActorSystem.create();
		ActorRef ref = sys.actorOf( Props.create( AdaHello.class ),"AdaHello" );
		sys.actorOf( Props.create( WatchStop.class, ref ) ,"WatchStop");
	}
	 public static class WatchStop extends AbstractLoggingActor{
		private final ActorRef ref;
		public WatchStop(ActorRef ref){
			this.ref = ref;
			getContext().watch( ref );
		}
		@Override
		public Receive createReceive() {
			return receiveBuilder().match( Terminated.class, m -> {
				log().info("{} has terminated, shutting down system", ref.path());
				getContext().system().terminate();
			} ).build();
		}
	}
}
