package org.o7planning.tutorial.helloosgi.calculatorservice.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.o7planning.tutorial.helloosgi.calculatorservice.CalculateService;

public class CalculateServiceImpl implements CalculateService {

	//constants
	Map<String, Double> dictTR = new HashMap<String, Double>() {{
		put("eksi",101.0     );
		put("sıfır",0.0     );
        put("bir",1.0     );
        put("iki",2.0     );
        put("üç",3.0      );
        put("dört",4.0    );
        put("beş",5.0     );
        put("altı",6.0    );
        put("yedi",7.0    );
        put("sekiz",8.0   );
        put("dokuz",9.0   );
        put("on",10.0    );
        put("on bir",11.0);
        put("on iki",12.0);
        put("yirmi",20.0 );
        put("otuz",30.0  );
        put("kırk",40.0  );
        put("elli",50.0  );
        put("altmış",60.0);
        put("yetmiş",70.0);
        put("seksen",80.0);
        put("doksan",90.0);
        put("yüz",100.0  );
        put("bin",1000.0 );
        put("milyon",1000000.0);
        put("milyar"   ,1000000000.0);
        put("trilyon"  ,1000000000000.0);
        put("katrilyon",1000000000000000.0);
        put("kentilyon",1000000000000000000.0);
        put("seksilyon",1000000000000000000000.0);
        put("septilyon",1000000000000000000000000.0);
        put("oktilyon" ,1000000000000000000000000000.0);
        put("nonilyon" ,1000000000000000000000000000000.0);
        put("desilyon" ,1000000000000000000000000000000000.0);
    }};  
    Map<String, Double> dictEN = new HashMap<String, Double>() {{
    	put("minus",101.0     );
		put("zero",0.0     );
        put("one",1.0     );
        put("two",2.0     );
        put("three",3.0      );
        put("four",4.0    );
        put("five",5.0     );
        put("six",6.0    );
        put("seven",7.0    );
        put("eight",8.0   );
        put("nine",9.0   );
        put("ten",10.0    );
        put("eleven",11.0);
        put("twelve",12.0);
        put("thirteen",13.0);
        put("fourteen",14.0);
        put("fifteen",15.0);
        put("sixteen",16.0);
        put("seventeen",17.0);
        put("eighteen",18.0);
        put("nineteen",19.0);
        put("twelve",12.0);
        put("twenty",20.0 );
        put("thirty",30.0  );
        put("fourty",40.0  );
        put("fifty",50.0  );
        put("sixty",60.0);
        put("seventy",70.0);
        put("eighty",80.0);
        put("ninety",90.0);
        put("hundred",100.0  );
        put("thousand",1000.0 );
        put("million",1000000.0);
        put("billion"    ,1000000000.0);
        put("trillion"   ,1000000000000.0);
        put("quadrillion",1000000000000000.0);
        put("quintillion",1000000000000000000.0);
        put("sextillion" ,1000000000000000000000.0);
        put("septillion" ,1000000000000000000000000.0);
        put("octillion"  ,1000000000000000000000000000.0);
        put("nonillion"  ,1000000000000000000000000000000.0);
        put("decillion"  ,1000000000000000000000000000000000.0);
    }};
    Map<Double,String> dictTRback = new HashMap<Double,String>() {{
        put(0.0      ,"sıfır"    );
        put(1.0      ,"bir"      );
        put(2.0      ,"iki"      );
        put(3.0      ,"üç"       );
        put(4.0      ,"dört"     );
        put(5.0      ,"beş"      );
        put(6.0      ,"altı"     );
        put(7.0      ,"yedi"     );
        put(8.0      ,"sekiz"    );
        put(9.0      ,"dokuz"    );
        put(10.0     , "on"      );
        put(11.0     , "on bir"  );
        put(12.0     , "on iki"  );
        put(20.0     , "yirmi"   );
        put(30.0     , "otuz"    );
        put(40.0     , "kırk"    );
        put(50.0     , "elli"    );
        put(60.0     , "altmış"  );
        put(70.0     , "yetmiş"  );
        put(80.0     , "seksen"  );
        put(90.0     , "doksan"  );
        put(100.0    , "yüz"     );
        put(1000.0   , "bin"     );
        put(10000.0  ,"on bin");
        put(100000.0  ,"yüz bin");
        put(1000000.0, "milyon"  );
    }};
    Map<Integer,String> dictTRpower = new HashMap<Integer,String>() {{
        put(6      ,"milyon"    );
        put(9      ,"milyar"      );
        put(12     ,"trilyon"      );
        put(15     ,"katrilyon"       );
        put(18     ,"kentilyon"     );
        put(21     ,"seksilyon"      );
        put(24     ,"septilyon"     );
        put(27     ,"oktilyon"     );
        put(30     ,"nonilyon"    );
        put(33     ,"desilyon"    );
    }};
    Map<Double,String> dictENback = new HashMap<Double,String>() {{
		put(0.0         ,"zero" );
        put(1.0          ,"one");
        put(2.0          ,"two");
        put(3.0        ,"three"   );
        put(4.0         ,"four");
        put(5.0         ,"five" );
        put(6.0          ,"six");
        put(7.0        ,"seven" );
        put(8.0        ,"eight");
        put(9.0         ,"nine");
        put(10.0         ,"ten");
        put(11.0      ,"eleven");
        put(12.0      ,"twelve");
        put(13.0      ,"thirteen");
        put(14.0      ,"fourteen");
        put(15.0      ,"fifteen");
        put(16.0      ,"sixteen");
        put(17.0      ,"seventeen");
        put(18.0      ,"eighteen");
        put(19.0      ,"nineteen");
        put(20.0      ,"twenty");
        put(30.0      ,"thirty" );
        put(40.0      ,"fourty" );
        put(50.0       ,"fifty");
        put(60.0       ,"sixty");
        put(70.0     ,"seventy");
        put(80.0      ,"eighty");
        put(90.0      ,"ninety");
        put(100.0    ,"hundred");
        put(1000.0  ,"thousand");
        put(10000.0  ,"ten thousand");
        put(100000.0  ,"hundred thousand");
        put(1000000.0,"millon");
    }};
    Map<Integer,String> dictENpower = new HashMap<Integer,String>() {{
        put(6      ,"million"    );
        put(9      ,"billion"      );
        put(12     ,"trillion"      );
        put(15     ,"quadrillion"       );
        put(18     ,"quintillion"     );
        put(21     ,"sextillion"      );
        put(24     ,"septillion"     );
        put(27     ,"octillion"     );
        put(30     ,"nonillion"    );
        put(33     ,"decillion"    );
    }};
    Map<Integer,String> dictMapName= new HashMap<Integer,String>() {{
    	put(0,"TR");
    	put(1,"EN");
    }};
    Map<Integer,Map<String,Double>> dictMap = new HashMap<Integer,Map<String,Double>>() {{
		put(0,dictTR);
		put(1,dictEN);
    }};
    
