package com.javarush.task.task39.task3913;

import sun.rmi.runtime.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Станислав on 20.08.2017.
 */
public class LogData {
    private String ip;
    private String name;
    private Date date;
    private Event event;
    private int taskNumber;
    private Status status;

    public LogData(String ip, String user, Date date, Event event, Status status) {
        this.ip = ip;
        this.name = user;
        this.date = date;
        this.event = event;
        this.status = status;
    }

    public LogData(){
    }

    public LogData(String record) {
        String[] strings = record.split("\t");
        this.ip = strings[0].trim();
        this.name = strings[1];
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");
        try {
            date = dateFormat.parse(strings[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String eventAndParameter[] = strings[3].split(" ");
        event = Event.valueOf(eventAndParameter[0]);
        if (eventAndParameter.length > 1)
            taskNumber = Integer.parseInt(eventAndParameter[1]);
        status = Status.valueOf(strings[4]);
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public Status getStatus() {
        return status;
    }
}
