package ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/***************************************************
 
  큐 클래스
  - TicketProcessingQueue 		: 동시에 3명이 들어갈 수 있는 티켓부스 큐
 	 => 직원이 3명이 있기 때문에, 티켓부스에 고객이 동시에 3명이 들어가서 티켓팅을 한다.
 	 => 한 고객이 티켓팅을 끝내는 데 걸리는 시간은 각자의 티켓소요시간에 의존한다. 
 	 => 티켓팅이 끝난 고객은 TicketProcessingQueue 에서 빠져나와 CustomerReadyforTrainQueue 로 이동한다.
 	 
  - CustomerTicketReadyQueue	: 역에 도착한 고객을 담을 리스트. CustomerOriginalDataQueue 에서 고객의 역 도착시간에 맞춰 CustomerTicketReadyQueue에 한 명씩 담아둔다.
  - CustomerReadyforTrainQueue	:
 
 ****************************************************/

public class QueueStruct {
	public static ArrayList<CustomerInfo> CustomerOriginalDataQueue = new ArrayList<CustomerInfo>();	// 고객에 대한 최초의 모든 정보들(id,이름,티켓팅 소요시간, 출발역, 도착역)
	public static ArrayList<CustomerInfo> CustomerTicketReadyQueue = new ArrayList<CustomerInfo>();		// 역에 도착한 고객을 담을 리스트(도착 시간이 빠른 순서대로 리스트에 담김)
	
	//public static ArrayList<CustomerInfo> TicketProcessingQueue = new ArrayList<CustomerInfo>();		// 고객 3명씩 들어와서 티켓팅을 처리하는 큐
	public static CustomerInfo[] TicketProcessingQueue = new CustomerInfo[3];							// 고객 3명씩 들어와서 티켓팅을 처리하는 큐
	
	public static ArrayList<CustomerInfo> CustomerReadyforTrainQueue = new ArrayList<CustomerInfo> ();	// 티켓팅 처리가 끝난 고객이 기차를 기다리기 위해 들어가는 큐
	public static ArrayList<CustomerInfo> TrainAtPlatformQueue = new ArrayList<CustomerInfo>();			// 기차가 역 플랫폼에 매 3분마다 도착하고 대기하는 고객을 태워가는 큐.
	public static ArrayList<CustomerInfo> CustomerFinalDataQueue = new ArrayList<CustomerInfo>(); 		// 목적지에 도착한 고객의 최종 데이터를 담는 큐(최초 고객 original 정보 + 티켓팅 대기시간, 열차 대기시간, 열차 출발시간, 열차 도착시간)
	
	public static void AllDisplayOfQueueInfo(ArrayList<CustomerInfo> al) {	// 큐에 있는 모든 정보를 보여준다.
		Iterator<CustomerInfo> ir = al.iterator();
		while(ir.hasNext()) {
			System.out.println(ir.next());
		}
		
	}
	
	public static void AllWriteOfQueueInfo(ArrayList<CustomerInfo> al, String fileName) throws Exception {	// 큐에 있는 모든 정보를 CSV 파일로 쓴다.
		Iterator<CustomerInfo> ir = al.iterator();
		String source = "";
		FileWriter fw = new FileWriter(fileName);;
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("ID," + "이름,"+"역 도착시간,"+"티케팅시간,"+"출발역,"+"도착역,"+"최소경로시간,"+"티켓대기시간,"+"열차대기시간,"+"열차출발시간,"+"열차도착시간\n");
		while(ir.hasNext()) {
			source = ir.next().toString();	
			bw.write(source);	
		}
		bw.close();
	}
}
