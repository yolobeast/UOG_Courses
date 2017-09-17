/*
 * ------------------------------------------------------------------
 *  GUI.java - creates the GUI and compies the function
 *
 *  CIS*2750 - Software Systems Development and Integration - W15
 *  School of Computer Science
 *  University of Guelph
 *  Author: Sathi Ranganatahan
 * ------------------------------------------------------------------
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUI extends JFrame {

    /*item that are initalized for the gui*/
    public static final int WIDTH = 650;
    public static final int HEIGHT = 570;
    private final JPanel background;
    private JTextField one = new JTextField(10);
    private JTextField startTime = new JTextField(20);
    private JTextField endTime = new JTextField(20);
    private JTextField comment = new JTextField(10);
    private JTextField location = new JTextField(10);
    String[] types = {"School", "Home", "Others"};
    JComboBox addtype;
    final JTextArea message = new JTextArea(10, 40);
    JPanel statusbar = new JPanel();
    JLabel statusLabel = new JLabel();
    JScrollPane scrol;
    File filename;
    String yolobeastfile;
    JFileChooser fileChooing;
    File filenametwo=new File(System.getProperty("user.dir"));
    String nameoffile;
    File binfile;
    File bin;
    String c;
    String status;
    String typee;
    String jacomp ;
    String jarun="java";
    String check ;
    String comopt;
    String jarunopt="";
    int compiler=1;
    
    /*gui class*/
	public GUI() {

        super("2750 GUI");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new AreYouSure());
        background = new JPanel();
        background.setLayout(new BorderLayout());
        GridBagConstraints b = new GridBagConstraints();
        add(background);
        JToolBar toolbar = new JToolBar();

        statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusbar, BorderLayout.SOUTH);
        message.getDocument().addDocumentListener(new MyDocumentListener());

        /*GUI menu*/
        JMenu file = new JMenu("File");
        JMenu compile = new JMenu("Compile");
        JMenu config = new JMenu("Config");
        JMenu help = new JMenu("Help");
		JMenuItem Add = new JMenuItem("New");
        file.add(Add);
        Add.addActionListener(new newuntitled());
        Add.setAccelerator(KeyStroke.getKeyStroke("control N"));
		/*add action listner 
		 * as well as the accelerator*/
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new open());
        /*open*/
        file.add(open);
        open.setAccelerator(KeyStroke.getKeyStroke("control O"));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new save());
        /*save*/
        file.add(save);
        save.setAccelerator(KeyStroke.getKeyStroke("control S"));

        JMenuItem saveass = new JMenuItem("Save As");
        saveass.addActionListener(new saveass());
        /*save as*/
        file.add(saveass);
        saveass.setAccelerator(KeyStroke.getKeyStroke("control shift S"));

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new quit());
        /*quit*/
        file.add(quit);
        quit.setAccelerator(KeyStroke.getKeyStroke("control Q"));

        JMenuItem comp = new JMenuItem("Compile");
        /*compile*/
        compile.add(comp);
        comp.addActionListener(new comp());

        JMenuItem comrun = new JMenuItem("Compile and Run");
        /*compile run*/
        compile.add(comrun);
        comrun.setAccelerator(KeyStroke.getKeyStroke("F9"));
        comrun.addActionListener(new comrunthis());
        yolobeastfile="./";

        JMenuItem jcom = new JMenuItem("Java Compiler");
        /*java compiler*/
        config.add(jcom);
        jcom.addActionListener(new javacompiler());
        jcom.setAccelerator(KeyStroke.getKeyStroke("F7"));

        JMenuItem comopt = new JMenuItem("Compile option");
        /*compiler option*/
        config.add(comopt);
        comopt.addActionListener(new compilerun());
        comopt.setAccelerator(KeyStroke.getKeyStroke("F6"));
      

        JMenuItem javrun = new JMenuItem("Java Run-Time");
        config.add(javrun);
        /*java run time*/
        javrun.addActionListener(new javarun());
        javrun.setAccelerator(KeyStroke.getKeyStroke("F4"));
        
        JMenuItem javrunopt = new JMenuItem("Java Run-Time option");
        config.add(javrunopt);
        /*java run time*/
        javrunopt.addActionListener(new javarunoption());
        javrunopt.setAccelerator(KeyStroke.getKeyStroke("F5"));

        JMenuItem workdic = new JMenuItem("Working Directory");
        config.add(workdic);
        workdic.addActionListener(new workingdir());
        workdic.setAccelerator(KeyStroke.getKeyStroke("F8"));
        
        JMenuItem changecom = new JMenuItem("Change Compiler");
        config.add(changecom);
        changecom.addActionListener(new changecompiler());
        changecom.setAccelerator(KeyStroke.getKeyStroke("F2"));

        JMenuItem hell = new JMenuItem("How to use");
        hell.addActionListener(new hell());
        /*help*/
        help.add(hell);
        hell.setAccelerator(KeyStroke.getKeyStroke("F1"));

        JMenuItem about = new JMenuItem("about");
        about.addActionListener(new abt());
        help.add(about);

        JMenuBar bar = new JMenuBar();
        bar.add(file);
        bar.add(compile);
        bar.add(config);
        bar.add(help);
        setJMenuBar(bar);

        /*set my images for the icons 
         * from the pics folder*/
        ImageIcon newpic = new ImageIcon("pics/new.gif");
        ImageIcon openpic = new ImageIcon("pics/open.gif");
        ImageIcon savepic = new ImageIcon("pics/save.gif");
        ImageIcon saveasspic = new ImageIcon("pics/saveass.gif");
        ImageIcon runpic = new ImageIcon("pics/run.gif");
        ImageIcon stoppic = new ImageIcon("pics/stop.gif");

        
        /*sets my buttons to my panel*/
        JButton newb = new JButton(newpic);
        newb.addActionListener(new newuntitled());
        JButton openb = new JButton(openpic);
        openb.addActionListener(new open());
        JButton saveb = new JButton(savepic);
        saveb.addActionListener(new save());
        JButton saveassb = new JButton(saveasspic);
        saveassb.addActionListener(new saveass());
        JButton runb = new JButton(runpic);
        runb.addActionListener(new comrunthis());
        JButton stopb = new JButton(stoppic);
        stopb.addActionListener(new quit());

        /*add the buttons to the tool bar*/
        toolbar.add(newb);
        toolbar.add(openb);
        toolbar.add(saveb);
        toolbar.add(saveassb);
        toolbar.add(runb);
        toolbar.add(stopb);

        /*sets the name of file to undefined*/
        nameoffile = "undefined";
        scrol = new JScrollPane(message, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        background.add(scrol, BorderLayout.CENTER);

        background.add(toolbar, BorderLayout.PAGE_START);
        /*sets the status to nothing*/
        status = "";
        statusLabel.setText("Current Project: " + nameoffile + status);
        scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
        statusbar.add(statusLabel);

    }

    /*my exitting checking class*/
    private class AreYouSure extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {

            /*thi sfunction is for checking is when quitting 
             * it will give you an option to save or not save or cancel*/
            if (status.equals("modified")) {
                int result = JOptionPane.showConfirmDialog(null, "Do you want to save this file and quit?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    savemyass();
                    System.exit(0);
                } else if (result == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else {
                    status = "modified";
                    scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
                    statusLabel.setText("Current Project: " + nameoffile + "[" + status + "]");
                }
            } else {
                System.exit(0);
            }
        }
    }

    /*this is a document listner for check too see of the text are is modefied*/
    private class MyDocumentListener implements DocumentListener {

        
        @Override
        public void insertUpdate(DocumentEvent e) {
            statusLabel.setText("Current Project: " + nameoffile + "[modified]");
            status = "modified";
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            statusLabel.setText("Current Project: " + nameoffile + "[modified]");
            status = "modified";
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            statusLabel.setText("Current Project: " + nameoffile + "[modified]");
            status = "modified";
        }
    }

    /*this function call a save as function to save it
     * or write it to the file if it's already choosen*/
    private class save implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
			int i;
			String checkext = "";
            if ("modified".equals(status) && "undefined".equals(nameoffile)) {
                savemyass();
            } else if (!"undefined".equals(nameoffile) && "modified".equals(status)) {
				
				i = nameoffile.lastIndexOf(".");
				if (i > 0) {
					checkext = nameoffile.substring(i);
				}
				if(checkext.equals(".config") || checkext.equals(" "))
				{
				try {
                    PrintWriter saveing = new PrintWriter(filename);
                    saveing.print(message.getText());
                    saveing.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showConfirmDialog(null, "The file dosnt exists", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                }
                status = "";
                scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
                statusLabel.setText("Current Project: " + nameoffile + status);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You have to save with .config at the end");
				}
            }

        }
    }

     
     /*this function to compile and run 
      * where it will call exec to run the java file */
     private class comrunthis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
			
			if (compiler==0)
			{
			if (status.equals("modified")) {
				 JOptionPane.showMessageDialog(null, "pleas save the file");
			}
			else if( nameoffile == "undefined") {
				 JOptionPane.showMessageDialog(null, "pleas open a file");
			}
			else{
				/*this will create an extra folder 
				 * and it will write to that folder by calling exec*/
				
				String nameoffile2;
				nameoffile2 = filename.getName();
				int i = nameoffile2.lastIndexOf(".");
				if (i != -1) {
					c = nameoffile2.substring(0, i);
					bin = new File(yolobeastfile +"/" + c);
					bin.mkdir();
				}
				compiled com= new compiled();
			        if(com.compiledfun(filename,bin,c,comopt,jacomp)==0)
			        {
				  JOptionPane.showMessageDialog(null, "Parse ERROR");
			        }
			        else
			        {
				  try 
				  {
					Process runthis2 = Runtime.getRuntime().exec("javac -cp "+bin.getPath()+"/ "+bin.getName()+"/"+bin.getName()+".java");
					runthis2.waitFor();
					Process runtest = Runtime.getRuntime().exec( jarun+" -cp "+bin.getPath()+" "+bin.getName() +" "+ jarunopt);
					
				  }
				  catch(Exception ex)
				  {
					/*this will catch the exception if the input file is messedup*/
					System.out.println("the file dosnt exist");
				  }
				}
			    }
			}
			else
			{
				if (status.equals("modified")) {
				      JOptionPane.showMessageDialog(null, "pleas save the file");
				}
				else if( nameoffile == "undefined") {
				    JOptionPane.showMessageDialog(null, "pleas open a file");
				}
				else
				{
				
				      String nameoffile3;
				      nameoffile3 = filename.getName();
				      int i = nameoffile3.lastIndexOf(".");
				      if (i != -1) {
					  check = nameoffile3.substring(0, i);
					  bin = new File(yolobeastfile +"/" + check);
					  bin.mkdir();
				      }
				      try
				      {
					  Process command1 = Runtime.getRuntime().exec("./create " + bin.getName() +" " + bin.getPath());
					  command1.waitFor();
					  Process command = Runtime.getRuntime().exec("./yadc " + filename.getName() +" "+ bin.getName() + " " + bin.getPath());
					  command.waitFor();
					  Process runthis2 = Runtime.getRuntime().exec("javac -cp "+bin.getPath()+"/ "+bin.getName()+"/"+bin.getName()+".java");
					  runthis2.waitFor();
					  File yolocheck = new File(bin.getPath());
					  File infilecheck = new File(bin.getPath()+"/"+bin.getName()+".java");
					  if(yolocheck.list().length==0 || infilecheck.length()<=1)
					  {
					    JOptionPane.showMessageDialog(null, "Parse ERROR");
					  }
					  else
					  {
					    Process runtest = Runtime.getRuntime().exec( jarun+" -cp "+bin.getPath()+" "+bin.getName() +" "+ jarunopt);
					  }
				      }
				      catch (Exception ex)
				      {
					    JOptionPane.showMessageDialog(null, "the file dosnt exist");
					    //System.out.println("the file dosnt exist");
				      } 
				}
			}
    }
}
    
    
    
    
    
    /*this function just for compiling
     * it will run the text file comoile the function to right file*/
    private class comp implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
          String cmd = "";
			/*this function will also will find if the thing is saved or not*/
			if(compiler==0)
			{
			String binyolofile;
			int i=1;
			if (status.equals("modified")) {
				 JOptionPane.showMessageDialog(null, "pleas save the file");
			}
			else if(nameoffile == "undefined") {
				 JOptionPane.showMessageDialog(null, "pleas open a file");
			}
			else{
				
				String nameoffile2;
				nameoffile2 = filename.getName();
				i = nameoffile2.lastIndexOf(".");
				if (i != -1) {
					check = nameoffile2.substring(0, i);
					binfile = new File(yolobeastfile +"/" + check);
					binfile.mkdir();
				}
				
				compiled com= new compiled();
			        i=com.compiledfun(filename,binfile,check,comopt,jacomp);
			        if(i==1)
			        {
				  JOptionPane.showMessageDialog(null, "The file compiled fine");
				}
				else
				{
				  JOptionPane.showMessageDialog(null, "didnt compiled fine");
				}
			}
			}
			else
			{
				File yolocheck;
				File infilecheck;
				
				if (status.equals("modified")) {
				      JOptionPane.showMessageDialog(null, "pleas save the file");
				}
				else if( nameoffile == "undefined") {
				    JOptionPane.showMessageDialog(null, "pleas open a file");
				}
				else
				{
				
				      String nameoffile3;
				      nameoffile3 = filename.getName();
				      int i = nameoffile3.lastIndexOf(".");
				      if (i != -1) {
					  check = nameoffile3.substring(0, i);
					  bin = new File(yolobeastfile +"/" + check);
					  bin.mkdir();
				      }
				      try
				      {
						Process command1 = Runtime.getRuntime().exec("./create " + bin.getName() +" " + bin.getPath());
						command1.waitFor();
						Process command = Runtime.getRuntime().exec("./yadc " + filename.getName() +" "+ bin.getName() + " " + bin.getPath());
						command.waitFor();
						Process runthis2 = Runtime.getRuntime().exec("javac -cp "+bin.getPath()+"/ "+bin.getName()+"/"+bin.getName()+".java");
					        runthis2.waitFor();
				      }
				      catch (Exception ex)
				      {
					   // JOptionPane.showMessageDialog(null, "Didnt compile properly");
				      }
				      yolocheck = new File(bin.getPath());
				      infilecheck = new File(bin.getPath()+"/"+bin.getName()+".java");
				      if(yolocheck.list().length==0 || infilecheck.length()<=1)
				      {
					   JOptionPane.showMessageDialog(null, "Didnt compile properly");
				      }
				      else
				      {
					  JOptionPane.showMessageDialog(null, "Compiled properley");
				      }
				}
			}
    }
}

