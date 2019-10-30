package com.istiaque.EVM.controllar;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 28/Oct/2019.
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    @ResponseBody
    public String getLog(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String paramDate = request.getParameter("date");

        try {
//            String logPath = Paths.get("../").toRealPath().toString();
            StringBuilder sb = new StringBuilder("<html><body>");
            String logPath = "C:/log/log.log";
            File logFile = new File(logPath);
            if (!logFile.isFile()) {
                if (paramDate != null && !paramDate.isEmpty())
                    return "No Error file is generated on " + paramDate + " " + "<br/> <b>server log file name </b>" + logPath;
                else
                    return "No Error file is generated on " + sdf.format(new Date()) + " " + "<br/> <b>server log file name </b> " + logPath;
            }
            sb.append("<table style='border-collapse: collapse; width: 100%;'>");

            sb.append("<tr><th style='padding-top: 12px;padding-bottom: 12px;text-align: center;background-color: #4CAF50;color: white;'>Date</th>" +
                    "<th style='padding-top: 12px;padding-bottom: 12px;text-align: center;background-color: #4CAF50;color: white;'>Time</th>" +
                    "<th style='padding-top: 12px;padding-bottom: 12px;text-align: center;background-color: #4CAF50;color: white;'>Type</th>" +
                    "<th style='padding-top: 12px;padding-bottom: 12px;text-align: center;background-color: #4CAF50;color: white;'>Class Name</th>" +
                    "<th style='padding-top: 12px;padding-bottom: 12px;text-align: center;background-color: #4CAF50;color: white;'>Method Name</th>" +
                    "<th style='padding-top: 12px;padding-bottom: 12px;text-align: center;background-color: #4CAF50;color: white;'>Reason</th></tr>");
            if (logFile.canRead()) {
                JsonObject jsonObject = null;
                Charset charset = Charset.forName("ISO-8859-1");
                List<String> logLines = Files.readAllLines(Paths.get(logPath), charset);
                Collections.sort(logLines, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o2.compareTo(o1);
                    }
                });
                String value="", date="", time="";
                for (String log : logLines) {
                    sb.append("<tr>");
                    jsonObject = new JsonParser().parse(log).getAsJsonObject();
                    value=(jsonObject.get("ts").toString().length()>2)?jsonObject.get("ts").toString().substring(1,jsonObject.get("ts").toString().length()-1):null;
                    date=(value != null)?value.substring(0,10):null;
                    time=(value != null)?value.substring(11,23):null;
                    sb.append("<td style='border: 1px solid #ddd;padding: 8px;text-align: center;'>");
                    sb.append(date);
                    sb.append("</td>");
                    sb.append("<td style='border: 1px solid #ddd;padding: 8px;text-align: center;'>");
                    sb.append(time);
                    sb.append("</td>");
                    value=(jsonObject.get("level").toString().length()>2)?jsonObject.get("level").toString().substring(1,jsonObject.get("level").toString().length()-1):null;
                    sb.append("<td style='border: 1px solid #ddd;padding: 8px;text-align: center;'>");
                    sb.append(value);
                    sb.append("</td>");
                    value=(jsonObject.get("class").toString().length()>2)?jsonObject.get("class").toString().substring(1,jsonObject.get("class").toString().length()-1):null;
                    sb.append("<td style='border: 1px solid #ddd;padding: 8px;text-align: center;'>");
                    sb.append(value);
                    sb.append("</td>");
                    value=(jsonObject.get("method").toString().length()>2)?jsonObject.get("method").toString().substring(1,jsonObject.get("method").toString().length()-1):null;
                    sb.append("<td style='border: 1px solid #ddd;padding: 8px;text-align: center;'>");
                    sb.append(value);
                    sb.append("</td>");
                    sb.append("<td style='border: 1px solid #ddd;padding: 8px;text-align: left;'>");
                    value=jsonObject.get("msg").toString();
                    value=(value.substring(1, value.length()-1).equals("{}"))? jsonObject.get("stack").toString().replace("\\r\\n", "</br>").replace("\\n", "</br>").replace("\\t","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").replace("(", "(<a href=#>").replace(")", "</a>)").replace("'", "") : value.substring(1, value.length()-1);
                    sb.append("<button id='"+value+"' onclick=show(this.id);>Show Details</button>");
                    sb.append("</td>");
                    sb.append("</tr>");
                }
                sb.append("</table>");
                sb.append("<button id=\"myBtn\" style='display:none'>Open Modal</button>" +
                        "<div id=\"myModal\" class=\"modal\">\n" +
                        "\n" +
                        "  <!-- Modal content -->\n" +
                        "  <div class=\"modal-content\">\n" +
                        "    <span class=\"close\">&times;</span>\n" +
                        "    <p id=\"modal-value\">Some text in the Modal..</p>\n" +
                        "  </div>\n" +
                        "\n" +
                        "</div>");
                sb.append("<script>" +
                        "function show(value){" +
                        "document.getElementById(\"modal-value\").innerHTML = value;" +
                        "modal.style.display = \"block\";" +
                        "}" +
                        "" +
                        "// Get the modal\n" +
                        "var modal = document.getElementById(\"myModal\");\n" +
                        "\n" +
                        "// Get the button that opens the modal\n" +
                        "var btn = document.getElementById(\"myBtn\");\n" +
                        "\n" +
                        "// Get the <span> element that closes the modal\n" +
                        "var span = document.getElementsByClassName(\"close\")[0];\n" +
                        "\n" +
                        "// When the user clicks the button, open the modal \n" +
                        "btn.onclick = function() {\n" +
                        "  modal.style.display = \"block\";\n" +
                        "}\n" +
                        "\n" +
                        "// When the user clicks on <span> (x), close the modal\n" +
                        "span.onclick = function() {\n" +
                        "  modal.style.display = \"none\";\n" +
                        "}\n" +
                        "\n" +
                        "// When the user clicks anywhere outside of the modal, close it\n" +
                        "window.onclick = function(event) {\n" +
                        "  if (event.target == modal) {\n" +
                        "    modal.style.display = \"none\";\n" +
                        "  }\n" +
                        "}" +
                        "" +
                        "</script>");
                sb.append("<style>\n" +
                        "body {font-family: Arial, Helvetica, sans-serif;}\n" +
                        "\n" +
                        "#modal-value{font-size:14px}" +
                        "" +
                        "/* The Modal (background) */\n" +
                        ".modal {\n" +
                        "  display: none; /* Hidden by default */\n" +
                        "  position: fixed; /* Stay in place */\n" +
                        "  z-index: 1; /* Sit on top */\n" +
                        "  padding-top: 100px; /* Location of the box */\n" +
                        "  left: 0;\n" +
                        "  top: 0;\n" +
                        "  width: 100%; /* Full width */\n" +
                        "  height: 100%; /* Full height */\n" +
                        "  overflow: auto; /* Enable scroll if needed */\n" +
                        "  background-color: rgb(0,0,0); /* Fallback color */\n" +
                        "  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */\n" +
                        "}\n" +
                        "\n" +
                        "/* Modal Content */\n" +
                        ".modal-content {\n" +
                        "  background-color: #fefefe;\n" +
                        "  margin: auto;\n" +
                        "  padding: 20px;\n" +
                        "  border: 1px solid #888;\n" +
                        "  width: 80%;\n" +
                        "}\n" +
                        "\n" +
                        "/* The Close Button */\n" +
                        ".close {\n" +
                        "  color: #aaaaaa;\n" +
                        "  float: right;\n" +
                        "  font-size: 28px;\n" +
                        "  font-weight: bold;\n" +
                        "}\n" +
                        "\n" +
                        ".close:hover,\n" +
                        ".close:focus {\n" +
                        "  color: #000;\n" +
                        "  text-decoration: none;\n" +
                        "  cursor: pointer;\n" +
                        "}\n" +
                        "</style>");
                sb.append("</body></html>");
                return sb.toString();
            } else {
                return "Cannot read " + logPath;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
