package org.o7planning.tutorial;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.o7planning.tutorial.helloosgi.calculatorservice.CalculateService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

public class InterfaceSwing extends JFrame {
	
	CalculateService service;
	
	JFrame f;
	
	//const
	JButton sumButton;
    JButton subButton;
    JButton mulButton;
    JLabel firstNumLabel;
    JLabel secondNumLabel;
    JLabel resultLabel;
    JTextField firstNumTextField;
    JTextField secondNumTextfield;
    JTextField resultTextfield;
    JButton divButton;
    String langTR = "TR";
    String langEN = "EN";
    
    Map<String,String> uiEN = new HashMap<String,String>() {{
        put("sum"      ,"Add"    );
        put("sub"      ,"Subtract"      );
        put("mul"     ,"Multiply"      );
        put("div"     ,"Divide"       );
        put("firstn"    ,"First Number"     );
        put("secondn"     ,"Second Number"      );
        put("result"     ,"Result"     );
    }};
    Map<String,String> uiTR = new HashMap<String,String>() {{
        put("sum"      ,"Topla"   );
        put("sub"      ,"Çıkar"     );
        put("mul"     ,"Çarp"      );
        put("div"     ,"Böl"       );
        put("firstn"    ,"Birinci Sayı"    );
        put("secondn"     ,"İkinci Sayı"      );
        put("result"     ,"Sonuç"     );
    }};
    
    //locale
    Locale systemLanguage = Locale.getDefault();
    
    String sumButtonTR = "Topla";
    String subButtonTR = "Çıkar";
    String mulButtonTR = "Çarp";
    String firstNumLabelTR = "Birinci Sayı";
    String secondNumLabelTR = "İkinci Sayı";
    String resultLabelTR = "Sonuç";
    String divButtonTR = "Böl";
    
    String sumButtonEN = "Add";
    String subButtonEN = "Subtract";
    String mulButtonEN = "Multiply";
    String firstNumLabelEN = "First Number";
    String secondNumLabelEN = "Second Number";
    String resultLabelEN = "Result";
    String divButtonEN = "Divide";
    
    //var
    String lang = langTR;
	
	InterfaceSwing(CalculateService s){  
		
		//System.out.println(systemLanguage.getLanguage());
		//system language
		Map<String,String> lang = new HashMap<String,String>();
		switch(systemLanguage.getLanguage()) {
		case "tr":
			lang = uiTR;
			break;
		case "en":
			lang = uiEN;
			break;
		default:
			lang = uiTR;
			break;
		}
		
		service = s;
		
	    sumButton = new JButton (lang.get("sum"));
        subButton = new JButton (lang.get("sub"));
        mulButton = new JButton (lang.get("mul"));
        divButton = new JButton (lang.get("div"));
        firstNumLabel = new JLabel (lang.get("firstn"));
        secondNumLabel = new JLabel (lang.get("secondn"));
        resultLabel = new JLabel (lang.get("result"));
        firstNumTextField = new JTextField (5);
        secondNumTextfield = new JTextField (5);
        resultTextfield = new JTextField (5);

        //adjust size and set layout
        setPreferredSize (new Dimension (431, 219));
        setLayout (null);

        //add components
        add (sumButton);
        add (subButton);
        add (mulButton);
        add (firstNumLabel);
        add (secondNumLabel);
        add (resultLabel);
        add (firstNumTextField);
        add (secondNumTextfield);
        add (resultTextfield);
        add (divButton);

        sumButton.addActionListener(e -> buttonPressed(sumButton.getText()));
        subButton.addActionListener(e -> buttonPressed(subButton.getText()));
        mulButton.addActionListener(e -> buttonPressed(mulButton.getText()));
        divButton.addActionListener(e -> buttonPressed(divButton.getText()));
             
        //set component bounds (only needed by Absolute Positioning)
        sumButton.setBounds (15, 180, 100, 20);
        subButton.setBounds (120, 180, 100, 20);
        mulButton.setBounds (225, 180, 95, 20);
        firstNumLabel.setBounds (20, 80, 70, 25);
        secondNumLabel.setBounds (20, 110, 70, 25);
        resultLabel.setBounds (20, 140, 65, 25);
        firstNumTextField.setBounds (90, 80, 330, 30);
        secondNumTextfield.setBounds (90, 110, 330, 30);
        resultTextfield.setBounds (90, 140, 330, 30);
        divButton.setBounds (325, 180, 95, 20);

		setSize(444, 300);  
		setLayout(null);  
		setVisible(true);  
	}  

	public static void runInterface(CalculateService s) {
		new InterfaceSwing(s);
	}
	
	public void buttonPressed(String buttonName) {
		String results[] = service.recieveInputs(firstNumTextField.getText(), secondNumTextfield.getText(), buttonName);
		
		resultTextfield.setText(results[0]);
		
		for(int i = 0; i < results.length; i++) {
			System.out.println(results[i]);
		}
	}
}
