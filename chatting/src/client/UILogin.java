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
	//�� �Ʒ��� ������Ʈ ��ü ����
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
		memberLoginl = new JLabel("ȸ�� �α���");
		idl = new JLabel("���̵�");
		passl = new JLabel("��й�ȣ");
		loginb = new JButton("�α���");
		idf = new TextField();
		passf = new TextField();
		passf.setEchoChar('*');
		memberResistl = new JLabel("ȸ�� ����", 2);
		finderl = new JLabel("���̵� / ��й�ȣ ã��", 2);
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
		infod = new JDialog(this, "�˸�", true);
		infod.setLocation(loc);
		infod.setSize(250, 100);
		infod.setLayout(new FlowLayout());
		infol = new JLabel();
		infob = new JButton("Ȯ��");
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
					infol.setText("���̵� �Է��ϼ���");
					infod.setVisible(true);
				}else if(passf.getText().length() == 0) {
					infol.setText("��й�ȣ�� �Է��ϼ���");
					infod.setVisible(true);
				}else {
					Login login = new Login(soc, idf.getText(), passf.getText());
					login.login();
					int r = login.r;
					if(r == 0) {
						infol.setText("���̵� �������� �ʽ��ϴ�");
						infod.setVisible(true);
					}else if(r == 3) {
						infol.setText("��й�ȣ�� Ʋ�Ƚ��ϴ�");
						infod.setVisible(true);
					}else if(r == 2) {	//id, pass ��ġ
						UIMain uimain = new UIMain(soc, idf.getText());
						myDispose();
					}else if(r == 4) {
						infol.setText("�̹� �������� ���̵� �Դϴ�");
						infod.setVisible(true);
					}
				}
				
			}
		});
	}
	
	public UILogin(Socket soc) {
		this.soc = soc;
		setTitle("����");
		size.width = 400;
		size.height = 300;
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

