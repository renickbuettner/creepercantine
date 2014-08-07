import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;

import com.philip260897.events.ConnectEvent;
import com.philip260897.events.DisconnectEventListener;
import com.philip260897.events.MessageEvent;
import com.philip260897.events.MessageEventListener;
import com.philip260897.events.StatusChangedEvent;
import com.philip260897.events.StatusChangedEventListener;
import com.philip260897.server.Client;
import com.philip260897.server.ClientMessage;
import com.philip260897.server.MessageType;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//java.util.Date date= new java.util.Date();
		System.out.println((int) (System.currentTimeMillis() / 1000L));
		
		System.out.println("Connecting...");
		Client client = new Client("Philip");
		client.openConnection("10.0.0.3", 25565);
		asd;fkjasld;fkjasld;fkjas;fkldjkldsfja;fjkds;kadsfj
		
		client.addMessageEvent(new MessageEventListener()
		{
			@Override
			public void OnMessageEvent(MessageEvent event) 
			{
				ClientMessage message = event.getMessage();
				System.out.println(message.getSender()+": " + message.getMessage());
			}
		});
		
		client.addDisconnectEvent(new DisconnectEventListener()
		{
			@Override
			public void OnDisconnectEvent(ConnectEvent event) 
			{
				System.out.println("["+event.getUsername() + "] " + event.getReason());
			}
		});
		
		client.addStatusChangedEvent(new StatusChangedEventListener()
		{
			@Override
			public void OnStatusChangedEvent(StatusChangedEvent event) 
			{
				System.out.println("["+event.getType() + "] " + event.getMessage());
			}
		});
		
		boolean keep = true;
		while(keep && client.isConnected()){
			String m= readLine();
			if(!m.equals("shutdown")) {
				ClientMessage message = new ClientMessage(MessageType.Global,"Philip", "message", m);
				client.sendMessage(message);
			} else {
				keep = false;
			}
		}
		client.shutdown();
	}
	
	public static String readLine()
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
