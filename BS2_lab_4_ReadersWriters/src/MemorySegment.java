import java.util.Random;

public class MemorySegment {

	private static MemorySegment _segment = null;
	private Random rand = new Random();

	protected MemorySegment() {
	}

	public void read() {
		System.out.println("Reading...");
		synchronized (this) {
			try {
				Thread.sleep(rand.nextInt(10000));
			} catch (InterruptedException ie) {
			}
		}
		System.out.println("Done reading...");
	}

	public void write() {
		System.out.println("Writing...");
		synchronized (this) {
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException ie) {
			}
		}
		System.out.println("Done writing...");
	}

}
