package com.leetcode;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lengthOfLongestSubstring("k"));

	}
	
	public static int lengthOfLongestSubstring(String s) {
		int max = 0;
		for (int i = 0; i < s.length()-max; i++) {
			int localMax = getMaxString(s.substring(i))[0];
			if(localMax > max)
				max = localMax;
			i += getMaxString(s.substring(i))[1];
		}
		return max;
	}

	private static int[] getMaxString(String str) {
		// TODO Auto-generated method stub
        int arr[] = new int[2];
		for(int i = 0; i< str.length(); i++) {
          arr[0]=i;
			for(int j = 0; j< i; j++) {
				if(str.charAt(j) == str.charAt(i)) {
					arr[1]=j;
					return arr;
				}
			}
		}
    arr[0]++;
	return arr;
	}
}

