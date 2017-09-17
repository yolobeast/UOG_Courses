import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.*;
public class testP1 extends JFrame implements testP1FieldEdit
{
	private JTextField Name_text;
	private JTextField A1_text;
	private JTextField A2_text;
	private JTextField A3_text;
	private JTextField A4_text;
		private JTextArea textarea;


		public static void main(String[] args) {
				testP1 gui = new testP1();
				gui.setVisible(true);
 	 	}

	 	public testP1()
 		{
 				super("testPro1");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(650,600);
				JPanel mainP = new JPanel(new FlowLayout());
				JPanel yoloP = new JPanel();
				JPanel buttP=new JPanel(new FlowLayout());
				JPanel statusbar = new JPanel();
				JPanel statusP =new JPanel(new BorderLayout());
				statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
				JLabel stdunt = new JLabel("Student Id:");

				JLabel Name_label = new JLabel("Name");
				Name_text = new JTextField(40);
				yoloP.add(Name_label);
				yoloP.add(Name_text);
				JLabel A1_label = new JLabel("A1");
				A1_text = new JTextField(40);
				yoloP.add(A1_label);
				yoloP.add(A1_text);
				JLabel A2_label = new JLabel("A2");
				A2_text = new JTextField(40);
				yoloP.add(A2_label);
				yoloP.add(A2_text);
				JLabel A3_label = new JLabel("A3");
				A3_text = new JTextField(40);
				yoloP.add(A3_label);
				yoloP.add(A3_text);
				JLabel A4_label = new JLabel("A4");
				A4_text = new JTextField(40);
				yoloP.add(A4_label);
				yoloP.add(A4_text);

				JButton Add_button = new JButton("Add");
				buttP.add(Add_button);
				JButton Update_button = new JButton("Update");
				buttP.add(Update_button);
				JButton Delete_button = new JButton("Delete");
				buttP.add(Delete_button);
				JButton Query_button = new JButton("Query");
				buttP.add(Query_button);
               			Query_button.addActionListener(new A2Listener_P1(this));
               			Delete_button.addActionListener(new A2Listener_P1(this));
               			Update_button.addActionListener(new A2Listener_P1(this));
               			Add_button.addActionListener(new A2Listener_P1(this));
	        		textarea = new JTextArea(20,40);
				textarea.setEditable(false);
				JScrollPane scroll = new JScrollPane(textarea);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				yoloP.setLayout(new BoxLayout(yoloP,BoxLayout.Y_AXIS));
				mainP.add(yoloP);
				mainP.add(buttP);

				statusbar.setLayout(new BoxLayout(statusbar,BoxLayout.Y_AXIS));
				JLabel  statusLabel = new JLabel();

				statusLabel.setText("status");
				statusbar.add(statusLabel);
				statusbar.add(scroll);
				mainP.add(statusbar);
				add(mainP);
		}


		public String getDCA4() throws IllegalFieldValueException
		{
		try
		{
			Integer.parseInt(A4_text.getText());
		}
		catch(NumberFormatException e)
		{
			throw new IllegalFieldValueException(A4_text.getText());
		}
    		 return(A4_text.getText());
			}
			public void setDCA4(String A4)
			{
    		A4_text.setText(A4);
			}
		public String getDCA3() throws IllegalFieldValueException
		{
		try
		{
			Integer.parseInt(A3_text.getText());
		}
		catch(NumberFormatException e)
		{
			throw new IllegalFieldValueException(A3_text.getText());
		}
    		 return(A3_text.getText());
			}
			public void setDCA3(String A3)
			{
    		A3_text.setText(A3);
			}
		public String getDCA2() throws IllegalFieldValueException
		{
		try
		{
			Integer.parseInt(A2_text.getText());
		}
		catch(NumberFormatException e)
		{
			throw new IllegalFieldValueException(A2_text.getText());
		}
    		 return(A2_text.getText());
			}
			public void setDCA2(String A2)
			{
    		A2_text.setText(A2);
			}
		public String getDCA1() throws IllegalFieldValueException
		{
		try
		{
			Integer.parseInt(A1_text.getText());
		}
		catch(NumberFormatException e)
		{
			throw new IllegalFieldValueException(A1_text.getText());
		}
    		 return(A1_text.getText());
			}
			public void setDCA1(String A1)
			{
    		A1_text.setText(A1);
			}
		public String getDCName() throws IllegalFieldValueException
		{
    		 return(Name_text.getText());
			}
			public void setDCName(String Name)
			{
    		Name_text.setText(Name);
			}
	public void appendToStatusArea(String message)
	{
		textarea.append(message + "\n");
	}
}
