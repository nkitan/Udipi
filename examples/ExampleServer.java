public class ExampleServer {
  public static void main(String args[]){
    UDPServer server = new UDPServer(8080); // Create a server to listen on port 8080
    server.Listen(0.0); // start listening on port 8008 without simulated losses
   
    // print received messages
    for(String message: server.getReceivedMessages()){
      System.out.println(message);
    }
    
    Boolean stop = false;
    if(stop){
	    server.Stop();// stop the server manually
  	
    }
  }
}
