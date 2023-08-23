import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
	
    public JFrame jf;
    public static JPanel drawJP;
	public JPanel toolJP;

    public JLabel jlpic = new JLabel();
    JPanel jPanel = new JPanel();
    JFrame jframe = new JFrame();

    public void showUI() {
    	// new一个JFrame窗体
        jf = new JFrame("地图最小生成树查询");
        // 设置窗体大小
        jf.setSize(600,600);
        // 设置窗体的布局为边界布局，分为东南西北中五个方位，可以将组件添加到指定的地方
        jf.setLayout(new BorderLayout());
        // 设置窗体居中显示
        jf.setLocationRelativeTo(null);
        // 给窗体设置退出按钮 关掉即退出程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // new一个区域JPanel
        drawJP = new JPanel();
		
        ImageIcon icon = new ImageIcon(getClass().getResource("MAP.jpg"));
        jlpic.setIcon(icon);
        drawJP.add(jlpic);
        jf.add(drawJP);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 定制操作模块
        toolJP = new JPanel();
        toolJP.setBackground(Color.WHITE);
        // 除了窗体使用setSize(),其他组件的大小设置都需要使用setPreferredSize()方法
        toolJP.setPreferredSize(new Dimension(0,70));
        jf.add(toolJP, BorderLayout.SOUTH);

		//查询按钮
		JButton jbt = new JButton("查询");
		jbt.setPreferredSize(new Dimension(80,30));
		toolJP.add(jbt);
		//按钮监听事件
		btnListener check = new btnListener();
		jbt.addActionListener(check);

		//清除按钮
		JButton jbtClear = new JButton("清除");
		jbtClear.setPreferredSize(new Dimension(80,30));
		toolJP.add(jbtClear);
		//按钮监听事件
		btnListenerClear clear = new btnListenerClear();
		jbtClear.addActionListener(clear);		

		// 设置窗体为可见，不然看不见窗体以及窗体中的内容
        jf.setVisible(true);
    }
}

//查询事件
class btnListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("查询事件");
		refresh.draw();
	}
}

//清除事件
class btnListenerClear implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("清除事件");
		refresh.clear();
	}
}

//刷新界面
class refresh extends GUI{
	//地图各个点的坐标，用于画线
	private static int[][] location = {{0,130,220},
										{1,400,100},
										{2,380,250}, 
										{3,240,400}, 
										{4,630,50},
										{5,630,150}, 
										{6,630,275}, 
										{7,450,385},
										{8,890,205}, 
										{9,870,385}, 
										{10,625,525}, 
										{11,1135,350}, 
										{12,870,470}};

	public static void draw(){
		Tools.search();
		drawRoad();
	}
	
	public static void clear(){
		drawJP.repaint();
	}

	public static void drawRoad(){
		Graphics g =  drawJP.getGraphics();
		g.setColor(Color.red);
		int head = -1;
		int tail = -1;
		for(int i=1; i<13; i++){
			head = Tools.array[i][0];
			tail = Tools.array[i][1];
			g.drawLine(location[head][1],location[head][2], location[tail][1],location[tail][2]);
		}
	}
}