/*this function that i have are for compile config*/ 

/*this function for Java run*/
private class javacompiler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        jacomp = JOptionPane.showInputDialog(null, "Enter Javacompiler option", "Enter javarun option", JOptionPane.QUESTION_MESSAGE);

    }
}
/*this function for compile run*/
private class compilerun implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        comopt = JOptionPane.showInputDialog(null, "Enter compile option", "Enter compile option", JOptionPane.QUESTION_MESSAGE);

    }
}

/*this function for Java run*/
private class javarun implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        jarun = JOptionPane.showInputDialog(null, "Enter javarun option", "Enter javarun option", JOptionPane.QUESTION_MESSAGE);

    }
}

private class javarunoption implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        
        jarunopt = JOptionPane.showInputDialog(null, "Enter javarun option", "Enter javarun option", JOptionPane.QUESTION_MESSAGE);

    }
}


private class changecompiler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel panel = new JPanel();
        JRadioButton lex = new JRadioButton("External compiler");
        JRadioButton normal = new JRadioButton("IDE compiler");
	panel.add(lex);
	panel.add(normal);
	JOptionPane.showOptionDialog(null, panel,"Radio Test", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
	if(lex.isSelected())
	{
	  compiler=1;
	}
	else
	{
	  compiler=0;
	}
    }
}

