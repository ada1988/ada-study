package org.ada.study.webmagic;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant;

/**  
 * Filename: GithubRepoPageProcessor.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月4日 <br>
 *
 *  
 */

public class GithubRepoPageProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
    	Html html = new Html( page.getHtml().toString().replaceAll( "&gt;", ">" ).replaceAll( "&lt;", "<" ));
    	System.out.println(">>>>>>>>>>>>>>>>>"+html.toString());
    	System.out.println(html.xpath( "//div[@class='tit']" ).match());
        if(html.xpath( "//record" ).match()){
        	List<String> list= html.xpath( "//record" ).regex( "[\u4e00-\u9fa5]{1,}" ).all();
        	for(String name:list){
        		System.out.println("name:"+name);
        	}
        }
        
         //模拟post请求
         Request req = new Request();
         req.setMethod(HttpConstant.Method.POST);
         req.setUrl("http://www.shandong.gov.cn/module/jslib/jquery/jpage/dataproxy.jsp?startrecord=0&endrecord=8&perpage=8");
         req.putExtra( "columnid", "7341" );
         req.putExtra( "unitid", "10267" );
         page.addTargetRequest(req);
        
    	page.putField("title", page.getHtml().xpath( "//div[@class='tit']/a" ).toString());
        //page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
       // page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.shandong.gov.cn/col/col7341/index.html").addPipeline(new JsonFilePipeline("D:\\data\\webmagic")).thread(1).run();
    }
}
