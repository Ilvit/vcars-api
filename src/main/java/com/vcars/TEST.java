package com.vcars;

import java.util.ArrayList;
import java.util.List;

public class TEST {

	public static void main(String[] args) {
		ArrayList<String>al=new ArrayList<>();
		List<String>al2=new ArrayList<>();
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("d");
		al.add("e");
		al.add("f");
		
		al2=al.subList(2, 5);
		al2.forEach(str->{
			System.out.println(str);
		});
	}

}
