package com.nylon.third.lab;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ManagedBean(name = "entriesBean")
@ApplicationScoped
public class EntriesBean implements Serializable {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private Entry entry;
    private List<Entry> entries;
    private final String jdbcUrl = externalContext.getInitParameter("jdbcUrl");
    private Connection connection;
    private final String jdbcUsername = externalContext.getInitParameter("jdbcName");
    private final String jdbcPassword = externalContext.getInitParameter("jdbcPassword");

    public EntriesBean() {
        entry = new Entry();
        entries = new ArrayList<>();
        try {
            connection = getConnection();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        loadEntries();
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
    }

    private void loadEntries() {
        entries.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM entry");
            while (resultSet.next()) {
                if (resultSet.getString("executionTime").isEmpty())
                    continue;
                Entry entry = new Entry();
                entry.setId(resultSet.getInt("id"));
                entry.setxValue(resultSet.getDouble("xvalue"));
                entry.setyValue(resultSet.getDouble("yvalue"));
                entry.setrValue(resultSet.getDouble("rvalue"));
                entry.sethitResult(resultSet.getBoolean("hitresult"));
                entry.setexecutionTime(resultSet.getString("executiontime"));
                entry.setresponseTime(resultSet.getDouble("responsetime"));
                entry.checkHit();
                entries.add(entry);
            }
            resultSet.close();
            statement.close();
            entry = new Entry();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String addEntry() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO entry (xvalue, yvalue, rvalue, hitresult, executiontime, responsetime) VALUES (?, ?, ?, ?, ?, ?)"
            );
            if (!entry.validateHit())
                return "redirect";
            // set current date in dd-mm-YY format
            double cur = (double) System.nanoTime()/1000000;
            DateFormat formatter = new SimpleDateFormat("dd-MM-YY");
            entry.setexecutionTime(formatter.format(Calendar.getInstance().getTime()));
            entry.checkHit();
            entry.setresponseTime((double)System.nanoTime()/1000000 - cur);
            preparedStatement.setDouble(1, entry.getxValue());
            preparedStatement.setDouble(2, entry.getyValue());
            preparedStatement.setDouble(3, entry.getrValue());
            preparedStatement.setBoolean(4, entry.gethitResult());
            preparedStatement.setString(5, entry.getexecutionTime());
            preparedStatement.setDouble(6, entry.getresponseTime());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            entries.add(entry); // Add the entry to the list
            entry = new Entry();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect"; // Return a navigation outcome
    }

    public String clearEntries() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM entry");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        entries.clear();
        return "redirect";
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}