/*this function for selecting workign directory*/
private class workingdir implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        File yolofile;
        fileChooing = new JFileChooser();
        fileChooing.setCurrentDirectory(filenametwo);
        fileChooing.setDialogTitle("choosertitle");
	fileChooing.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	fileChooing.setAcceptAllFileFilterUsed(false);
        
        if (fileChooing.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          yolobeastfile=fileChooing.getSelectedFile().getPath();
	} else {
	  yolobeastfile="./";
	}
    }
}
/*actionlistner for about*/
private class abt implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog(null, "Paramether manager Sathi.Ranganthan 0757619");

    }
}

/*action listner for how to use*/
private class hell implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

       //JLabel labelone = new JLabel("<html> How to use the program : <BR><BR> "
             //   + "</html>");

        
         JOptionPane.showMessageDialog(null,  "<html>how to run <br><br>"
         +"<html> 1. open a file to run (.config) file <br><br>"
         +"<html> 2.compile to compile the file<br><br>"
         +"<html> 3.compile and run inorder to see the result of the file <br><br> "
         +"<html> 4.Exit <br><br>");
         
        
    }
}

/*action listner to quit*/
private class quit implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (status.equals("modified")) {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to save this file and quit?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                savemyass();
                System.exit(0);
            } else if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else {
                status = "modified";
                scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
                statusLabel.setText("Current Project: " + nameoffile + "[" + status + "]");
            }
        } else {
            System.exit(0);
        }

    }
}

