package oldhorse.programming.lesson79;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CompletionServiceDemo {
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
		ExecutorService es = Executors.newFixedThreadPool(10);
		CompletionService<String> cs = new ExecutorCompletionService<String>(es);
//        String url1 = "http://www.cnblogs.com/swiftma/p/5396551.html";
//        String url2 = "http://www.cnblogs.com/swiftma/p/5399315.html";
        List<UrlTitleParser> tasks = Arrays.asList(
    		new UrlTitleParser[]{
    				new UrlTitleParser("http://www.cnblogs.com/swiftma/p/5396551.html"),
    				new UrlTitleParser("http://www.cnblogs.com/swiftma/p/5399315.html"),
    				new UrlTitleParser("http://www.cnblogs.com/swiftma/p/5405417.html"),
    				new UrlTitleParser("http://www.cnblogs.com/swiftma/p/5409424.html"),
    		}
        );
        try{
        	for (UrlTitleParser parser: tasks){
        		cs.submit(parser);
        	}
//        	List<Future<String>> results = cs.invokeAll(
//        			tasks, 10, TimeUnit.SECONDS);
        	for (int i=0; i<tasks.size(); i++ ){
        		try {
					System.out.println(cs.take().get());
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }finally{
        	es.shutdown();
        }
        
	}
}
