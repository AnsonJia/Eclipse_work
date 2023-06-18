package com.leetcode;

import java.util.*;

public class Evaluate_Division {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    	List<Double> valList = new ArrayList<Double>();
			Object[] ListComp = fullList(equations, values, valList);
			double[] retArray = new double[queries.size()];
			int count = 0;

      for (List<String> list : queries) {
				for (int i = 0; i<ListComp.length; i++){
					if(ListComp[i].equals(list)){
						retArray[count] = valList.get(i);
						break;
					}else{
						retArray[count] = -1.0;
					}
				}
				count++;		
    	}
		return retArray; 
    }
    
    public Object[] fullList(List<List<String>> equations, double[] values, List<Double> valList){
    	Set<List<String>> set = new LinkedHashSet<List<String>>();
    	List<String> temp = new ArrayList<String>();
			int lengthCheck = 0;
    	for (List<String> list : equations) {
				lengthCheck = set.size();
        set.add(list);
        if (set.size()!=lengthCheck){
          valList.add(values[equations.indexOf(list)]);
          lengthCheck++;
        }
            
        temp.add(list.get(1));
        temp.add(list.get(0));
        set.add(new ArrayList<String>(temp));
        if (set.size()!=lengthCheck){
          valList.add(1/values[equations.indexOf(list)]);
          lengthCheck++;
        }
        temp.clear();
            
        temp.add(list.get(0));
        temp.add(list.get(0));
        set.add(new ArrayList<String>(temp));
        if (set.size()!=lengthCheck){
          valList.add(1.0);
          lengthCheck++;
        }
        temp.clear();
        
				temp.add(list.get(1));
        temp.add(list.get(1));
        set.add(new ArrayList<String>(temp));
        if (set.size()!=lengthCheck){
          valList.add(1.0);
        }
        temp.clear();
    	}

    	//////////////////////////////////////////////////////////////////////////////////////////
    	double value;
			while (set.size()!=lengthCheck) {
    		lengthCheck = set.size();
				int secondCheck = lengthCheck;
				
    		Object[] arr = set.toArray();
    		for (int i = 0; i<lengthCheck-1;i++) {
    			for (int j = i+1; j<lengthCheck;j++) {
            if (((List)arr[i]).get(0).equals(((List)arr[j]).get(1))) {
							temp.add((String)((List)arr[j]).get(0));
        			temp.add((String)((List)arr[i]).get(1));
        			set.add(new ArrayList<String>(temp));
							if (set.size()!=secondCheck){
								value = valList.get(i)*valList.get(j);
								valList.add(value);
								secondCheck++;
							}
							temp.clear();
    				}

    				if (((List)arr[i]).get(1).equals(((List)arr[j]).get(0))) {
							temp.add((String)((List)arr[i]).get(0));
        			temp.add((String)((List)arr[j]).get(1));
        			set.add(new ArrayList<String>(temp));
							if (set.size()!=secondCheck){
								value = valList.get(i)*valList.get(j);
								valList.add(value);
								secondCheck++;
							}
							temp.clear();
    				}
    			}
    		}
    	}
			Object[] arr = set.toArray();
    	return arr;
    }
}
