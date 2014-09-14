package Ticket;

import java.io.IOException;

public class TicketMain {

	public static void main(String[] args) throws IOException {
		FileProcess fp = new FileProcess("OriginalTicketInfo.csv");
		fp.CustomerInfoListAllDisplay();
	}

}
