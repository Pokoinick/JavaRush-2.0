package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Станислав on 23.05.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())){
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }

    public static void sendBroadcastMessage(Message message){
        for (Map.Entry<String, Connection> m: connectionMap.entrySet()) {
            try {
                m.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения");
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String userName = "";
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом " + socket.getRemoteSocketAddress());
            try(Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при работе с сервером");
            } catch (ClassNotFoundException ex) {
                ConsoleHelper.writeMessage("Ошибка при работе с сервером");
            }
            if(!userName.isEmpty()){
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message request = new Message(MessageType.NAME_REQUEST);
            Message answer;

            while (true) {
                connection.send(request);
                answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME)
                    if(!answer.getData().isEmpty())
                        if (!connectionMap.containsKey(answer.getData()))
                            break;
            }

            connectionMap.put(answer.getData(), connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED));

            return answer.getData();
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> m: connectionMap.entrySet()) {
                if(!m.getKey().equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, m.getKey()));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT,userName + ": " + message.getData()));
                }
                else
                    ConsoleHelper.writeMessage("Error");

            }
        }
    }
}
