package com.leetcode;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lengthOfLongestSubstring("k"));

	}
	
	public static int lengthOfLongestSubstring(String s) {
		int max = 0;
		for (int i = 0; i < s.length()-max; i++) {
			IJVals vals = getMaxString(s.substring(i));
			if(vals.length > max)
				max = vals.length;
			i += vals.position;
		}
		return max;
	}

	private static IJVals getMaxString(String str) {
		// TODO Auto-generated method stub
		IJVals value = new IJVals();
		for(int i = 0; i< str.length(); i++) {
			for(int j = 0; j< i; j++) {
				if(str.charAt(j) == str.charAt(i)) {
					value.length=i;
					value.position=j;
					return value;
				}
			}
		}
		value.length = str.length();
		return value;
	}
}

class IJVals{
	int length;
	int position;
}

