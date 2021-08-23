package jo.secondstep.separator;

import java.util.ArrayList;
import java.util.List;

public class Separator {
  final  private List<Integer> fullList=new ArrayList<>();
  final  private List<Integer> evenList=new ArrayList<>();
  final  private List<Integer> oddList=new ArrayList<>();
  
  
	public void separate(List<Integer> list) {
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (fullList) {
					while(true) {
						if(list.isEmpty()) {
							try {
								fullList.wait(10000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						else{
							fullList.addAll(list);
							for(int num:fullList) {
								if(num%2==0) {
									synchronized(evenList) {
										if(!evenList.isEmpty()) {
										  	 try {
												evenList.wait(10000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}										
										  }
										else {
											evenList.add(num);
											evenList.notify();
										}
									}
								}else {
									synchronized(oddList) {
										if(!oddList.isEmpty()) {
										  	 try {
										  		oddList.wait(10000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}										
										  }
										else {
											oddList.add(num);
											oddList.notify();
										}
									}
								}
							}
							list.clear();
						}
							
					}
					
					
				}
				
			}
			
			
		});
		t1.start();
		
	   Thread t2 =new Thread(new Runnable() {
		
		@Override
		public void run() {
			synchronized (evenList) {
				while(true) {
					if(evenList.isEmpty()){
						try {
							evenList.wait(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else {
						for(int num:evenList) {
							for(int i=0;i<num;i++) {
								System.out.print(" t2: "+num);
							}
							System.out.println();
						}
						evenList.clear();
						evenList.notify();
					}
					
				
			    }
				
			}
			
		}
	});
	   
	   t2.start();
	   
	   Thread t3 =new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (oddList) {
					while(true) {
						if(oddList.isEmpty()){
							try {
								oddList.wait(10000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}else {
							for(int num:oddList) {
								for(int i=0;i<num;i++) {
									System.out.print(" t3: "+num);
								}
								System.out.println();
							}
							oddList.clear();
							oddList.notify();
						}
						
					
				    }
					
				}
				
			}
		});
		   
		   t3.start();
	}
}
