import java.util.Scanner;
import java.util.Random;
class WaterSort 
{
	Character top = null;
	// create constants for colors
	static Character red= new Character('r');
	static Character blue = new Character('b');
	static Character yellow= new Character('g');
	// Bottles declaration
	
	
	public static void showAll( StackAsMyArrayList bottles[])
	{
		for (int i = 0; i<=4; i++)
		 {
			 System.out.println("Bottle "+ i+ ": " + bottles[i]);
		 }
	}

	//Method to collect and validate user input
	public static String InputAndValidate()
	{
		Scanner input = new Scanner(System.in);   //Getting user input
		System.out.print("Enter source bottle number:");
		Integer S_Name = Integer.parseInt(input.nextLine());

		//Expetion handling. Input validation
		while(S_Name>=5)
		{
			System.out.println("Invalid bottle please select a bottle between 0 and 4.");
			System.out.print("Enter source bottle number:");
			S_Name = Integer.parseInt(input.nextLine());
		}

		//Selecting Target bottle
		Scanner input2 = new Scanner(System.in);  //Getting user input
		System.out.print("Enter target bottle number:");
		Integer T_Name = Integer.parseInt(input2.nextLine());

		//Expetion handling. Input validation
		while(T_Name>=5)
		{
			System.out.println("Invalid bottle please select a bottle between 0 and 4.");
			System.out.print("Enter target bottle number:");
			T_Name = Integer.parseInt(input2.nextLine());
		}

		return S_Name+","+T_Name;
	}

	public static boolean solved(StackAsMyArrayList bottles[]) 
	{
		boolean isSolved = false;
		int count = 0;

		for(int i = 0; i<5;i++)
		{
			if(bottles[i].checkStackUniform()==true && bottles[i].getStackSize()==4)
			{
				count++;	//a bottle is filled with the same color we increament count.
			}
			
			//if we counted 3 bottles filled with the same color and there are 3 colors in total then we are done.
			if(count==3)
			{
				isSolved = true;
			}
			else
			{
				isSolved = false;
			}
		}
		return isSolved;
	}

    public static void main(String args[])
    {
		int moves = 0;// number of moves to mix the water
		int source = 0; // number of bottle to pour FROM
		int target = 0; // number of bottle to pour TO
		int max = 4; // total number of items allowed per bottle
		Random randomNum = new Random();

		// Bottles declaration
		StackAsMyArrayList bottles[] = new StackAsMyArrayList[5];
		//You can do this with a for also
		bottles[0] = new StackAsMyArrayList<Character>();
		bottles[1] = new StackAsMyArrayList<Character>();
		bottles[2] = new StackAsMyArrayList<Character>();
		bottles[3] = new StackAsMyArrayList<Character>();
		bottles[4] = new StackAsMyArrayList<Character>();
		 
		//////STRATEGY #1
		while (moves<4) // 4 moves per 3 colors = 12 moves required
        {
          // get source bottle
          target = randomNum.nextInt(max+1);
          while (bottles[target].getStackSize() == 4)// target is full
             {
               target = randomNum.nextInt(max);
             }
          bottles[target].push(blue);
		  target = randomNum.nextInt(max+1);
		  while (bottles[target].getStackSize() == 4)// target is full
            {
        	   target = randomNum.nextInt(max);
			}
          bottles[target].push(red);
		  target = randomNum.nextInt(max+1);
		  while (bottles[target].getStackSize() == 4)// target is full
            {
               target = randomNum.nextInt(max);
            }
          bottles[target].push(yellow);
          
          //increment valid moves
          moves++;
        }
		showAll(bottles);

		//Code for playing the game.
		while(!solved(bottles))
		{
			//Selecting Source bottle
			String parts[] = InputAndValidate().split(","); //Calling the InputAndValidate() and spliting the return of into an array 
			int S_Num = -1,T_Num = -1; //Declairing and initializing variables.

			//Conveting the array parts into integers.
			S_Num = Integer.parseInt(parts[0]);
			T_Num = Integer.parseInt(parts[1]);

			boolean allIsWell = false; //To check if everthing is okay to push and pop.

			//If the target bottle is empty we just push into it.
			if(bottles[T_Num].getStackSize()==0)
			{
				allIsWell = true;
			}
			else if(bottles[S_Num].getStackSize()==0) //If the source bottle is empty allow for different selection.
			{
				while(bottles[S_Num].getStackSize()==0)
				{
					System.out.println("Ivalid selection. Source bottle cannot be empty");
					parts = InputAndValidate().split(","); //Calling the InputAndValidate() and spliting the return of into an array
					S_Num = Integer.parseInt(parts[0]);
					T_Num = Integer.parseInt(parts[1]);
				}
				allIsWell = true;
			}
			else
			{
				//While to make sure that colors match. 
				while(bottles[S_Num].peek()!=bottles[T_Num].peek())
				{
					System.out.println("Ivalid move, Colors dont match"); //Colors must match

					parts = InputAndValidate().split(",");//Calling the InputAndValidate() and spliting the return of into an array
					S_Num = Integer.parseInt(parts[0]);
					T_Num = Integer.parseInt(parts[1]);
				}
					
				allIsWell = true;
			}

			//Checking if 
			if(bottles[T_Num].getStackSize()<4 && allIsWell)
			{
				bottles[T_Num].push(bottles[S_Num].pop());
				
				//While to move all the same colors at once
				while(bottles[S_Num].peek()==bottles[T_Num].peek() && bottles[T_Num].getStackSize()<4)
				{
					bottles[T_Num].push(bottles[S_Num].pop());
				}
			}
			else
			{
				System.out.println("Target bottle is full. Please choose again.");
			}
				
			showAll(bottles);
		}

		System.out.println("Congratulations!!! You solved the puzzle :)");
	}
}

