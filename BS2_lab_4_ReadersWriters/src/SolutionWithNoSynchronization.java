import java.util.ArrayList;
import java.util.List;

public class SolutionWithNoSynchronization implements MemoryWrapper {

	private MemorySegment _memory = null;
	private List<Process> processes;

	public SolutionWithNoSynchronization() {
		_memory = new MemorySegment();
		processes = new ArrayList<Process>();
	}

	public void read(Process p) {
		p.setState("wantread");
		synchronized (this) {
			processes.add(p);

			while (processes.subList(0, processes.indexOf(p)).stream()
					.anyMatch(pr -> pr.getState().equals("writing") || pr.getState().equals("wantwrite"))) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			p.setState("reading");
		}
		
		_memory.read();

		synchronized (this) {
			p.setState("idle");
			processes.remove(p);
			notifyAll();
		}
	}

	public synchronized void write(Process p) {
		p.setState("wantwrite");
		processes.add(p);

		while (processes.indexOf(p) != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		p.setState("writing");
		_memory.write();
		p.setState("idle");
		processes.remove(p);
		notifyAll();
	}
}
