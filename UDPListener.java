import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;

public class UDPListener {
    public static void main(String[] args) {
        int port = 9876; // Port to listen on
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(port);
            byte[] buffer = new byte[1024]; // Buffer for incoming data

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received packet from " + packet.getAddress().getHostAddress() + ":" + packet.getPort() + ": " + received);

                // Print packet details
                System.out.println("Packet Details:");
                System.out.println("Source Address: " + packet.getAddress());
                System.out.println("Source Port: " + packet.getPort());
                System.out.println("Packet Length: " + packet.getLength());
                System.out.println("Packet Data: " + received);
                System.out.println();
            }
        } catch (SocketException e) {
            System.err.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}