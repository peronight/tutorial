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
	
	//�� �Ʒ��� ������Ʈ ��ü ����
	JPanel contentPane;
	
	private void init() {
		//ContentPane�� �̿��Ͽ� ������Ʈ ���
	}
	
	private void eventInit() {
		//�̺�Ʈ ���
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
		contentPane = new JPanel(); //�۾� �г�
		this.setContentPane(contentPane); //�����ӿ� �г��߰�
		init(); //������Ʈ�� �ʱ�ȭ �ϴ� �޼���
		eventInit();	//�̺�Ʈ�� ����ϴ� �޼���
	}
	
	public static void main(String[] args) {
		UIBase f = new UIBase("����", 400, 500);
		f.setVisible(true);
	}
	
}