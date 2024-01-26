package enums;

public enum Hands {
	
	Rock("グー, 0"),
	Scissors("チョキ", 0),
	Paper("パー", 2);
	
	private final String display;
	private final int number;
}