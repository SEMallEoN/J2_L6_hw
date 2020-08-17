package J2_L6_hw;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public Server() {
        Scanner sc = new Scanner(System.in);
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(2222)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String strOut = sc.nextLine();
                if (strOut.equals("/end")) {
                    break;
                }
                out.writeUTF(strOut);

                String strIn = in.readUTF();
                if (strIn.equals("/end")) {
                    break;
                }
                System.out.println("Клиент: " + strIn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}