package com.netty.logging.encoder;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.encoder.EncoderBase;
import ch.qos.logback.core.spi.PreSerializationTransformer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang.time.FastDateFormat;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TimeZone;

import static org.apache.commons.io.IOUtils.LINE_SEPARATOR;
import static org.apache.commons.io.IOUtils.write;

public class EventEncoder extends EncoderBase<ILoggingEvent> implements PreSerializationTransformer<ILoggingEvent> {

    private static final ObjectMapper MAPPER = new ObjectMapper().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    private static final FastDateFormat ISO_DATETIME_TIME_ZONE_FORMAT_WITH_MILLIS = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", TimeZone.getTimeZone("UTC"));

    private String host;

    public EventEncoder() {
        try {
            host = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            host = "UNKNOWN";
        }
    }

    private ObjectNode getObjectNode(ILoggingEvent event) {
        ObjectNode eventNode = MAPPER.createObjectNode();
        eventNode.put("@timestamp", ISO_DATETIME_TIME_ZONE_FORMAT_WITH_MILLIS.format(event.getTimeStamp()));
        eventNode.put("@version", 1);
        try {
            JSONObject obj = (JSONObject) JSONValue.parseWithException(event.getMessage());
            eventNode.putPOJO("message", obj.toString());
        } catch (ParseException e) {
            eventNode.put("message", event.getMessage());
        }
        eventNode.put("source_host", host);
        eventNode.put("loggerName", event.getLoggerName());
        eventNode.put("threadName", event.getThreadName());
        eventNode.put("level", event.getLevel().toString());
        eventNode.put("levelValue", event.getLevel().toInt());


        return eventNode;
    }

    @Override
    public Serializable transform(ILoggingEvent event) {
        try {
            return MAPPER.writeValueAsString(getObjectNode(event)) + CoreConstants.LINE_SEPARATOR;
        } catch (Exception e) {
            // eat it
            return null;
        }
    }

    private boolean immediateFlush = true;

    @Override
    public void doEncode(ILoggingEvent event) throws IOException {

        // payload in json form , being written with escape character (for each key value pair)
        // i.e. {"message":{\"timestamp\":\"2015-12-10T04:08:31.905Z\"}}
        // which will cause problem while deserializing the logging event.

//        write(MAPPER.writeValueAsBytes(getObjectNode(event)), outputStream);
        write(getObjectNode(event).toString(), outputStream);
        write(CoreConstants.LINE_SEPARATOR, outputStream);
        if (immediateFlush) {
            outputStream.flush();
        }
    }

    @Override
    public void close() throws IOException {
        write(LINE_SEPARATOR, outputStream);
    }

    public boolean isImmediateFlush() {
        return immediateFlush;
    }

    public void setImmediateFlush(boolean immediateFlush) {
        this.immediateFlush = immediateFlush;
    }
}
