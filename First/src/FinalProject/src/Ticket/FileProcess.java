package Ticket;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;


public class FileProcess {
	private ArrayList<CustomerInfo> CustomerInfoList;	// 고객 인스턴스를 담을 arrayList
	public String fileName;	// 읽어들일 csv, xls 파일 경로 변수
	BufferedReader br = null;
	String line = "";	// readLine()을 담을 변수
	String[] afterSplit;
	
	public FileProcess(String fileName) throws IOException {
		CustomerInfoList = new ArrayList<CustomerInfo>();
		
		br = new BufferedReader(new FileReader(new File(fileName)));
		line = br.readLine();	// 첫 라인은 그냥 버리고
		
		while((line = br.readLine()) != null) {
			afterSplit = line.split(",");
			insertCustomerInfo(afterSplit);
		}
			
	}
	
	public void insertCustomerInfo(String[] str) {
		CustomerInfo customerinfo = new CustomerInfo();
		
		// 멤버변수 셋팅 순서 : id,고객이름,역 도착시간, 티케팅소요시간, 출발역, 도착역
		customerinfo.setIdOfCustomer(Integer.parseInt(str[0]));						// id
		customerinfo.setNameOfCustomer(str[1]);										// 고객이름
		customerinfo.setTimeOfCustomerArrivalAtStation(Integer.parseInt(str[2]));	// 고객이 역에 도착한 시간
		customerinfo.setTimeOfCustomerTicketing(Integer.parseInt(str[3]));			// 고객의 티켓팅 소요시간
		customerinfo.setNameOfDepartureCity(str[4]);								// 출발역
		customerinfo.setNameOfArrivalCity(str[5]);									// 도착역
	
		CustomerInfoList.add(customerinfo);
		
	}
	
	
	public void CustomerInfoListAllDisplay() {
		Iterator<CustomerInfo> ir = CustomerInfoList.iterator();
		while(ir.hasNext()) {
			System.out.println(ir.next());
		}
	}
	
}
