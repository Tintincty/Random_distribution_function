package experiment.Ex04_RandomVariablesAndRandomDistribution;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
* @ClassName: MainFrameg5e1
* @Description: TODO(RandomVariablesAndRandomDistribution)
* @author caoty
* @date 2019��10��
*/

public class MainFrameg5e1 extends JFrame implements ActionListener,ItemListener {
	Container contentPane;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel slabel1;
	JLabel slabel2;
	Icon icon1=new ImageIcon("��ʽ1.png");
	Icon icon2=new ImageIcon("��ʽ2.png");
	Icon icon3=new ImageIcon("��ʽ3.png");
	Icon icon4=new ImageIcon("��ʽ4.png");
	
	JTextField jtx1;
	JTextField jtx2;
	JTextField jtx3;
	
	JButton btn1;//��ʾͼ��ť
	
	ChartPanel chartPanel;
	ChartPanel chartPanel2;
	ChartPanel chartPanel3;
	double a;
	//ͼ�ν���ĵ�һ���û������������Բ�ͬ���������Ų�ͬ���壬�Ծ��Ⱥ�����������˵㣻
	//���ڶ�����̬�ֲ������������ֵ��
	double b;//ͼ�ν���ĵڶ����û������������Բ�ͬ���������Ų�ͬ���塣
	String reminder1="Now showing\n the uniform distribution function";
	String reminder2="Now showing\n the exponential distribution function";
	String reminder3="Now showing\n the normal distribution function";
	String reminder4="Now showing\n the lognormal distribution function";
	String reminder5="Now showing\n the binomial distribution function";
	String reminder6="Now showing\n is the Poisson distribution function";
	String reminder7="Now showing]n the geometric distribution function";
	String reminder8="Check whether there is a problem with the parameter input before";
	
	
	JComboBox combox1;
	
