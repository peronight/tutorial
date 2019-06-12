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
	
	//�� �Ʒ��� ������Ʈ ��ü ����
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
		titlel = new JLabel("��ȭ�� �̸�");
		titlef = new TextField();
		maxl = new JLabel("�ִ� �ο�");
		titlel.setBounds(20, 20, 80, 20);
		titlef.setBounds(100, 20, 200, 20);
		maxl.setBounds(320, 20, 60, 20);
		con.add(titlel);
		con.add(titlef);
		con.add(maxl);
		passc = new JCheckBox("�������");
		passc.setHorizontalTextPosition(SwingConstants.LEFT);
		passf = new TextField();
		passf.setEchoChar('*');
		passf.setEnabled(false);
		maxn = new Integer[9];
		for(int i = 0; i < maxn.length; i++) {
			maxn[i] = i + 2;
		}
		maxbox = new JComboBox<Integer>(maxn);
		createb = new JButton("�����");
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
		setTitle("��ȭ�� �����");
		size.width = 420;
		size.height = 150;
		setSize(size);
		loc.x = screenSize.width/2 - getWidth() / 2;
		loc.y = screenSize.height/2 - getHeight() / 2;
		setLocation(loc);
		setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel(); //�۾� �г�
		this.setContentPane(contentPane); //�����ӿ� �г��߰�
		init(); //������Ʈ�� �ʱ�ȭ �ϴ� �޼���
		eventInit();	//�̺�Ʈ�� ����ϴ� �޼���
		setModal(true);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		UICreateRoom f = new UICreateRoom();
		f.setVisible(true);
	}
	
}