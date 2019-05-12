package Lecture;

public class Display implements CounterObserver {
	public void showQueue(int queueNo) {
		System.out.println("Now serving: " + queueNo);
	}
	
	@Override
	public void newnNumber(int newNumber) {
		showQueue(newNumber);
	}
}

	
