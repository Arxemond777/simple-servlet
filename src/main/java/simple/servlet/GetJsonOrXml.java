package simple.servlet;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class GetJsonOrXml extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null)
            type = "";

        switch (type) {
            case "json":
                getJson(response);
                break;
            case "xml":
                getXml(response);
                break;
            case "bd_xml":
                getBd(response);
                break;
            default:
                getText(response);
        }
    }

    protected void getXml(HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.append("<sales_description>");
        writer.append("<a>xml</a>");
        writer.append("</sales_description>");
        response.getWriter().close();
    }

    protected void getJson(HttpServletResponse response) throws IOException {
        JSONObject json      = new JSONObject();
        JSONArray addresses  = new JSONArray();
        JSONObject address;
        try {
            for (int i = 0; i < 15; i++) {
                address = new JSONObject();
                address.put("CustomerName"     , "Decepticons" + i);
                address.put("AccountId"        , "1999" + i);
                address.put("SiteId"           , "1888" + i);
                address.put("Number"           , "7" + i);
                address.put("Building"         , "StarScream Skyscraper" + i);
                address.put("Street"           , "Devestator Avenue" + i);
                address.put("City"             , "Megatron City" + i);
                address.put("ZipCode"          , "ZZ00 XX1" + i);
                address.put("Country"          , "CyberTron" + i);
                addresses.put(address);
            }
            json.put("Addresses", addresses);
        } catch (JSONException jse) {
            jse.printStackTrace();
        }

        response.setContentType("application/json");
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    protected void getText(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().write("Hello world plain text response.");
        response.getWriter().close();
    }

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test?useSSL=false";

    static final String USER = "root";
    static final String PASS = "123";

    protected  void /*Set<Map<String, String>>*/ getBd(HttpServletResponse response) throws IOException {

        Connection conn = null;
        Statement stmt = null;
        Set<Map<String, String>> item = new HashSet<Map<String, String>>();
        try{
            Class.forName(JDBC_DRIVER).newInstance();

            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name FROM test.test";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));

                item.add(map);
            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do

            try{
                if (conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }

            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.append("<sales_description>");

                Iterator<Map<String, String>> it = item.iterator();
                while (it.hasNext()) {
                    writer.append("<data>");
                        for (Map.Entry<String, String> e : it.next().entrySet())
                            writer.append("<" + e.getKey() + ">" + e.getValue() + "</" + e.getKey() + ">");
                    writer.append("</data>");
                }

            writer.append("</sales_description>");
            writer.close();
        }
    }
}
