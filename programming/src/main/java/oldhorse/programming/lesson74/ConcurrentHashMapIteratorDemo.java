package oldhorse.programming.lesson74;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapIteratorDemo {
	public static void main(String[] args){
		final ConcurrentHashMap<String, String> map =
				new ConcurrentHashMap<String, String>();
		map.put("a", "abstract");
		map.put("b", "basic");
		
		Thread t1 = new Thread(){
			@Override
			public void run(){
				for (Entry<String, String> entry: map.entrySet()){
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(entry.getKey()+","+entry.getValue());
				}//end of for
			}// end of run
		};
		
		t1.start();
		
		//确保线程t1已启动
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put("c", "call");	//will be printed
		//map.put("g", "call");	//will NOT be printed
	}
}
