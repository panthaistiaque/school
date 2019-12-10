package com.istiaque.EVM.controllar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.istiaque.EVM.model.LogModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 27/Oct/2019.
 */
@Controller
public class IndexController {
    @Value("${application.log.path}")
    String logPath  ;
    List<LogModel> logModelList;

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("commonPage/home");
        return modelAndView;
    }

    @GetMapping("/syslog")
    public ModelAndView syslog(ModelAndView modelAndView) throws IOException {
        logModelList = new ArrayList<>();
        int i = 1;
        StringBuilder sb = new StringBuilder();

        File logFile = new File(logPath);
        List<String> logLines;
        JsonObject jsonObject = null;
        Charset charset = Charset.forName("ISO-8859-1");
        logLines = Files.readAllLines(Paths.get(logPath), charset);
        Collections.sort(logLines, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for (String s : logLines) {
            Gson g = new Gson();
            LogModel p = g.fromJson(s.replace("class", "clas"), LogModel.class);
            p.setId(i);
            logModelList.add(p);
            i++;
        }

        String value = "", date = "", time = "";
        modelAndView.addObject("logList", logModelList);
        modelAndView.setViewName("log/log");
        return modelAndView;
    }
    @ResponseBody
    @GetMapping("/syslog/{id}")
    public LogModel getLogDetails(@PathVariable Integer id){
        for (LogModel logModel: logModelList) {
            if(logModel.getId().intValue()==id){
                return logModel;
            }
        }
        return null;
    }
}
