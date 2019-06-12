package client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UIPubChat extends JFrame{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension size = new Dimension();
	Point loc = new Point();
	
	//이 아래로 컴포넌트 객체 정의
	JPanel contentPane;
	JLabel titlel;
	JLabel userListl;
	ArrayList<String> userListTmp;
	Vector<String> userVec;
	JList<String> userList;
	JScrollPane userScroll;
	JScrollPane textScroll;
	JTextArea texta;
	JTextField chatf;
	JButton sendb;
	String user;
	
	private void userListInit() {
		userListTmp = new ArrayList<String>();
		for(int i = 0; i < 10; i++) {
			userListTmp.add("테스트유저" + i);
		}
		userVec = new Vector<String>();
		for(int i = 0; i < userListTmp.size(); i++) {
			userVec.addElement(userListTmp.get(i));
		}
		userList = new JList<String>(userVec);
		userScroll = new JScrollPane(userList);
	}
	
	
	private void init() {
		Container con = this.getContentPane();
		con.setLayout(null);
		texta = new JTextArea();
		texta.setEditable(false);
		textScroll = new JScrollPane(texta);
		chatf = new JTextField();
		sendb = new JButton("보내기");
		textScroll.setBounds(0, 0, 450, 350);
		chatf.setBounds(0, 350, 370, 20);
		sendb.setBounds(370, 350, 80, 20);
		userListInit();
		userListl = new JLabel("참여중인 사용자");
		userListl.setBounds(450, 0, 100, 20);
		userScroll.setBounds(450, 20, 150, 350);
		con.add(textScroll);
		con.add(chatf);
		con.add(sendb);
		con.add(userListl);
		con.add(userScroll);
		user = "임시사용자";
	}
	
	private void eventInit() {
		chatf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				if(chatf.getText().length() > 30) ke.consume();	//입력 글자수 제한
			}
		});
		chatf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!chatf.getText().equals("")) {
						texta.append(chatf.getText() + "\n");
						chatf.setText("");
						texta.setCaretPosition(texta.getDocument().getLength());
					}
				}
			}
		});
		sendb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!chatf.getText().equals("")) {
					texta.append(chatf.getText() + "\n");
					chatf.setText("");
					texta.setCaretPosition(texta.getDocument().getLength());
				}
			}
		});
	}
	
	public UIPubChat() {
		setTitle("임시제목");
		size.width = 600;
		size.height = 400;
		setSize(size);
		loc.x = screenSize.width/2 - getWidth() / 2;
		loc.y = screenSize.height/2 - getHeight() / 2;
		setLocation(loc);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel(); //작업 패널
		this.setContentPane(contentPane); //프레임에 패널추가
		init(); //컴포넌트를 초기화 하는 메서드
		eventInit();	//이벤트를 등록하는 메서드
	}
	
	public static void main(String[] args) {
		UIPubChat f = new UIPubChat();
		f.setVisible(true);
	}
	
}