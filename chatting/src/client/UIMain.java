package client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UIMain extends JFrame implements Runnable{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension size = new Dimension();
	Point loc = new Point();
	Socket soc = null;
	String id = null;
	Main2 main2 = null;
	
	JPanel contentPane;
	JMenuBar mb = new JMenuBar();
	JMenu findUser = new JMenu("유저 찾기");
	JMenu friendList = new JMenu("친구 목록");
	JMenu exit = new JMenu("프로그램 종료");
	JLabel roomListl;
	ArrayList<String> roomListTmp;
	Vector<String> roomVec;
	JList<String> roomList;
	JScrollPane roomScroll;
	JLabel userListl;
	ArrayList<String> userListTmp;
	Vector<String> userVec;
	JList<String> userList;
	JScrollPane userScroll;
	JButton newRoomb;
	JButton joinRoomb;
	
	private void roomListInit() {
		roomListTmp = main2.roomList;
		roomVec = new Vector<String>();
		for(int i = 0; i < roomListTmp.size(); i++) {
			roomVec.addElement(roomListTmp.get(i));
		}
		roomList = new JList<String>(roomVec);
		roomScroll = new JScrollPane(roomList);
//		roomScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}
	
	private void userListInit() {
		userListTmp = main2.logList;
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
		mb.add(findUser);
		mb.add(friendList);
		mb.add(exit);
		setJMenuBar(mb);
		roomListl = new JLabel("생성된 대화방 목록");
		roomListl.setBounds(0, 30, 150, 20);
		roomListInit();
		roomScroll.setBounds(0, 50, 500, 300);
		con.add(roomScroll);
		con.add(roomListl);
		userListl = new JLabel("접속중인 유저");
		userListl.setBounds(600, 30, 150, 20);
		userListInit();
		userScroll.setBounds(600, 50, 195, 300);
		con.add(userListl);
		con.add(userScroll);
		newRoomb = new JButton("대화방 만들기");
		joinRoomb = new JButton("대화방 참가");
		joinRoomb.setBounds(20, 370, 150, 50);
		newRoomb.setBounds(190, 370, 150, 50);
		con.add(joinRoomb);
		con.add(newRoomb);
	}
	
	private void eventInit() {
		newRoomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog cr = new UICreateRoom();
			}
		});
	}
	
	public UIMain(Socket soc, String id) {
		this.soc = soc;
		this.id = id;
		main2 = new Main2(soc, id);
		setTitle(id + "님 환영합니다!");
		size.width = 800;
		size.height = 500;
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
		Thread tr = new Thread(this);
		tr.start();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
	}

	@Override
	public synchronized void run() {
		while(true) {
			userListInit();
			roomListInit();
			try {
				System.out.println("list refresh");
				wait(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}