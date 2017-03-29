package oldhorse.programming.lesson74;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk1.8运行时未发生死锁，jdk1.7下运行时会发生死锁
 * @author 2000105922
 *
 * http://mp.weixin.qq.com/s/4LuEFbq3luRSEC9Ihe21gA
 */
public class UnsafeConcurrentUpdate {
	public static void main(String[] args){
		
		//使用普通的Map(jdk1.7下有时现死锁)
		//final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		//使用Collections生成同步的容器
		//同步容器有几个问题：
		//  每个方法都需要同步，支持的并发度比较低
		//  对于迭代和复合操作，需要调用方加锁，使用比较麻烦，且容易忘记
		//final Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<Integer, Integer>());

		//使用并发容器
		//特点：
		//  并发安全
		//  直接支持一些原子复合操作
		//  支持高并发、读操作完全并行、写操作支持一定程度的并行
		//  与同步容器Collections.synchronizedMap相比，迭代不用加锁，不会抛出ConcurrentModificationException
		//  弱一致性
		final Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
		
		for (int i=0; i<100; i++) {
			Thread t = new Thread(){
				Random rnd= new Random();
				
				@Override
				public void run(){
					for(int i=0;i<100;i++){
						map.put(rnd.nextInt(), 1);
					}
				}
			};
			
			t.start();
			System.out.println(String.format("i=%d, map size is <%d>.", i, map.size()));
		}//end of for
	}
}
