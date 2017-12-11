package org.ada.study.model;

import org.springframework.data.annotation.Id;

/**
 * Filename: Customer.java <br>
 *
 * Description: <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年12月4日 <br>
 *
 * 
 */

public class Address {
	@Id
	public String	id;

	public String	firstName;
	public String	lastName;

	public Address() {
	}

	public Address(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format( "Customer[id=%s, firstName='%s', lastName='%s']", id, firstName, lastName );
	}
}
