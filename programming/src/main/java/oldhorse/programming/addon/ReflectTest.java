package oldhorse.programming.addon;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Java的反射机制，你需要理解这些
 * 原文地址：http://mp.weixin.qq.com/s/5TH-g3BkAhN70jTD5oyMCw
 * @author 2000105922
 *
 */
public class ReflectTest {
	
	@SuppressWarnings({ "unused"})
		public static void main(String[] args){
		String s = new String();
		
		//取得类的三种方法
		Class<String> c1 = String.class;
		Class<? extends String> c2 = s.getClass();
		try {
			Class<?> c3 = Class.forName("oldhorse.programming.addon.ReflectTest");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//取得类名
		Class<String> clazz = String.class;
		System.out.println("clazz.getName:\t"+clazz.getName());
		System.out.println("clazz.getSimpleName:\t"+clazz.getSimpleName());
		
		//取得公有方法（包括父类的）
		Method[] methods = clazz.getMethods();
		int i=1;
		System.out.println("============================");
		System.out.println("get class method (public)...");
		System.out.println("============================");
		for (Method method: methods) {
			Class<?>[] paramTypes = method.getParameterTypes();
			StringBuilder sb = new StringBuilder();
			for (Class<?> type: paramTypes) {
				sb.append(type.getSimpleName()).append(", ");
			}
			if (sb.toString().endsWith(", ")) {
				sb.delete(sb.length()-2, sb.length());
			}
			System.out.println(String.format("[%2d]%s\t%s(%s)",
					i++, 
					method.getReturnType().getSimpleName(),
					method.getName(),
					sb.toString()
					));
			//method.getModifiers();
			//Modifier
		}
		
		//取得所有方法（不包括父类的）
		methods = clazz.getDeclaredMethods();
		i=1;
		System.out.println("=====================================");
		System.out.println("get class method defined in itself...");
		System.out.println("=====================================");
		for (Method method: methods) {
			Class<?>[] paramTypes = method.getParameterTypes();
			//取得方法限定符
			StringBuilder modifier = convertModifiers(method.getModifiers());
			
			StringBuilder params = new StringBuilder();
			//取得参数列表
			for (Class<?> type: paramTypes) {
				params.append(type.getSimpleName()).append(", ");
			}
			if (params.toString().endsWith(", ")) {
				params.delete(params.length()-2, params.length());
			}
			//输出完整的方法说明
			System.out.println(String.format("[%2d]%s %s\t%s(%s)",
					i++, 
					modifier,
					method.getReturnType().getSimpleName(),
					method.getName(),
					params.toString()
					));
		}
		
		//取得共有属性（包括父类）
		Field[] fields = clazz.getFields();
		i=1;
		System.out.println("===========================");
		System.out.println("get class fields(public)...");
		System.out.println("===========================");
		for (Field field: fields) {
			System.out.println(
					//String.format("[#%2d] field type:%s, field name:%s",
					String.format("[#%2d] %s\t%s",
							i++, field.getType().getSimpleName(), field.getName()));
		}
		
		//取得所有属性（不包括父类）
		fields = clazz.getDeclaredFields();
		i=1;
		System.out.println("============================================");
		System.out.println("get class fields(declared in itself only)...");
		System.out.println("============================================");
		for (Field field: fields) {
			System.out.println(
					//String.format("[#%2d] field type:%s, field name:%s",
					String.format("[#%2d] %s\t%s",
							i++, field.getType().getSimpleName(), field.getName()));
		}
		
		//调用方法
		Class<Modifier> CModifier = java.lang.reflect.Modifier.class;
		methods = CModifier.getMethods();
		for (Method method: methods){
			//调用所有“is”开头的方法，如isPublic，isPrivate...
			if (method.getName().startsWith("is")){
				try {
					boolean isResult = (Boolean)method.invoke(new Modifier(), 10);
					System.out.println(
							String.format("method name:%s, result:%b", 
									method.getName(), isResult));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//调用方法2
		Method method = null;
		Class<ReflectTest> clazz2 = oldhorse.programming.addon.ReflectTest.class;
		try {
			//写法1
			//Method method = clazz2.getMethod("add", new Class<?>[]{int.class ,int.class});
			//写法2
			method = clazz2.getMethod("add", int.class, int.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			method.invoke(new ReflectTest(), 10, 20);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end of main
	
    public void add(int a,int b){   
        System.out.print(a+b);   
    }   

    public void toUpper(String a){   
        System.out.print(a.toUpperCase());   
    }   
    
    public static StringBuilder convertModifiers(int modifier_flags){
    	StringBuilder sb = new StringBuilder();
    	
    	Class<Modifier> clazz = Modifier.class;
    	Modifier modifier = new Modifier();
    	Method[] methods = clazz.getDeclaredMethods();
    	for (Method method: methods){
    		if(method.getName().startsWith("is")){
    			
    			boolean is_modified = false;
    			try {
					method.setAccessible(true);
					is_modified = (Boolean) method.invoke(modifier, modifier_flags);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			if (is_modified) {
    				sb.append(method.getName().replaceFirst("is", "")+" ");
    			}
    		}//end of is startsWith("is")
    	}//end of for
    	
//    	if (sb.toString().endsWith(" ")){
//    		sb.delete(sb.length()-1, sb.length());
//    	}
    	return sb;
    }
}