    //var
	private String num1  = "";
	private String num2  = "";
	private String op  = "";
	private Locale lang; 
	private List<String> num1List;
	private List<String> num2List;
	
	@Override
	public String[] recieveInputs(String numString1, String numString2, String operation) {
		//var
		String result = "";
		double num1Result = 0;
		double num2Result = 0;
		double resultInt = 0;
		
		num1 = numString1.trim().toLowerCase();
		num2 = numString2.trim().toLowerCase();
		op = operation.trim();
		num1List = new ArrayList<String>(Arrays.asList(num1.split(" ")));
		num2List = new ArrayList<String>(Arrays.asList(num2.split(" ")));
		
		lang = detectLanguageBruteForce(num1List, num2List);
			
		if(lang == null) {
			return new String[] {"Error : Conflicting languages or invalid word."};
		}
		else {
			
			num1Result = translate(num1List);
			num2Result = translate(num2List);

			if(num2Result == 0 && operation.equals("Böl")) {
				return new String[] {"Error : Division by 0 cannot be computed."};
			}
			else {		
				resultInt = calculate(num1Result,num2Result,operation);
			}
			
			result = translateBack(resultInt);

			return new String[]{lang.getDisplayLanguage(),result,Double.toString(num1Result),Double.toString(num2Result),Double.toString(resultInt)};
		}
		
		
	}
	
