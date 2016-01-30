package com.netty.log.schedular;

import com.netty.log.dto.EventDetails;
import com.netty.log.handler.LogQueryHandler;
import com.netty.util.ConfigFile;
import com.netty.util.GsonUtils;
import com.netty.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raja .
 */
@DisallowConcurrentExecution
public class LogProcessorScheduler extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogProcessorScheduler.class.getCanonicalName());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("Starting Log Processor Scheduler.");

        processLog();

    }

    private void processLog() throws JobExecutionException {

        ConfigFile configFile = SpringUtils.getBean("properties", ConfigFile.class);

        String fileName = configFile.getStringProperty("process.log.file", "/data/logs/netty/event_per_host/eventlog.log");
        // Open the file
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        try {
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                LOGGER.info("Processing event :", strLine);

                if (StringUtils.isNotBlank(strLine)) {

                    try {

                        JSONObject obj = (JSONObject) JSONValue.parseWithException(strLine);
                        JSONObject responseObj = (JSONObject) obj.get("message");
                        EventDetails eventDetails = GsonUtils.getInstanceWithExclusion().fromJson(responseObj.toJSONString(), EventDetails.class);
                        if (StringUtils.isBlank(eventDetails.getClientId())) {
                            eventDetails.setClientId("DUMMY_CLIENT_ID");
                        }
                        Map<String, Map<Long, EventDetails>> logEventsMap = LogQueryHandler.getLogEventsMap();
                        if (logEventsMap.containsKey(eventDetails.getClientId())) {
                            Map<Long, EventDetails> event = logEventsMap.get(eventDetails.getClientId());
                            event.put(System.currentTimeMillis(), eventDetails);
                        } else {
                            Map<Long, EventDetails> event = new HashMap<>();
                            event.put(System.currentTimeMillis(), eventDetails);
                            logEventsMap.put(eventDetails.getClientId(), event);
                        }
                    } catch (Exception e) {
                        LOGGER.error("Exception in from json conversion.", e);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Exception in file reading.", e);
        } finally {
            //Close the input stream
            try {
                br.close();
            } catch (IOException e) {
                LOGGER.error("Exception in closing input stream", e);
            }

        }


    }
}
