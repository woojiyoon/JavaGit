package ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/***************************************************
 
  ť Ŭ����
  - TicketProcessingQueue 		: ���ÿ� 3���� �� �� �ִ� Ƽ�Ϻν� ť
 	 => ������ 3���� �ֱ� ������, Ƽ�Ϻν��� ���� ���ÿ� 3���� ���� Ƽ������ �Ѵ�.
 	 => �� ���� Ƽ������ ������ �� �ɸ��� �ð��� ������ Ƽ�ϼҿ�ð��� �����Ѵ�. 
 	 => Ƽ������ ���� ���� TicketProcessingQueue ���� �������� CustomerReadyforTrainQueue �� �̵��Ѵ�.
 	 
  - CustomerTicketReadyQueue	: ���� ������ ���� ���� ����Ʈ. CustomerOriginalDataQueue ���� ���� �� �����ð��� ���� CustomerTicketReadyQueue�� �� �� ��Ƶд�.
  - CustomerReadyforTrainQueue	:
 
 ****************************************************/

public class QueueStruct {
	public static ArrayList<CustomerInfo> CustomerOriginalDataQueue = new ArrayList<CustomerInfo>();	// ���� ���� ������ ��� ������(id,�̸�,Ƽ���� �ҿ�ð�, ��߿�, ������)
	public static ArrayList<CustomerInfo> CustomerTicketReadyQueue = new ArrayList<CustomerInfo>();		// ���� ������ ���� ���� ����Ʈ(���� �ð��� ���� ������� ����Ʈ�� ���)
	
	//public static ArrayList<CustomerInfo> TicketProcessingQueue = new ArrayList<CustomerInfo>();		// �� 3�� ���ͼ� Ƽ������ ó���ϴ� ť
	public static CustomerInfo[] TicketProcessingQueue = new CustomerInfo[3];							// �� 3�� ���ͼ� Ƽ������ ó���ϴ� ť
	
	public static ArrayList<CustomerInfo> CustomerReadyforTrainQueue = new ArrayList<CustomerInfo> ();	// Ƽ���� ó���� ���� ���� ������ ��ٸ��� ���� ���� ť
	public static ArrayList<CustomerInfo> TrainAtPlatformQueue = new ArrayList<CustomerInfo>();			// ������ �� �÷����� �� 3�и��� �����ϰ� ����ϴ� ���� �¿����� ť.
	public static ArrayList<CustomerInfo> CustomerFinalDataQueue = new ArrayList<CustomerInfo>(); 		// �������� ������ ���� ���� �����͸� ��� ť(���� �� original ���� + Ƽ���� ���ð�, ���� ���ð�, ���� ��߽ð�, ���� �����ð�)
	
	public static void AllDisplayOfQueueInfo(ArrayList<CustomerInfo> al) {	// ť�� �ִ� ��� ������ �����ش�.
		Iterator<CustomerInfo> ir = al.iterator();
		while(ir.hasNext()) {
			System.out.println(ir.next());
		}
		
	}
	
	public static void AllWriteOfQueueInfo(ArrayList<CustomerInfo> al, String fileName) throws Exception {	// ť�� �ִ� ��� ������ CSV ���Ϸ� ����.
		Iterator<CustomerInfo> ir = al.iterator();
		String source = "";
		FileWriter fw = new FileWriter(fileName);;
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("ID," + "�̸�,"+"�� �����ð�,"+"Ƽ���ýð�,"+"��߿�,"+"������,"+"�ּҰ�νð�,"+"Ƽ�ϴ��ð�,"+"�������ð�,"+"������߽ð�,"+"���������ð�\n");
		while(ir.hasNext()) {
			source = ir.next().toString();	
			bw.write(source);	
		}
		bw.close();
	}
}
