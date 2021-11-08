public class ExampleServer {
  public static void main(){
    UDPServer server = new UDPServer(8080); // Create a server to listen on port 8080
    server.Listen(0.0); // start listening on port 8008 without simulated losses
   
    // print received messages
    for(String message: server.getReceivedMessages()){
      System.out.println(message);
    }
    
    stop = false;
    stop? server.Stop() : continue; // stop the server manually
  }
}
