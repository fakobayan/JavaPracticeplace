package jp.com.adlobe.sevensegment.segment;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SevenSegment extends JPanel {
	private JLabel segA;
	private JLabel segB;
	private JLabel segF;
	private JLabel segG;
	private JLabel segC;
	private JLabel segE;
	private JLabel segD;

	private boolean[][] lightTable = {
			{ true, true, true, true, true, true, false }// 0
			, { false, true, true, false, false, false, false }//1
			, { true, true, false, true, true, false, true }//2
			, { true, true, true, true, false, false, true }//3
			, { false, true, true, false, false, true, true }//4
			, { true, false, true, true, false, true, true }//5
			, { true, false, true, true, true, true, true }//6
			, { true, true, true, false, false, false, false }//7
			, { true, true, true, true, true, true, true }//8
			, { true, true, true, true, false, true, true }//9
	};


	JLabel[] everythingSegs;//segの配列
//	JLabel[] everythingSegs = new JLabel[7]; 明日ここのメソッドを書く

//	JLabel[] everythingSegs = { segA, segB, segC, segD, segE, segF, segG };//segの配列

	final Color ON_COLOR = Color.ORANGE;
	final Color OFF_COLOR = Color.LIGHT_GRAY;

	//ここまでフィールド
	/**
	 * Create the panel.
	 */
	//ここからメソッド
	public SevenSegment() {
		setLayout(null);

		segA = new JLabel("");
		segA.setBackground(Color.ORANGE);
		segA.setOpaque(true);
		segA.setBounds(17, 0, 50, 13);
		add(segA);

		segB = new JLabel("");
		segB.setOpaque(true);
		segB.setBackground(Color.ORANGE);
		segB.setBounds(67, 16, 13, 50);
		add(segB);

		segF = new JLabel("");
		segF.setOpaque(true);
		segF.setBackground(Color.ORANGE);
		segF.setBounds(0, 16, 13, 50);
		add(segF);

		segG = new JLabel("");
		segG.setBackground(Color.ORANGE);
		segG.setOpaque(true);
		segG.setBounds(17, 66, 50, 13);
		add(segG);

		segC = new JLabel("");
		segC.setOpaque(true);
		segC.setBackground(Color.ORANGE);
		segC.setBounds(67, 76, 13, 50);
		add(segC);

		segE = new JLabel("");
		segE.setBackground(Color.ORANGE);
		segE.setOpaque(true);
		segE.setBounds(0, 76, 13, 50);
		add(segE);

		segD = new JLabel("");
		segD.setOpaque(true);
		segD.setBackground(Color.ORANGE);
		segD.setBounds(17, 125, 50, 13);
		add(segD);

	everythingSegs = new JLabel[] { segA, segB, segC, segD, segE, segF, segG };
	}

	//
	public void display(int inputNum) {
		for (int i = 0; i < everythingSegs.length; i++) {
			if (lightTable[inputNum][i] == true) {
				everythingSegs[i].setBackground(ON_COLOR);
			} else {
				everythingSegs[i].setBackground(OFF_COLOR);

			}

		}
	}

}
