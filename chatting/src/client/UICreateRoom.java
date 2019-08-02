package client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UICreateRoom extends JDialog{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension size = new Dimension();
	Point loc = new Point();
	
	//이 아래로 컴포넌트 객체 정의
	JPanel contentPane;
	JLabel titlel;
	JLabel maxl;
	JComboBox<Integer> maxbox;
	Integer[] maxn;
	TextField titlef;
	JCheckBox passc;
	boolean passb;
	TextField passf;
	JButton createb;
	
	private void init() {
		Container con = this.getContentPane();
		con.setLayout(null);
		titlel = new JLabel("대화방 이름");
		titlef = new TextField();
		maxl = new JLabel("최대 인원");
		titlel.setBounds(20, 20, 80, 20);
		titlef.setBounds(100, 20, 200, 20);
		maxl.setBounds(320, 20, 60, 20);
		con.add(titlel);
		con.add(titlef);
		con.add(maxl);
		passc = new JCheckBox("비공개방");
		passc.setHorizontalTextPosition(SwingConstants.LEFT);
		passf = new TextField();
		passf.setEchoChar('*');
		passf.setEnabled(false);
		maxn = new Integer[9];
		for(int i = 0; i < maxn.length; i++) {
			maxn[i] = i + 2;
		}
		maxbox = new JComboBox<Integer>(maxn);
		createb = new JButton("만들기");
		passc.setBounds(20, 40, 80, 20);
		passf.setBounds(100, 40, 200, 20);
		maxbox.setBounds(320, 40, 60, 20);
		createb.setBounds(320, 70, 80, 40);
		con.add(passc);
		con.add(passf);
		con.add(maxbox);
		con.add(createb);
	}
	
	private void eventInit() {
		passc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passc.isSelected()) {
					passf.setEnabled(true);
				}else {
					passf.setEnabled(false);
				}
			}
		});
	}
	
	public UICreateRoom() {
		setTitle("대화방 만들기");
		size.width = 420;
		size.height = 150;
		setSize(size);
		loc.x = screenSize.width/2 - getWidth() / 2;
		loc.y = screenSize.height/2 - getHeight() / 2;
		setLocation(loc);
		setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel(); //작업 패널
		this.setContentPane(contentPane); //프레임에 패널추가
		init(); //컴포넌트를 초기화 하는 메서드
		eventInit();	//이벤트를 등록하는 메서드
		setModal(true);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		UICreateRoom f = new UICreateRoom();
		f.setVisible(true);
	}
	
}