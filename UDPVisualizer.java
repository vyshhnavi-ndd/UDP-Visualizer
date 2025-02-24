import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPVisualizer extends JFrame {
    private JTextArea textArea;

    public UDPVisualizer() {
        setTitle("UDP Visualizer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        setVisible(true);
    }

    public void appendText(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(text + "\n"));
    }

    public static void main(String[] args) {
        UDPVisualizer visualizer = new UDPVisualizer();

        new Thread(() -> {
            int port = 9876; // Port to listen on
            DatagramSocket socket = null;
            try {
                socket = new DatagramSocket(port);
                byte[] buffer = new byte[1024]; // Buffer for incoming data

                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    visualizer.appendText("Received packet: " + received);
                }
            } catch (Exception e) {
                visualizer.appendText("Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            }
        }).start();
    }
}