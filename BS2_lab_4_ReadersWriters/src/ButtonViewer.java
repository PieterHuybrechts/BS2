import java.awt.*;
import java.util.Observer;
import java.util.Observable;

public class ButtonViewer extends java.awt.Button implements Observer 
{

    public ButtonViewer(String name, Process p) {
    	super(name + "                       ");
    	setBackground(Color.green);
    	p.addObserver(this);
    }

    public void update(Observable o, Object arg) {
		String state = ((Process)o).getState();
		setLabel((Process)o + ": " + state + " ...");
		if (state.equals("idle"))
			setBackground(Color.green);
		if( state.equals("reading") )
		    setBackground(Color.blue);
		else if( state.equals("writing") )
		    setBackground(Color.red);
		else if (state.equals("wantread"))
		    setBackground(Color.yellow);
		else if (state.equals("wantwrite"))
			setBackground(Color.yellow);
		
    }
}
