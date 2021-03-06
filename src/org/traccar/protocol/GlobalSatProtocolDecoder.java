/*
 * Copyright 2013 Anton Tananaev (anton.tananaev@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.protocol;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.traccar.BaseProtocolDecoder;
import org.traccar.ServerManager;
import org.traccar.helper.AdvancedConnection;
import org.traccar.helper.Log;
import org.traccar.helper.NamedParameterStatement;
import org.traccar.model.Position;

public class GlobalSatProtocolDecoder extends BaseProtocolDecoder {

    private String format0;
    private String format1;

    public GlobalSatProtocolDecoder(ServerManager serverManager) {
        super(serverManager);

        // Initialize format strings
        format0 = "TSPRXAB27GHKLMnaicz*U!";
        format1 = "SARY*U!";
        if (getServerManager() != null) {
            Properties p = getServerManager().getProperties();
            if (p.contains("globalsat.format0")) {
                format0 = p.getProperty("globalsat.format0");
            }
            if (p.contains("globalsat.format1")) {
                format1 = p.getProperty("globalsat.format1");
            }
        }
    }

    /**
     * Regular expressions pattern
     */
    /*static private Pattern pattern = Pattern.compile(
            "\\$GPRMC," +
            "(\\d{2})(\\d{2})(\\d{2})\\.(\\d+)," + // Time (HHMMSS.SSS)
            "([AV])," +                    // Validity
            "(\\d{2})(\\d{2}\\.\\d+)," +   // Latitude (DDMM.MMMM)
            "([NS])," +
            "(\\d{3})(\\d{2}\\.\\d+)," +   // Longitude (DDDMM.MMMM)
            "([EW])," +
            "(\\d+\\.?\\d*)?," +           // Speed
            "(\\d+\\.?\\d*)?," +           // Course
            "(\\d{2})(\\d{2})(\\d{2})" +   // Date (DDMMYY)
            ".+");                         // Other (Checksumm)*/

    // TODO: Acknowledgement
    
    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, Object msg)
            throws Exception {

        String sentence = (String) msg;

        // Message type
        String format;
        if (sentence.startsWith("GSr")) {
            format = format0;
        } else if (sentence.startsWith("GSh")) {
            format = format1;
        } else {
            return null;
        }

        // TODO: check that message contains required parameters
        
        

        // Detect device ID
        /*if (sentence.contains("$PGID")) {
            String imei = sentence.substring(6, sentence.length() - 3);
            try {
                deviceId = getDataManager().getDeviceByImei(imei).getId();
            } catch(Exception error) {
                Log.warning("Unknown device - " + imei);
            }
        }

        // Parse message
        else if (sentence.contains("$GPRMC") && deviceId != null) {

            // Send response
            if (channel != null) {
                channel.write("OK1\r\n");
            }

            // Parse message
            Matcher parser = pattern.matcher(sentence);
            if (!parser.matches()) {
                return null;
            }

            // Create new position
            Position position = new Position();
            position.setDeviceId(deviceId);

            Integer index = 1;

            // Time
            Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            time.clear();
            time.set(Calendar.HOUR, Integer.valueOf(parser.group(index++)));
            time.set(Calendar.MINUTE, Integer.valueOf(parser.group(index++)));
            time.set(Calendar.SECOND, Integer.valueOf(parser.group(index++)));
            index += 1; // Skip milliseconds

            // Validity
            position.setValid(parser.group(index++).compareTo("A") == 0 ? true : false);

            // Latitude
            Double latitude = Double.valueOf(parser.group(index++));
            latitude += Double.valueOf(parser.group(index++)) / 60;
            if (parser.group(index++).compareTo("S") == 0) latitude = -latitude;
            position.setLatitude(latitude);

            // Longitude
            Double lonlitude = Double.valueOf(parser.group(index++));
            lonlitude += Double.valueOf(parser.group(index++)) / 60;
            if (parser.group(index++).compareTo("W") == 0) lonlitude = -lonlitude;
            position.setLongitude(lonlitude);

            // Speed
            String speed = parser.group(index++);
            if (speed != null) {
                position.setSpeed(Double.valueOf(speed));
            } else {
                position.setSpeed(0.0);
            }

            // Course
            String course = parser.group(index++);
            if (course != null) {
                position.setCourse(Double.valueOf(course));
            } else {
                position.setCourse(0.0);
            }

            // Date
            time.set(Calendar.DAY_OF_MONTH, Integer.valueOf(parser.group(index++)));
            time.set(Calendar.MONTH, Integer.valueOf(parser.group(index++)) - 1);
            time.set(Calendar.YEAR, 2000 + Integer.valueOf(parser.group(index++)));
            position.setTime(time.getTime());

            // Altitude
            position.setAltitude(0.0);

            return position;
        }*/

        return null;
    }

}
