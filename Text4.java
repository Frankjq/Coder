package Hang_OJ;
import java.util.*;
//
//�趨����n���۸�
//����n���۸�
//�������������˵ļ۸�
//�����ڵĻ�����-1
//
public class Text4 {
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		
		List<Integer> list = new ArrayList<Integer>();
	
		
		while(num>0){
	
		list.add(Integer.parseInt(input.next()));	
		num--;
		}
		Collections.sort(list);
	
		
		for(int i=0;i<list.size();i++)
		{
			for (int j=i+1;j<list.size();j++){
				if(list.get(i)==list.get(j)){
					list.remove(j);
					j--;
				}
			}
		}
		
		if(list.size()<3){
			System.out.println("-1");		
		}else{
			System.out.println(list.get(2));
		}
		
//		for(int i=0;i<list.size();i++){						//����
//		System.out.print (list.get(i)+"   ");	
//		}
		
	}
}
