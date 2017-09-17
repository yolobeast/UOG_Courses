import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.*;
public class testP2 extends JFrame implements testP2FieldEdit
{
	private JTextField stud_text;
	private JTextField asdf_text;
	private JTextField like_text;
	private JTextField lag_text;
		private JTextArea textarea;


		public static void main(String[] args) {
				testP2 gui = new testP2();
				gui.setVisible(true);
 	 	}

	 	public testP2()
 		{
 				super("testPro2");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(650,600);
				JPanel mainP = new JPanel(new FlowLayout());
				JPanel yoloP = new JPanel();
				JPanel buttP=new JPanel(new FlowLayout());
				JPanel statusbar = new JPanel();
				JPanel statusP =new JPanel(new BorderLayout());
				statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
				JLabel stdunt = new JLabel("Student Id:");

				JLabel stud_label = new JLabel("stud");
				stud_text = new JTextField(40);
				yoloP.add(stud_label);
				yoloP.add(stud_text);
				JLabel asdf_label = new JLabel("asdf");
				asdf_text = new JTextField(40);
				yoloP.add(asdf_label);
				yoloP.add(asdf_text);
				JLabel like_label = new JLabel("like");
				like_text = new JTextField(40);
				yoloP.add(like_label);
				yoloP.add(like_text);
				JLabel lag_label = new JLabel("lag");
				lag_text = new JTextField(40);
				yoloP.add(lag_label);
				yoloP.add(lag_text);

				JButton b1_button = new JButton("b1");
				buttP.add(b1_button);
				JButton b2_button = new JButton("b2");
				buttP.add(b2_button);
               			b2_button.addActionListener(new A2Listener_P2(this));
               			b1_button.addActionListener(new A2Listener_P2(this));
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


		public String getDClag() throws IllegalFieldValueException
		{
    		 return(lag_text.getText());
			}
			public void setDClag(String lag)
			{
    		lag_text.setText(lag);
			}
		public String getDClike() throws IllegalFieldValueException
		{
    		 return(like_text.getText());
			}
			public void setDClike(String like)
			{
    		like_text.setText(like);
			}
		public String getDCasdf() throws IllegalFieldValueException
		{
    		 return(asdf_text.getText());
			}
			public void setDCasdf(String asdf)
			{
    		asdf_text.setText(asdf);
			}
		public String getDCstud() throws IllegalFieldValueException
		{
		try
		{
			Integer.parseInt(stud_text.getText());
		}
		catch(NumberFormatException e)
		{
			throw new IllegalFieldValueException(stud_text.getText());
		}
    		 return(stud_text.getText());
			}
			public void setDCstud(String stud)
			{
    		stud_text.setText(stud);
			}
	public void appendToStatusArea(String message)
	{
		textarea.append(message + "\n");
	}
}
