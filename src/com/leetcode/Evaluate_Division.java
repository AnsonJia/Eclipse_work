package com.leetcode;

import java.util.*;

public class Evaluate_Division {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<EquationEntry> equations = new ArrayList<>();
		List<EquationEntry> questions = new ArrayList<>();

		String eqn = "[[a,b,2.0];[b,c,3.0];[c,d,6.0]]";
		eqn = eqn.replaceAll("\\[", "");
		eqn = eqn.replaceAll("]", "");

		String ques = "[[\"a\",\"c\"];[\"b\",\"a\"];[\"a\",\"e\"];[\"a\",\"a\"];[\"d\",\"a\"]]";
		ques = ques.replaceAll("\\[", "");
		ques = ques.replaceAll("]", "");
		ques = ques.replaceAll("\"", "");

		String[] eqnArr = eqn.split(";");
		String[] quesArr = ques.split(";");

		for (String a : eqnArr) {
			String[] split = a.split(",");
			equations.add(new EquationEntry(split[0], split[1], Double.parseDouble(split[2])));
		}

		for (String a : quesArr) {
			String[] split = a.split(",");
			questions.add(new EquationEntry(split[0], split[1], 0.0));
		}
		// System.out.println(equations);
		// System.out.println(questions);

		Solution run = new Solution();
		double[] ans = run.calcEquation(equations, questions);
		System.out.println(Arrays.toString(ans));
	}
}

class Solution {
	public double[] calcEquation(List<EquationEntry> equations, List<EquationEntry> queries) {
		EquationEntry[] ListComp = fullList(equations);
		double[] retArray = new double[queries.size()];

		int count = 0;
		for (EquationEntry list : queries) {
			for (EquationEntry item : ListComp) {
				if (list.equals(item)) {
					retArray[count] = item.v;
					break;
				} else {
					retArray[count] = -1.0;
				}
			}
			count++;
		}
		return retArray;
	}

	public EquationEntry[] fullList(List<EquationEntry> equations) {
		Set<EquationEntry> set = new LinkedHashSet<EquationEntry>();

		for (EquationEntry list : equations) {
			set.add(list);
			set.add(new EquationEntry(list.e2, list.e1, 1 / list.v));
			set.add(new EquationEntry(list.e1, list.e1, 1.0));
			set.add(new EquationEntry(list.e2, list.e2, 1.0));
		}

		///////////////////////////////////////////////////////////////////////////////////////

		int lengthCheck = 0;

		while (set.size() != lengthCheck) {
			lengthCheck = set.size();
			EquationEntry[] arr = set.toArray(new EquationEntry[set.size()]);
			for (int i = 0; i < lengthCheck - 1; i++) {
				for (int j = i + 1; j < lengthCheck; j++) {
					if (arr[i].e1.equals(arr[j].e2)) {
						set.add(new EquationEntry(arr[j].e1, arr[i].e2, arr[i].v * arr[j].v));
					}
					if (arr[i].e2.equals(arr[j].e1)) {
						set.add(new EquationEntry(arr[i].e1, arr[j].e2, arr[i].v * arr[j].v));
					}
				}
			}
		}
		EquationEntry[] arr = set.toArray(new EquationEntry[set.size()]);
		return arr;
	}
}

class EquationEntry {
	public EquationEntry() {

	}

	public EquationEntry(String e1, String e2, double v) {
		this.e1 = e1;
		this.e2 = e2;
		this.v = v;
	}

	@Override
	public String toString() {
		return "[" + e1 + "," + e2 + "," + v + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(e1, e2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquationEntry other = (EquationEntry) obj;
		return Objects.equals(e1, other.e1) && Objects.equals(e2, other.e2);
	}

	String e1;
	String e2;
	double v;
}
