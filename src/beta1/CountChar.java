package beta1;

public class CountChar {	
	
	
	final static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//������ĸ����
		
	static int[] letterTimes = new int[26];//�洢ÿ����ĸ���ֵĸ���
	
	
	public static void countLetters(String s){
		
		s = s.toUpperCase();//���ַ���ȫ��ת��Ϊ��д
		
		char[] sToLetters = s.toCharArray();//���ַ���ת��Ϊ�ַ�����
		
		for(int i = 0; i < letterTimes.length; i++){//ѭ��ͳ��26����ĸ�ĳ��ִ���
			
			for(int j = 0; j < sToLetters.length; j++){
				if(letters[i] == sToLetters[j])
					letterTimes[i]++;
			}
		}
		
		for(int i = 0; i < letterTimes.length; i++){
			System.out.println(letters[i] + ":" + letterTimes[i]);
		}
	}

}
