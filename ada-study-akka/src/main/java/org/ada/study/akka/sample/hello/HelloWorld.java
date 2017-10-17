package org.ada.study.akka.sample.hello;

import org.ada.study.akka.sample.hello.Greeter.Msg;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Filename: HelloWorld.java <br>
 *
 * Description: hello world <br>
 * 
 * 模拟场景：碰面
 * 
 * 角色：helloWorld，Greeter
 * 
 * 事件：greet、done 会面、结束
 * 
 * 过程：helloWorld 创建前，约见Greeter，Greeter接受到消息（相当于见面了），通知helloWorld结束会面
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年9月28日 <br>
 *
 * 
 */

public class HelloWorld extends AbstractActor {

	@Override
	public Receive createReceive() {
		return receiveBuilder().matchEquals( Msg.DONE, m -> {
			// when the greeter is done, stop this actor and with it the
			// application
				getContext().stop( self() );
			} ).build();
	}

	@Override
	public void preStart() {
		// create the greeter actor
		final ActorRef greeter = getContext().actorOf( Props.create( Greeter.class ), "greeter" );
		// tell it to perform the greeting
		greeter.tell( Msg.GREET, self() );
	}
}