/*to open a new file 
 * this function also checks for if its needs to save or not*/
private class newuntitled implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String data = message.getText().trim();
        if (status.equals("modified")) {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to save this file?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                message.setText(null);
                savemyass();
                nameoffile = "undefined";
                status = "";
                scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
                statusLabel.setText("Current Project: " + nameoffile + status);
            } else if (result == JOptionPane.NO_OPTION) {
                message.setText(null);
                nameoffile = "undefined";
                status = "";
                scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
                statusLabel.setText("Current Project: " + nameoffile + status);
            } else {
                status = "modified";
                statusLabel.setText("Current Project: " + nameoffile + "[" + status + "]");
            }
        } else {
            nameoffile = "undefined";
            message.setText(null);
            status = "";
            scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
            statusLabel.setText("Current Project: " + nameoffile + status);
        }

    }
}


/*this function will let you open
 * a file*/
private void openthis() {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(filenametwo);
        int openedval = chooser.showOpenDialog(null);
        if (openedval == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile();
            nameoffile = filename.getName();
            try {

                Scanner open = new Scanner(chooser.getSelectedFile());
                String check = "";

                while (open.hasNext()) {
                    check = check + open.nextLine() + "\n";
                }
                message.setText(check);

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "File not found");

            }
            statusLabel.setText("Current Project: " + nameoffile);
            scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
            status = "";
        }

    }

   /*this is for saveing a file
    * it will open a file chooser*/
    private void savemyass() {
        String checkext = "";
        int i;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(filenametwo);
        int openedvalue = chooser.showSaveDialog(null);

        if (openedvalue == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile();
            nameoffile = filename.getName();
            i = nameoffile.lastIndexOf(".");
            if (i > 0) {
            checkext = nameoffile.substring(i);
            }
            if(checkext.equals(".config") || checkext.equals(" "))
            {
            try {
				PrintWriter filechoosen = new PrintWriter(chooser.getSelectedFile());
				filechoosen.print(message.getText());
				filechoosen.close();
				
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "File not found");
			}
			statusLabel.setText("Current Project: " + nameoffile);
            scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
            status = "";
			}
			else
			{
				 JOptionPane.showMessageDialog(null, "You have to save with .config at the end");
			}
            
        }
    }

/*this will call the savemyass function lol*/
private class saveass implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        savemyass();

	}
}

/*when opening a new file it will sak for you to save it or not*/
private class open implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (status.equals("modified")) {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to save and open a differnt one?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                savemyass();
                openthis();
            } else if (result == JOptionPane.NO_OPTION) {
                openthis();
            } else {
                status = "modified";
                scrol.setBorder(BorderFactory.createTitledBorder(nameoffile));
                statusLabel.setText("Current Project: " + nameoffile + "[" + status + "]");
            }
        } else {
            openthis();
        }

    }
}

/*input exception handler*/
public class noinputexception extends Exception {

    public noinputexception() throws noinputexception {
        super();

    }
}
}
