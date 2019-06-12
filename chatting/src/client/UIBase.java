package client;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UIBase extends JFrame{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension size = new Dimension();
	Point loc = new Point();
	
	//이 아래로 컴포넌트 객체 정의
	JPanel contentPane;
	
	private void init() {
		//ContentPane을 이용하여 컴포넌트 등록
	}
	
	private void eventInit() {
		//이벤트 등록
	}
	
	public UIBase(String t, int w, int h) {
		super(t);
		size.width = w;
		size.height = h;
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
		UIBase f = new UIBase("제목", 400, 500);
		f.setVisible(true);
	}
	
}