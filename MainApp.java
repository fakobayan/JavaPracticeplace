package jp.co.adlobe.sevensegment;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jp.com.adlobe.sevensegment.segment.SevenSegment;

public class MainApp extends JFrame {
	//フィールド**********************************************************
	private JPanel contentPane;
	private SevenSegment sevenSegment_youScore;
	private SevenSegment sevenSegment_youScore1;
	private SevenSegment sevenSegment_cpuScore;
	private SevenSegment sevenSegment_cpuScore1;//セグメント
	private JButton resetButton;//リセットボタン
	private JButton jankenRockButton_1;//グーボタン
	private JButton jankenScicsorsButton_2;//チョキボタン
	private JButton jankenpaperButton_3;//パーボタン
	private JLabel judgementWinOrLose;//勝敗の表記
	private JLabel yourChoice;//自分の選択した手
	private JLabel cpuChoice;//cpuの選択した手
	private JLabel yourName;//あなたの名前
	private JLabel cpuName;//cpuの名前
	private JLabel startWords;//ゲーム開始時の文面
	private JLabel reasultScore;
	private JButton stopResultButton;
	private JLabel cpuTalk;
	private JLabel jankenCalling;

	int myWinScoreTenDigits = 0;//自分十桁の表記
	int myWinScoreOneDigits = 0;//自分一桁の表記
	int cpuWinScoreTenDigits = 0;//CPU十桁の表記
	int cpuWinScoreOneDigits = 0;//CPU一桁の表記

	final int ROCK_HAND = 0;//グー
	final int SCICSORS_HAND = 1;//チョキ
	final int PAPER_HAND = 2;//パー

	int myHands;
	int cpuHands;

