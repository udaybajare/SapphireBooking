package com.sapphire.utils;

import javax.annotation.ManagedBean;

import org.springframework.core.Conventions;

@ManagedBean
public class NumberWordConverter {
	public static final String[] units = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	public static final String[] tens = { 
			"", // 0
			" ", // 1
			"twenty", // 2
			"thirty", // 3
			"forty", // 4
			"fifty", // 5
			"sixty", // 6
			"seventy", // 7
			"eighty", // 8
			"ninety" // 9
	};

	public String convert(final Double n) {
		if (n < 0) {
			return "Minus " + convert(-n);
		}

		if (n < 20) {			
			String number = String.valueOf(n);			
			int intN = Integer.parseInt(number.substring(0, number.indexOf(".")));
			return units[intN];
		}

		if (n < 100) {
			return tens[(int) (n / 10)] + ((n % 10 != 0) ? " " : "") + units[(int) (n % 10)];
		}

		if (n < 1000) {
			return units[(int) (n / 100)] + " hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 100000) {
			return convert(n / 1000) + " thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}

		return convert(n / 10000000) + " crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}

	public static void main(String[] args) {

		double d = 1591;
		System.out.println(new NumberWordConverter().convert(d));

	}

}
