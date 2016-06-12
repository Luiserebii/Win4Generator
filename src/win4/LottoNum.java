package win4;

public class LottoNum {

	private String date;
	private int[] lottoNums;

	public LottoNum(String inDate, int[] inLottoNums){
		date = inDate;
		lottoNums = inLottoNums;
	}

	public String getDate(){ return date; }
	public int[] getLottoNums(){ return lottoNums; }

	public void setDate(String inDate){ date = inDate; }
	public void setLottoNums(int[] inLottoNums){ lottoNums = inLottoNums; }
}
