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
}
