import java.awt.event.*;

public class A2Listener_P1 implements ActionListener
{
	testP1FieldEdit d;
	public A2Listener_P1(testP1FieldEdit d)	{
		this.d = d;	}
	public void actionPerformed(ActionEvent e)	{
		try
		{
			d.getDCA4();
			d.getDCA3();
			d.getDCA2();
			d.getDCA1();
			d.getDCName();
			d.appendToStatusArea ("button pressed");
		}
		catch(Exception tr)
		{
		d.appendToStatusArea ("ERROR");
		}
	}
}
