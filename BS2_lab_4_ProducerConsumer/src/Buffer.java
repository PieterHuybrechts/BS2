class Buffer {

	private char[] buffer;
	private int count = 0, in = 0, out = 0;

	Buffer(int size) {
		buffer = new char[size];
	}

	public synchronized void put(char c) {
		while(count == buffer.length){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		System.out.println("Producing " + c + " ...");
		buffer[in] = c;
		in = (in + 1) % buffer.length;
		count++;
		notify();
	}

	public synchronized char get() {
		while (count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		char c = buffer[out];
		out = (out + 1) % buffer.length;
		count--;
		notify();
                 System.out.println("Consuming " + c + " ...");  
                 return c;
            }
      }
