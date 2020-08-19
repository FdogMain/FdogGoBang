package call;

import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
import java.net.URL; 
import java.net.URI;
import java.lang.*;
import java.applet.*;
import java.io.*;
import javazoom.jl.player.*;
class ArrayGame{
	private  int x;
	private  int y;
	private  int true_flase;
	private  boolean Black_White;
	public ArrayGame(int x,int y,int TF,boolean BW){
		this.x=x;
		this.y=y;
		this.true_flase=TF;
		this.Black_White=BW;
	}
    public boolean getBW() {
    	return this.Black_White;
    }
    public void setTF(int TF) {
    	this.true_flase =TF;
    }
    public int getTF() {
    	return this.true_flase;
    }
}
class ArrayLabel{
	private static Icon WhiteIcon = new ImageIcon("E:\\dianmingqi\\call\\lib\\baiqi.png");//����
	private static Icon BlackIcon = new ImageIcon("E:\\dianmingqi\\call\\lib\\heiqi.png");//����
	private  int x;
	private  int y;
	private  boolean Black_White;
	private  JLabel myjlabel =new JLabel();
	public  ArrayLabel(int x,int y ,boolean Black_White) {
		this.x=x;
		this.y=y;
		this.Black_White=Black_White;
		if(Black_White==true) {
			myjlabel.setIcon(WhiteIcon);
		}
		else {
			myjlabel.setIcon(BlackIcon);
		}
		
		myjlabel.setBounds(21+x*54, 20+y*54, 54, 54);
	}
	public void setArrayLabel(int x,int y ,boolean Black_White) {
		this.x=x;
		this.y=y;
		this.Black_White=Black_White;
	}
	public JLabel getLabel() {
		return this.myjlabel;
	}
	public int getX() {
		return this.x; 
	}
	public int getY() {
		return this.y; 
	}
	public boolean getBlack_White() {
		return Black_White;
	}
}
public class gobang extends JFrame implements Runnable {
	private static Player player =null;
	//ÿ����ť������ͼƬ,��һ�Ŵ���δ����״̬,�ڶ��Ŵ�����״̬�������Ӿ���
	private static Icon StartIcon1 =new ImageIcon("E:\\dianmingqi\\call\\lib\\kaishiyouxi.jpg");//��ʼ��Ϸ
	private static Icon StartIcon2 =new ImageIcon("E:\\dianmingqi\\call\\lib\\kaishiyouxi2.jpg");
	private static Icon EndIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\jieshuyouxi.jpg");//������Ϸ
	private static Icon EndIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\jieshuyouxi2.jpg");
	private static Icon PauseIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\zantingyouxi.jpg");//��ͣ��Ϸ
	private static Icon PauseIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\zantingyouxi2.jpg");
	private static Icon PersonIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\renrenduizhan.jpg");//���˶�ս
	private static Icon PersonIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\renrenduizhan2.jpg");
	private static Icon MachineIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\renjiduizhan.jpg");//�˻���ս
	private static Icon MachineIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\renjiduizhan2.jpg");
	private static Icon AboutIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\guanyu.jpg");//����
	private static Icon AboutIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\guanyu2.jpg");
	private static Icon BackIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\huiqi.jpg");//����
	private static Icon BackIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\huiqi2.jpg");
	private static ArrayLabel[] myArrayLabel =new ArrayLabel[200];//�������飬���˳����Ϣ
	private static ArrayGame[][] myArrayGame =new ArrayGame[14][14];//�������飬���˳����Ϣ
	private static JButton Start_end_Button = new JButton(); //��ʼ��Ϸ��ť
	private static JButton Pause_Butten = new JButton();//��ͣ��ť
	private static JButton Person_Person_Button = new JButton();//���˶�ս��ť
	private static JButton Person_Machine_Button = new JButton();//�˻���ս��ť
	private static JButton Back_Button = new JButton();//���尴ť
	private static JButton About_Button = new JButton();//���ڰ�ť
	private static JLabel Countdown_Label = new JLabel();//��ǩ����ʱ
	private static JLabel Now_Label = new JLabel();//��ǩ��ʾ�������һ��
	private static JLabel Show_Label = new JLabel("��ǰģʽ��");//��ǩ��ʾ��ǰģʽ
	private static JTextField Countdown_TextField =new JTextField();//�ı�����ʾ����ʱ
	private static Font font = new Font("��������ڼ���",Font.BOLD,20); //���������С
	private static Font font_2 = new Font("��������ڼ���",Font.BOLD,15); //���������С
	private static JFrame jf=null; //��������
	private static Container c =null;//��������
	private static boolean Combat_TF =false;//�ж��Ƿ�ѡ���սģʽ
	private static boolean Person_Machine_TF =true;//�ж�ѡ���ģʽ����Ϊ����
	private static boolean Showboonum=true; //��Ϊ���ˣ���Ϊ�˻�
	private static volatile boolean boonum=false;//���ñ���Ϊ��ʱ�������壬���Ϊ�٣�����ͣģʽ
	private static boolean White_Black_Label=true;//���úڰ���
	private static volatile int time =0; //���õ���ʱ
	private static volatile int ss =time % 60;
	private static int sum = 0;//��¼����ʹ�����
	
