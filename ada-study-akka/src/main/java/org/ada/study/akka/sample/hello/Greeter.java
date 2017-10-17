package org.ada.study.akka.sample.hello;

import akka.actor.AbstractActor;

/**  
 * Filename: Greeter.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月29日 <br>
 *
 *  
 */

public class Greeter extends AbstractActor {

	  public static enum Msg {
	    GREET, DONE;
	  }

	  @Override
	  public Receive createReceive() {
	    return receiveBuilder()
	      .matchEquals(Msg.GREET, m -> {
	        System.out.println("Hello World!");
	        sender().tell(Msg.DONE, self());
	      })
	      .build();
	  }
	}
