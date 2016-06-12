package debug;

import java.util.Scanner;

import win4.Win4Generator;

public class DebugWin4Generator {

	private Win4Generator wg;
	private Scanner sc;

	public DebugWin4Generator(){
		wg = new Win4Generator();
		sc = new Scanner(System.in);
	}

	public void debugCheckOldLottoNums(){

		System.out.println("Debugging old lotto nums... Type a lotto num, check if it's been used");
		boolean isRunning = true;
		String input;
		while(isRunning){
			input = sc.nextLine();
			try {
				int[] testLottoNums = inputToIntArr(input);
				System.out.println(wg.checkOldLottoNums(testLottoNums));

			} catch (Exception e){
				if(input.equals("quit")){
					isRunning = false;
				} else {
					System.out.println("Error occured - check input (Type \"quit\" to quit)");
				}
			}
		}
	}

	public void debugRandomLottoNums(){

		System.out.println("Debugging random lotto nums... Type in a number, get that many random lotto nums");
		boolean isRunning = true;
		String input;
		while(isRunning){
			input = sc.nextLine();
			try {
				int cnt = Integer.valueOf(input);
				for(int i = 0; i < cnt; i++){
					int[] temp = wg.generateRandomLottoNums();
					System.out.println(intArrToString(temp));
				}
			} catch (Exception e){
				if(input.equals("quit")){
					isRunning = false;
				} else {
					System.out.println("Error occured - check input (Type \"quit\" to quit)");
				}
			}
		}
	}

	public void debugLottoNums(){

		System.out.println("Debugging lotto nums... Type in a number, get that many lotto nums");
		boolean isRunning = true;
		String input;
		while(isRunning){
			input = sc.nextLine();
			try {
				int cnt = Integer.valueOf(input);
				for(int i = 0; i < cnt; i++){
					int[] temp = wg.generateLottoNums();
					System.out.println(intArrToString(temp));
				}
			} catch (Exception e){
				if(input.equals("quit")){
					isRunning = false;
				} else {
					System.out.println("Error occured - check input (Type \"quit\" to quit)");
				}
			}
		}
	}


	public int[] inputToIntArr(String in){

		String[] intArr = in.trim().split(",");
		int[] nums = new int[4];
		for(int i = 0; i < intArr.length; i++){
			nums[i] = Integer.valueOf(intArr[i]);
		}
		return nums;
	}

	public String intArrToString(int[] intArr){

		String str = "[";
		for(int i = 0; i < intArr.length; i++){
			str = str + intArr[i] + " ";
		}
		str = str.trim() + "]";
		return str;
	}

	public void loadStuff(){ //not good practice, just quick testing
		wg.loadPrevLottoNums();
	}
}
