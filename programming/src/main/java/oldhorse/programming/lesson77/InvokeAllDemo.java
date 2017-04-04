package oldhorse.programming.lesson77;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * ExecutorService的invokeAll示例
 * @author mlxia
 *
 */
public class InvokeAllDemo {
	static class UrlTitleParser implements Callable<String> {
		private String url;
		public UrlTitleParser(String url){
			this.url = url;
		}
		
		public String call() throws Exception {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("head title");
			if (elements.size()>0) {
				return elements.get(0).text();
			}
			return null;
		}
	}// end of class UrlTitleParser
	
	public static void main(String[] args){
		ExecutorService executor = Executors.newFixedThreadPool(10);
	}
}
