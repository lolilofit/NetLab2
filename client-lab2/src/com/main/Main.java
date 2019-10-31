package com.main;
import com.main.protocol.Hello;
import com.main.protocol.ServerAnswer;
import sun.reflect.misc.FieldUtil;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket clientSocket = null;
        try {
            Scanner console = new Scanner(new InputStreamReader(System.in, "UTF-8"));
            String filePath = console.nextLine();
            String[] parts = filePath.split("/");
            String fileName = parts[parts.length-1];
            //String serverAddr = console.nextLine();
            String serverAddr = "localhost";

            OutputStream socketOut;
            InputStream socketIn;
            clientSocket = new Socket(serverAddr, 8082);

            File in = new File(filePath);
            FileInputStream in_reader = new FileInputStream(in);
            Hello hello = new Hello();
            hello.setFileName(fileName);
            hello.setFileSize(in.length());
            socketIn = clientSocket.getInputStream();
            socketOut = clientSocket.getOutputStream();

            ObjectOutputStream objectOutput = new ObjectOutputStream(socketOut);
            ObjectInputStream objectInput = new ObjectInputStream(socketIn);
            objectOutput.writeObject(hello);

            byte[] message = new byte[20];
            int readen = 0;
            while ((readen = in_reader.read(message)) != -1) {
                byte[] ret = new byte[readen];
                for(int i = 0; i < readen; i++)
                    ret[i] = message[i];
                objectOutput.writeObject(ret);
            }

            ServerAnswer answer = (ServerAnswer) objectInput.readObject();
            System.out.println(answer.getDescription());
        }catch (SocketException e) {
            e.printStackTrace();
            clientSocket.close();
            return;
        }
        clientSocket.close();
    }
}
