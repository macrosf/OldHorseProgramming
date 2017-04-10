package oldhorse.programming.addon.arraycopy;

import java.util.Arrays;

/**
 * 克隆方式复制数组（浅拷贝）
 * 原文地址：http://blog.csdn.net/abyjun/article/details/46444921
 * @author 2000105922
 *
 */
public class ArrayClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        //对象克隆（深拷贝）
    	Aby aby1 = new Aby(1);
        Aby aby2 = (Aby) aby1.clone();
        aby1.i = 2;
        System.out.println(aby1.i); //2
        System.out.println(aby2.i); //1

        //数组克隆（浅拷贝）
        Aby[] arr = {aby1,aby2};
        Aby[] arr2 = arr.clone();
        arr2[0].i = 3;
        System.out.println(arr[0].i);   //3
        System.out.println(arr2[0].i);  //3
        
        //System.arraycopy
        Aby[] arr3 = new Aby[arr2.length];
        System.arraycopy(arr2, 0, arr3, 0, arr.length);
        arr2[0].i = 4;
        System.out.println(arr3[0].i);  //4
        
        //Arrays.copyof
        Aby[] arr4 = Arrays.copyOf(arr2, arr2.length);
        arr2[0].i = 5;
        System.out.println(arr4[0].i);	//5
        
        //for循环(深浅自便)
        Aby[] arr5 = new Aby[arr2.length];
        for (int i=0; i<arr2.length; i++){
        	//arr5[i].i =arr2[i].i;//NullPointerException
        	arr5[i] = arr2[i];	//浅拷贝
        }
        arr2[0].i = 6;
        System.out.println(arr5[0].i);	//6
    }
}
