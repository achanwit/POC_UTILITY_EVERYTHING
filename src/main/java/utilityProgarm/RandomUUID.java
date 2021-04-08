package utilityProgarm;

import java.util.UUID;

public class RandomUUID {

	public RandomUUID() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// creating UUID      
	      UUID uid = new UUID(1, 1);

	      // checking the value of random UUID
	      System.out.println("Random UUID value: "+uid.randomUUID());

	}

}
