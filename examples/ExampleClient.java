public class ExampleClient {
 public static void main(String args[]){
  UDPClient client = new UDPClient("127.0.0.1", 8080);
  String response = "";
  
   // sending Hello World message
   response = client.SendMessage("Hello World!");
   
   
   // sending multiple messages
   for( int i = 0; i < 10; i++){
    response = client.SendMessage("Hello World!");
    System.out.println(response);
   }
                                  
   // terminate client
   client.Stop();
 }
}
