package bg.duosoft.cronjob.nacid.controller;


import bg.duosoft.cronjob.cron.JobStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * User: ggeorgiev
 * Date: 07.04.2021
 * Time: 13:40
 */
@Controller
@Slf4j
@RequestMapping("/cron")
public class CronController {
    @Autowired
    private JobStarter jobStarter;

    @RequestMapping("/start")
    @ResponseBody
    public String startCronByClass(@RequestParam(value = "name")String name){
        try {
            jobStarter.startJob(name);
            return "done...";
        } catch (Exception e) {
            return "error - " + getStackTrace(e);
        }
    }

    @RequestMapping("/reschedule")
    @ResponseBody
    public String reschedule(@RequestParam(value = "name")String name){
        try {
            jobStarter.reinitJob(name);
            return "done...";
        } catch (Exception e) {
            return "error - " + getStackTrace(e);
        }
    }

    @RequestMapping("/unschedule")
    @ResponseBody
    public String unschedule(@RequestParam(value = "name")String name){
        try {
            jobStarter.deleteJob(name);
            return "done...";
        } catch (Exception e) {
            return "error - " + getStackTrace(e);
        }
    }

    private static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }


}
