public class ExampleClient {
 public static void main(){
  UDPClient client = new UDPClient();
  String response = "";
  
   // sending Hello World message
   response = client.SendMessage("Hello World!");
   
   
   // sending multiple messages
   for( int i = 0; i < 10; i++){
    response = client.SendMessage("Hello World!)";
    System.out.println(response);
   }
                                  
   // terminate client
   client.Stop();
 }
}
