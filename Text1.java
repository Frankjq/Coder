package Hang_OJ;
import java.util.*;
public class Text1 {
//����ϵͳ
//���޵Ľ�ǮP�����޵Ĳ�Ʒ����C
//��Ӧ�Ĳ˼۸�price��Ӧ��Ӧ�������value
//��Ҫ�õ���õ�Value

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);		
		int p = input.nextInt();
		int c = input.nextInt();
		int f[]= new int [p+1];
		
		int price []= new int [c];
		int value []= new int [c];
		
		for (int i = 0 ;i<c;i++)
		{
			price[i] = input.nextInt();
			value[i] = input.nextInt();
		}
		
		for(int i = 0; i<c;i++){
		
			for (int j = p;j>=0;j--){
			if (j >= price[i])
				f[j] = Math.max(f[j], f[j-price[i]]+value[i]);
				
			}
		}
		
		System.out.print(f[p]);
		
	}

}
