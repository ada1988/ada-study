package org.ada.study.util;

import java.util.NoSuchElementException;
import java.util.Optional;

/**  
 * Filename: OptionalDemo.java  <br>
 *
 * Description:  Optional是Java8提供的为了解决null安全问题的一个API <br>
 * 使用Optional，我们就可以把下面这样的代码进行改写。
	public static String getName(User u) {
	    if (u == null)
	        return "Unknown";
	    return u.name;
	}
不过，千万不要改写成这副样子。
	public static String getName(User u) {
	    Optional<User> user = Optional.ofNullable(u);
	    if (!user.isPresent())
	        return "Unknown";
	    return user.get().name;
	}
这样改写非但不简洁，而且其操作还是和第一段代码一样。无非就是用isPresent方法来替代u==null。这样的改写并不是Optional正确的用法，我们再来改写一次。

	public static String getName(User u) {
	    return Optional.ofNullable(u)
	                    .map(user->user.name)
	                    .orElse("Unknown");
	}
看一段代码：
	public static String getChampionName(Competition comp) throws IllegalArgumentException {
	    if (comp != null) {
	        CompResult result = comp.getResult();
	        if (result != null) {
	            User champion = result.getChampion();
	            if (champion != null) {
	                return champion.getName();
	            }
	        }
	    }
	    throw new IllegalArgumentException("The value of param comp isn't available.");
	}
让我们看看经过Optional加持过后，这些代码会变成什么样子。

	public static String getChampionName(Competition comp) throws IllegalArgumentException {
	    return Optional.ofNullable(comp)
	            .map(c->c.getResult())
	            .map(r->r.getChampion())
	            .map(u->u.getName())
	            .orElseThrow(()->new IllegalArgumentException("The value of param comp isn't available."));
	}
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年9月30日 <br>
 *
 *  
 */

public class OptionalDemo {
	public static void main(String[] args) {
		//创建Optional实例，也可以通过方法返回值得到。
	    Optional<String> name = Optional.of("Sanaulla");
	    //创建没有值的Optional实例，例如值为'null'
	    Optional empty = Optional.ofNullable(null);
	    //isPresent方法用来检查Optional实例是否有值。
	    if (name.isPresent()) {
	      //调用get()返回Optional值。
	      System.out.println(name.get());
	    }
	    try {
	        //在Optional实例上调用get()抛出NoSuchElementException。
	        System.out.println(empty.get());
	      } catch (NoSuchElementException ex) {
	        System.out.println(ex.getMessage());
	      }
	    //ifPresent方法接受lambda表达式参数。
	    //如果Optional值不为空，lambda表达式会处理并在其上执行操作。
	    name.ifPresent((value) -> {
	      System.out.println("The length of the value is: " + value.length());
	    });
	    
	    //如果有值orElse方法会返回Optional实例，否则返回传入的错误信息。
	    System.out.println(empty.orElse("There is no value present!"));
	    System.out.println(name.orElse("There is some value!"));
	    //orElseGet与orElse类似，区别在于传入的默认值。
	    //orElseGet接受lambda表达式生成默认值。
	    System.out.println(empty.orElseGet(() -> "Default Value"));
	    System.out.println(name.orElseGet(() -> "Default Value"));
	    //map方法通过传入的lambda表达式修改Optonal实例默认值。 
	    //lambda表达式返回值会包装为Optional实例。
	    Optional<String> upperName = name.map((value) -> value.toUpperCase());
	    System.out.println(upperName.orElse("No value found"));
	  //flatMap与map（Funtion）非常相似，区别在于lambda表达式的返回值。
	    //map方法的lambda表达式返回值可以是任何类型，但是返回值会包装成Optional实例。
	    //但是flatMap方法的lambda返回值总是Optional类型。
	    upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
	    System.out.println(upperName.orElse("No value found"));
	    //filter方法检查Optiona值是否满足给定条件。
	    //如果满足返回Optional实例值，否则返回空Optional。
	    Optional<String> longName = name.filter((value) -> value.length() > 6);
	    System.out.println(longName.orElse("The name is less than 6 characters"));
	    //另一个示例，Optional值不满足给定条件。
	    Optional<String> anotherName = Optional.of("Sana");
	    Optional<String> shortName = anotherName.filter((value) -> value.length() > 6);
	    System.out.println(shortName.orElse("The name is less than 6 characters"));
	}
}
