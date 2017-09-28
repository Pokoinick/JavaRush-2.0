package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Станислав on 23.05.2017.
 */
public class BotClient extends Client {
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected String getUserName() {
        return "date_bot_"+(int)(Math.random()*100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            SimpleDateFormat format = null;

           if(message != null && message.isEmpty() && !message.contains(": "))
                ConsoleHelper.writeMessage(message);
            String[] mess = message.split(":");
            if(mess.length == 2) {
                ConsoleHelper.writeMessage(message);
                if (mess[1].trim().equals("дата"))
                    format = new SimpleDateFormat("d.MM.YYYY");
                if (mess[1].trim().equals("день"))
                    format = new SimpleDateFormat("d");
                if (mess[1].trim().equals("месяц"))
                    format = new SimpleDateFormat("MMMM");
                if (mess[1].trim().equals("год"))
                    format = new SimpleDateFormat("YYYY");
                if (mess[1].trim().equals("время"))
                    format = new SimpleDateFormat("H:mm:ss");
                if (mess[1].trim().equals("час"))
                    format = new SimpleDateFormat("H");
                if (mess[1].trim().equals("минуты"))
                    format = new SimpleDateFormat("m");
                if (mess[1].trim().equals("секунды"))
                    format = new SimpleDateFormat("ss");
                if (format != null)
                    sendTextMessage("Информация для " + mess[0].trim() + ": " + format.format(System.currentTimeMillis()));
            }
        }
    }

}