	//���������
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrameg5e1 mf=new MainFrameg5e1();
		mf.setVisible(true);
	}
	
	//���沼��
	public MainFrameg5e1() {
		this.setTitle("Random variables and random distribution functions");
		this.setBounds(100, 100, 880, 700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane=this.getContentPane();
		contentPane.setLayout(null);
		
		panel1=new JPanel();
		panel1.setLayout(null);
		panel1.setBorder(BorderFactory.createTitledBorder("Parameter input area"));
		panel1.setSize(200, 600);
		panel1.setLocation(10,5);
		contentPane.add(panel1);
		
		String[] functionName= {"Evenly distributed","index distribution","Normal distribution","Lognormal distribution","Binomial distribution","Poisson distribution","Geometric distribution"};
		combox1=new JComboBox(functionName);
		combox1.setBounds(10, 40, 160, 45);
		combox1.setBorder(BorderFactory.createTitledBorder("Please select function type")); 
		panel1.add(combox1);
		combox1.addItemListener(this);
		
		label1=new JLabel("parm a:");
		panel1.add(label1);
		label1.setBounds(20, 100, 60, 25);
		jtx1=new JTextField();
		panel1.add(jtx1);
		jtx1.setBounds(85, 100, 80, 25);
		
		label2=new JLabel("parm b:");
		panel1.add(label2);
		label2.setBounds(20, 130, 60, 25);
		jtx2=new JTextField();
		panel1.add(jtx2);
		jtx2.setBounds(85, 130, 80, 25);
		
		label3=new JLabel(reminder1);
		panel1.add(label3);
		label3.setBounds(20, 170, 160, 25);

		panel2=new JPanel();
		panel2.setLayout(null);
		panel2.setSize(420, 600);
		panel2.setLocation(215,5);
		panel2.setBorder(BorderFactory.createTitledBorder("Table display area"));
		contentPane.add(panel2);
		
		btn1=new JButton("Display image");
		btn1.setBounds(130, 50, 160, 30);
		panel2.add(btn1);
		btn1.addActionListener(this);
				
		panel3=new JPanel();
		panel3.setBorder(BorderFactory.createTitledBorder("Experiment description area"));
		panel3.setLayout(null);
		panel3.setBounds(640, 5, 235, 600);
		panel3.add(new Button("east"));
		contentPane.add(panel3);
		slabel1=new JLabel();
		slabel1.setIcon(icon1);
		slabel1.setSize(220,79);
		slabel1.setLocation(10,60);
		panel3.add(slabel1);
	}
	
	//����Ϊ��׼��̬�ֲ����������޷������ֺ�����ת��Ϊ�����֣�����aת���󶨻�������0,b����1,n�������������Ŀ����С��ζ�ž�ȷ�ȣ�
			//u����ת��ǰ������������,r����,p�������ޱ䶯��ֵ��
	public static double jiFen(double a,double b,int n,double u,double r,double p) {
		double e=(b - a) / n;
		double sum=0;
		for (int i = 1; i <= 1000; i++) {
            double x = a + i * (b - a) / n;
            sum = sum +Math.pow(Math.E, -Math.pow(p-(1-x)/x-u, 2)/(2*r*r))/(x*x);
        }
		sum*=e;
		return sum;
	}
	
	//���ƺ���ͼ��
	//command��ͬ��ֵ������Ʋ�ͬ���ĺ���ͼ�񣬷�Ϊ����������Ⱥ������ȵȡ�
	//chartPanel�����ڲ�ͬλ�û���ͼ�񣬱�ͼ�ν��湲����λ��չʾͼ��
	//����type=1������Ƹ����ܶȺ�����type=2������Ƹ��ʷֲ�����
	public void drawPicture(int command,ChartPanel chartPanel,String title,int type) {		
		XYSeries series = new XYSeries("xySeries");
		
		//����ֲ�
		if(command==5) {
			if(type==1) {
				double p=a;
				for (double x = 1; x<=30; x++){
					//ÿ�γ�ʼy
					double y=0;
					series.add(0, y);
					//����ѭ��
					for(double k=1;k<=x;k++) {
						double n=1;
						double m=1;
						double xx=1;
						for(double i=1;i<=x;i++) {
							xx=xx*i;
						}
						for(double i=1;i<=k;i++) {
							n=n*i;
						}
						for(double i=1;i<=x-k;i++) {
							m=m*i;
						}	
						y=y+(xx/(n*m))*Math.pow(p, k)*Math.pow((1-p), (x-k));
					}
					
					series.add(x, y);
				}
			}
			else {				
				double p=a;
				double y=0;
				for (double x = 1; x<=30; x= x + 1){
					//ÿ�γ�ʼy
					//double y;
					//����ѭ��
					for(double k=1;k<=x;k++) {
						double n=1;
						double m=1;
						double xx=1;
						for(double i=1;i<=x;i++) {
							xx=xx*i;
						}
						for(double i=1;i<=k;i++) {
							n=n*i;
						}
						for(double i=1;i<=x-k;i++) {
							m=m*i;
						}
						y=(xx/(n*m))*Math.pow(p, k)*Math.pow((1-p), (x-k));
					}
					series.add(x, y);
				}
			}
		}
		
		//���ɷֲ�
		if(command==6) {
			if(type==1) {
				double L=a;
				for (double x = 1; x<=20; x= x + 1){
					//ÿ�γ�ʼy
					double y=Math.exp(-L);
					//����ѭ��
					for(double k=1;k<=x;k++) {
						double kk=1;
						for(double i=1;i<=k;i++) {
							kk=kk*i;
						}
						double LL=1;
						for(double i=1;i<=k;i++) {
							LL*=L;
						}
						y=y+(LL/kk)*Math.exp(-L);
					}
					series.add(x, y);
				}
			}
			else {				
				double L=a;
				double y=Math.exp(-L);
				series.add(0, y);
				for (double x = 1; x<=20; x= x + 1){
					//ÿ�γ�ʼy
					//double y=Math.exp(-L);
					//����ѭ��
					for(double k=1;k<=x;k++) {
						double kk=1;
						for(double i=1;i<=k;i++) {
							kk=kk*i;
						}
						double LL=1;
						for(double i=1;i<=k;i++) {
							LL*=L;
						}
						y=(LL/kk)*Math.exp(-L);
					}
					series.add(x, y);
				}
			}
		}
		
		//���ηֲ�
		if(command==7) {
			if(type==1) {
				double p=a;
				for (double x = 1; x<=30; x= x + 1){
					//ÿ�γ�ʼy
					double y=0;
					//����ѭ��
					for(double k=1;k<=x;k++) {
						double q=1-p;
						double qq=1;
						for(double i=1;i<=k-1;i++) {
							qq=qq*q;
						}
						y=y+p*qq;
					}
					series.add(x, y);
				}
			}
			else {				
				double p=a;
				double y=0;
				for (double x = 1; x<=30; x= x + 1){
					//ÿ�γ�ʼy
					//double y=0;
					//����ѭ��
					for(double k=1;k<=x;k++) {
						double q=1-p;
						double qq=1;
						for(double i=1;i<=k-1;i++) {
							qq=qq*q;
						}
						y=p*qq;
					}
					series.add(x, y);
				}
			}
		}
		
		//���ȷֲ�
		if(command==4) {
			if(type==1) {
				for (double x = a-1; x < b+1; x+=0.005) {
					if(x>=a&&x<=b) {
						double y = 1/(b-a);
						series.add(x, y);
					}else {
						series.add(x, 0);
					}
				}
			}
			else {				
				for (double x = a-1; x < b+1; x+=0.005) {
					if(x>=a&&x<=b) {
						double y =(x-a)/(b-a);
						series.add(x, y);
						
					}else if(x<a) {
						series.add(x, 0);
					}else {
						series.add(x, 1);
					}
				}
			}
		}
		
		//ָ���ֲ�
		if(command==1) {
			if(type==1) {
				for (double x = -0.5; x < 6; x+=0.005) {
					if(x>0) {
						double y = a*Math.pow(Math.E, -a*x);
						series.add(x, y);
					}else {
						series.add(x, 0);
					}
				}
			}
			else {				
				for (double x = -0.5; x < 6; x+=0.005) {
					if(x>=0) {
						double y =1-Math.pow(Math.E, -a*x);
						series.add(x, y);
						
					}else {
						series.add(x, 0);
					}
				}
			}
		}
		
		//��̬�ֲ�
		if(command==2) {
			if(type==1) {
				for (double x = a-3; x <= a+3; x+=0.005) {
					double y =  1.0/(Math.sqrt(2*Math.PI)*b)*Math.pow(Math.E, -(x-a)*(x-a)/(2*b*b));
					series.add(x, y);
				}
			}
			else {
				for (double x = -a-3; x <=a+3; x+=0.005) {
					double y =1.0/(Math.sqrt(2*Math.PI)*b)*jiFen(0,1,1000,a,b,x);
					series.add(x, y);
				}
			}
		}
		
		//������̬�ֲ�
		if(command==3) {
			for (double x = -0.5; x <=a+3; x+=0.05) {
				if(x>=0) {
					double y = 1.0/(Math.sqrt(2*Math.PI)*b*x)*Math.pow(Math.E, -Math.pow(Math.log(x)/Math.log(Math.E)-a,2)/(2*b*b));
					series.add(x, y);
				}else {
					series.add(x, 0);
				}
			}
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createXYLineChart(
				title, // chart title
				"x", // x axis label
				"f", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				false, // tooltips
				false // urls
				);
		
		//��ɢ�ͱ�������ɢͼ����
		if(command>4) {
			XYPlot plot = (XYPlot)chart.getPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesLinesVisible(0, false); // �������߲��ɼ�
		        plot.setRenderer(renderer);
		}
		
		chartPanel=new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		chartPanel.setSize(400,235);
		if(type==1) {
			chartPanel.setLocation(10, 130);
		}
		if(type==2)
		{
			chartPanel.setLocation(10, 365);
		}
		if(type==3)
		{
			chartPanel.setLocation(10, 130);
			chartPanel.setSize(400,470);
		}
		panel2.add(chartPanel);
		chartPanel.repaint();//���ٵ��ø�������Ʒ�����Ҫ����
	}

	//���ղ�������ͼ
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		try {
		if(combox1.getSelectedItem().toString()=="Binomial distribution") {
			a=Double.valueOf(jtx1.getText()); 
			if(a<=0||a>1) {
				JOptionPane.showMessageDialog(null, "The binomial distribution parameter p should be greater than 0 and less than or equal to 1!","Prompt",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			drawPicture(5,chartPanel,"Binomial distribution function",1);
			drawPicture(5,chartPanel2,"Binomial distribution density function",2);
		}	
			
		if(combox1.getSelectedItem().toString()=="Poisson distribution") {
			a=Double.valueOf(jtx1.getText()); 
			if(a<=0) {
				JOptionPane.showMessageDialog(null, "The Poisson distribution parameter �� should be greater than 0!","Prompt",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			drawPicture(6,chartPanel,"Poisson distribution function",1);
			drawPicture(6,chartPanel2,"Binomial distribution density function",2);
		}
		
		if(combox1.getSelectedItem().toString()=="Geometric distribution") {
			a=Double.valueOf(jtx1.getText()); 
			if(a<=0||a>=1) {
				JOptionPane.showMessageDialog(null, "The geometric distribution parameter p should be greater than 0 and less than or equal to 1!","Prompt",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			drawPicture(7,chartPanel,"Geometric distribution function",1);
			drawPicture(7,chartPanel2,"Geometric distribution density function",2);
		}
			
		if(combox1.getSelectedItem().toString()=="Evenly distributed") {
			a=Double.valueOf(jtx1.getText()); 
			b=Double.valueOf(jtx2.getText()); 
			if(a>=b) {
				JOptionPane.showMessageDialog(null, "Uniform distribution function, the first parameter is the left endpoint should be smaller than the second parameter right endpoint!","Prompt",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			drawPicture(4,chartPanel,"Uniform distribution density function",1);
			drawPicture(4,chartPanel2,"Uniform distribution function",2);
		}
		
		if(combox1.getSelectedItem().toString()=="index distribution") {
			a=Double.valueOf(jtx1.getText()); 
			if(a<=0) {
				JOptionPane.showMessageDialog(null, "The exponential distribution parameter �� should be greater than 0!","Prompt",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			drawPicture(1,chartPanel,"Exponential distribution density function",1);
			drawPicture(1,chartPanel2,"Exponential distribution function",2);
		}
		if(combox1.getSelectedItem().toString()=="Normal distribution") {
			a=Double.valueOf(jtx1.getText()); 
			b=Double.valueOf(jtx2.getText()); 
			if(b<=0) {
				JOptionPane.showMessageDialog(null, "The standard deviation �� of the normal distribution should be greater than 0!","Prompt",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			drawPicture(2,chartPanel,"Normal distribution density function",1);
			drawPicture(2,chartPanel2,"Normal distribution function",2);
		}
		
		if(combox1.getSelectedItem().toString()=="Lognormal distribution") {
			a=Double.valueOf(jtx1.getText()); 
			b=Double.valueOf(jtx2.getText()); 
			if(b<=0) {
				JOptionPane.showMessageDialog(null, "The standard deviation �� of the lognormal distribution should be greater than 0!","Prompt",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			drawPicture(3,chartPanel,"log normal distribution density function",3);	
//		drawPicture(3,chartPanel,"log normal distribution function",2);	
		}
		}catch(Exception e1) {
			label3.setText("Please check whether the parameter input is correct!");
			JOptionPane.showMessageDialog(null, "Please check whether the parameter input is correct!","Prompt",JOptionPane.PLAIN_MESSAGE);
		}

	}
	
	//���������״̬����
	//���û�ѡ��Ҫʹ�õĺ������ͺ󣬻�������Ӧ�Ĳ���������ʾ����ʵ��˵����
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		slabel1.setVisible(true);
		if(combox1.getSelectedItem().toString()=="Evenly distributed") {
			label1.setText("parm a");
			label2.setText("parm b");
			label3.setText(reminder1);
			label2.setVisible(true);
			jtx2.setVisible(true);
			slabel1.setIcon(icon1);
			slabel1.setSize(220,79);
		}		
		else if(combox1.getSelectedItem().toString()=="index distribution") {
			label3.setText(reminder2);
			label2.setVisible(false);
			label1.setText("parm �ã�");
			jtx2.setVisible(false);
			slabel1.setIcon(icon2);
			slabel1.setSize(220,81);
		}
		else if(combox1.getSelectedItem().toString()=="Normal distribution") {
			label1.setText("Mean ��:");
			label2.setText("Sd ��:");
			label3.setText(reminder3);
			label2.setVisible(true);
			jtx2.setVisible(true);
			slabel1.setIcon(icon3);
			slabel1.setSize(220,68);
		}		
		else if(combox1.getSelectedItem().toString()=="Lognormal distribution") {
			label1.setText("Mean ��:");
			label2.setText("Sd ��:");
			label3.setText(reminder4);	
			label2.setVisible(true);
			jtx2.setVisible(true);
			slabel1.setIcon(icon4);
			slabel1.setSize(220,40);
		}
		else if(combox1.getSelectedItem().toString()=="Binomial distribution") {
			label3.setText(reminder5);
			label2.setVisible(false);
			label1.setText("parm p��");
			jtx2.setVisible(false);
			slabel1.setVisible(false);
			slabel1.setSize(220,81);
		}else if(combox1.getSelectedItem().toString()=="Poisson distribution") {
			label3.setText(reminder6);
			label2.setVisible(false);
			label1.setText("parm �ã�");
			jtx2.setVisible(false);
			slabel1.setVisible(false);
			slabel1.setSize(220,81);
		}else if(combox1.getSelectedItem().toString()=="Geometric distribution") {
			label3.setText(reminder7);
			label2.setVisible(false);
			label1.setText("parm p");
			jtx2.setVisible(false);
			slabel1.setVisible(false);
			slabel1.setSize(220,81);
		}
	}
}

