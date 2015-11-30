/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philosopher;

/**
 *
 * @author KRISHNA CHAITHANYA
 */
public class semaphore {
			private int val;
			public semaphore(int i)
			{
				val=i;
			}
			public semaphore()
			{
				val=0;
			}
			public synchronized void Wait_for_turn()
			{
				val--;
				if (val<0)
				{	
					try
					{
						wait();
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
			public synchronized void pick_the_chopsticks()
			{
				val++;
				if(val<=0)
				{
					notify();
				}
			}
		}
