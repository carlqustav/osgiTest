package org.o7planning.tutorial.helloosgi.calculatorservice.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.o7planning.tutorial.helloosgi.calculatorservice.CalculateService;

public class CalculateServiceImpl implements CalculateService {

	//constants
	Map<String, Integer> dictTR = new HashMap<String, Integer>() {{
		put("sıfır",0     );
        put("bir",1     );
        put("iki",2     );
        put("üç",3      );
        put("dört",4    );
        put("beş",5     );
        put("altı",6    );
        put("yedi",7    );
        put("sekiz",8   );
        put("dokuz",9   );
        put( "on",10    );
        put( "on bir",11);
        put( "on iki",12);
        put( "yirmi",20 );
        put( "otuz",30  );
        put( "kırk",40  );
        put( "elli",50  );
        put( "altmış",60);
        put( "yetmiş",70);
        put( "seksen",80);
        put( "doksan",90);
        put( "yüz",100  );
        put( "bin",1000 );
        put( "milyon",1000000);
    }};
    Map<String, Integer> dictEN = new HashMap<String, Integer>() {{
		put("zero",0     );
        put("one",1     );
        put("two",2     );
        put("three",3      );
        put("four",4    );
        put("five",5     );
        put("six",6    );
        put("seven",7    );
        put("eight",8   );
        put("nine",9   );
        put( "ten",10    );
        put( "eleven",11);
        put( "twelve",12);
        put( "thirteen",13);
        put( "fourteen",14);
        put( "fifteen",15);
        put( "sixteen",16);
        put( "seventeen",17);
        put( "eighteen",18);
        put( "nineteen",19);
        put( "twelve",12);
        put( "twenty",20 );
        put( "thirty",30  );
        put( "fourty",40  );
        put( "fifty",50  );
        put( "sixty",60);
        put( "seventy",70);
        put( "eighty",80);
        put( "ninety",90);
        put( "hundred",100  );
        put( "thousand",1000 );
        put( "million",1000000);
    }};
    Map<Integer,String> dictTRback = new HashMap<Integer,String>() {{
        put(0      ,"sıfır"    );
        put(1      ,"bir"      );
        put(2      ,"iki"      );
        put(3      ,"üç"       );
        put(4      ,"dört"     );
        put(5      ,"beş"      );
        put(6      ,"altı"     );
        put(7      ,"yedi"     );
        put(8      ,"sekiz"    );
        put(9      ,"dokuz"    );
        put(10     , "on"      );
        put(11     , "on bir"  );
        put(12     , "on iki"  );
        put(20     , "yirmi"   );
        put(30     , "otuz"    );
        put(40     , "kırk"    );
        put(50     , "elli"    );
        put(60     , "altmış"  );
        put(70     , "yetmiş"  );
        put(80     , "seksen"  );
        put(90     , "doksan"  );
        put(100    , "yüz"     );
        put(1000   , "bin"     );
        put(10000  ,"on bin");
        put(100000  ,"yüz bin");
        put(1000000, "milyon"  );
    }};                            
    Map<Integer,String> dictENback = new HashMap<Integer,String>() {{
		put(0         ,"zero" );
        put(1          ,"one");
        put(2          ,"two");
        put(3        ,"three"   );
        put(4         ,"four");
        put(5         ,"five" );
        put(6          ,"six");
        put(7        ,"seven" );
        put(8        ,"eight");
        put(9         ,"nine");
        put(10         ,"ten");
        put(11      ,"eleven");
        put(12      ,"twelve");
        put(13      ,"thirteen");
        put(14      ,"fourteen");
        put(15      ,"fifteen");
        put(16      ,"sixteen");
        put(17      ,"seventeen");
        put(18      ,"eighteen");
        put(19      ,"nineteen");
        put(20      ,"twenty");
        put(30      ,"thirty" );
        put(40      ,"fourty" );
        put(50       ,"fifty");
        put(60       ,"sixty");
        put(70     ,"seventy");
        put(80      ,"eighty");
        put(90      ,"ninety");
        put(100    ,"hundred");
        put(1000  ,"thousand");
        put(10000  ,"ten thousand");
        put(100000  ,"hundred thousand");
        put(1000000,"millon");
    }};
	
	private String num1  = "";
	private String num2  = "";
	private String op  = "";
	private String lang  = ""; 
	
	
	@Override
	public String[] recieveInputs(String numString1, String numString2, String operation, String lang) {
		
		num1 = numString1.trim();
		num2 = numString2.trim();
		op = operation.trim();
		this.lang = lang.trim();
		
		int num1Result = translate(num1);
		int num2Result = translate(num2);
		
		int resultInt = calculate(num1Result,num2Result,operation,lang);
		String result = translateBack(resultInt);
		
		return new String[]{result,Integer.toString(num1Result),Integer.toString(num2Result),Integer.toString(resultInt)};
	}
	
