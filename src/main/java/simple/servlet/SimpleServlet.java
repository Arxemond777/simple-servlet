package simple.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class SimpleServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write("ok 4 " + new Date());

        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < 10; ++i) {
            list.add(i);
        }

        request.setAttribute("message", "Hi from servlet русский");
        request.setAttribute("list", list);

        SimlpeExampleQuery simlpeExampleQuery = new SimlpeExampleQuery();
        request.setAttribute("items", simlpeExampleQuery.simpleExample());

        request.getRequestDispatcher("WEB-INF/jsps/new-1.jsp").forward(request, response);
    }
}

class SimlpeExampleQuery
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost/EMP";
    static final String DB_URL = "jdbc:mysql://localhost/test?useSSL=false";

    static final String USER = "root";
    static final String PASS = "123";

    static Set<Map<String, String>> simpleExample() {
        Connection conn = null;
        Statement stmt = null;
        Set<Map<String, String>> item = new HashSet<Map<String, String>>();
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER).newInstance();

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name FROM test.test";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set

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
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try

            return item;
        }//end try
    }
}
