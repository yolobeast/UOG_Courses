/*
 * ------------------------------------------------------------------
 *  compiled.java - this function will call jni wrapper and write to file
 *
 *  CIS*2750 - Software Systems Development and Integration - W15
 *  School of Computer Science
 *  University of Guelph
 *  Author: Sathi Ranganatahan
 * ------------------------------------------------------------------
 */

import java.io.*;
import java.util.*;

/*Generating function to main GUI when compailed*/
public class compiled
{
	
	public static int compiledfun(File file,File fp,String cl,String compiloption,String compailrunoption)
	{	
		try{
			
			/*calling my jni function*/
			JNIwrapper gen = new JNIwrapper();
			gen.create_PM();
			gen.Manage_PM("title",2,1);
			gen.Manage_PM("fields",3,1);
			gen.Manage_PM("buttons",3,1);
  
			/*too check if the parse is good*/
			if(gen.parseFrom_PM(file.getAbsolutePath())==0)
			{
			  return(0);
			}
			else
			{
			
			String title=gen.getstring("title");
			String[] string=gen.getlist("fields");
			String[] buttonzz = gen.getlist("buttons");
			
			gen = new JNIwrapper();
			
			for(String s:string)
			{
			   gen.Manage_PM(s,2,1);
			}
			for(String h:buttonzz)
			{
			   gen.Manage_PM(h,2,1);
			}
			
			if(gen.parseFrom_PM(file.getAbsolutePath())==0)
			{
			  return(0);
			}
			
			/*print writes to the file that is specified*/
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(fp.getPath()+"/"+fp.getName()+".java")));
			PrintWriter setmethod = new PrintWriter(new BufferedWriter(new FileWriter(fp.getPath()+"/"+fp.getName()+"FieldEdit.java")));
			PrintWriter exceptionWriter = new PrintWriter(new BufferedWriter(new FileWriter(fp.getPath()+ "/" + "IllegalFieldValueException.java")));
			for(String a : buttonzz)
			{
			  
				PrintWriter interfacewritter = new PrintWriter(new BufferedWriter(new FileWriter(fp.getPath()+"/"+gen.getstring(a)+".java")));
				interfacewritter.println("import java.awt.event.*;");
				interfacewritter.println("public class "+gen.getstring(a)+" implements ActionListener");
				interfacewritter.println("{");
				interfacewritter.println("\t"+cl+"FieldEdit dialog;");
				interfacewritter.println("\tpublic "+gen.getstring(a)+"("+cl+"FieldEdit dialog)");
				interfacewritter.println("\t{");
				interfacewritter.println("\t\tthis.dialog = dialog;");
				interfacewritter.println("\t}");
				interfacewritter.println("\tpublic void actionPerformed(ActionEvent e)");
				interfacewritter.println("\t{");
			  if(a.equalsIgnoreCase("ADD"))
			  {
			    interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\"Adding the record\");");
			  }
			  else if(a.equalsIgnoreCase("REMOVE"))
			  {
			    interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\"Removing the record\");");
			  }
			  else if(a.equalsIgnoreCase("UPDATE"))
			  {
			    interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\"Updating the record\");");
			  }
			  else if(a.equalsIgnoreCase("QUERY"))
			  {
			    interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\"Querying the record\");");
			  }
			  else if(a.equalsIgnoreCase("Get"))
			  {

			    for(String x : string)
			    {
			      interfacewritter.println("\t\ttry");
			      interfacewritter.println("\t\t{");
			      interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"("+"\""+x+"="+"\""+"+ dialog.getDC"+x+"());");
			      interfacewritter.println("\t\t}");
			      interfacewritter.println("\t\tcatch(Exception ex)");
			      interfacewritter.println("\t\t{");
			      interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\""+x+" = wrong type\");");
			      interfacewritter.println("\t\t}");
			    }
			  }
			  else if(a.equalsIgnoreCase("Set"))
			  {

			    for(String x : string)
			    {
			      if(gen.getstring(x).equalsIgnoreCase("integer"))
			      {
				interfacewritter.println("\t\ttry");
			        interfacewritter.println("\t\t{");
				interfacewritter.println("\t\t\tdialog.setDC"+x+"(\"123\");");
				interfacewritter.println("\t\t}");
				interfacewritter.println("\t\tcatch(Exception ex)");
				interfacewritter.println("\t\t{");
				interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\""+x+" = wrong type\");");
				interfacewritter.println("\t\t}");
			      }
			      else if(gen.getstring(x).equalsIgnoreCase("float"))
			      {
				interfacewritter.println("\t\ttry");
			        interfacewritter.println("\t\t{");
				interfacewritter.println("\t\t\tdialog.setDC"+x+"(\"123.15\");");
				interfacewritter.println("\t\t}");
				interfacewritter.println("\t\tcatch(Exception ex)");
				interfacewritter.println("\t\t{");
				interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\""+x+" = wrong type\");");
				interfacewritter.println("\t\t}");
			      }
			      else if(gen.getstring(x).equalsIgnoreCase("string"))
			      {
				interfacewritter.println("\t\ttry");
			        interfacewritter.println("\t\t{");
				interfacewritter.println("\t\t\tdialog.setDC"+x+"(\"yolo\");");
				interfacewritter.println("\t\t}");
				interfacewritter.println("\t\tcatch(Exception ex)");
				interfacewritter.println("\t\t{");
				interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\""+x+" = wrong type\");");
				interfacewritter.println("\t\t}");
			      }
			    }
			  }
			  else
			  {
			    interfacewritter.println("\t\t\tdialog.appendToStatusArea"+"(\"Click\");");
			  }
			  interfacewritter.println("\t}");
			  interfacewritter.println("}");
			  interfacewritter.close();
			}
			
			exceptionWriter.println("public class IllegalFieldValueException extends Exception");
			exceptionWriter.println("{");
			exceptionWriter.println("\tpublic IllegalFieldValueException()");
			exceptionWriter.println("\t{");
			exceptionWriter.println("\t\tsuper(); ");
			exceptionWriter.println("\t}");
			exceptionWriter.println("\tpublic IllegalFieldValueException(String message)");
			exceptionWriter.println("\t{");
			exceptionWriter.println("\t\tsuper(message);");
			exceptionWriter.println("\t}");
			exceptionWriter.println("}");
			exceptionWriter.close();
		
			setmethod.println("public interface "+cl+"FieldEdit");
			setmethod.println("{");
			/*writes my interface*/
			for(String s:string)
			{
				setmethod.println("		public String getDC"+s+"() throws IllegalFieldValueException;");
				setmethod.println("		public void setDC"+s+"(String "+s+");");
			}
			setmethod.println("		public void appendToStatusArea(String message);");
			setmethod.println("}");
			setmethod.close();
		
			/*import things to generated gui*/
			output.println("import javax.swing.border.BevelBorder;");
			output.println("import javax.swing.*;");
			output.println("import java.awt.*;");
			output.println("\n\n");
			output.println("public class "+cl+" extends JFrame implements "+cl+"FieldEdit");
			output.println("{");
			for(String s:string)
			{
				output.println("	private	JTextField "+s+"_text;");
			}
		        output.println("	private JTextArea textarea;"); 
			output.println("\n");
			/*prints the buttons*/
			output.println("	public static void main(String[] args) {");
			output.println("		"+cl+" gui = new "+cl+"();");
			output.println("		gui.setVisible(true);");
			output.println(" 	 }");
			output.println("	 public "+cl+"()");
			output.println(" 	{");
			output.println(" 		super(\""+title+"\");");
			output.println("\n");
			/*sets the panel*/
			output.println("		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);");
			output.println("		setSize(650,600);");
			output.println("		JPanel mainP = new JPanel(new FlowLayout());");
			output.println("		JPanel yoloP = new JPanel();");
			output.println("		JPanel buttP=new JPanel(new FlowLayout());");
			output.println("		JPanel statusbar = new JPanel();");
			output.println("		JPanel statusP =new JPanel(new BorderLayout());");
			output.println("		statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));");
			output.println("		JLabel stdunt = new JLabel(\"Student Id:\");");
			output.println("\n");
			
			/*prints the feilds*/
			for(String s:string)
			{
				output.println("		JLabel "+s+"_label = new JLabel(\""+s+"\");");
				output.println("		"+s+"_text = new JTextField(40);");
				output.println("		yoloP.add("+s+"_label);");
				output.println("		yoloP.add("+s+"_text);");
			}
		
			output.println("\n");
			/*prints the buttons*/
			for(String g : buttonzz)
			{
				output.println("		JButton "+g+"_button = new JButton(\""+g+"\");");
				output.println("                "+ g + "_button.addActionListener(new " + gen.getstring(g) + "(this));");
				output.println("		buttP.add("+g+"_button);");
			}
		
				output.println("	        textarea = new JTextArea(20,40);");
				output.println("		textarea.setEditable(false);");
				output.println("		JScrollPane scroll = new JScrollPane(textarea);");
				output.println("		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);");
				output.println("		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);");
				output.println("\n");
				output.println("		yoloP.setLayout(new BoxLayout(yoloP,BoxLayout.Y_AXIS));");
				output.println("\n");
				/*sets things too my main panel*/
				output.println("		mainP.add(yoloP);");
				output.println("		mainP.add(buttP);");
				output.println("\n");
				output.println("		statusbar.setLayout(new BoxLayout(statusbar,BoxLayout.Y_AXIS));");
				output.println("		JLabel  statusLabel = new JLabel();");
				output.println("\n");
				output.println("		statusLabel.setText(\"status\");");
				output.println("		statusbar.add(statusLabel);");
				output.println("		statusbar.add(scroll);");
				output.println("		mainP.add(statusbar);");
				output.println("		add(mainP);");
				output.println("	}");
				
 				for(String s:string)
 				{
				    
				    
				    output.println("\tpublic String getDC"+s+"() throws IllegalFieldValueException");
				    output.println("\t{");
 				
				    if(gen.getstring(s).equals("integer"))
				    {
					output.println("\t\ttry");
					output.println("\t\t{");
					output.println("\t\t\tInteger.parseInt("+ s + "_text.getText());");
					output.println("\t\t}");
					output.println("\t\tcatch(NumberFormatException e)");
					output.println("\t\t{");
					output.println("\t\t\tthrow new IllegalFieldValueException(" + s + "_text.getText());");
					output.println("\t\t}");
				    }
				    else if(gen.getstring(s).equals("float"))
				    {
					 output.println("\t\ttry");
					 output.println("\t\t{");
					 output.println("\t\t\tFloat.parseFloat(" + s + "_text.getText());");
					 output.println("\t\t}");
					 output.println("\t\tcatch(NumberFormatException e)");
					 output.println("\t\t{");
					 output.println("\t\t\tthrow new IllegalFieldValueException(" + s + "_text.getText());");
					 output.println("\t\t}");
				    }
				    
				    
				    output.println("    \t\t return("+s+"_text.getText());"); 
				    output.println("	}");
				    output.println("	public void setDC"+s+"(String "+s+")");
				    output.println("	{");
				    output.println("    \t\t"+s+"_text.setText("+s+");"); 
				    output.println("	}");
 				}
 				output.println("\tpublic void appendToStatusArea(String message)");
				output.println("\t{");
				output.println("\t\ttextarea.append(message + \"\\n\");");
				output.println("\t}");
				output.println("}");
				
				output.close();
				
				//gen.destroy_PM();
		    return(1);
		  }
		}
		catch(IOException ex){
			/*check to see if the file exists*/
			System.out.println("file not found");
		}
	  return(1);
	}
	
	
}
