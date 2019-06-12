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
	
	//�� �Ʒ��� ������Ʈ ��ü ����
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
		idl = new JLabel("���̵�", SwingConstants.RIGHT);
		passl = new JLabel("��й�ȣ", 4);
		maill = new JLabel("�̸���", 4);
		idf = new TextField(12);
		passf = new TextField(15);
		passf.setEchoChar('*');
		mailf = new TextField();
		dupcheck = new JButton("�ߺ� Ȯ��");
		join = new JButton("����");
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
		dupcheckinfo = new JDialog(this, "�˸�", true);
		dupcheckinfo.setSize(250, 100);
		dupcheckinfo.setLocation(loc);
		dupcheckinfo.setLayout(new FlowLayout());
		dupcheckl = new JLabel();
		dupcheckb = new JButton("Ȯ��");
		dupcheckp = new JPanel(new FlowLayout());
		dupcheckp.add(dupcheckl);
		dupcheckp.add(dupcheckb);
		dupcheckinfo.add(dupcheckl);
		dupcheckinfo.add(dupcheckb);
	}
	
	private void eventInit() {
		idf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				dupchecktmp = 2;	//id �ʵ忡 �ؽ�Ʈ �Է½� id�ߺ�üũ �ٽ� �ϰ� ����
				if(idf.getText().length() > 14) ke.consume();	//�Է� ���ڼ� ����
			}
		});
		passf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				if(passf.getText().length() > 14) ke.consume();	//�Է� ���ڼ� ����
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
						dupcheckl.setText("����� �� �ִ� ���̵� �Դϴ�.");
						dupcheckinfo.setVisible(true);
					}
					if(dupchecktmp == 1) {
						dupcheckl.setText("�̹� ������� ���̵� �Դϴ�.");
						dupcheckinfo.setVisible(true);
					}
				}else {
					dupcheckl.setText("ID�� �ױ��� �̻� �����մϴ�");
					dupcheckinfo.setVisible(true);
				}
			}
		});
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dupchecktmp == 2 || dupchecktmp == 1) {
					dupcheckl.setText("���� ���̵� �ߺ� üũ�� �� �ּ���");
					dupcheckinfo.setVisible(true);
				}else if(passf.getText().equals("")) {
					dupcheckl.setText("��й�ȣ�� �Է��ϼ���");
					dupcheckinfo.setVisible(true);
				}else if(passf.getText().length() < 4) {
					dupcheckl.setText("��й�ȣ�� ���ڸ� �̻� �����մϴ�");
					dupcheckinfo.setVisible(true);
				}else if(mailf.getText().equals("")) {
					dupcheckl.setText("�̸��� �ּҸ� �Է��ϼ���");
					dupcheckinfo.setVisible(true);
				}else {
					int joined = mr.memberJoin(idf.getText(), passf.getText(), mailf.getText());
					if(joined == 0) {
						dupcheckl.setText("���� ����");
						dupcheckb.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								dupcheckinfo.setVisible(false);
								myDispose();
							}
						});
					dupcheckinfo.setVisible(true);
					} else if(joined == 1) {
						dupcheckl.setText("���� ����");
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
		System.out.println("mr����");
		setTitle("ȸ�� ����");
		size.width = 430;
		size.height = 200;
		setSize(size);
		loc.x = screenSize.width/2 - getWidth() / 2;
		loc.y = screenSize.height/2 - getHeight() / 2;
		setLocation(loc);
		setResizable(false);
		contentPane = new JPanel(); //�۾� �г�
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setContentPane(contentPane); //�����ӿ� �г��߰�
		init(); //������Ʈ�� �ʱ�ȭ �ϴ� �޼���
		eventInit();	//�̺�Ʈ�� ����ϴ� �޼���
		setModal(true);
		setVisible(true);
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket tmp = new Socket("127.0.0.1", 5656);
		UIMemberRegist f = new UIMemberRegist(tmp);
	}
	
}