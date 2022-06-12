package dubiner.org.onehacksapp;

import java.io.IOException;
import fi.iki.elonen.NanoHTTPD;

public class HTTPServer extends NanoHTTPD{
    private String updateJavascript = "";
    private boolean createdJavascript = false;

    // when creating instance specify port as 8080
    public HTTPServer(int port) throws IOException {
        super(port);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
//        System.out.println("\nRunning! Go to http://localhost:8080/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        if (session.getMethod() == Method.GET && session.getParameters().size() >= 1) {
            switch(session.getParameters().get("itemId").get(0)){
                case "accelerationX":
                    return newFixedLengthResponse("\n" + MainActivity.data.get("accelerometerX"));
                case "accelerationY":
                    return newFixedLengthResponse("\n" + MainActivity.data.get("accelerometerY"));
                case "accelerationZ":
                    return newFixedLengthResponse("\n" + MainActivity.data.get("accelerometerZ"));
            }
        }

        if(!createdJavascript) {
            for (int i = 0; i < MainActivity.data.keySet().size(); i++) {
                System.out.println(MainActivity.data.keySet().toArray()[i]);
            }
            for (int i = 0; i < MainActivity.data.size(); i++) {
                updateJavascript += "$(\".data" + i + "\").load(location.href + \" .data" + i + "\");\n";
            }
            createdJavascript = true;
        }


        // switch between id and class
        String msg =
                    "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\n" +
                            "    <p>Accelerometer X: <div class=\"data0\">" + MainActivity.data.get("accelerometerX") + "</div></p>\n" +
                            "    <p>Accelerometer Y: <div class=\"data1\">" + (MainActivity.data.get("accelerometerY")) + "</div></p>\n" +
                            "    <p>Accelerometer Z: <div class=\"data2\">" + (MainActivity.data.get("accelerometerZ")) + "</div></p>\n" +
                            "    <p>Linear Acceleration X: <div class=\"data3\">" + (MainActivity.data.get("linearAccelerationX")) + "</div></p>\n" +
                            "    <p>Linear Acceleration Y: <div class=\"data4\">" + (MainActivity.data.get("linearAccelerationY")) + "</div></p>\n" +
                            "    <p>Linear Acceleration Z: <div class=\"data5\">" + (MainActivity.data.get("linearAccelerationZ")) + "</div></p>\n" +
                            "    <p>Gyroscope X: <div class=\"data6\">" + (MainActivity.data.get("gyroscopeX")) + "</div></p>\n" +
                            "    <p>Gyroscope Y: <div class=\"data7\">" + (MainActivity.data.get("gyroscopeY")) + "</div></p>\n" +
                            "    <p>Gyroscope Z: <div class=\"data8\">" + (MainActivity.data.get("gyroscopeZ")) + "</div></p>\n" +
                            "    <p>Magnetic Field X: <div class=\"data9\">" + (MainActivity.data.get("magneticFieldX")) + "</div></p>\n" +
                            "    <p>Magnetic Field Y: <div class=\"data10\">" + (MainActivity.data.get("magneticFieldY")) + "</div></p>\n" +
                            "    <p>Magnetic Field Z: <div class=\"data11\">" + (MainActivity.data.get("magneticFieldZ")) + "</div></p>\n" +
                            "    <p>Ambient Temperature: <div class=\"data12\">" + (MainActivity.data.get("ambientTemperature")) + "</div></p>\n" +
                            "    <p>Light: <div class=\"data13\">" + (MainActivity.data.get("light")) + "</div></p>\n" +
                            "    <p>Pressure: <div class=\"data14\">" + (MainActivity.data.get("pressure")) + "</div></p>\n" +
                            "    <p>Relative Humidity: <div class=\"data15\">" + (MainActivity.data.get("relativeHumidity")) + "</div></p>\n" +
                            "    <script>\n" +
                            "        $(document).ready(\n" +
                            "            function(){\n" +
                            "                setInterval(function(){\n" +
                            updateJavascript +
                            "                }, 500);\n" +
                            "            }\n" +
                            "        );\n" +
                            "    </script>\n";

            return newFixedLengthResponse(msg + "</body></html>\n");

    }



}
