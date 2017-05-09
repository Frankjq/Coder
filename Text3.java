package Hang_OJ;
import java.util.*;

public class Text3 {

	//输入数据之后进行排序
	
	
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		List<Integer> list = new ArrayList<Integer>();
		int count = input.nextInt();
		while(count>0){
			list.add(input.nextInt());
			count--;
		}
		list.sort(null);
		
		System.out.print(list);
		
		
	}

}
