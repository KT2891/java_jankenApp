package window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.Hands;
import enums.Status;

public class MainWindow {
	
	private final JFrame frame;
	
	private final JLabel messageLabel;
	
	private final JButton rockButton;
	private final JButton scissorsButton;
	private final JButton paperButton;
	
	private final JButton resetButton;
	
	private Status playState;
	private Hands opponentHand;
	private JTextField winText;
	private JTextField loseText;
	
	private int winCnt = 0;
	private int loseCnt = 0;
	
	public MainWindow() {
		this.frame = new JFrame("じゃんけんゲーム!");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(200, 200, 600, 400);
		
		var pane = this.frame.getContentPane();
		
		var canvas = new JPanel();
		canvas.setLayout(null);
		
		this.messageLabel = new JLabel("じゃーんけーん・・・");
		this.messageLabel.setBounds(20, 20, 400, 30);
		canvas.add(this.messageLabel);
		
		this.rockButton = new JButton(Hands.Rock.getDisplay());
		this.rockButton.setBounds(100, 100, 100, 40);
		this.rockButton.addActionListener((e) -> this.selectHand(Hands.Rock));
		canvas.add(this.rockButton);
		
		this.scissorsButton = new JButton(Hands.Scissors.getDisplay());
		this.scissorsButton.setBounds(250, 100, 100, 40);
		this.scissorsButton.addActionListener((e) -> this.selectHand(Hands.Scissors));
		canvas.add(this.scissorsButton);
		
		this.paperButton = new JButton(Hands.Paper.getDisplay());
		this.paperButton.setBounds(400, 100, 100, 40);
		this.paperButton.addActionListener((e) -> this.selectHand(Hands.Paper));
		canvas.add(this.paperButton);
		
		this.resetButton = new JButton("もう一度遊ぶ");
		this.resetButton.setBounds(250, 200, 100, 40);
		this.resetButton.addActionListener((e) -> this.reset());
		canvas.add(this.resetButton);
		
		pane.add(canvas);
		
		winText = new JTextField(String.format("勝ち : %s", winCnt));
		winText.setBounds(100, 300, 130, 40);
		canvas.add(winText);
		winText.setColumns(10);
		
		loseText = new JTextField(String.format("負け : %s", loseCnt));
		loseText.setColumns(10);
		loseText.setBounds(370, 300, 130, 40);
		canvas.add(loseText);
	}
	
	public void show() {
		this.init();
		this.frame.setVisible(true);
	}
	
	public void init() {
		this.opponentHand = Hands.getRandomHand();
		this.playState = Status.Wait;
	}
	
	public void reset() {
		this.messageLabel.setText("じゃーんけーん・・・");
		this.opponentHand = Hands.getRandomHand();
		this.playState = Status.Wait;
	}
	
	public void update() {
		this.winText.setText(String.format("勝ち : %s", winCnt));
		this.loseText.setText(String.format("負け : %s", loseCnt));
	}
	
	public void selectHand(Hands selected) {
		if (this.playState != Status.Wait) {
			return;
		}
		
		switch ((selected.getNumber() - opponentHand.getNumber() + 3) % 3) {
		case 0:
			this.messageLabel.setText("あーいこーで・・・");
			this.init();
			break;
		case 1:
			this.messageLabel.setText(String.format("相手が出したのは「%s」なのであなたの負けです。", this.opponentHand.getDisplay()));
			this.playState = Status.Done;
			loseCnt ++;
			update();
			break;
		case 2:
			this.messageLabel.setText(String.format("相手が出したのは「%s」なのであなたの勝ちです。", this.opponentHand.getDisplay()));
			this.playState = Status.Done;
			winCnt ++;
			update();
			break;
		}
	}
}