package oldhorse.programming.lesson75;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapTest {
	public static void main(String[] args){
		Map<String, String> map = new ConcurrentSkipListMap<String, String>(
				Collections.reverseOrder());
		map.put("a", "abstract");
		map.put("c", "call");
		map.put("b", "basic");
		System.out.println(map.toString());
	}
}
