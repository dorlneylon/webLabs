package com.nylon.second.servlets.beans;

import com.google.gson.Gson;
import lombok.*;
import java.io.Serializable;
// import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Storage implements Serializable {
    private List<Row> list = Collections.synchronizedList(new LinkedList<>());

    public void add(Row row) {
        list.add(row);
    }

    public String getRows() {
        List<Row> rowList = this.getList();
        ListIterator<Row> rowListIterator = rowList.listIterator(rowList.size());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Collections.sort(rowList, new Comparator<Row>() {
            @Override
            public int compare(Row row1, Row row2) {
                try {
                    Date date1 = dateFormat.parse(row1.getCurrentTime());
                    Date date2 = dateFormat.parse(row2.getCurrentTime());
                    return date2.compareTo(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        StringBuilder rows = new StringBuilder();
        while (rowListIterator.hasPrevious()) {
            Row row = rowListIterator.previous();
            rows.append("<tr>")
                    .append("<td>").append(row.getX()).append("</td>")
                    .append("<td>").append(row.getY()).append("</td>")
                    .append("<td>").append(row.getR()).append("</td>")
                    .append("<td>").append(row.isResult()).append("</td>")
                    .append("<td>").append(row.getCurrentTime()).append("</td>")
                    .append("<td>").append(row.getExecutionTime()).append("</td>")
                    .append("</tr>");
        }
        return rows.toString();
    }

    public String getJson() {
        Set<String> jsonHitList = new HashSet<>();
        this.getList().forEach(row -> {
            String jsonRow = row.json();
            jsonHitList.add(jsonRow);
        });
        return new Gson().toJson(jsonHitList);
    }

    public void clear() {
        list.clear();
    }
}