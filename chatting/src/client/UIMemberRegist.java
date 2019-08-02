package client;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UIMemberRegist extends JDialog{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension size = new Dimension();
	Point loc = new Point();
	Socket soc = null;
	MemberRegist mr = null;
	
	private void myDispose() {
		this.dispose();
	}
	
	//이 아래로 컴포넌트 객체 정의
	JPanel contentPane;
	JLabel idl;
	JLabel passl;
	JLabel maill;
	TextField idf;
	TextField passf;
	TextField mailf;
	JButton dupcheck;
	JButton join;
	JDialog dupcheckinfo;
	int dupchecktmp = 2;
	JLabel dupcheckl;
	JButton dupcheckb;
	JPanel dupcheckp;
	
	private void init() {
		idl = new JLabel("아이디", SwingConstants.RIGHT);
		passl = new JLabel("비밀번호", 4);
		maill = new JLabel("이메일", 4);
		idf = new TextField(12);
		passf = new TextField(15);
		passf.setEchoChar('*');
		mailf = new TextField();
		dupcheck = new JButton("중복 확인");
		join = new JButton("가입");
		Container con = this.getContentPane();
		con.setLayout(null);
		idl.setBounds(20, 50, 60, 20);
		passl.setBounds(20, 70, 60, 20);
		maill.setBounds(20, 90, 60, 20);
		idf.setBounds(90, 50, 200, 20);
		passf.setBounds(90, 70, 200, 20);
		mailf.setBounds(90, 90, 200, 20);
		dupcheck.setBounds(300, 50, 90, 20);
		join.setBounds(300, 90, 90, 20);
		con.add(idl);
		con.add(passl);
		con.add(maill);
		con.add(idf);
		con.add(passf);
		con.add(mailf);
		con.add(dupcheck);
		con.add(join);
		dupcheckinfo = new JDialog(this, "알림", true);
		dupcheckinfo.setSize(250, 100);
		dupcheckinfo.setLocation(loc);
		dupcheckinfo.setLayout(new FlowLayout());
		dupcheckl = new JLabel();
		dupcheckb = new JButton("확인");
		dupcheckp = new JPanel(new FlowLayout());
		dupcheckp.add(dupcheckl);
		dupcheckp.add(dupcheckb);
		dupcheckinfo.add(dupcheckl);
		dupcheckinfo.add(dupcheckb);
	}
	
	private void eventInit() {
		idf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				dupchecktmp = 2;	//id 필드에 텍스트 입력시 id중복체크 다시 하게 만듦
				if(idf.getText().length() > 14) ke.consume();	//입력 글자수 제한
			}
		});
		passf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				if(passf.getText().length() > 14) ke.consume();	//입력 글자수 제한
			}
		});
		dupcheckb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dupcheckinfo.setVisible(false);
			}
		});
		dupcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idf.getText().length() > 3) {
					dupchecktmp = mr.dupCheck(idf.getText());
					if(dupchecktmp == 0) {
						dupcheckl.setText("사용할 수 있는 아이디 입니다.");
						dupcheckinfo.setVisible(true);
					}
					if(dupchecktmp == 1) {
						dupcheckl.setText("이미 사용중인 아이디 입니다.");
						dupcheckinfo.setVisible(true);
					}
				}else {
					dupcheckl.setText("ID는 네글자 이상 가능합니다");
					dupcheckinfo.setVisible(true);
				}
			}
		});
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dupchecktmp == 2 || dupchecktmp == 1) {
					dupcheckl.setText("먼저 아이디 중복 체크를 해 주세요");
					dupcheckinfo.setVisible(true);
				}else if(passf.getText().equals("")) {
					dupcheckl.setText("비밀번호를 입력하세요");
					dupcheckinfo.setVisible(true);
				}else if(passf.getText().length() < 4) {
					dupcheckl.setText("비밀번호는 네자리 이상 가능합니다");
					dupcheckinfo.setVisible(true);
				}else if(mailf.getText().equals("")) {
					dupcheckl.setText("이메일 주소를 입력하세요");
					dupcheckinfo.setVisible(true);
				}else {
					int joined = mr.memberJoin(idf.getText(), passf.getText(), mailf.getText());
					if(joined == 0) {
						dupcheckl.setText("가입 성공");
						dupcheckb.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								dupcheckinfo.setVisible(false);
								myDispose();
							}
						});
					dupcheckinfo.setVisible(true);
					} else if(joined == 1) {
						dupcheckl.setText("가입 실패");
						dupcheckb.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								dupcheckinfo.setVisible(false);
								myDispose();
							}
						});
					}
				}
			}
		});
	}
	
	public UIMemberRegist(Socket soc) {
		this.soc = soc;
		mr = new MemberRegist(soc);
		System.out.println("mr생성");
		setTitle("회원 가입");
		size.width = 430;
		size.height = 200;
		setSize(size);
		loc.x = screenSize.width/2 - getWidth() / 2;
		loc.y = screenSize.height/2 - getHeight() / 2;
		setLocation(loc);
		setResizable(false);
		contentPane = new JPanel(); //작업 패널
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setContentPane(contentPane); //프레임에 패널추가
		init(); //컴포넌트를 초기화 하는 메서드
		eventInit();	//이벤트를 등록하는 메서드
		setModal(true);
		setVisible(true);
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket tmp = new Socket("127.0.0.1", 5656);
		UIMemberRegist f = new UIMemberRegist(tmp);
	}
	
}