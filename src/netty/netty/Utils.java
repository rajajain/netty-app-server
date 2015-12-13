package netty.netty;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raja on 30/11/15.
 */
public class Utils {

    public static Map<String, List<String>> getUrlParameters(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String pair[] = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }
                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }
            return params;
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }

    public static String getStringParameter(Map<String, List<String>> urlParameters, String paramName) {
        if(urlParameters == null)
            return null;
        if(urlParameters.get(paramName) != null && urlParameters.get(paramName).size() > 0) {
            String nresultsParam = urlParameters.get(paramName).get(0);
            if(nresultsParam != null) {
                return nresultsParam.trim();
            }
        }
        return null;
    }
}
