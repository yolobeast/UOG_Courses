import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.*;
public class testP3 extends JFrame implements testP3FieldEdit
{
	private JTextField F_Name_text;
	private JTextField L_Name_text;
		private JTextArea textarea;


		public static void main(String[] args) {
				testP3 gui = new testP3();
				gui.setVisible(true);
 	 	}

	 	public testP3()
 		{
 				super("testPro3");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(650,600);
				JPanel mainP = new JPanel(new FlowLayout());
				JPanel yoloP = new JPanel();
				JPanel buttP=new JPanel(new FlowLayout());
				JPanel statusbar = new JPanel();
				JPanel statusP =new JPanel(new BorderLayout());
				statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
				JLabel stdunt = new JLabel("Student Id:");

				JLabel F_Name_label = new JLabel("F_Name");
				F_Name_text = new JTextField(40);
				yoloP.add(F_Name_label);
				yoloP.add(F_Name_text);
				JLabel L_Name_label = new JLabel("L_Name");
				L_Name_text = new JTextField(40);
				yoloP.add(L_Name_label);
				yoloP.add(L_Name_text);

				JButton Add_button = new JButton("Add");
				buttP.add(Add_button);
				JButton Clean_button = new JButton("Clean");
				buttP.add(Clean_button);
				JButton Submit_button = new JButton("Submit");
				buttP.add(Submit_button);
               			Submit_button.addActionListener(new A2Listener_P3(this));
               			Clean_button.addActionListener(new A2Listener_P3(this));
               			Add_button.addActionListener(new A2Listener_P3(this));
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


		public String getDCL_Name() throws IllegalFieldValueException
		{
    		 return(L_Name_text.getText());
			}
			public void setDCL_Name(String L_Name)
			{
    		L_Name_text.setText(L_Name);
			}
		public String getDCF_Name() throws IllegalFieldValueException
		{
    		 return(F_Name_text.getText());
			}
			public void setDCF_Name(String F_Name)
			{
    		F_Name_text.setText(F_Name);
			}
	public void appendToStatusArea(String message)
	{
		textarea.append(message + "\n");
	}
}
