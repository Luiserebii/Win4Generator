package win4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Win4Generator {

	private ArrayList<LottoNum> prevLottoNums;
	public static boolean isOrderMatters; //to allow access from settings; considering making class Singleton, with static stuff

	private String latestDate;

	public Win4Generator(){
		prevLottoNums = new ArrayList<LottoNum>();
		isOrderMatters = true;
	}

	public void loadPrevLottoNums(){

		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/winners.txt"));
			String currLine = null;
			while ((currLine = reader.readLine()) != null){
				String d = currLine;
				String[] tempNums = reader.readLine().trim().split("-");
				//Convert String[] to proper int[]... NOTE: Insert as local LottoNum constructor?
				int[] nums = new int[4];
				for(int i = 0; i < tempNums.length; i++){
					nums[i] = Integer.valueOf(tempNums[i]);
				}
				prevLottoNums.add(new LottoNum(d, nums));
				reader.readLine();
			}
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int[] generateLottoNums(){

		int[] lottoNums = generateRandomLottoNums();
		while(checkOldLottoNums(lottoNums)){
			lottoNums = generateRandomLottoNums();
		}
		return lottoNums;
	}

	public boolean checkOldLottoNums(int[] lottoNums){
		boolean isOldLottoNums = false;
		if(!isOrderMatters){
			int[] sortedLottoNums = new int[lottoNums.length];
			System.arraycopy(lottoNums, 0, sortedLottoNums, 0, lottoNums.length); //make true copy, otherwise just passing a reference - want to keep unordered for nums to seem random
			Arrays.sort(sortedLottoNums); //sort first to check for true equivalency
			//System.out.println(intArrToString(sortedLottoNums));

			for(int i = 0; i < prevLottoNums.size(); i++){
				int[] oldNumsRef = prevLottoNums.get(i).getLottoNums();
				int[] sortedOldNums = new int[oldNumsRef.length];
				System.arraycopy(oldNumsRef, 0, sortedOldNums, 0, oldNumsRef.length);
				Arrays.sort(sortedOldNums); //same note as above
				//System.out.println(intArrToString(sortedOldNums));
				if(Arrays.equals(sortedLottoNums, sortedOldNums)){
					isOldLottoNums = true;
					//System.out.println("RED FLAG BRUH");
				}
			}
		} else { //for order mattering...
			for(int i = 0; i < prevLottoNums.size(); i++){
				if(Arrays.equals(lottoNums, prevLottoNums.get(i).getLottoNums())){
					isOldLottoNums = true;
				}
			}
		}

		return isOldLottoNums;
	}

	public int[] generateRandomLottoNums(){
		Random r = new Random();
		int[] lottoNums = new int[4];
		for(int i = 0; i < lottoNums.length; i++){
			lottoNums[i] = r.nextInt(10);
		}
		return lottoNums;
	}

	public String intArrToString(int[] intArr){

		String str = "[";
		for(int i = 0; i < intArr.length; i++){
			str = str + intArr[i] + " ";
		}
		str = str.trim() + "]";
		return str;
	}

	public void updateLatestDate(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/winners.txt"));
			String currLine = reader.readLine();
			latestDate = currLine.substring(0, currLine.trim().indexOf(" "));
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLatestDate(){
		return latestDate;
	}

	public String loadLatestDate(){
		updateLatestDate();
		return getLatestDate();
	}

}
