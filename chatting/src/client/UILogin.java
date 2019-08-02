package client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UILogin extends JFrame{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension size = new Dimension();
	Point loc = new Point();
	Socket soc = null;
	//이 아래로 컴포넌트 객체 정의
	JPanel contentPane;
	JLabel memberLoginl;
	JLabel idl;
	JLabel passl;
	JLabel memberResistl;
	JLabel finderl;
	TextField idf;
	TextField passf;
	JButton loginb;
	JDialog infod;
	JLabel infol;
	JButton infob;
	
	private void init() {
		memberLoginl = new JLabel("회원 로그인");
		idl = new JLabel("아이디");
		passl = new JLabel("비밀번호");
		loginb = new JButton("로그인");
		idf = new TextField();
		passf = new TextField();
		passf.setEchoChar('*');
		memberResistl = new JLabel("회원 가입", 2);
		finderl = new JLabel("아이디 / 비밀번호 찾기", 2);
		Container con = this.getContentPane();
		con.setLayout(null);
		memberLoginl.setBounds(80, 50, 100, 20);
		con.add(memberLoginl);
		idl.setBounds(20, 90, 50, 20);
		idf.setBounds(80, 90, 200, 20);
		con.add(idl);
		con.add(idf);
		passl.setBounds(15, 110, 60, 20);
		passf.setBounds(80, 110, 200, 20);
		con.add(passl);
		con.add(passf);
		loginb.setBounds(300, 90, 80, 40);
		con.add(loginb);
		memberResistl.setBounds(20, 220, 200, 20);
		finderl.setBounds(20, 240, 200, 20);
		con.add(memberResistl);
		con.add(finderl);
		infod = new JDialog(this, "알림", true);
		infod.setLocation(loc);
		infod.setSize(250, 100);
		infod.setLayout(new FlowLayout());
		infol = new JLabel();
		infob = new JButton("확인");
		infod.add(infol);
		infod.add(infob);
	}
	
	private void eventInit() {
		memberResistl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UIMemberRegist mr = new UIMemberRegist(soc);
			}
		});
		infob.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				infod.setVisible(false);
			}
		});
		loginb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(idf.getText().length() == 0) {
					infol.setText("아이디를 입력하세요");
					infod.setVisible(true);
				}else if(passf.getText().length() == 0) {
					infol.setText("비밀번호를 입력하세요");
					infod.setVisible(true);
				}else {
					Login login = new Login(soc, idf.getText(), passf.getText());
					login.login();
					int r = login.r;
					if(r == 0) {
						infol.setText("아이디가 존재하지 않습니다");
						infod.setVisible(true);
					}else if(r == 3) {
						infol.setText("비밀번호가 틀렸습니다");
						infod.setVisible(true);
					}else if(r == 2) {	//id, pass 일치
						UIMain uimain = new UIMain(soc, idf.getText());
						myDispose();
					}else if(r == 4) {
						infol.setText("이미 접속중인 아이디 입니다");
						infod.setVisible(true);
					}
				}
				
			}
		});
	}
	
	public UILogin(Socket soc) {
		this.soc = soc;
		setTitle("제목");
		size.width = 400;
		size.height = 300;
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
		setVisible(true);
	}
	
	public void myDispose() {
		this.dispose();
	}
	
	public static void main(String[] args) {
		Socket tmp = null;
		UILogin f = new UILogin(tmp);
		f.setVisible(true);
	}
}

