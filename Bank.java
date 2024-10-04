import java.util.ArrayList;
import java.util.Collections;  // Import the Collections class
import java.util.Scanner;  // Import the Scanner class

public class Bank{

	public static ArrayList<Account> accList = new ArrayList<Account>(); // ArrayList objact to store all bank accounts

	public static Account createAccount(){
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		// Create Account
		System.out.println("> OK! Let's create a new account for you!\n");

		System.out.print("> Your Name: ");
		String accName = myObj.nextLine();  // Read account name

		System.out.print("> Your NIC: ");
		int accNo = myObj.nextInt();  // Read account no
		myObj.nextLine();  // Consume leftover newline

		System.out.print("> Your Account Type (Savings - 1 | Current - 2 | F/D - 3): ");
		int accTypeNo = myObj.nextInt();  // Read account type no
		myObj.nextLine();  // Consume leftover newline

		String accType; // var to store acc type (string)

		switch (accTypeNo) {
			case 1:
				// savings
				accType = "Savings Account";
				break;
			case 2:
				// current
				accType = "Current Account";
				break;
			default:
				// f/d
				accType = "F/D Account";
				break;
		}

		System.out.print("> Deposit Amount: ");
		int accAmount = myObj.nextInt();  // Read cash amount
		myObj.nextLine();  // Consume leftover newline

		System.out.println();

		// Create new account
		Account a1 = new Account(accNo, accName, accType, accAmount);
		a1.displayAllDetails();
		System.out.println();
		System.out.println("Thank you! Come again later!\n");
		return a1;
	}

	public static int[] depositCash(){
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		System.out.println("> OK! Let's deposit cash to ur account!\n");

		System.out.print("> Your Account No: ");
		int accNo = myObj.nextInt(); // Read acc no

		System.out.print("> Deposit Amount: ");
		int cashAmount = myObj.nextInt(); // Read cash amount

		int[] retArray = {accNo, cashAmount};

		return retArray;
	}

	public static int[] withdrawCash(){
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		System.out.println("> OK! Let's withdraw cash from ur account!\n");

		System.out.print("> Your Account No: ");
		int accNo = myObj.nextInt(); // Read acc no

		System.out.print("> Withdraw Amount: ");
		int cashAmount = myObj.nextInt(); // Read cash amount

		int[] retArray = {accNo, cashAmount};

		return retArray;
	}

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		// create seperator lines
		SepLine mainSep = new SepLine("=", 30);
		SepLine subSep = new SepLine("-", 30);

		// draw a main seperator line
		mainSep.drawLine();

		// Bank Name & sep
		System.out.println("	   UNC BANK");
		subSep.drawLine();
		
		initiateConversation(); // works recursively

		mainSep.drawLine(); // end of the line
	}

	public static void initiateConversation(){
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		SepLine subSep = new SepLine("-", 30);

		// welcome text & sep
		System.out.println("\n> Hello customer! What can I do for you today?\n");
		System.out.println("> Create Account: 	1");
		System.out.println("> Deposit Cash: 	2");
		System.out.println("> Withdraw Cash: 	3");
		System.out.println("> View Balance: 	4");
		System.out.println("> Exit: 		5\n");
		System.out.print("> ");

		int taskNo = myObj.nextInt(); // Read task no
		System.out.println();

		switch(taskNo){
			case 1:
				// create account method (returns an 'Account' object. Catch it & add to 'accList')
				accList.add(createAccount());
				subSep.drawLine();
				/*// This works!! Accounts gets added to the 'accList'!!
				for (Account i : accList) {
					System.out.println(i.accAmount);
				}
				*/
				initiateConversation();
				break;
			case 2:
				if(accList.size() > 0){
					//Deposit Cash
					// This works!! 'depositCash()' returns an int[] which contains 'accNo' & 'cashAmount'!!
					int[] catchDArray = depositCash();
					//System.out.println(catchDArray[0] + " " + catchDArray[1]);
					//System.out.println(accList.size());

					for(Account i : accList){ // loop through 'accList' to find the account by 'accNo'
						//System.out.println(i.accNo);
						if(i.accNo == catchDArray[0]){
							i.accAmount += catchDArray[1]; // add cash to previous amount

							System.out.println();
							subSep.drawLine();

							System.out.println();
							System.out.print("Deposit Made! ");

							i.displayAmount();
							System.out.println();
						}
					}
					subSep.drawLine();

					initiateConversation();
				}else{
					System.out.println("!!!Create an account first!!!");
					initiateConversation();
				}
				break;
			case 3:
				if(accList.size() > 0){
					//Withdraw Cash
					// This works!! 'withdrawCash()' returns an int[] which contains 'accNo' & 'cashAmount'!!
					int[] catchWArray = withdrawCash();
					//System.out.println(catchWArray[0] + " " + catchWArray[1]);
					//System.out.println(accList.size());

					for(Account i : accList){ // loop through 'accList' to find the account by 'accNo'
						//System.out.println(i.accNo);
						if(i.accNo == catchWArray[0]){
							i.accAmount -= catchWArray[1]; // minus cash from previous amount

							System.out.println();
							subSep.drawLine();

							System.out.println();
							System.out.print("Winthdraw Made! ");

							i.displayAmount();
							System.out.println();
						}
					}
					subSep.drawLine();

					initiateConversation();
				}else{
					System.out.println("!!!Create an account first!!!");
					initiateConversation();
				}
				break;
			case 4:
				//View Balance
				if(accList.size() > 0){
					System.out.print("> Your Account No: ");
					int accNo1 = myObj.nextInt(); // Read acc no
					System.out.println();
					
					for(Account i : accList){ // loop through 'accList' to find the account by 'accNo'
						if(i.accNo == accNo1){
							subSep.drawLine();
							System.out.println();
							System.out.print("Data Recieved! ");
							i.displayAmount();
							System.out.println();
						}
					}
					subSep.drawLine();
					initiateConversation();
				}else{
					System.out.println("!!!Create an account first!!!");
					initiateConversation();
				}
				break;
			default:
				// exit app
				System.out.println("Goodbye!");
				break;
		}
	}
}
class Account{
	public int accNo;
	public String accName;
	public String accType;
	public int accAmount;

	public Account(int accNo, String accName, String accType, int accAmount){
		this.accNo = accNo;
		this.accName = accName;
		this.accType = accType;
		this.accAmount = accAmount;
	}

	public void displayAmount(){
		System.out.println("> Amount: $" + this.accAmount);
	}

	public void displayName(){
		System.out.println("> Customer Name: " + this.accName);
	}

	public void displayType(){
		System.out.println("> Account Type: " + this.accType);
	}

	public void displayNo(){
		System.out.println("> Account No: " + this.accNo);
	}

	public void displayAllDetails(){
		SepLine subSep = new SepLine("-", 30);
		subSep.drawLine();

		System.out.println("> Account No: " + this.accNo);
		System.out.println("> Customer Name: " + this.accName);
		System.out.println("> Account Type: " + this.accType);
		System.out.println("> Amount: $" + this.accAmount);

		subSep.drawLine();
	}
}

class SepLine{
	private String lineType;
	private int lineLength;

	public SepLine(String lineType, int lineLength){
		this.lineType = lineType;
		this.lineLength = lineLength;
	}

	public void drawLine(){
		if(lineType == "="){
			for (int i = 0; i < lineLength; i++) {
				System.out.print("=");
			}
			System.out.print("\n");
		}else{
			for (int i = 0; i < lineLength; i++) {
				System.out.print("-");
			}
			System.out.print("\n");
		}
	}
}