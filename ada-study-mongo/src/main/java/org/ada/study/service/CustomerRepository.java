package org.ada.study.service;

import java.util.List;

import org.ada.study.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**  
 * Filename: CustomerRepository.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月4日 <br>
 *
 *  
 */

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
