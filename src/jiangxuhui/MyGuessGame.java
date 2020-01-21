/**
 * 
 */
package jiangxuhui;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author ������
 *
 */
public class MyGuessGame extends JFrame implements ActionListener {
	JTextField tf=new JTextField();
	JButton b1=new JButton();
	JLabel j1=new JLabel();
	int m;
	int count;//��Ų²������
	int oldNumber;//���ԭ�м�¼��������
    boolean isEnd;  //����Ƿ��Ƽ�¼����
	/**
	 * @param args
	 */
    public MyGuessGame() {
    	b1.setActionCommand("start");
    	JPanel p=new JPanel();
    	p.add(b1);
    	b1.addActionListener(this);
    	tf.addActionListener(this);
    	tf.setEnabled(false);
    	this.getContentPane().add(tf,"North");
    	this.getContentPane().add(j1);
    	this.getContentPane().add(p,"South");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(300,200);
    	this.setLocation(300,200);
    	this.setVisible(true);
    	
    }
    
    public int getNumber() {
    	int m=(int)(Math.random()*100)+1;//1`100
        return m;
    }
    
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		int n=0;//����û��������ֵı�
	
		if(s.equals("start")) {
			isEnd=false;
			count=0;
			m=this.getNumber();
			System.out.print(m);
			b1.setEnabled(false);
			tf.setEnabled(true);
			j1.setText("������1-100������");
			tf.requestFocus();
			oldNumber=readRecord();
		}
		else {
		
			if(!isEnd) {
				++count;
				String sn=tf.getText();
				try {
					n=Integer.parseInt(sn);
				}catch(NumberFormatException e1) {
					j1.setText("����������");
					return;
				}
				
				if(n<m) {
					j1.setText("���µ���ƫС");
					return;
				}
				else if(n>m) {
					j1.setText("���µ���ƫ��");
					return;
				}else {
					j1.setText("��ϲ��¶��ˣ���������Ϊ:"+ count);
					tf.setText("");
					b1.setEnabled(true);
					if(oldNumber>count)
					{
						j1.setText("���Ƽ�¼�ˣ������ı�����������������");
						isEnd=true;
					}
					}
				
				}
			
			else {
					String name=tf.getText();
					this.saveRecord(name,count);
					j1.setText("���ļ�¼�Ѿ���¼�ڲᣬ����Ŭ����");
					tf.setText("");
					b1.setEnabled(true);
				}
				
			}
	}	

	public void saveRecord(String name, int count) {
		File f1=new File("record.txt");
		try {
			FileWriter fout=new FileWriter(f1);
			PrintWriter bw=new PrintWriter(fout);
			bw.println(count);
			bw.println(name);
			bw.close();
			fout.close();
		}catch(java.io.FileNotFoundException e){}
			catch(IOException e) {}
	
		// TODO Auto-generated method stub
		
	}

		public int readRecord() {
		int count=100;
		File f1=new File("record.text");
		try {
			FileReader fin=new FileReader(f1);
			BufferedReader br=new BufferedReader(fin);
			String s=br.readLine();
			count =Integer.parseInt(s);
			br.close();
			fin.close();
			
			
		}catch(java.io.FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}return count;
	}
	
	
	public static void main(String[] args) {
		new MyGuessGame();
		// TODO Auto-generated method stub

	}
}
