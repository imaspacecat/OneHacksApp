package dubiner.org.onehacksapp;

import com.google.gson.Gson;

import java.io.IOException;
import fi.iki.elonen.NanoHTTPD;

public class HTTPServer extends NanoHTTPD{
    private String updateJavascript = "";
    private boolean createdJavascript = false;
    private String jsonAccelerometerXValues;
    private String jsonAccelerometerYValues;
    private String jsonAccelerometerZValues;
    private String jsonLinearAccelerometerXValues;
    private String jsonLinearAccelerometerYValues;
    private String jsonLinearAccelerometerZValues;
    private String jsonGyroscopeXValues;
    private String jsonGyroscopeYValues;
    private String jsonGyroscopeZValues;
    private String jsonMagnetometerXValues;
    private String jsonMagnetometerYValues;
    private String jsonMagnetometerZValues;
    private String jsonAmbientTemperatureValues;
    private String jsonLightValues;
    private String jsonPressureValues;
    private String jsonRelativeHumidityValues;

    // when creating instance specify port as 8080
    public HTTPServer(int port) throws IOException {
        super(port);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
//        System.out.println("\nRunning! Go to http://localhost:8080/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        jsonAccelerometerXValues = new Gson().toJson(MainActivity.accelerometerXValues);
        jsonAccelerometerYValues = new Gson().toJson(MainActivity.accelerometerYValues);
        jsonAccelerometerZValues = new Gson().toJson(MainActivity.accelerometerZValues);
        jsonLinearAccelerometerXValues = new Gson().toJson(MainActivity.linearAccelerometerXValues);
        jsonLinearAccelerometerYValues = new Gson().toJson(MainActivity.linearAccelerometerYValues);
        jsonLinearAccelerometerZValues = new Gson().toJson(MainActivity.linearAccelerometerZValues);
        jsonGyroscopeXValues = new Gson().toJson(MainActivity.gyroscopeXValues);
        jsonGyroscopeYValues = new Gson().toJson(MainActivity.gyroscopeYValues);
        jsonGyroscopeZValues = new Gson().toJson(MainActivity.gyroscopeZValues);
        jsonMagnetometerXValues = new Gson().toJson(MainActivity.magnetometerXValues);
        jsonMagnetometerYValues = new Gson().toJson(MainActivity.magnetometerYValues);
        jsonMagnetometerZValues = new Gson().toJson(MainActivity.magnetometerZValues);
        jsonAmbientTemperatureValues = new Gson().toJson(MainActivity.ambientTemperatureValues);
        jsonLightValues = new Gson().toJson(MainActivity.lightValues);
        jsonPressureValues = new Gson().toJson(MainActivity.pressureValues);
        jsonRelativeHumidityValues = new Gson().toJson(MainActivity.relativeHumidityValues);


        if (session.getMethod() == Method.GET && session.getParameters().size() >= 1) {
            switch(session.getParameters().get("itemId").get(0)){
                case "accelerationX":
                    if(session.getUri().equalsIgnoreCase("/all")){

                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonAccelerometerXValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("accelerometerX"));
                case "accelerationY":
                    if(session.getUri().equalsIgnoreCase("/all")){

                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonAccelerometerYValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("accelerometerY"));
                case "accelerationZ":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonAccelerometerZValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("accelerometerZ"));
                case "linearAccelerationX":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonLinearAccelerometerXValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("linearAccelerationX"));
                case "linearAccelerationY":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonLinearAccelerometerYValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("linearAccelerationY"));
                case "linearAccelerationZ":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonLinearAccelerometerZValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("linearAccelerationZ"));
                case "gyroscopeX":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonGyroscopeXValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("gyroscopeX"));
                case "gyroscopeY":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonGyroscopeYValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("gyroscopeY"));
                case "gyroscopeZ":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonGyroscopeZValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("gyroscopeZ"));
                case "magneticFieldX":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonMagnetometerXValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("magneticFieldX"));
                case "magneticFieldY":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonMagnetometerYValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("magneticFieldY"));
                case "magneticFieldZ":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonMagnetometerZValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("magneticFieldZ"));
                case "ambientTemperature":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonAmbientTemperatureValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("ambientTemperature"));
                case "light":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonLightValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("light"));
                case "pressure":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonPressureValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("pressure"));
                case "relativeHumidity":
                    if(session.getUri().equalsIgnoreCase("/all")){
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonRelativeHumidityValues);
                    }
                    return newFixedLengthResponse("\n" + MainActivity.data.get("relativeHumidity"));
            }
        }




        if(!createdJavascript) {
            for (int i = 0; i < MainActivity.data.keySet().size(); i++) {
                System.out.println(MainActivity.data.keySet().toArray()[i]);
            }
            for (int i = 0; i < MainActivity.data.size(); i++) {
                updateJavascript += "$(\"#data" + i + "\").load(location.href + \" #data" + i + "\");\n";
            }
            createdJavascript = true;
        }

        // switch between id and class
        // add variables for the json strings
        // add
        String msg = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    \n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor\" crossorigin=\"anonymous\">\n" +
                "    <style>\n" +
                "        body{\n" +
                "            background-color: black;\n" +
                "        }\n" +
                "        .colorBackground{\n" +
                "            background-color: #191c24;\n" +
                "        } \n" +
                "\n" +
                "        p {\n" +
                "           color: #6c7293 ;\n" +
                "        }\n" +
                "\n" +
                "        h6 {\n" +
                "           color: white; \n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "    <script src=\"https://cdn.plot.ly/plotly-2.12.1.js\" charset=\"utf-8\"></script>\n" +
                "    <script src='https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.17/d3.min.js'></script>\n" +
                "    \n" +
                "</head>\n" +
                "<body>\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\n" +
                "\n" +
                "    \n" +
                "    <br>\n" +
                "    <h1 class= \"text-center\" style = \"color: #6c7293; font-family: monospace;\"><b>Sensor Dashboard</b></h1>\n" +
                "    \n" +
                "    <div class=\"container-fluid pt-4 px-4\">\n" +
                "        <div class=\"row g-4\">\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4 \">\n" +
                "                    <div class = \"ms-3\"> \n" +
                "                        <p class = \"mb-2\">Accelerometer X: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data0\" class=\"data\">" + MainActivity.data.get("accelerometerX") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"accelerometerX\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>   \n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">      \n" +
                "                        <p class = \"mb-2\">Accelerometer Y: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data1\" class=\"data\">" + MainActivity.data.get("accelerometerY") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"accelerometerY\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\"> \n" +
                "                        <p class = \"mb-2\">Accelerometer Z: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data2\"  class=\"data\">" + MainActivity.data.get("accelerometerZ") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"accelerometerZ\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>  \n" +
                "            <div class=\"col-sm-6 col-xl-3\"> \n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Linear Acceleration X: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data3\"  class=\"data\">" + MainActivity.data.get("linearAccelerationX") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"linearAccelerometerX\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Linear Acceleration Y: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data4\"  class=\"data\">" + MainActivity.data.get("linearAccelerationY") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"linearAccelerometerY\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Linear Acceleration Z: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data5\"  class=\"data\">" + MainActivity.data.get("linearAccelerationZ") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"linearAccelerometerZ\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Gyroscope X: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data6\"  class=\"data\">" + MainActivity.data.get("gyroscopeX") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"gyroscopeX\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Gyroscope Y: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data7\"  class=\"data\">" + MainActivity.data.get("gyroscopeY") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"gyroscopeY\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Gyroscope Z: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data8\"  class=\"data\">" + MainActivity.data.get("gyroscopeZ") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"gyroscopeZ\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Magnetic Field X: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data9\"  class=\"data\">" + MainActivity.data.get("magneticFieldX") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"magnetometerX\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Magnetic Field Y: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data10\"  class=\"data\">" + MainActivity.data.get("magneticFieldY") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"magnetometerY\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Magnetic Field Z:</p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data11\"  class=\"data\">" + MainActivity.data.get("magneticFieldZ") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"magnetometerZ\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Ambient Temperature: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data12\"  class=\"data\">" + MainActivity.data.get("ambientTemperature") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"ambientTemperature\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Light: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data13\"  class=\"data\">" + MainActivity.data.get("light") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"light\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Pressure: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data14\"  class=\"data\">" + MainActivity.data.get("pressure") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"pressure\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 col-xl-3\">\n" +
                "                <div class=\"colorBackground rounded d-flex align-items-center justify-content-between p-4\">\n" +
                "                    <div class = \"ms-3\">\n" +
                "                        <p class = \"mb-2\">Relative Humidity: </p>\n" +
                "                        <h6 class = \"mb-0\" id=\"data15\" class=\"data\">" + MainActivity.data.get("relativeHumidity") + "</h6>\n" +
                "                        <br>\n" +
                "                        <div id = \"relativeHumidity\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "\n" +
                "   \n" +
                "    \n" +
                "    <script>\n" +
                "var jsonAccelerometerXValues =" + jsonAccelerometerXValues + ";\n" +
                "var jsonAccelerometerYValues =" + jsonAccelerometerYValues + ";\n" +
                "var jsonAccelerometerZValues =" + jsonAccelerometerZValues + ";\n" +
                "var jsonLinearAccelerometerXValues =" + jsonLinearAccelerometerXValues + ";\n" +
                "var jsonLinearAccelerometerYValues =" + jsonLinearAccelerometerYValues + ";\n" +
                "var jsonLinearAccelerometerZValues =" + jsonLinearAccelerometerZValues + ";\n" +
                "var jsonGyroscopeXValues =" + jsonGyroscopeXValues + ";\n" +
                "var jsonGyroscopeYValues =" + jsonGyroscopeYValues + ";\n" +
                "var jsonGyroscopeZValues =" + jsonGyroscopeZValues + ";\n" +
                "var jsonMagnetometerXValues =" + jsonMagnetometerXValues + ";\n" +
                "var jsonMagnetometerYValues =" + jsonMagnetometerYValues + ";\n" +
                "var jsonMagnetometerZValues =" + jsonMagnetometerZValues + ";\n" +
                "var jsonAmbientTemperatureValues =" + jsonAmbientTemperatureValues + ";\n" +
                "var jsonLightValues =" + jsonLightValues + ";\n" +
                "var jsonPressureValues =" + jsonPressureValues + ";\n" +
                "var jsonRelativeHumidityValues =" + jsonRelativeHumidityValues + ";\n" +
                "    lineGraph(jsonAccelerometerXValues, 1, \"accelerometerX\");\n" +
                "    lineGraph(jsonAccelerometerYValues, 1, \"accelerometerY\");\n" +
                "    lineGraph(jsonAccelerometerZValues, 1, \"accelerometerZ\");\n" +
                "    lineGraph(jsonLinearAccelerometerXValues, 1, \"linearAccelerometerX\");\n" +
                "    lineGraph(jsonLinearAccelerometerYValues, 1, \"linearAccelerometerY\");\n" +
                "    lineGraph(jsonLinearAccelerometerZValues, 1, \"linearAccelerometerZ\");\n" +
                "    lineGraph(jsonGyroscopeXValues, 1, \"gyroscopeX\");\n" +
                "    lineGraph(jsonGyroscopeYValues, 1, \"gyroscopeY\");\n" +
                "    lineGraph(jsonGyroscopeZValues, 1, \"gyroscopeZ\");\n" +
                "    lineGraph(jsonMagnetometerXValues, 1, \"magnetometerX\");\n" +
                "    lineGraph(jsonMagnetometerYValues, 1, \"magnetometerY\");\n" +
                "    lineGraph(jsonMagnetometerZValues, 1, \"magnetometerZ\");\n" +
                "    lineGraph(jsonAmbientTemperatureValues, 1, \"ambientTemperature\");\n" +
                "    lineGraph(jsonLightValues, 1, \"light\");\n" +
                "    lineGraph(jsonPressureValues, 1, \"pressure\");\n" +
                "    lineGraph(jsonRelativeHumidityValues, 1, \"relativeHumidity\");\n" +
                "\n" +
                "    function lineGraph(jsonFile, samplingRate, div){\n" +
                "    var floatArray =[];\n" +
                "    for (var i = 0; i<jsonFile.length; i++){\n" +
                "        floatArray.push(jsonFile[i].value);\n" +
                "    }\n" +
                "\n" +
                "    var timeArray = [];\n" +
                "    var j = 0;\n" +
                "    for (var i = 0; i<floatArray.length; i++){\n" +
                "        timeArray.push(j);\n" +
                "        j+=samplingRate\n" +
                "    }\n" +
                "\n" +
                "    var line1 = {\n" +
                "            x: timeArray,\n" +
                "            y: floatArray,\n" +
                "            type: 'scatter'\n" +
                "        };\n" +
                "\n" +
                "    var data = [line1];\n" +
                "\n" +
                "    var layout = {\n" +
                "      autosize: true,\n" +
                "      width: 350,\n" +
                "      height: 200,\n" +
                "      margin: {\n" +
                "        l: 25,\n" +
                "        r: 25,\n" +
                "        b: 25,\n" +
                "        t: 25,\n" +
                "        pad: 4 \n" +
                "      },\n" +
                "      paper_bgcolor: '#7f7f7f',\n" +
                "      plot_bgcolor: '#c7c7c7'\n" +
                "    };\n" +
                "\n" +
                "    Plotly.newPlot(div, data,layout);\n" +
                "    }\n" +
                "\n" +
                "function positionGraph(jsonX, jsonY, jsonZ, samplingRate, div){\n" +
                "    var xArray =[];\n" +
                "    for (var i = 0; i<jsonX.length; i++){\n" +
                "        xArray.push(jsonX[i].value);\n" +
                "    }\n" +
                "    var yArray =[];\n" +
                "    for (var i = 0; i<jsonY.length; i++){\n" +
                "        yArray.push(jsonY[i].value);\n" +
                "    }\n" +
                "    var zArray =[];\n" +
                "    for (var i = 0; i<jsonZ.length; i++){\n" +
                "        zArray.push(jsonZ[i].value);\n" +
                "    }\n" +
                "\n" +
                "    switch(Math.min(xArray.length, yArray.length, zArray.length)) {\n" +
                "        case xArray.length:\n" +
                "            while (xArray.length < yArray.length){yArray.pop()}\n" +
                "            while (xArray.length < zArray.length){zArray.pop()}\n" +
                "        case yArray.length:\n" +
                "            while (yArray.length < xArray.length){xArray.pop()}\n" +
                "            while (yArray.length < zArray.length){zArray.pop()}\n" +
                "        case zArray.length:\n" +
                "            while (zArray.length < xArray.length){xArray.pop()}\n" +
                "            while (zArray.length < yArray.length){yArray.pop()}\n" +
                "    }\n" +
                "\n" +
                "    var accelarationArray = [xArray,yArray,zArray];\n" +
                "\n" +
                "    var positionArray = [[],[],[]];\n" +
                "\n" +
                "    for (var i = 0; i < accelarationArray.length; i++) {\n" +
                "        \n" +
                "        for (var j = 0; j<accelarationArray[i].length; j++){\n" +
                "            positionArray[i][j] = accelarationArray[i][j] * Math.pow(samplingRate,2);\n" +
                "            if (j != 0){\n" +
                "                positionArray[i][j] += positionArray[i][j-1];\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    var data ={\n" +
                "        type: 'scatter3d',\n" +
                "        mode: 'lines',\n" +
                "        x: positionArray[0],\n" +
                "        y: positionArray[1],\n" +
                "        z: positionArray[2],\n" +
                "        opacity: 1,\n" +
                "        line: {\n" +
                "            width: 6,\n" +
                "            reversescale: false\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "    <script>\n" +
                "        $(document).ready(\n" +
                "            function(){\n" +
                "                setInterval(function(){\n" +
                updateJavascript +
                "                }, 500);\n" +
                "            }\n" +
                "        );\n" +
                "    </script>\n" +
                "</body></html>";
