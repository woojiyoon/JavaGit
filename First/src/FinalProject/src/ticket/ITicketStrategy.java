package ticket;

public interface ITicketStrategy {
	public void startToEnd();
	public void sendOriginalCustomerToTicketReadyQueue();
	public void sendTicketReadyCustomerToTicketProcessingQueue();
	public void sendCustomerAtPlatformToGettingOnTrain ();
} 
