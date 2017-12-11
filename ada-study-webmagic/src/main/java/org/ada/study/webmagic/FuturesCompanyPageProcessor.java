package org.ada.study.webmagic;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant;

/**  
 * Filename: GithubRepoPageProcessor.java  <br>
 *
 * Description: 期货公司爬取  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月4日 <br>
 *
 *  
 */

public class FuturesCompanyPageProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(1).setSleepTime(100);

	int currentPage = 1;
	int pageSize = 20;
	
    @Override
    public void process(Page page) {
        if(page.getHtml().xpath( "//table[@id='cominfomanages']//table/tbody/tr/td[2]/a" ).match()){
        	List<String> list= page.getHtml().xpath( "//table[@id='cominfomanages']//table/tbody/tr/td[2]/a/text()" ).all();
        	for(String name:list){
        		System.out.println("机构名称:"+name);
        	}
        	page.putField( "list", list );
        }
        
        if (page.getResultItems().get("list")==null){
            page.setSkip(true);
        }
        
        currentPage = currentPage +1;
        //下一页
        StringBuffer pageUrl = new StringBuffer("http://www.cfachina.org/cfainfo/assetmanageinfoServlet");
        pageUrl.append( "?" ).append( "currentPage=" ).append( currentPage ).append( "&" ).append( "pageSize=" ).append( pageSize );
        page.addTargetRequest( pageUrl.toString() );
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new FuturesCompanyPageProcessor()).addUrl("http://www.cfachina.org/cfainfo/assetmanageinfoServlet").addPipeline(new JsonFilePipeline("D:\\data\\webmagic")).thread(1).run();
    }
}
