package jankenApp;

import java.util.Random;

// じゃんけんの手
// 列挙型で作成
public enum Hands {
	Rock("グー", 0),
	Scissorrs("チョキ", 0),
	Paper("パー", 0);
	
	private final String display;
	private final int number;
	
//	コンストラクタ
	Hands(String display, int number){
		this.display = display;
		this.number = number;
	}
	
	public static Hands getRandomHand() {
		Random rand = new Random();
		return Hands.values()[rand.nextInt(3)];
	}
}
