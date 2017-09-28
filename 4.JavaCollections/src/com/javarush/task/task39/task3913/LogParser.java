package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;
import sun.rmi.runtime.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<LogData> logDatas = new ArrayList<>();
    private List<String> logs = new ArrayList<>();

    public LogParser(Path logDir) {
        this.logDir = logDir;
        readAllLogsInDir();
        parseLogsToObjects();
    }

    private void readAllLogsInDir() {
        for (File file : logDir.toFile().listFiles()) {
            if (file.isFile() && file.getName().endsWith(".log")) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.toString())))) {
                    while (reader.ready()) {
                        logs.add(reader.readLine());
                    }
                } catch (Exception e) {
                    System.out.println("Exception in reading logs from file");
                }
            }
        }
    }

    private void parseLogsToObjects() {
        for (String recordString : logs) {
            String[] recordStringArray = recordString.split("\\t");
            LogData record = new LogData();
            // ip адрес
            record.setIp(recordStringArray[0]);
            // имя пользователя
            record.setName(recordStringArray[1]);
            // дата
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
            try {
                Date date = dateFormat.parse(recordStringArray[2]);
                record.setDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // event
            record.setEvent(Event.valueOf(recordStringArray[3].split("\\s")[0]));
            // номер задачи
            if (recordStringArray[3].split("\\s").length > 1) {
                record.setTaskNumber(Integer.parseInt(recordStringArray[3].split("\\s")[1]));
            }
            // status
            record.setStatus(Status.valueOf(recordStringArray[4]));
            // добавление записи в список
            logDatas.add(record);
        }
    }

    private boolean isDateFromInterval(Date current, Date after, Date before) {
        boolean result = false;
        if (after == null) after = current;
        if (before == null) before = current;
        if ((current.getTime() >= after.getTime()) && (current.getTime() <= before.getTime())) result = true;
        return result;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before))
                result.add(data.getIp());
        }
        return result.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before))
                result.add(data.getIp());
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getName().equals(user))
                result.add(data.getIp());
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getEvent().equals(event))
                result.add(data.getIp());
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getStatus().equals(status))
                result.add(data.getIp());
        }
        return result;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            result.add(data.getName());
        }
        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before))
                result.add(data.getName());
        }
        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user))
                result.add(data.getEvent());
        }
        return result.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getIp().equals(ip))
                result.add(data.getName());
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getEvent().equals(Event.LOGIN))
                result.add(data.getName());
        }
        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                result.add(data.getName());
        }
        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getEvent().equals(Event.WRITE_MESSAGE))
                result.add(data.getName());
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getEvent().equals(Event.SOLVE_TASK))
                result.add(data.getName());
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getEvent().equals(Event.SOLVE_TASK)
                    && data.getTaskNumber() == task)
                result.add(data.getName());
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before) && data.getEvent().equals(Event.DONE_TASK))
                result.add(data.getName());
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getEvent().equals(Event.DONE_TASK)
                    && data.getTaskNumber() == task)
                result.add(data.getName());
        }
        return result;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user)
                    && data.getEvent().equals(event))
                result.add(data.getDate());
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getStatus().equals(Status.FAILED))
                result.add(data.getDate());
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getStatus().equals(Status.ERROR))
                result.add(data.getDate());
        }
        return result;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        List<Date> dates = new ArrayList<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user)
                    && data.getEvent().equals(Event.LOGIN))
                dates.add(data.getDate());
        }
        if (dates.isEmpty())
            return null;
        Date firstDate = new Date(Long.MAX_VALUE);
        for (Date date : dates) {
            if (firstDate.getTime() > date.getTime())
                firstDate = date;
        }
        return firstDate;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        List<Date> dates = new ArrayList<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user)
                    && data.getEvent().equals(Event.SOLVE_TASK)
                    && data.getTaskNumber() == task)
                dates.add(data.getDate());
        }
        if (dates.isEmpty())
            return null;
        Date firstDate = new Date(Long.MAX_VALUE);
        for (Date date : dates) {
            if (firstDate.getTime() > date.getTime())
                firstDate = date;
        }
        return firstDate;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        List<Date> dates = new ArrayList<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user)
                    && data.getEvent().equals(Event.DONE_TASK)
                    && data.getTaskNumber() == task)
                dates.add(data.getDate());
        }
        if (dates.isEmpty())
            return null;
        Date firstDate = new Date(Long.MAX_VALUE);
        for (Date date : dates) {
            if (firstDate.getTime() > date.getTime())
                firstDate = date;
        }

        return firstDate;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user)
                    && data.getEvent().equals(Event.WRITE_MESSAGE))
                result.add(data.getDate());
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user)
                    && data.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                result.add(data.getDate());
        }
        return result;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before))
                result.add(data.getEvent());
        }
        return result.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before))
                result.add(data.getEvent());
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getIp().equals(ip))
                result.add(data.getEvent());
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user))
                result.add(data.getEvent());
        }
        return result;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getStatus().equals(Status.FAILED))
                result.add(data.getEvent());
        }
        return result;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getStatus().equals(Status.ERROR))
                result.add(data.getEvent());
        }
        return result;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        List<Event> result = new ArrayList<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getEvent().equals(Event.SOLVE_TASK)
                    && data.getTaskNumber() == task)
                result.add(data.getEvent());
        }
        return result.size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        List<Event> result = new ArrayList<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getEvent().equals(Event.DONE_TASK)
                    && data.getTaskNumber() == task)
                result.add(data.getEvent());
        }
        return result.size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getTaskNumber() != 0
                    && getNumberOfAttemptToSolveTask(data.getTaskNumber(), after, before) != 0)
                result.put(data.getTaskNumber(), getNumberOfAttemptToSolveTask(data.getTaskNumber(), after, before));
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getTaskNumber() != 0
                    && getNumberOfSuccessfulAttemptToSolveTask(data.getTaskNumber(), after, before) != 0)
                result.put(data.getTaskNumber(), getNumberOfSuccessfulAttemptToSolveTask(data.getTaskNumber(), after, before));
        }
        return result;
    }

    private Set<Date> getAllDates(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before))
                result.add(data.getDate());
        }
        return result;
    }

    private Set<Date> getDatesForUsers(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user))
                result.add(data.getDate());
        }
        return result;
    }

    private Set<Status> getAllStatuses(Date after, Date before) {
        Set<Status> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before))
                result.add(data.getStatus());
        }
        return result;
    }

    private Set<Status> getStatusesForUser(String user, Date after, Date before) {
        Set<Status> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getName().equals(user))
                result.add(data.getStatus());
        }
        return result;
    }

    private Set<Status> getUsersForDates(Date date, Date after, Date before) {
        Set<Status> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getDate().getTime() == date.getTime())
                result.add(data.getStatus());
        }
        return result;
    }

    private Set<Status> getUsersForEvents(Event event, Date after, Date before) {
        Set<Status> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getEvent().equals(event))
                result.add(data.getStatus());
        }
        return result;
    }

    private Set<Status> getUsersForStatus(Status status, Date after, Date before) {
        Set<Status> result = new HashSet<>();
        for (LogData data : logDatas) {
            if (isDateFromInterval(data.getDate(), after, before)
                    && data.getStatus().equals(status))
                result.add(data.getStatus());
        }
        return result;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> res = new HashSet<>();
        if (query == null || query.isEmpty()) return res;
        Pattern p = Pattern.compile("get (ip|user|date|event|status)"
                + "( for (ip|user|date|event|status) = \"(.*?)\")?"
                + "( and date between \"(.*?)\" and \"(.*?)\")?");
        Matcher m = p.matcher(query);
        String field1 = null;
        String field2 = null;
        String value1 = null;
        Date dateFrom = null;
        Date dateTo = null;
        if (m.find()) {
            field1 = m.group(1);
            field2 = m.group(3);
            value1 = m.group(4);
            String d1 = m.group(6);
            String d2 = m.group(7);
            try {
                dateFrom = new SimpleDateFormat("d.M.yyyy H:m:s").parse(d1);
            } catch (Exception e) {
                dateFrom = null;
            }
            try {
                dateTo = new SimpleDateFormat("d.M.yyyy H:m:s").parse(d2);
            } catch (Exception e) {
                dateTo = null;
            }
            switch (field1) {
                case "ip": {
                    res.addAll(getAllIps(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "user": {
                    res.addAll(getAllUsers(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "date": {
                    res.addAll(getAllDates(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "event": {
                    res.addAll(getAllEvents(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "status": {
                    res.addAll(getAllStatuses(field2, value1, dateFrom, dateTo));
                    break;
                }
            }
        }
        return res;
    }

    private boolean isFieldMatch(String field, String value, LogData record) throws ParseException {
        boolean criteria = false;
        if (field == null) return true;
        if (value == null) return false;
        switch (field) {
            case "ip": {
                criteria = record.getIp().equals(value);
                break;
            }
            case "user": {
                criteria = record.getName().equals(value);
                break;
            }
            case "date": {
                criteria = record.getDate().equals(new SimpleDateFormat("d.M.yyyy H:m:s").parse(value));
                break;
            }
            case "event": {
                criteria = record.getEvent().equals(Event.valueOf(value));
                break;
            }
            case "status": {
                criteria = record.getStatus().equals(Status.valueOf(value));
                break;
            }
        }
        return criteria;
    }

    private Set<String> getAllIps(String field, String value, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogData record : logDatas) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    users.add(record.getIp());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return users;
    }
    private Set<Date> getAllDates(String field, String value, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LogData record : logDatas) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    dates.add(record.getDate());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return dates;
    }
    private Set<Status> getAllStatuses(String field, String value, Date after, Date before) {
        Set<Status> set = new HashSet<>();
        for (LogData record : logDatas) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    set.add(record.getStatus());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return set;
    }
    private Set<Event> getAllEvents(String field, String value, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (LogData record : logDatas) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    set.add(record.getEvent());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return set;
    }
    private Set<String> getAllUsers(String field, String value, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogData record : logDatas) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    users.add(record.getName());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return users;
    }
    private Set<String> getIpSet(Object recordField, Date after, Date before) {
        Set<String> ipSet = new HashSet<>();
        for (LogData record : logDatas) {
            if (isDateInside(after, before, record.getDate()) && isFieldMatch(recordField, record)) {
                ipSet.add(record.getIp());
            }
        }
        return ipSet;
    }
    private List<LogData> getParsedRecords(Path logDir) {
        List<LogData> recordList = new ArrayList<>();
        try {
            for (File file : logDir.toFile().listFiles()) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".log"))
                    for (String record : Files.readAllLines(file.toPath())) {
                        recordList.add(new LogData(record));
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordList;
    }
    private boolean isFieldMatch(Object recordField, LogData record) {
        boolean criteria = false;
        if (recordField == null)
            return true;
        if (recordField instanceof String)
            criteria = record.getName().equals(recordField);
        else if (recordField instanceof Event)
            criteria = record.getEvent().equals(recordField);
        else if (recordField instanceof Status)
            criteria = record.getStatus().equals(recordField);
        return criteria;
    }
    private boolean isDateInside(Date after, Date before, Date currentDate) {
        if (after != null) {
            if (currentDate.getTime() <= after.getTime())
                return false;
        }
        if (before != null) {
            if (currentDate.getTime() >= before.getTime())
                return false;
        }
        return true;
    }
}