	private int calculate(int num1Result, int num2Result, String operation, String lang) {
		if(num1Result == -1 || num2Result == -1) {
			return -1;
		}
		else {
			if(lang.equals("TR")) {
				switch(operation) {
				case "Topla":
					return add(num1Result,num2Result);
				case "Çıkar":
					return sub(num1Result,num2Result);
				case "Çarp":
					return mul(num1Result,num2Result);
				case "Böl":
					return div(num1Result,num2Result);
				default:
					return -1;
				}
			}
			else if(lang.equals("EN")) {
				switch(operation) {
				case "Add":
					return add(num1Result,num2Result);
				case "Subtract":
					return sub(num1Result,num2Result);
				case "Multiply":
					return mul(num1Result,num2Result);
				case "Divide":
					return div(num1Result,num2Result);
				default:
					return -1;
				}
			}
			else
				return -1;
		}
		
	}

	private int div(int num1Result, int num2Result) {
		if(num2Result != 0)
			return num1Result / num2Result;
		else
			return -1;
	}

	private int mul(int num1Result, int num2Result) {
		return num1Result*num2Result;
	}

	private int sub(int num1Result, int num2Result) {
		return num1Result-num2Result;
	}

	private int add(int num1Result, int num2Result) {
		return num1Result+num2Result;
	}

	private int translate(String num) {
		List<String> list = new ArrayList<String>(Arrays.asList(num.split(" ")));
		boolean compFlag = true;
		int index = 0;
		int result = 0;
		int length = list.size();
		//to-do check language
		Map<String, Integer> dict = new HashMap<String, Integer>();
		switch(lang) {
		case "TR":
			dict = dictTR;
			break;
		case "EN":
			dict = dictEN;
			break;
		}
		
		while(compFlag && index < length) {
			int temp1 = dict.getOrDefault(list.get(index),101);
			//if its readable
			if(temp1 != 101) {
				//if its not the last number/string
				if(index < length - 1) {
					//if the first num is not 100 or 1000
					if(temp1%100 != 0) {
						int temp2 = dict.getOrDefault(list.get(index + 1),101);
						//if its 10 20 .. 100 1000
						if(temp2%10 == 0) {
							temp1 *= temp2;
						}
						//if its 10 20 .. 100 1000 else
						else {
							temp1 += temp2;
						}
						result += temp1;
						//if the next next number/string exists
						if(index + 2 < length) {
							index += 2;
						}
						else {
							compFlag = false;
						}
					}
					//if the first num is not 100 or 1000 else
					else {
						result += temp1;
						index += 1;
					}		
				}
				//if its not the last number/string else
				else {
					result += temp1;				
					compFlag = false;
				}
			}
			//if its readable else
			else {
				return -1;
			}
			//System.out.println("Index = " + index + " result = " + result);
		}
		
		return result;
	}
	
	private String translateBack(int num) {
		Map<Integer,String> dict = new HashMap<Integer,String>();
		String result = "";
		String minus = "";
		String minusTR = "eksi ", minusEN = "minus ";
		int n;
		int powerOfTen = 0;
		boolean firstTime = true;
		
		switch(lang) {
		case "TR":
			dict = dictTRback;
			minus = minusTR;
			break;
		case "EN":
			dict = dictENback;
			minus = minusEN;
			break;
		}
	
		n = Math.abs(num);
		
		while(n > 0) {
			//check for powers of ten
			if(firstTime) {
				int temp1 = n % 100;
				String temp2 = dict.getOrDefault(temp1, "");
				
				if(temp1 == 0) {	
					n = n / 100;
					powerOfTen += 2;
				}
				else if(!temp2.equals("")) {
					result = temp2 + " " + result;
					n = n / 100;
					powerOfTen += 2;
				}
				else {
					int temp3 = temp1 % 10;
					int temp4 = temp1 - temp3;
					
					String temp5 = dict.getOrDefault(temp3, "");
					String temp6 = dict.getOrDefault(temp4, "");
					
					if(!temp5.equals("")) {
						result = temp5 + " " + result;
						n = n / 10;
						powerOfTen += 1;
					}	
					if(!temp6.equals("")) {
						result = temp6 + " " + result;
						n = n / 10;
						powerOfTen += 1;
					}
				}
				firstTime = false;
			}
			else {
				int temp1 = n % 10;
				int powerOfTenInt = (int) Math.pow(10, powerOfTen);
				
				String temp2 = dict.getOrDefault(temp1, "");
				if(!temp2.equals("")) {
					if(temp2.equals("bir")) {
						temp2 = "";
					}
					result = temp2 + " "+ dict.getOrDefault(powerOfTenInt, "") + " " + result;
					powerOfTen += 1;
				}
				n = n /10;
			}
		}
		
		if(num < 0) {
			result = minus + " " + result;
		}
		
		return result.trim();
	}

}
