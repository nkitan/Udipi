import java.net.*;
import java.io.*;

class UDPClient {
    private DatagramSocket UDPSocket;
    private byte[] Buffer;
    private InetAddress Host;
    private Integer Port;

    public UDPClient(String Host, Integer Port){
        try{
            this.Host = InetAddress.getByName(Host);
        } catch(UnknownHostException e){
            System.out.println("[CLIENT] - " + e.getMessage());
        }
        
        try{
            this.UDPSocket = new DatagramSocket();
        } catch (SocketException e){
            System.out.println("[CLIENT][SOCKET] - " + e.getMessage());
        }   
        
        this.Buffer = new byte[1024];
        this.Port = Port;
    }
    
    private String SendRequest(String message){
        String logString = "";
        try {
            DatagramPacket request = new DatagramPacket(message.getBytes(), message.getBytes().length, this.Host, this.Port);
            UDPSocket.send(request);

            DatagramPacket response = new DatagramPacket(this.Buffer, this.Buffer.length);
            UDPSocket.receive(response);

            if(message.equals("STOP")){
                this.Stop();
                return "STOPPED";
            }

            return (new String(response.getData()));
            } catch (SocketException e) {
                logString = ("[CLIENT][SOCKET] - " + e.getMessage());
            } catch (IOException e){
                logString = ("[CLIENT][IO] - " + e.getMessage());
            }   
        return logString;
    }

    public String SendMessage(String message){
        return this.SendRequest(message);
    }

    public void Stop(){
        this.UDPSocket.close();
    }
    public static void main(String[] args) {
        if(args.length < 3){
            System.out.println("USAGE: java UDPClient <Host Name> <Port Number> <Message>");
            System.exit(1);
        }

        UDPClient UDPclient = new UDPClient(args[0], Integer.valueOf(args[1]).intValue());
        System.out.println(UDPclient.SendRequest(args[2]));
    }
}
