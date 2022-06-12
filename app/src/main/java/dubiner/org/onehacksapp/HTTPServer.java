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
            for (int i = 0; i < MainActivity.data.size(); i++) {
                updateJavascript += "$(\".data" + i + "\").load(location.href + \" .data" + i + "\");\n";
            }
            createdJavascript = true;
        }


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
