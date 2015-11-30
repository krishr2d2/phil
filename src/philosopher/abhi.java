/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philosopher;

import javax.swing.JApplet;
import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

//import philosopher.Launch.Phil;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class abhi extends JPanel 
{
	String [] str = new String[5];
	JTextArea textArea;
	JTextArea textArea1;
	private JTextArea textArea_1;
	private JTextArea textField;
	private JTextArea textField_1;
	public static void main(String[] args)
	{
		abhi d =new abhi();
		JFrame j = new JFrame();
		j.setTitle("Dining Philosopher");
		j.setSize(600, 400);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.getContentPane().add(d);
	}
	class Launch
	{
		int num,i;
		//public static final int u=0;
		semaphore [] chopstick ;
		Phil [] phil;
		semaphore mutex ;
		Graphics f;
		/* Array of philosophers */
	//String ws = "";
		Launch()
		{
			num=5;						                      /* Total philosophers */
			chopstick = new semaphore[5];               /* Total Chopsticks */
		    mutex = new semaphore(1);                     /* Chopstick not occupied- initialised as 1 */
		    phil = new Phil[5];
		    i=0;
		    for(;i<5;i++)									
		    {
		    	chopstick[i]=new semaphore(1);						          /* Giving All chopsticks value as 1 - not occupied */
		    	phil[i]=new Phil(i);								          /* Initialising philosophers array */
		    	phil[i].start();									          /* Starting the simulation */
		    }
		//return ws;
		}
		class Phil extends Thread
		{
			int id;
			//public int u=0;
			String Status="";
			int quantum=2087;
			Phil (int x)
			{
				id=x;
				Status="Hungry";
			}
			public void run()
			{
				int i=0,j=0;
				while(1==1)
				{
					mutex.Wait_for_turn();						           /* Giving indication that chopstick is occupied */
					chopstick[id].Wait_for_turn();				           /* Left chopstick is occupied */
					chopstick[(id+1)%(num)].Wait_for_turn();         /* Right Chopstick is occupied */
					Status="Eating";
					PrintStatus();
					int timequantum=(int)(Math.random()*quantum);			       /* Eating time */
					try
					{
						Thread.sleep(timequantum);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					chopstick[id].pick_the_chopsticks();				       /* Left chopstick is free */
					chopstick[(id+1)%(num)].pick_the_chopsticks();   		   /* Right Chopstick is free */
					Status="Thinking";
					PrintStatus();
					mutex.pick_the_chopsticks();					           /* Indication that chopstick is free */
					timequantum=(int)(Math.random()*quantum);			       /* Thinking Time */
					try
					{
						Thread.sleep(timequantum);
					}	
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					Status="Hungry";
					PrintStatus();
				}
			}
			public void PrintStatus()
			{
				int u=0;
				int y=0;
				int r=0;
				String sw = "";
				for(int i=0;i<num;i++)
				{
					sw+=phil[i].Status+" ";
					if(phil[i].Status=="Eating")
						u=i;
				}
				String [] ew=sw.split(" ");
				System.out.println(sw);
				
			
				textArea.setText(phil[0].Status);
				textArea1.setText(phil[1].Status);
				textField.setText(phil[2].Status);
				textField_1.setText(phil[3].Status);
				textArea_1.setText(phil[4].Status);
			//	paintComponent(f,u);
		    //		ws+=sw;
			//			return ws;
			//System.out.println(ws);
			}
			
		}
	}
	public abhi() 
	{
		setLayout(null);	
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Launch t=new Launch();
			}
		});
		btnRun.setBounds(31, 434, 97, 25);
		add(btnRun);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnStop.setBounds(586, 434, 97, 25);
		add(btnStop);		
		textArea = new JTextArea();
		textArea.setBounds(326, 20, 100, 22);
		add(textArea);
		textArea1 = new JTextArea();
		textArea1.setBounds(480, 220, 100, 22);
		add(textArea1);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(27, 220, 100, 22);
		add(textArea_1);
		
		textField = new JTextArea();
		textField.setBounds(370, 350, 100, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextArea();
		textField_1.setBounds(180, 350, 100, 22);
		add(textField_1);
		textField_1.setColumns(10);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(150,110, 300, 150);
//		g.fillRect(10, 10, 20,10);
		g.setColor(Color.BLACK);
		g.fillOval(270,30, 50, 50);
		g.fillOval(80, 160, 50, 50);
		g.fillOval(200, 280, 50, 50);
		g.fillOval(350, 280, 50, 50);
		g.fillOval(475, 160, 50, 50);
		g.setColor(Color.YELLOW);
		g.fillOval(270, 150, 60, 60);
		//color=BLACK;
	}
}


