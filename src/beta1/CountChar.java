package beta1;

public class CountChar {	
	
	
	final static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//所有字母常量
		
	static int[] letterTimes = new int[26];//存储每个字母出现的个数
	
	
	public static void countLetters(String s){
		
		s = s.toUpperCase();//将字符串全部转化为大写
		
		char[] sToLetters = s.toCharArray();//将字符串转化为字符数组
		
		for(int i = 0; i < letterTimes.length; i++){//循环统计26个字母的出现次数
			
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