//                    "<!DOCTYPE html>\n" +
//                            "<html>\n" +
//                            "<head>\n" +
//                            "</head>\n" +
//                            "<body>\n" +
//                            "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\n" +
//                            "    <p>Accelerometer X: <div class=\"data0\">" + MainActivity.data.get("accelerometerX") + "</div></p>\n" +
//                            "    <p>Accelerometer Y: <div class=\"data1\">" + (MainActivity.data.get("accelerometerY")) + "</div></p>\n" +
//                            "    <p>Accelerometer Z: <div class=\"data2\">" + (MainActivity.data.get("accelerometerZ")) + "</div></p>\n" +
//                            "    <p>Linear Acceleration X: <div class=\"data3\">" + (MainActivity.data.get("linearAccelerationX")) + "</div></p>\n" +
//                            "    <p>Linear Acceleration Y: <div class=\"data4\">" + (MainActivity.data.get("linearAccelerationY")) + "</div></p>\n" +
//                            "    <p>Linear Acceleration Z: <div class=\"data5\">" + (MainActivity.data.get("linearAccelerationZ")) + "</div></p>\n" +
//                            "    <p>Gyroscope X: <div class=\"data6\">" + (MainActivity.data.get("gyroscopeX")) + "</div></p>\n" +
//                            "    <p>Gyroscope Y: <div class=\"data7\">" + (MainActivity.data.get("gyroscopeY")) + "</div></p>\n" +
//                            "    <p>Gyroscope Z: <div class=\"data8\">" + (MainActivity.data.get("gyroscopeZ")) + "</div></p>\n" +
//                            "    <p>Magnetic Field X: <div class=\"data9\">" + (MainActivity.data.get("magneticFieldX")) + "</div></p>\n" +
//                            "    <p>Magnetic Field Y: <div class=\"data10\">" + (MainActivity.data.get("magneticFieldY")) + "</div></p>\n" +
//                            "    <p>Magnetic Field Z: <div class=\"data11\">" + (MainActivity.data.get("magneticFieldZ")) + "</div></p>\n" +
//                            "    <p>Ambient Temperature: <div class=\"data12\">" + (MainActivity.data.get("ambientTemperature")) + "</div></p>\n" +
//                            "    <p>Light: <div class=\"data13\">" + (MainActivity.data.get("light")) + "</div></p>\n" +
//                            "    <p>Pressure: <div class=\"data14\">" + (MainActivity.data.get("pressure")) + "</div></p>\n" +
//                            "    <p>Relative Humidity: <div class=\"data15\">" + (MainActivity.data.get("relativeHumidity")) + "</div></p>\n" +
//                            "    <script>\n" +
//                            "        $(document).ready(\n" +
//                            "            function(){\n" +
//                            "                setInterval(function(){\n" +
//                            updateJavascript +
//                            "                }, 500);\n" +
//                            "            }\n" +
//                            "        );\n" +
//                            "    </script>\n";


            return newFixedLengthResponse(msg);

    }



}