	private Locale detectLanguageBruteForce(List<String> num1List2, List<String> num2List2) {
		List<String> newList = Stream.concat(num1List2.stream(), num2List2.stream())
                .collect(Collectors.toList());
		
		boolean foundMatch = false;
		String foundLang = "";
		
		for(int index = 0; index < newList.size(); index++) {
			String tempStr = newList.get(index);
			foundMatch = false;
			
			for(int i = 0; i < dictMap.size(); i++) {
				if(dictMap.get(i).containsKey(tempStr)) {
					String tempLang = dictMapName.get(i);
					foundMatch = true;
					
					if(foundLang.equals("")) {
						foundLang = tempLang;
					}
					else if(!tempLang.equals(foundLang)) {
						return null;
					}		
				}
				else if(i == 1) {
					if(foundMatch) {
						foundMatch = false;
					}
					else {
						return null;
					}			
				}
			}
		}
		
		return new Locale(foundLang);
	}

	private double calculate(double num1Result, double num2Result, String operation) {
		switch(operation) {
		case "sum":
			return add(num1Result,num2Result);
		case "sub":
			return sub(num1Result,num2Result);
		case "mul":
			return mul(num1Result,num2Result);
		case "div":
			return div(num1Result,num2Result);
		default:
			return -1;
		}
	}

	private double div(double num1Result, double num2Result) {
		if(num2Result != 0)
			return Math.floor(num1Result / num2Result);
		else
			return -1;
	}

	private double mul(double num1Result, double num2Result) {
		return num1Result*num2Result;
	}

	private double sub(double num1Result, double num2Result) {
		return num1Result-num2Result;
	}

	private double add(double num1Result, double num2Result) {
		return num1Result+num2Result;
	}

