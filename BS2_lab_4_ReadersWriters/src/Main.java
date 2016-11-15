import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends WindowAdapter{
   
   static{
      Process.setSynchronisationWrapper(new SolutionWithNoSynchronization());
//    Process.setSynchronisationWrapper(new SolutionWithSynchronization());
   }
   
   public Main(){
      Process p1 = new Process(1, "reader");
      Process p2 = new Process(2, "writer");
      Process p3 = new Process(3, "writer");
      Process p4 = new Process(4, "writer");
      Process p5 = new Process(5, "reader");
      
      java.awt.Frame f = new java.awt.Frame("Practicum besturingssystemen");
      f.setLayout(new java.awt.FlowLayout());
      f.setSize(700, 800);
      f.add(new ButtonViewer("Proces 1", p1));
      f.add(new ButtonViewer("Proces 2", p2));
      f.add(new ButtonViewer("Proces 3", p3));
      f.add(new ButtonViewer("Proces 4", p4));
      f.add(new ButtonViewer("Proces 5", p5));
      
      f.addWindowListener(this);
      f.pack();
      f.show();
      
      p1.start();
      p2.start();
      p3.start();
      p4.start();
      p5.start();
   }
   
   public void windowClosing(WindowEvent e){
      System.exit(0);
   }
   
   public static void main(String[] args){
      Main m = new Main();
   }
}
