package org.ada.study.elasctic.search.service;

import org.ada.study.elasctic.search.model.CustomerQuestions;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**  
 * Filename: ICustomerQuestionsService.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年11月23日 <br>
 *
 *  
 */

public interface ICustomerQuestionsService extends ElasticsearchRepository<CustomerQuestions,String>{

}
