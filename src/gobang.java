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
	private static Icon WhiteIcon = new ImageIcon("E:\\dianmingqi\\call\\lib\\baiqi.png");//白棋
	private static Icon BlackIcon = new ImageIcon("E:\\dianmingqi\\call\\lib\\heiqi.png");//黑棋
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
	//每个按钮有两种图片,第一张代表未按下状态,第二张代表按下状态，增加视觉感
	private static Icon StartIcon1 =new ImageIcon("E:\\dianmingqi\\call\\lib\\kaishiyouxi.jpg");//开始游戏
	private static Icon StartIcon2 =new ImageIcon("E:\\dianmingqi\\call\\lib\\kaishiyouxi2.jpg");
	private static Icon EndIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\jieshuyouxi.jpg");//结束游戏
	private static Icon EndIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\jieshuyouxi2.jpg");
	private static Icon PauseIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\zantingyouxi.jpg");//暂停游戏
	private static Icon PauseIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\zantingyouxi2.jpg");
	private static Icon PersonIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\renrenduizhan.jpg");//人人对战
	private static Icon PersonIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\renrenduizhan2.jpg");
	private static Icon MachineIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\renjiduizhan.jpg");//人机对战
	private static Icon MachineIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\renjiduizhan2.jpg");
	private static Icon AboutIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\guanyu.jpg");//关于
	private static Icon AboutIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\guanyu2.jpg");
	private static Icon BackIcon1 = new ImageIcon("E:\\dianmingqi\\call\\lib\\huiqi.jpg");//悔棋
	private static Icon BackIcon2 = new ImageIcon("E:\\dianmingqi\\call\\lib\\huiqi2.jpg");
	private static ArrayLabel[] myArrayLabel =new ArrayLabel[200];//创建数组，存放顺序信息
	private static ArrayGame[][] myArrayGame =new ArrayGame[14][14];//创建数组，存放顺序信息
	private static JButton Start_end_Button = new JButton(); //开始游戏按钮
	private static JButton Pause_Butten = new JButton();//暂停按钮
	private static JButton Person_Person_Button = new JButton();//人人对战按钮
	private static JButton Person_Machine_Button = new JButton();//人机对战按钮
	private static JButton Back_Button = new JButton();//悔棋按钮
	private static JButton About_Button = new JButton();//关于按钮
	private static JLabel Countdown_Label = new JLabel();//标签倒计时
	private static JLabel Now_Label = new JLabel();//标签显示该下棋的一方
	private static JLabel Show_Label = new JLabel("当前模式：");//标签显示当前模式
	private static JTextField Countdown_TextField =new JTextField();//文本框显示倒计时
	private static Font font = new Font("方正正大黑简体",Font.BOLD,20); //设置字体大小
	private static Font font_2 = new Font("方正正大黑简体",Font.BOLD,15); //设置字体大小
	private static JFrame jf=null; //创建窗体
	private static Container c =null;//创建容器
	private static boolean Combat_TF =false;//判断是否选择对战模式
	private static boolean Person_Machine_TF =true;//判断选择的模式，真为人人
	private static boolean Showboonum=true; //真为人人，假为人机
	private static volatile boolean boonum=false;//当该变量为真时，可下棋，如果为假，即暂停模式
	private static boolean White_Black_Label=true;//设置黑白棋
	private static volatile int time =0; //设置倒计时
	private static volatile int ss =time % 60;
	private static int sum = 0;//记录数组使用情况
	
	public boolean getPerson_Person_combat(int x,int y,boolean White_Black) {
		// 得到该棋子的信息
	    // 水平方向遍历
		// 先向左遍历
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
		//再向右遍历
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
		
		//垂直方向
		//先向上遍历
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
		 //向下遍历
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
		//两条对角线遍历 
		//第一条：判断左上
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
		 //判断右下
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
		//第二条：左下
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
		//判断右上
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
		jf = new JFrame("花狗五子棋 ");
		jf.setIconImage(new ImageIcon("Icon.jpg").getImage());
		ImageIcon img =new ImageIcon("lib\\qipan.jpg");
		jf.setLayout(null);//使窗体取消布局管理器设置
		JLabel imgLabel = new JLabel(img);
		jf.getLayeredPane().add(imgLabel,new Integer(Integer.MIN_VALUE));
		imgLabel.setSize(1090, 800);
	     c =jf.getContentPane();
		Cursor cursor =Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("E:\\dianmingqi\\call\\lib\\shouzhi.png").getImage(),new Point(0,0), "stick");
        jf.setCursor(cursor);
		((JPanel)c).setOpaque(false);
		Countdown_Label.setFont(font);//设置字体
		Now_Label.setFont(font);//设置字体
		Show_Label.setFont(font);//设置字体
		Countdown_TextField.setFont(font_2);
		Countdown_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Countdown_Label.setText("倒计时：");
		Now_Label.setHorizontalAlignment(SwingConstants.CENTER);	
		Countdown_TextField.setBackground(Color.orange);
		//设置按钮位置
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
		//将棋盘建立一个二维坐标
		//建立的时候检测该坐标是否有为空
		//将所有棋子放在一起，使用后进先出原则
		if(myArrayGame[x][y].getTF()==0) {
		myArrayLabel[sum_c]=new ArrayLabel(x, y, Black_White);
		myArrayGame[x][y] = new ArrayGame(x,y,1,Black_White);
		if(White_Black_Label==true) {
		Now_Label.setText("该黑子下棋....");
			White_Black_Label=false;
		}
		else {
		Now_Label.setText("该白子下棋....");
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
			about.showMessageDialog(null, "恭喜你，赢得了比赛！");
		}
		}
	}
	
	public void getBackLabel() {
		//悔棋方法
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
			showMessage.showMessageDialog(null, "没有棋可毁");
		}
	}

	public  void getMouse() {	
		Start_end_Button.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {//移入组件时被触发
				Start_end_Button.setIcon(StartIcon2);
			}
			public void mousePressed(MouseEvent e) {//鼠标按键按下时被触发
			}
			public void mouseReleased(MouseEvent e) {//鼠标按键被释放时被触发
				if(Combat_TF==true) {
					c.remove(Start_end_Button);
					c.add(Pause_Butten);
					time =1800;
					boonum =true;
					getWindowsMouse();
					getRefreshWindows();
					//鼠标按键被释放触发
					Now_Label.setText("该白子下棋....");
				}
				else {
					JOptionPane about =new JOptionPane();
					about.showMessageDialog(null, "请先选择对战模式");
				}
			}
			public void mouseClicked(MouseEvent e) {//发生单击事件时被触发
			}
			public void mouseExited(MouseEvent e) {//移出组件时被触发
				Start_end_Button.setIcon(StartIcon1);
			}
		});	
		Pause_Butten.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				//Point point=MouseInfo.getPointerInfo().getLocation();//获取鼠标坐标  
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
				//鼠标按键被释放触发
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
				Show_Label.setText("当前模式：人人对战");
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
				Show_Label.setText("当前模式：人机对战");
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
				jo.showMessageDialog(null, "使用java swing编写\n更多资源关注本人博客：fdogcsdn.com\nQQ/WX：2506897252");
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
				//获取鼠标单击事件 ，当鼠标单击时，获取坐标
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
              String str =" "+ss+" 秒";
              if(ss==0) {
              }
              else {
            	  if(ss-1==0) {
            		  Countdown_TextField.setText(str);
            	 	  JOptionPane jo =new JOptionPane();
        				jo.showMessageDialog(null, "很遗憾，你输掉了！");
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
		Thread myThread_1 = new Thread(go,"线程1");
		myThread_1.start();
		go.getArratGame();
		go.getwindows();
		go.getMouse();
		Music music = new Music();
		music.getMusic();
	}
}




