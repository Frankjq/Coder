package Hang_OJ;
import java.util.*;

public class Text2 {

//25*25=625
//625*625
//搜索n以内的此类数据
	
	
	
	public  static void main(String []args){
		
		Scanner input = new Scanner (System.in);
		int n = input.nextInt();	
		int num =0;
		
		for (int i = 0; i<n ;i++){
			if(Isnumber(i)){

				System.out.print(i+"    ");	
				num++;
				
			}
		}
		System.out.println(num);	
		
	}
	
	
	public static boolean Isnumber(int num){
		
		int s =num*num;
		String str = String.valueOf(num);
		int length = str.length();
		int wei = (int) (s % (Math.pow(10, length)));//平方函数
		
		if ( num== wei){
			return true;
		}
		else{
		return false;
		}
	}
		
	public static boolean Isnum(int num){
		int p =num*num;
		String str_p = String.valueOf(p);
		String str_num = String.valueOf(num);
		if (str_p.endsWith(str_num)){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
}
