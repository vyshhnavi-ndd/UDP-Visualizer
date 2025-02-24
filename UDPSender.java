import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
    public static void main(String[] args) {
        String message = "Hello, UDP!";
        int port = 9876; // Port to send data to
        int bufferSize = 1024; // Buffer size for the packet

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost"); // or the IP address of the UDP visualizer
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.setBroadcast(true); // Enable broadcast
            socket.setSendBufferSize(bufferSize); // Set the send buffer size
            socket.send(packet);
            System.out.println("Packet sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}