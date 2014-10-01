package ticket;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

/****************************************************
 * 
 *  파일 프로세스 처리는, 싱글톤으로 만듬.
 *
 **************************************************/


public class FileProcess {
	
	private static FileProcess fp = new FileProcess();
	public String fileName;	// 읽어들일 csv, xls 파일 경로 변수
	BufferedReader br = null;
	String line = "";	// readLine()을 담을 변수
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
		line = br.readLine();	// 첫 라인은 그냥 버리고
		
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
		
		// 멤버변수 셋팅 순서 : id,고객이름,역 도착시간, 티케팅소요시간, 출발역, 도착역
		customerinfo.setIdOfCustomer(Integer.parseInt(str[0]));						// id
		customerinfo.setNameOfCustomer(str[1]);										// 고객이름
		customerinfo.setTimeOfCustomerArrivalAtStation(Integer.parseInt(str[2]));	// 고객이 역에 도착한 시간
		customerinfo.setTimeOfCustomerTicketing(Integer.parseInt(str[3]));			// 고객의 티켓팅 소요시간
		customerinfo.setNameOfDepartureCity(str[4]);								// 출발역
		customerinfo.setNameOfArrivalCity(str[5]);									// 도착역
		
		// 출발지, 도착지 정보를 이용해 Dijkstra 알고리즘을 돌려서 나온 최소 경로 값을(최소이동시간) set 하기
		customerinfo.setTimeDurationOfTrain(shortPathProcess(str[4], str[5], Define.TotalNumberOfCity));
		
		return customerinfo;
		
	}
	
	
	
	
	
	
	/**************************************************************************
	 
	 고객의 original 정보에서 출발역과 도착역 정보를 "Dijkstra" 알고리즘에 넣어서 최단 거리를 구하는 부분
	 
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
			System.out.println("잘못입력 된 도시 이름 입니다.");
			return 0;
		}
		else {
			shortPath = new ShortPath();	// 인스턴스 생성
			minValueOfPath = shortPath.shortestPath(tempStart, tempDestination, Define.TotalNumberOfCity);
			return minValueOfPath;
		}
	}
	
}
