package ticket;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

/****************************************************
 * 
 *  ���� ���μ��� ó����, �̱������� ����.
 *
 **************************************************/


public class FileProcess {
	
	private static FileProcess fp = new FileProcess();
	public String fileName;	// �о���� csv, xls ���� ��� ����
	BufferedReader br = null;
	String line = "";	// readLine()�� ���� ����
	String[] afterSplit;
	
	ShortPath shortPath;
	
	private FileProcess() { }
	
	public static FileProcess getInstance() {
		if(fp == null)
			fp = new FileProcess();
		return fp;
	}
	
	public void CSVFileRead(String fileName) throws IOException {
		
		br = new BufferedReader(new FileReader(new File(fileName)));
		line = br.readLine();	// ù ������ �׳� ������
		
		while((line = br.readLine()) != null) {
			afterSplit = line.split(",");
			//CustomerInfoList.add(insertCustomerInfo(afterSplit));
			QueueStruct.CustomerOriginalDataQueue.add(insertCustomerInfo(afterSplit));
		}
			
	}
	
	public void CSVFileWrite(String fileName) {
		try {
			QueueStruct.AllWriteOfQueueInfo(QueueStruct.CustomerFinalDataQueue, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CustomerInfo insertCustomerInfo(String[] str) {
		CustomerInfo customerinfo = new CustomerInfo();
		
		// ������� ���� ���� : id,���̸�,�� �����ð�, Ƽ���üҿ�ð�, ��߿�, ������
		customerinfo.setIdOfCustomer(Integer.parseInt(str[0]));						// id
		customerinfo.setNameOfCustomer(str[1]);										// ���̸�
		customerinfo.setTimeOfCustomerArrivalAtStation(Integer.parseInt(str[2]));	// ���� ���� ������ �ð�
		customerinfo.setTimeOfCustomerTicketing(Integer.parseInt(str[3]));			// ���� Ƽ���� �ҿ�ð�
		customerinfo.setNameOfDepartureCity(str[4]);								// ��߿�
		customerinfo.setNameOfArrivalCity(str[5]);									// ������
		
		// �����, ������ ������ �̿��� Dijkstra �˰����� ������ ���� �ּ� ��� ����(�ּ��̵��ð�) set �ϱ�
		customerinfo.setTimeDurationOfTrain(shortPathProcess(str[4], str[5], Define.TotalNumberOfCity));
		
		return customerinfo;
		
	}
	
	
	
	
	
	
	/**************************************************************************
	 
	 ���� original �������� ��߿��� ������ ������ "Dijkstra" �˰��� �־ �ִ� �Ÿ��� ���ϴ� �κ�
	 
	 *************************************************************************/
	
	public int shortPathProcess(String start, String destination, int totalNumberOfCity) {
		
		int tempStart = -1;
		int tempDestination = -1;
		int minValueOfPath = -1;
		
		if(start.equals("Seoul")) 
			tempStart = Define.Seoul;		// 0
		else if(start.equals("Chuncheon"))
			tempStart = Define.Chuncheon;	// 1
		else if(start.equals("Wonju"))
			tempStart = Define.Wonju;		// 2
		else if(start.equals("Asan"))
			tempStart = Define.Asan;		// 3
		else if(start.equals("Kyungju"))
			tempStart = Define.Gyeongju;	// 4
		else if(start.equals("Deajeon"))
			tempStart = Define.Deajeon;		// 5
		else if(start.equals("Gwangju"))
			tempStart = Define.Gwangju;		// 6
		else
			tempStart = -1;
		
		
		if(destination.equals("Seoul")) 
			tempDestination = Define.Seoul;
		else if(destination.equals("Chuncheon"))
			tempDestination = Define.Chuncheon;
		else if(destination.equals("Wonju"))
			tempDestination = Define.Wonju;
		else if(destination.equals("Asan"))
			tempDestination = Define.Asan;
		else if(destination.equals("Kyungju"))
			tempDestination = Define.Gyeongju;
		else if(destination.equals("Deajeon"))
			tempDestination = Define.Deajeon;
		else if(destination.equals("Gwangju"))
			tempDestination = Define.Gwangju;
		else
			tempDestination = -1;
		
		if(tempStart == -1 || tempDestination == -1) {
			System.out.println("�߸��Է� �� ���� �̸� �Դϴ�.");
			return 0;
		}
		else {
			shortPath = new ShortPath();	// �ν��Ͻ� ����
			minValueOfPath = shortPath.shortestPath(tempStart, tempDestination, Define.TotalNumberOfCity);
			return minValueOfPath;
		}
	}
	
}
