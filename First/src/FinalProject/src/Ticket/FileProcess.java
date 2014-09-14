package Ticket;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;


public class FileProcess {
	private ArrayList<CustomerInfo> CustomerInfoList;	// �� �ν��Ͻ��� ���� arrayList
	public String fileName;	// �о���� csv, xls ���� ��� ����
	BufferedReader br = null;
	String line = "";	// readLine()�� ���� ����
	String[] afterSplit;
	
	public FileProcess(String fileName) throws IOException {
		CustomerInfoList = new ArrayList<CustomerInfo>();
		
		br = new BufferedReader(new FileReader(new File(fileName)));
		line = br.readLine();	// ù ������ �׳� ������
		
		while((line = br.readLine()) != null) {
			afterSplit = line.split(",");
			insertCustomerInfo(afterSplit);
		}
			
	}
	
	public void insertCustomerInfo(String[] str) {
		CustomerInfo customerinfo = new CustomerInfo();
		
		// ������� ���� ���� : id,���̸�,�� �����ð�, Ƽ���üҿ�ð�, ��߿�, ������
		customerinfo.setIdOfCustomer(Integer.parseInt(str[0]));						// id
		customerinfo.setNameOfCustomer(str[1]);										// ���̸�
		customerinfo.setTimeOfCustomerArrivalAtStation(Integer.parseInt(str[2]));	// ���� ���� ������ �ð�
		customerinfo.setTimeOfCustomerTicketing(Integer.parseInt(str[3]));			// ���� Ƽ���� �ҿ�ð�
		customerinfo.setNameOfDepartureCity(str[4]);								// ��߿�
		customerinfo.setNameOfArrivalCity(str[5]);									// ������
	
		CustomerInfoList.add(customerinfo);
		
	}
	
	
	public void CustomerInfoListAllDisplay() {
		Iterator<CustomerInfo> ir = CustomerInfoList.iterator();
		while(ir.hasNext()) {
			System.out.println(ir.next());
		}
	}
	
}