	//メソッド**********************************************************
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		setTitle("JANKEN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		sevenSegment_youScore = new SevenSegment();
		sevenSegment_youScore.setBounds(12, 36, 91, 144);
		contentPane.add(sevenSegment_youScore);

		sevenSegment_youScore1 = new SevenSegment();
		sevenSegment_youScore1.setBounds(115, 36, 91, 144);
		contentPane.add(sevenSegment_youScore1);

		resetButton = new JButton("リセット");
		//リセットボタンの処理。カウントした数やせりふをリセットする
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ランダムでリスタート時にセリフが変わる
				int rndRestartIndexes = new java.util.Random().nextInt(10);
				String[] messages = { 
						 "まだだ、まだおわらんよ！",
						"モウイチド、ゲームシマス", 
						"次は負けませんわ！", 
						 "次は勝ちますわぁ", "もう一度勝負…ってコト!?" };

				//セグメントを0でリセット。
				myWinScoreOneDigits = 0;
				cpuWinScoreOneDigits = 0;
				cpuWinScoreTenDigits = 0;
				myWinScoreTenDigits = 0;

				sevenSegment_youScore.display(myWinScoreTenDigits);
				sevenSegment_youScore1.display(myWinScoreOneDigits);
				sevenSegment_cpuScore.display(cpuWinScoreTenDigits);
				sevenSegment_cpuScore1.display(cpuWinScoreOneDigits);


				//コメントをリセット
				cpuTalk.setText("CPU「" + messages[rndRestartIndexes] + "」");
				judgementWinOrLose.setText("");
				yourChoice.setText("");
				cpuChoice.setText("");
				reasultScore.setText("");

			}
		});
		//とりあえずグーの中に処理を全部書いてテスト、そのあと可能ならメソッド化
		resetButton.setBounds(590, 410, 91, 21);
		contentPane.add(resetButton);
		jankenRockButton_1 = new JButton("グー");
		jankenRockButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				 myHands = ROCK_HAND;
				yourChoice.setText("私「ぐー」");

				//グー

				 cpuHands = new java.util.Random().nextInt(3);
				//CPU側が出す手(ランダムに1～3を生成)

				if (cpuHands == SCICSORS_HAND) {
					myWinScoreOneDigits++;

					if (myWinScoreOneDigits == 10) {
						myWinScoreOneDigits = 0;
						myWinScoreTenDigits++;

					}
					//勝ったら得点を1追加10点目になったら隣の桁に+1をする

					sevenSegment_youScore.display(myWinScoreTenDigits);
					sevenSegment_youScore1.display(myWinScoreOneDigits);
					cpuChoice.setText("CPU「ちょき」");
					judgementWinOrLose.setText("あなたの勝ち！");

					//自分の勝ち　
				} else if (cpuHands == PAPER_HAND) {
					cpuWinScoreOneDigits++;

					if (cpuWinScoreOneDigits == 10) {
						cpuWinScoreOneDigits = 0;
						cpuWinScoreTenDigits++;
					}
					sevenSegment_cpuScore.display(cpuWinScoreTenDigits);
					sevenSegment_cpuScore1.display(cpuWinScoreOneDigits);
					cpuChoice.setText("CPU「ぱー」");
					judgementWinOrLose.setText("CPUの勝ち！");

					//自分の負け

				} else {
					cpuChoice.setText("CPU「ぐー」");
					judgementWinOrLose.setText("あいこ！");
					//あいこ

				}

			}
		});
		jankenRockButton_1.setBounds(59, 251, 132, 21);
		contentPane.add(jankenRockButton_1);

		jankenScicsorsButton_2 = new JButton("チョキ");
		jankenScicsorsButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 myHands = SCICSORS_HAND; // チョキ
				yourChoice.setText("私「ちょき」");

				 cpuHands = new java.util.Random().nextInt(3);//CPU側が出す手

				if (cpuHands == PAPER_HAND) {
					myWinScoreOneDigits++;
					if (myWinScoreOneDigits == 10) {
						myWinScoreOneDigits = 0;
						myWinScoreTenDigits++;

					}
					sevenSegment_youScore.display(myWinScoreTenDigits);
					sevenSegment_youScore1.display(myWinScoreOneDigits);
					cpuChoice.setText("CPU「ぱー」");
					judgementWinOrLose.setText("あなたの勝ち！");

					//自分の勝ち　
				} else if (cpuHands == ROCK_HAND) {
					cpuWinScoreOneDigits++;

					if (cpuWinScoreOneDigits == 10) {
						cpuWinScoreOneDigits = 0;
						cpuWinScoreTenDigits++;
					}
					sevenSegment_cpuScore.display(cpuWinScoreTenDigits);
					sevenSegment_cpuScore1.display(cpuWinScoreOneDigits);
					cpuChoice.setText("CPU「ぐー」");
					judgementWinOrLose.setText("CPUの勝ち！");

					//自分の負け

				} else {
					cpuChoice.setText("CPU「ちょき」");
					judgementWinOrLose.setText("あいこ！");

				}

			}
		});
		jankenScicsorsButton_2.setBounds(283, 251, 132, 21);
		contentPane.add(jankenScicsorsButton_2);

		jankenpaperButton_3 = new JButton("パー");
		jankenpaperButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 myHands = PAPER_HAND; //パー
				yourChoice.setText("私「ぱー」");
				 cpuHands = new java.util.Random().nextInt(3);//CPU側が出す手

				if (cpuHands == ROCK_HAND) {
					myWinScoreOneDigits++;
					if (myWinScoreOneDigits == 10) {
						myWinScoreOneDigits = 0;
						myWinScoreTenDigits++;

					}
					sevenSegment_youScore.display(myWinScoreTenDigits);
					sevenSegment_youScore1.display(myWinScoreOneDigits);
					cpuChoice.setText("CPU「ぐー」");
					judgementWinOrLose.setText("あなたの勝ち！");

					//自分の勝ち　
				} else if (cpuHands == SCICSORS_HAND) {
					cpuWinScoreOneDigits++;

					if (cpuWinScoreOneDigits == 10) {
						cpuWinScoreOneDigits = 0;
						cpuWinScoreTenDigits++;
					}
					sevenSegment_cpuScore.display(cpuWinScoreTenDigits);
					sevenSegment_cpuScore1.display(cpuWinScoreOneDigits);
					cpuChoice.setText("CPU「ちょき」");
					judgementWinOrLose.setText("CPUの勝ち！");

					//自分の負け

				} else {
					cpuChoice.setText("CPU「ぱー」");
					judgementWinOrLose.setText("あいこ！");

				}

			}
		});
		jankenpaperButton_3.setBounds(510, 251, 132, 21);
		contentPane.add(jankenpaperButton_3);

		sevenSegment_cpuScore = new SevenSegment();
		sevenSegment_cpuScore.setBounds(489, 36, 91, 144);
		contentPane.add(sevenSegment_cpuScore);

		sevenSegment_cpuScore1 = new SevenSegment();
		sevenSegment_cpuScore1.setBounds(595, 32, 86, 148);
		contentPane.add(sevenSegment_cpuScore1);

		judgementWinOrLose = new JLabel("CPU「いざ尋常に勝負!!」");
		judgementWinOrLose.setHorizontalAlignment(SwingConstants.CENTER);
		judgementWinOrLose.setBounds(174, 72, 343, 65);
		contentPane.add(judgementWinOrLose);

		yourChoice = new JLabel("");
		yourChoice.setHorizontalAlignment(SwingConstants.CENTER);
		yourChoice.setBounds(75, 206, 91, 21);
		contentPane.add(yourChoice);

		cpuChoice = new JLabel("");
		cpuChoice.setHorizontalAlignment(SwingConstants.CENTER);
		cpuChoice.setBounds(518, 206, 91, 21);
		contentPane.add(cpuChoice);

		yourName = new JLabel("YOU");
		yourName.setHorizontalAlignment(SwingConstants.CENTER);
		yourName.setFont(new Font("メイリオ", Font.BOLD, 14));
		yourName.setBounds(75, 13, 50, 13);
		contentPane.add(yourName);

		cpuName = new JLabel("CPU");
		cpuName.setFont(new Font("メイリオ", Font.BOLD, 14));
		cpuName.setHorizontalAlignment(SwingConstants.CENTER);
		cpuName.setBounds(559, 10, 50, 13);
		contentPane.add(cpuName);

		reasultScore = new JLabel("");
		reasultScore.setBounds(12, 294, 680, 44);
		contentPane.add(reasultScore);

	    startWords = new JLabel("CPUと勝負！");
		startWords.setHorizontalAlignment(SwingConstants.CENTER);
		startWords.setBounds(233, -4, 217, 44);
		contentPane.add(startWords);

		stopResultButton = new JButton("ストップ");
		stopResultButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int myTotalScore = (myWinScoreTenDigits * 10) + myWinScoreOneDigits;
				//ここで自分とCPUのセグメントの数字を計算し得点に変えている。
				int cpuTotalScore = (cpuWinScoreTenDigits * 10) + cpuWinScoreOneDigits;

				if (myTotalScore > cpuTotalScore) {
					int rndWinResultIndexes = new java.util.Random().nextInt(12);
					//cpu敗北時セリフ
					String[] winMessages = { "おめでとうございますわ",
							"つ、次こそは！"
							 "悔しいですわ…",
							"ガーーーン", "そんな…このわたくしが…!?", "わァ…あ……", "あなた、お強いですわね",
							"素直に負けを認めますの", };

					reasultScore.setText("勝負、" + myTotalScore + "：" + cpuTotalScore + "であなたの勝ち！");
					cpuTalk.setText("CPU「" + winMessages[rndWinResultIndexes] + "」");

				} else if (myTotalScore < cpuTotalScore) {
					int rndLoseResultIndexes = new java.util.Random().nextInt(13);
					//CPU勝利時セリフ
					String[] loseMessages = { "人間がわたくしに勝てるとでも？", "わたくしの勝ちです！",
							 "おほほほほほほほ",
							"WRYYYYYYY、無駄無駄無駄無駄無駄無駄無駄無駄ァ！", "CPUちゃんの勝利ですわあ～",
						
							"やった！、やりましたわ！", "勝利をこの手にですわ",   };
					reasultScore.setText("勝負、" + myTotalScore + "：" + cpuTotalScore + "であなたの負け！");
					cpuTalk.setText("CPU「" + loseMessages[rndLoseResultIndexes] + "」");

				} else {
					int rndDrawResultIndexes = new java.util.Random().nextInt(5);
					//CPU引き分け時セリフ
					String[] drawMessages = {
							"このわたくしと互角ですわ!?", "とてもエレガントですわ",  "いい勝負ね、気に入りましたわ",
							"やりますわね、人間!!" };
					reasultScore.setText("勝負、" + myTotalScore + "：" + cpuTotalScore + "で引き分け！");

					cpuTalk.setText("CPU「" + drawMessages[rndDrawResultIndexes] + "」");

				}

			}
		});
		stopResultButton.setBounds(470, 410, 91, 21);
		contentPane.add(stopResultButton);

		cpuTalk = new JLabel("");
		cpuTalk.setBounds(12, 348, 680, 58);
		contentPane.add(cpuTalk);

		jankenCalling = new JLabel("じゃ～んけ～ん");
		jankenCalling.setHorizontalAlignment(SwingConstants.CENTER);
		jankenCalling.setBounds(283, 163, 120, 33);
		contentPane.add(jankenCalling);
	}
	//public int myWinScore(int x, int y) {
//	int countTenDigits;
	//	x++;
		//if (x == 10) {
		//	x = 0;
		//	y++;

	//	}
	//	sevenSegment_youScore.display(myWinScoreTenDigits);
		//sevenSegment_youScore1.display(myWinScoreOneDigits);
	//	cpuChoice.setText("CPU「ぱー」");

//	}
//	re
}
