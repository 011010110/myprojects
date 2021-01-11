package org.jiang.sharingjdbc;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Rep012JsonUtil {

    public static void main(String[] args) throws JSONException {
//        getSqlFromJson(LogInfo.data);
        getSqlFromJson(LogInfo.rep068);
    }

    private static void getSqlFromJson(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject rep012Data = jsonObject.getJSONObject("data");
        JSONObject reportData = jsonObject.getJSONObject("report");
        String fillId = jsonObject.getString("fill_id");
        getFactFillSql(jsonObject);
        getiRepData(rep012Data, fillId, "rep068");
//        System.out.printf("reportData:%s\n", reportData.toString());
    }

    private static String getiRepData(JSONObject jsonObject, String fillId, String reportCode) throws JSONException {
        jsonObject.put("fill_sn", "r001");
        jsonObject.put("fill_id", fillId);
        String sql = " insert into eimm_rep_" + reportCode + " (#{colums}) values(#{values});";
        StringBuffer sqlKey = new StringBuffer("");
        StringBuffer sqlValue = new StringBuffer("");
        Iterator jsonkeys = jsonObject.keys();
        while (jsonkeys.hasNext()) {
            String key = (String) jsonkeys.next();
            if (key.equals(reportCode + "_pkId")) {
                continue;
            }
            String value = jsonObject.getString(key);
            sqlKey.append("\"").append(key).append("\",");
            /*if(key.equals("pk_id")||key.equals("fill_id")||key.equals("fill_sn")){
                sqlValue.append("'").append(value).append("',");
            }else{
                sqlValue.append(value).append(",");
            }*/
            sqlValue.append("'").append(value).append("',");
        }
        String keys = sqlKey.substring(0, sqlKey.length() - 1);
        String values = sqlValue.substring(0, sqlValue.length() - 1);
        sql = sql.replace("#{colums}", keys);
        sql = sql.replace("#{values}", values);
        System.out.printf("%s\n", sql);
        return sql;
    }

    private static String getFactFillSql(JSONObject jsonObject) throws JSONException {
        jsonObject.put("parent_id", "0");
        String sql = " insert into eimm_rep_fact_fill (#{colums}) values(#{values});";
        StringBuffer sqlKey = new StringBuffer("");
        StringBuffer sqlValue = new StringBuffer("");
        String[] factFillColums = {"fill_id", "report_period", "fill_state", "report_id",
                "fill_dept", "fill_time", "fill_person", "refill_state", "parent_id", "rele_fill_id", "isend", "back_state"};
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("fill_id", "fill_id");
        keyMap.put("report_period", "report_period");
        keyMap.put("fill_state", "oper_fill_state");
        keyMap.put("report_id", "report_id");
        keyMap.put("fill_dept", "fill_dept");
        keyMap.put("fill_time", "time");
        keyMap.put("fill_person", "fill_person");
        keyMap.put("refill_state", "refill_state");
        keyMap.put("parent_id", "parent_id");
        keyMap.put("rele_fill_id", "rele_fill_id");
        keyMap.put("isend", "isend");
        keyMap.put("back_state", "back_state");
        Iterator<Map.Entry<String, String>> iterator = keyMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String jsonKey = next.getValue();
            String value = "null";
            if (jsonObject.has(jsonKey)) {
                if (jsonKey.equals("time")) {
                    long time = jsonObject.getLong(jsonKey);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(time);
                    String formatDate = sdf.format(date);
                    value = "'" + formatDate + "'";
                } else {
                    value = "'" + jsonObject.getString(jsonKey) + "'";
                }

            }
            sqlKey.append("\"").append(key).append("\",");
            sqlValue.append(value).append(",");
        }
        String keys = sqlKey.substring(0, sqlKey.length() - 1);
        String values = sqlValue.substring(0, sqlValue.length() - 1);
        sql = sql.replace("#{colums}", keys);
        sql = sql.replace("#{values}", values);
        System.out.printf("%s\n", sql);
        return sql.toString();
    }
}
