import java.util.Observable;
import java.util.Random;

public class Process extends Observable implements Runnable {

    private int _id;
    private String _rwPreference;
    private String _state = "idle";
    private static MemoryWrapper _memory;

    public static void setSynchronisationWrapper(MemoryWrapper memory){
    	_memory = memory;
    }

    public Process(int id, String rwPreference){
    	_id = id;
    	_rwPreference = rwPreference;
    }

    public void start(){
    	new Thread(this).start();
    	setState("idle");
    }

    public String getState(){
    	return _state;
    }

    public void setState(String state){
    	_state = state;
    	setChanged();
    	notifyObservers();
    }

    public String toString(){
    	return "Proces " + _id;
    }

    private void read(){
    	_memory.read(this);
    }

    private void write(){
    	_memory.write(this);
    }

    public void run(){
    	Random rand = new Random();
    	while(true){
    		synchronized(this){
    			try{
    				Thread.sleep(rand.nextInt(1000));
    			}catch(InterruptedException ie){
    			}
    		} 
    		if( _rwPreference.equals("reader") ){
    			read();
    		}
    		else if( _rwPreference.equals("writer") ){
    			write();
    		} else {
    			System.out.println("Unvalid process.");
    		}
    	}
    }
    
}