	private double translate(List<String> list) {
		boolean compFlag = true;
		int index = 0;
		double result = 0;
		int sign = 1;
		int length = list.size();

		Map<String, Double> dict = new HashMap<String, Double>();
		switch(lang.getLanguage()) {
		case "tr":
			dict = dictTR;
			break;
		case "en":
			dict = dictEN;
			break;
		}
		
		while(compFlag && index < length) {
			double temp1 = dict.getOrDefault(list.get(index),101.0);
			//if its readable
			if(temp1 != 101.0) {
				//if its not the last number/string
				if(index < length - 1) {
					//if the first num is not 100 or 1000
					if(temp1%100 != 0.0) {
						double temp2 = dict.getOrDefault(list.get(index + 1),101.0);
						//if its 10 20 .. 100 1000
						if(temp2%10 == 0.0) {
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
					if(index  > 0 && result == 0.0 && sign != -1) {
						result = 0.0;
					}
					else {
						result += temp1;					
					}	
					compFlag = false;
				}
			}
			//if its readable else
			else {
				sign *= -1;
				index += 1;
			}
			//System.out.println("Index = " + index + " result = " + result);
		}
		
		return result * sign;
	}

	private String translateBack(double num) {
		if(num > Math.pow(10, 33)) {
			return "Error : Result is bigger than 10^33";
		}
		
		Map<Double,String> dict = new HashMap<Double,String>();
		Map<Integer,String> powerDict = new HashMap<Integer,String>();
		String result = "";
		String minus = "";
		String minusTR = "eksi", minusEN = "minus";
		double n;
		int powerOfTen = 0;
		boolean firstTime = true;
		
		switch(lang.getLanguage()) {
		case "tr":
			dict = dictTRback;
			powerDict = dictTRpower;
			minus = minusTR;
			break;
		case "en":
			dict = dictENback;
			powerDict = dictENpower;
			minus = minusEN;
			break;
		}
	
		n = Math.abs(num);
		
		if(num == 0) {
			return dict.getOrDefault(num, "");
		}
		while(n > 0) {
			//0 < x < 100
			if(firstTime) {
				double temp1 = n % 100.0;
				int tempPOT = powerOfTen;
				String temp2 = dict.getOrDefault(temp1, "");
				//check if its zero
				if(temp1 == 0.0) {	
					n = n / 100;
					tempPOT += 2;
				}
				else if(!temp2.equals("")) {
					result = temp2 + " " + result;
					n = n / 100;
					tempPOT += 2;
				}
				else {
					double temp3 = temp1 % 10;
					double temp4 = temp1 - temp3;
					
					String temp5 = dict.getOrDefault(temp3, "");
					String temp6 = dict.getOrDefault(temp4, "");
					
					if(!temp5.equals("")) {
						result = temp5 + " " + result;
						n = n / 10;
					}	
					if(!temp6.equals("")) {
						result = temp6 + " " + result;
						n = n / 10;
					}
					tempPOT += 2;
				}
				n = Math.floor(n);
				firstTime = false;
				powerOfTen = tempPOT;
			}
			//x >= 1m (0 <= n < 1000)
			else if(powerOfTen >= 6){
				double temp = n % 1000;
				double temp1 = n % 100;
				int tempPOT = powerOfTen;
				String temp2 = dict.getOrDefault(temp1, "");
				String subResult = "";			
				
				//if n > 100
				if(temp/100 >= 1) {
					if(temp/100 == 1.0) {
						//3e bölünmüyorsa
						if(powerOfTen%3 != 0) {
							subResult = dict.getOrDefault( temp / 100.0, "") + " " + dict.getOrDefault(100.0, "") + " " + subResult;
						}
						else {
							//nothing
						}
					}
					else {
						//bozuk
						subResult = dict.getOrDefault( Math.floor(temp / 100.0), "") + " " + dict.getOrDefault(100.0, "") + " " + subResult;
					}
				}
				else {	
					//check if its zero
					if(temp1 == 0.0) {	
						//nothing
					}
					else if(!temp2.equals("")) {
						subResult = temp2 + " " + subResult;
					}
					else {
						double temp3 = temp1 % 10;
						double temp4 = temp1 - temp3;
						
						String temp5 = dict.getOrDefault(temp3, "");
						String temp6 = dict.getOrDefault(temp4, "");
						
						if(!temp5.equals("")) {
							subResult = temp5 + " " + result;
							n = n / 10;
						}	
						if(!temp6.equals("")) {
							subResult = temp6 + " " + result;
							n = n / 10;
						}
					}
					
				}
				if(!result.equals("") && temp1 != 0.0) {
					result = subResult + powerDict.getOrDefault(powerOfTen,"") + " " + result;
				}
				else if(temp1 != 0.0) {
					result = subResult + powerDict.getOrDefault(powerOfTen,"");
				}
				
				firstTime = false;
				tempPOT += 3;
				n = Math.floor(n/1000);
				powerOfTen = tempPOT;
				
			}
			//1k < x < 1m
			else {
				//if its not on thousands
				if(powerOfTen != 3) {
					double temp1 = n % 10;
					double powerOfTenInt = (double) Math.pow(10, powerOfTen);
					
					String temp2 = dict.getOrDefault(temp1, "");
					if(!temp2.equals("")) {
						if(temp2.equals(dict.getOrDefault(0.0, ""))) {
							//nothing
						}
						else if(temp2.equals(dict.getOrDefault(1.0, "")) && lang.getLanguage().equals("tr")){
							result = dict.getOrDefault(powerOfTenInt, "") + " " + result;
						}
						else {
							result = temp2 + " " + dict.getOrDefault(powerOfTenInt, "") + " " + result;
						}			
					}
					powerOfTen += 1;
					n = Math.floor(n/10);	
				}
				else {
					double temp1 = n % 100.0;
					int tempPOT = powerOfTen;
					String temp2 = dict.getOrDefault(temp1, "");
					//check if its zero
					if(temp1 == 0.0) {	
						n = n / 100;
						tempPOT += 2;
					}
					else if(!temp2.equals("")) {
						result = temp2 + " " + dict.getOrDefault(1000.0, "") + " " + result;
						n = n / 100;
						tempPOT += 2;
					}
					else {
						double temp3 = temp1 % 10;
						double temp4 = temp1 - temp3;
						
						String temp5 = dict.getOrDefault(temp3, "");
						String temp6 = dict.getOrDefault(temp4, "");
						
						if(!temp5.equals("")) {
							result = temp5 + " " + dict.getOrDefault(1000.0, "") + " "  + result;
							n = n / 10;
						}	
						if(!temp6.equals("")) {
							result = temp6 + " " + result;
							n = n / 10;
						}
						tempPOT += 2;
					}
					n = Math.floor(n);
					powerOfTen = tempPOT;
				}
			}
			if(n < 1 && n > 0) {
				n = 0.0;
			}
		}
		
		if(num < 0) {
			result = minus + " " + result;
		}
		
		return result.trim();
	}

}
