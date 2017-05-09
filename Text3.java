package Hang_OJ;
import java.util.*;

public class Text3 {

	//ÊäÈëÊý¾ÝÖ®ºó½øÐÐÅÅÐò
	
	
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		List<Integer> list = new ArrayList<Integer>();
		int count = input.nextInt();
		while(count>0){
			list.add(input.nextInt());
			count--;
		}
		list.sort(null);
		
		for (int i = 0; i< list.size();i++)
			{
			System.out.print(list.get(i)+"  ");
			}
		
	}

}
