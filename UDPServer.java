import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

class UDPServer {
    private DatagramSocket UDPSocket;
    private Integer Port;
    private byte[] Buffer;
    private ArrayList<String> ReceivedMessages;
    
    public UDPServer(Integer Port){
        try{
            this.Port = Port;
            this.UDPSocket = new DatagramSocket(this.Port);
            this.Buffer = new byte[1024];
            this.ReceivedMessages = new ArrayList<String>(1024);
        } catch (SocketException e){
            System.out.println("[SERVER][SOCKET] - " + e.getMessage());
        }
    }

    private void Start(Double lossiness){
        try {
            while(true){
                DatagramPacket request = new DatagramPacket(this.Buffer, this.Buffer.length);
                this.UDPSocket.receive(request);
                Random random = new Random();
                String message = new String(request.getData(), request.getOffset(), request.getLength());
                
                if(message.equals("STOP")){
                    System.out.println("[SERVER] STOPPING SERVER");
                    this.Stop();
                }

                if(random.nextDouble() < lossiness){
                    // Simulating an Unacknowleged Frame
                    System.out.println("[SERVER] UNACKNOWLEDGED PACKET " + message);
                    DatagramPacket reply = new DatagramPacket("UNACK".getBytes(), "UNACK".length(), request.getAddress(), request.getPort());
                    this.UDPSocket.send(reply);
                } else {
                    System.out.println("[SERVER] RECEIVED PACKET - " + message);
                    this.ReceivedMessages.add(message);
                    DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                    this.UDPSocket.send(reply);
                }
            }
        } catch (SocketException e) {
            System.out.println("[SERVER][SOCKET] - " + e.getMessage());
        } catch (IOException e){
            System.out.println("[SERVER][IO] - " + e.getMessage());
        } 
    }

    public void Listen(Double lossiness){
        System.out.println("[SERVER] LISTENING ON PORT " + this.Port);
        System.out.println("[SERVER] LOSS PROBABILITY: " + lossiness);
        this.Start(lossiness);
    }
    
    public void showReceivedMessages(){
        Integer greatest = 0;
        Integer current = 0;

        System.out.print("RECEIVED PACKETS: [ ");
        for (String message : this.ReceivedMessages) {
            try {
                current = Integer.parseInt(message);
            } catch(Exception e){
                System.out.println("[SERVER] CONVERSION ERROR " + e.getMessage());
            }

            if( current > greatest){
                greatest = current;
            }
            System.out.print(message + ", ");
        }
        System.out.print("]\n");

        System.out.print("LOST PACKETS: [ ");
        for(int i = 0; i < greatest.intValue(); i++){
            if(!this.ReceivedMessages.contains(String.valueOf(i))){
                System.out.print(String.valueOf(i) + ", ");
            }
        }
        System.out.print("]\n");
    }

    public void Stop(){
        this.showReceivedMessages();
        this.UDPSocket.close();
        System.exit(0);
    }
    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("USAGE: java UDPServer <PORT NUMBER> <LOSS PROBABILITY>");
            System.exit(1);
        }
        
        UDPServer UDPserver = new UDPServer(Integer.valueOf(args[0]).intValue());
        UDPserver.Start(Double.valueOf(args[1]));
    }
}
