package J2_L6_hw;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String SERVER_ADDR = "localhost";
    public static final int SERVER_PORT = 2222;
    public static Socket socket;
    public static DataInputStream in;
    public static DataOutputStream out;

    public static void main(String[] args) {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openConnection() throws IOException {
        Scanner sc = new Scanner(System.in);
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        try {
            while (true) {
                String strIn = in.readUTF();
                if (strIn.equals("/end")) {
                    break;
                }
                System.out.println("Сервер: " + strIn);

                String strOut = sc.nextLine();
                if (strOut.equals("/end")) {
                    break;
                }
                out.writeUTF(strOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}