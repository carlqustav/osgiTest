package org.o7planning.tutorial;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.o7planning.tutorial.helloosgi.calculatorservice.CalculateService;
import org.o7planning.tutorial.helloosgi.interfaceservice.InterfaceService;
import org.o7planning.tutorial.helloosgi.interfaceservice.impl.InterfaceServiceImpl;
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
    JButton langButton;
    JLabel langLabel;
    String langTR = "TR";
    String langEN = "EN";
    
    String sumButtonTR = "Topla";
    String subButtonTR = "Çıkar";
    String mulButtonTR = "Çarp";
    String firstNumLabelTR = "Birinci Sayı";
    String secondNumLabelTR = "İkinci Sayı";
    String resultLabelTR = "Sonuç";
    String divButtonTR = "Böl";
    String langButtonTR = "Dil";
    
    String sumButtonEN = "Add";
    String subButtonEN = "Subtract";
    String mulButtonEN = "Multiply";
    String firstNumLabelEN = "First Number";
    String secondNumLabelEN = "Second Number";
    String resultLabelEN = "Result";
    String divButtonEN = "Divide";
    String langButtonEN = "Language";
    
    //var
    String lang = langTR;
	
	InterfaceSwing(CalculateService s){  
		
		service = s;
		//to do locale
	    sumButton = new JButton ("Topla");
        subButton = new JButton ("Çıkar");
        mulButton = new JButton ("Çarp");
        firstNumLabel = new JLabel ("Birinci Sayı");
        secondNumLabel = new JLabel ("İkinci Sayı");
        resultLabel = new JLabel ("Sonuç");
        firstNumTextField = new JTextField (5);
        secondNumTextfield = new JTextField (5);
        resultTextfield = new JTextField (5);
        divButton = new JButton ("Böl");
        langButton = new JButton ("Dil");
        langLabel = new JLabel (langTR);

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
        add (langButton);
        add (langLabel);

        sumButton.addActionListener(e -> buttonPressed(sumButton.getText()));
        subButton.addActionListener(e -> buttonPressed(subButton.getText()));
        mulButton.addActionListener(e -> buttonPressed(mulButton.getText()));
        divButton.addActionListener(e -> buttonPressed(divButton.getText()));
        
        langButton.addActionListener(e -> changeLang());
        
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
        langButton.setBounds (15, 40, 70, 25);
        langLabel.setBounds (90, 35, 330, 35);  

		setSize(444, 300);  
		setLayout(null);  
		setVisible(true);  
	}  
	
	

	public static void runInterface(CalculateService s) {
		new InterfaceSwing(s);
	}
	
	public void buttonPressed(String buttonName) {
		String results[] = service.recieveInputs(firstNumTextField.getText(), secondNumTextfield.getText(), buttonName, lang);
		
		resultTextfield.setText(results[0]);
		
		for(int i = 0; i < results.length; i++) {
			System.out.println(results[i]);
		}
	}
	
	private void changeLang() {
		if (lang.equals(langTR)) {
			lang = langEN;
			langLabel.setText(langEN);
			sumButton.setText(sumButtonEN     );
		    subButton.setText(subButtonEN     );
		    mulButton.setText(mulButtonEN     );
		    firstNumLabel.setText(firstNumLabelEN );
		    secondNumLabel.setText(secondNumLabelEN);
		    resultLabel.setText(resultLabelEN   );
		    divButton.setText(divButtonEN     );
		    langButton.setText(langButtonEN    );
		}	                       
		else {
			lang = langTR;
			langLabel.setText(langTR);
			sumButton.setText(sumButtonTR     );
		    subButton.setText(subButtonTR     );
		    mulButton.setText(mulButtonTR     );
		    firstNumLabel.setText(firstNumLabelTR );
		    secondNumLabel.setText(secondNumLabelTR);
		    resultLabel.setText(resultLabelTR   );
		    divButton.setText(divButtonTR     );
		    langButton.setText(langButtonTR    );
		}
		
		firstNumTextField.setText("");
		secondNumTextfield.setText("");
		resultTextfield.setText("");
			
	}
}
