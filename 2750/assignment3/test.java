import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.*;



public class test extends JFrame{
	public static void main(String[] args) {
		test gui = new test();
		gui.setVisible(true);
 	 }
	 public test()
 	{
 		super("compiled_example");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650,600);
		JPanel mainP = new JPanel(new FlowLayout());
		JPanel yoloP = new JPanel();
		JPanel buttP=new JPanel(new FlowLayout());
		JPanel statusbar = new JPanel();
		JPanel statusP =new JPanel(new BorderLayout());
		statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		JLabel stdunt = new JLabel("Student Id:");


		JLabel name_label = new JLabel("name");
		JTextField name_text = new JTextField(40);
		yoloP.add(name_label);
		yoloP.add(name_text);
		JLabel age_label = new JLabel("age");
		JTextField age_text = new JTextField(40);
		yoloP.add(age_label);
		yoloP.add(age_text);
		JLabel gender_label = new JLabel("gender");
		JTextField gender_text = new JTextField(40);
		yoloP.add(gender_label);
		yoloP.add(gender_text);
		JLabel address_label = new JLabel("address");
		JTextField address_text = new JTextField(40);
		yoloP.add(address_label);
		yoloP.add(address_text);


		JButton yoo_button = new JButton("yoo");
		buttP.add(yoo_button);
		JButton sup_button = new JButton("sup");
		buttP.add(sup_button);
		JButton hello_button = new JButton("hello");
		buttP.add(hello_button);
		JButton help_button = new JButton("help");
		buttP.add(help_button);
		JTextArea textarea = new JTextArea(20,40);
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
}
