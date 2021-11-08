# Udipi
super simple UDP client server implementation in java

## About
Can be used as a standalone application or implemented by creating an object of the class.
Supports command line arguments as well as interactive user input.

## Dependencies
There are no external dependencies except the java standard libraries

## Usage
#### * Standalone

1. Compile both files  
`javac UDPServer.java UDPClient.java`  

2. Start the server  
`java UDPServer <Port Number>`  

3. Fire up the client and send requests  
`java UDPClient <Host Name> <Port Number> <Message>`  

#### * As Class  

##### [Server]
Creating an instance of the UDPServer Class  
`UDPServer server = new UDPServer(Integer Port);`  

Start listening on a port  
`server.Listen(Double Lossiness)`  
This will start the server on the specified port.  

The server can be stoppped using the public void function Stop()  
`server.Stop();`  
or by receiving a message string "STOP"  

One can also see all received messages using the public void function showReceivedMessages()     
`server.showReceivedMessages();`  

or retrieve a String ArrayList of received Messages using the public ArrayList<String> function getReceivedMessages()  
`ArrayList<String> received = server.getReceivedMessages();`


##### [Client]
Creating an instance of the UDPClient Class   
`UDPClient client = new UDPClient(String Host, Integer Port);`  

Sending a message uses the public String function SendMessage(String Message)  
`client.SendMessage("Hello World!");`  

Once sending is complete, the client can be stopped using the public void function Stop()  
`client.Stop();`  
  
 
 
  
 There are examples of client-server implementation in the examples folder. To compile, ensure UDPClient.java and UDPServer.java exist in the same directory as ExampleServer.java and ExampleClient.java.
  
 `javac UDPServer.java UDPClient.java ExampleServer.java ExampleClient.java`

 Then, fire up the server and the client independently
  `java ExampleServer`  
  
  `java ExampleClient`    