	public boolean getPerson_Person_combat(int x,int y,boolean White_Black) {
		// �õ������ӵ���Ϣ
	    // ˮƽ�������
		// ���������
	   int count_x = 1;
	   int count_y = 1;
	   int count_1 = 1;
	   int count_2 = 1;
		for(int posX =x-1;posX>=0;posX--) {
			if(myArrayGame[posX][y].getBW()==White_Black&&myArrayGame[posX][y].getTF()==1) {
				count_x++;
				if(count_x>=5) {
					return true;
				}
			}
			else {
				break;
			}
		}
		//�����ұ���
		for(int posX =x+1;posX<14;posX++) {
			if(myArrayGame[posX][y].getBW()==White_Black&&myArrayGame[posX][y].getTF()==1) {
				count_x++;
				if(count_x>=5) {
					return true;
				}
			}
			else {
				break;
			}
		}
		
		//��ֱ����
		//�����ϱ���
		 for(int posY = y - 1; posY >= 0; posY--) {
				if(myArrayGame[x][posY].getBW()==White_Black&&myArrayGame[x][posY].getTF()==1) {
					count_y++;
					if(count_y>=5) {
						return true;
					}
				}
				else {
					break;
				}
		 }
		 //���±���
		 for(int posY = y + 1; posY <14; posY++) {
				if(myArrayGame[x][posY].getBW()==White_Black&&myArrayGame[x][posY].getTF()==1) {
					count_y++;
					if(count_y>=5) {
						return true;
					}
				}
				else {
					break;
				}
		 }
		//�����Խ��߱��� 
		//��һ�����ж�����
		 for(int posX = x - 1, posY = y - 1; posX >= 0 && posY >= 0; posX--, posY--) {
			 if(myArrayGame[posX][posY].getBW()==White_Black && myArrayGame[posX][posY].getTF()==1) {
				 count_1++;
				 if(count_1>=5) {
					 return true;
				 }
			 }
			 else {
				 break;
			 }
		 }
		 //�ж�����
		 for(int posX = x + 1, posY = y + 1; posX < 14 && posY < 14; posX++, posY++) {
			 if(myArrayGame[posX][posY].getBW()==White_Black && myArrayGame[posX][posY].getTF()==1) {
				 count_1++;
				 if(count_1>=5) {
					 return true;
				 }
			 }
			 else {
				 break;
			 }
		 }
		//�ڶ���������
		 for(int posX = x + 1, posY = y - 1; posX < 14 && posY >=0; posX++, posY--) {
			 if(myArrayGame[posX][posY].getBW()==White_Black && myArrayGame[posX][posY].getTF()==1) {
				 count_1++;
				 if(count_1>=5) {
					 return true;
				 }
			 }
			 else {
				 break;
			 }
		 }
		//�ж�����
		 for(int posX = x - 1, posY = y + 1; posX >=0 && posY <14; posX--, posY++) {
			 if(myArrayGame[posX][posY].getBW()==White_Black && myArrayGame[posX][posY].getTF()==1) {
				 count_1++;
				 if(count_1>=5) {
					 return true;
				 }
			 }
			 else {
				 break;
			 }
		 }
    return false;
	}
	public void getArratGame() {
		for(int i=0;i<14;i++) {
			for(int j=0;j<14;j++) {
				myArrayGame[i][j]=new ArrayGame(0,0,0,true);
			}
		}
	}
	public  void getwindows() {
		jf = new JFrame("���������� ");
		jf.setIconImage(new ImageIcon("Icon.jpg").getImage());
		ImageIcon img =new ImageIcon("lib\\qipan.jpg");
		jf.setLayout(null);//ʹ����ȡ�����ֹ���������
		JLabel imgLabel = new JLabel(img);
		jf.getLayeredPane().add(imgLabel,new Integer(Integer.MIN_VALUE));
		imgLabel.setSize(1090, 800);
	     c =jf.getContentPane();
		Cursor cursor =Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("E:\\dianmingqi\\call\\lib\\shouzhi.png").getImage(),new Point(0,0), "stick");
        jf.setCursor(cursor);
		((JPanel)c).setOpaque(false);
		Countdown_Label.setFont(font);//��������
		Now_Label.setFont(font);//��������
		Show_Label.setFont(font);//��������
		Countdown_TextField.setFont(font_2);
		Countdown_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Countdown_Label.setText("����ʱ��");
		Now_Label.setHorizontalAlignment(SwingConstants.CENTER);	
		Countdown_TextField.setBackground(Color.orange);
		//���ð�ťλ��
		Start_end_Button.setBounds(870, 150, 143, 47);
		Pause_Butten.setBounds(870, 150, 143, 47);
		Show_Label.setBounds(855,240, 200, 30);
		Now_Label.setBounds(870, 275, 150, 30);
		Countdown_Label.setBounds(870, 310, 90, 30);
		Countdown_TextField.setBounds(950, 310, 60, 26);
		Person_Person_Button.setBounds(870, 370, 143, 47);
		Person_Machine_Button.setBounds(870, 480, 143, 47);
		Back_Button.setBounds(870, 590, 143, 47);
		About_Button.setBounds(870, 700, 143, 47);
		Start_end_Button.setIcon(StartIcon1);
		Pause_Butten.setIcon(PauseIcon1);
		Person_Person_Button.setIcon(PersonIcon1);
		Person_Machine_Button.setIcon(MachineIcon1);
		Back_Button.setIcon(BackIcon1);
		About_Button.setIcon(AboutIcon1);
		c.add(Start_end_Button);
		c.add(Show_Label);
		c.add(Now_Label);
		c.add(Countdown_Label);
		c.add(Countdown_TextField);
		c.add(Person_Person_Button);
		c.add(Person_Machine_Button);
		c.add(Back_Button);
		c.add(About_Button);
		jf.setSize(1100, 840);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	    
	}
	
	public  void getLabelShow(int x,int y ,boolean Black_White,int sum_c) {
		//�����̽���һ����ά����
		//������ʱ����������Ƿ���Ϊ��
		//���������ӷ���һ��ʹ�ú���ȳ�ԭ��
		if(myArrayGame[x][y].getTF()==0) {
		myArrayLabel[sum_c]=new ArrayLabel(x, y, Black_White);
		myArrayGame[x][y] = new ArrayGame(x,y,1,Black_White);
		if(White_Black_Label==true) {
		Now_Label.setText("�ú�������....");
			White_Black_Label=false;
		}
		else {
		Now_Label.setText("�ð�������....");
			White_Black_Label=true;
		}
		c.add(myArrayLabel[sum_c].getLabel());
		getRefreshWindows();
		try {
		    File f = new File("E:\\dianmingqi\\call\\lib\\xiaqi1.mp3");
		    FileInputStream fileInputStream = new FileInputStream(f);
		    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		    player = new Player(bufferedInputStream);
		    player.play();
			}catch(Exception e) {
				e.printStackTrace();
			}
		sum++;
		
		boolean b = getPerson_Person_combat(x,y,Black_White);
		if(b==true) {
			JOptionPane about =new JOptionPane();
			about.showMessageDialog(null, "��ϲ�㣬Ӯ���˱�����");
		}
		}
	}
	
	public void getBackLabel() {
		//���巽��
		if(sum!=0) {
		int sum_c =--sum;
		c.remove(myArrayLabel[sum_c].getLabel());
		getRefreshWindows();
		int x = myArrayLabel[sum_c].getX();
		int y = myArrayLabel[sum_c].getY();
		myArrayGame[x][y].setTF(0);
		}
		else {
			JOptionPane showMessage =new JOptionPane();
			showMessage.showMessageDialog(null, "û����ɻ�");
		}
	}

	public  void getMouse() {	
		Start_end_Button.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {//�������ʱ������
				Start_end_Button.setIcon(StartIcon2);
			}
			public void mousePressed(MouseEvent e) {//��갴������ʱ������
			}
			public void mouseReleased(MouseEvent e) {//��갴�����ͷ�ʱ������
				if(Combat_TF==true) {
					c.remove(Start_end_Button);
					c.add(Pause_Butten);
					time =1800;
					boonum =true;
					getWindowsMouse();
					getRefreshWindows();
					//��갴�����ͷŴ���
					Now_Label.setText("�ð�������....");
				}
				else {
					JOptionPane about =new JOptionPane();
					about.showMessageDialog(null, "����ѡ���սģʽ");
				}
			}
			public void mouseClicked(MouseEvent e) {//���������¼�ʱ������
			}
			public void mouseExited(MouseEvent e) {//�Ƴ����ʱ������
				Start_end_Button.setIcon(StartIcon1);
			}
		});	
		Pause_Butten.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				//Point point=MouseInfo.getPointerInfo().getLocation();//��ȡ�������  
				Pause_Butten.setIcon(PauseIcon2);
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				c.remove(Pause_Butten);
				c.add(Start_end_Button);
				boonum =false;
				getWindowsMouse();
				getRefreshWindows();
				//��갴�����ͷŴ���
			}
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				Pause_Butten.setIcon(PauseIcon1);
			}
		});
		Person_Person_Button.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				Person_Person_Button.setIcon(PersonIcon2);
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				Show_Label.setText("��ǰģʽ�����˶�ս");
				Combat_TF=true;
				Person_Machine_TF=true;
			}
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				Person_Person_Button.setIcon(PersonIcon1);
			}
		});
		Person_Machine_Button.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				Person_Machine_Button.setIcon(MachineIcon2);
			}
			public void mousePressed(MouseEvent e) {

			}
			public void mouseReleased(MouseEvent e) {
				Show_Label.setText("��ǰģʽ���˻���ս");
				Combat_TF=true;
				Person_Machine_TF=false;
			}
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				Person_Machine_Button.setIcon(MachineIcon1);
			}
		});
		Back_Button.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				Back_Button.setIcon(BackIcon2);
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				getBackLabel();
			}
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				Back_Button.setIcon(BackIcon1);
			}
		});
		About_Button.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				About_Button.setIcon(AboutIcon2);
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				JOptionPane jo =new JOptionPane();
				jo.showMessageDialog(null, "ʹ��java swing��д\n������Դ��ע���˲��ͣ�fdogcsdn.com\nQQ/WX��2506897252");
			}
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				About_Button.setIcon(AboutIcon1);
			}
		});
	}	
	public  void getWindowsMouse() {
		jf.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				
			}
			public void mousePressed(MouseEvent e) {
				
			}
			public void mouseReleased(MouseEvent e) {
				
			}
			public void mouseClicked(MouseEvent e) {
				//��ȡ��굥���¼� ������굥��ʱ����ȡ����
				if(boonum==true) {
				if(19<=(e.getX()-30) && (e.getX()-30)<=725 
				&& 18<=(e.getY()-50) && (e.getY()-50)<=724) {
				for(int i=0;i<=13;i++) {
				for(int j=0;j<=13;j++) {
				if(((20+i*54-27)<=(e.getX()-30)&&(e.getX()-30)<=(21+i*54+27))
				&&((20+j*54-27)<=(e.getY()-50)&&(e.getY()-50)<=(20+j*54+27))) {		
				time =1800;
				getLabelShow(i,j,White_Black_Label,sum);
				break;
					}
				}
				}
				}
				}
			}
			public void mouseExited(MouseEvent e) {
				
			}
		});
		}
	public  void getRefreshWindows() {
		jf.validate();
		jf.repaint();
	}
	public  void run() {
		while(true) {
		while(time>0) {
			if(boonum==false) {
			}
			else {
				time--;
			}
			try {
			Thread.sleep(1000);
               ss = (time % 60);
              String str =" "+ss+" ��";
              if(ss==0) {
              }
              else {
            	  if(ss-1==0) {
            		  Countdown_TextField.setText(str);
            	 	  JOptionPane jo =new JOptionPane();
        				jo.showMessageDialog(null, "���ź���������ˣ�");
        				time=0;
            	  }
            	  else {
              Countdown_TextField.setText(str);
            	  }
              }
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
	public static void main(String[] args) {
		gobang go = new gobang();
		Thread myThread_1 = new Thread(go,"�߳�1");
		myThread_1.start();
		go.getArratGame();
		go.getwindows();
		go.getMouse();
		Music music = new Music();
		music.getMusic();
	}
}




