import java.awt.event.*;

public class A2Listener_P2 implements ActionListener
{
	testP2FieldEdit d;
	public A2Listener_P2(testP2FieldEdit d)	{
		this.d = d;	}
	public void actionPerformed(ActionEvent e)	{
		try
		{
			d.getDClag();
			d.getDClike();
			d.getDCasdf();
			d.getDCstud();
			d.appendToStatusArea ("button pressed");
		}
		catch(Exception tr)
		{
		d.appendToStatusArea ("ERROR");
		}
	}
}
