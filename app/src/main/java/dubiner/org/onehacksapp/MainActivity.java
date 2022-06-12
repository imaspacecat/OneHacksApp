package dubiner.org.onehacksapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


// some inspiration from https://github.com/lopspower/AndroidWebServer/blob/c6a29257e9b077340dcba54871f5b3ed1029fc95/app/src/main/java/com/mikhaellopez/androidwebserver/MainActivity.java#L80

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private HTTPServer server;
    private boolean serverStarted = false;
    private TextView serverStatusText;
    private Button startButton;

    // textAzimuth, textPitch, textRoll,
    private TextView textAccelerometerX, textAccelerometerY, textAccelerometerZ,
            textLinearAccelerometerX, textLinearAccelerometerY, textLinearAccelerometerZ,
            textGyroscopeX, textGyroscopeY, textGyroscopeZ,
            textMagnetometerX, textMagnetometerY, textMagnetometerZ,
            textAmbientTemperature,
            textLight,
            textPressure,
            textRelativeHumidity;
//    private TextView[] allSensorTexts;


    private SensorManager sensorManager;
//    private List<Sensor> allSensors;
    private Sensor accelerometer;
    private Sensor linearAccelerometer;
    private Sensor gyroscope;
    private Sensor magnetometer;
    private Sensor ambientTemperature;
    private Sensor light;
    private Sensor pressure;
    private Sensor relativeHumidity;


    private final int PORT = 8080;

//    private float[] gravity;
//    private float[] geomagnetic;
//    private float[] rotationMatrix;
//    private float[] orientation;

    public static Map<String, Float> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        orientation = new float[3];

//        // get text id
//        textAzimuth = findViewById(R.id.textAzimuth);
//        textPitch = findViewById(R.id.textPitch);
//        textRoll = findViewById(R.id.textRoll);

        textAccelerometerX = findViewById(R.id.textAccelerometerX);
        textAccelerometerY = findViewById(R.id.textAccelerometerY);
        textAccelerometerZ = findViewById(R.id.textAccelerometerZ);

        textLinearAccelerometerX = findViewById(R.id.textLinearAccelerometerX);
        textLinearAccelerometerY = findViewById(R.id.textLinearAccelerometerY);
        textLinearAccelerometerZ = findViewById(R.id.textLinearAccelerometerZ);

        textGyroscopeX = findViewById(R.id.textGyroscopeX);
        textGyroscopeY = findViewById(R.id.textGyroscopeY);
        textGyroscopeZ = findViewById(R.id.textGyroscopeZ);

        textMagnetometerX = findViewById(R.id.textMagnetometerX);
        textMagnetometerY = findViewById(R.id.textMagnetometerY);
        textMagnetometerZ = findViewById(R.id.textMagnetometerZ);

        textAmbientTemperature = findViewById(R.id.textAmbientTemperature);
        textLight = findViewById(R.id.textLight);
        textPressure = findViewById(R.id.textPressure);
        textRelativeHumidity = findViewById(R.id.textRelativeHumidity);

        serverStatusText = findViewById(R.id.serverStatusText);
        startButton = findViewById(R.id.startButton);

//        allSensorTexts = new TextView[]{textAccelerometerX, textAccelerometerY, textAccelerometerZ,
//                textLinearAccelerometerX, textLinearAccelerometerY, textLinearAccelerometerZ, textGyroscopeX,
//                textGyroscopeY, textGyroscopeZ, textMagnetometerX, textMagnetometerY, textMagnetometerZ,
//                textAmbientTemperature, textLight, textPressure, textRelativeHumidity}; // length = 16
//        // first individual sensortext is in index 12

        // create sensor objects
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        accelerometer       = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        linearAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        gyroscope           = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometer        = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        ambientTemperature  = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        light               = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        pressure            = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        relativeHumidity    = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

//        allSensors = new ArrayList<>(Arrays.asList(accelerometer, linearAccelerometer, gyroscope,
//                magnetometer, ambientTemperature, light, pressure, relativeHumidity)); // length = 8
        // individual sensor is at index 4

        data = new LinkedHashMap<>();
//        data = new LinkedHashMap<>();
    }

    public void startServer(View view){
        if(!serverStarted) {
            try {
                server = new HTTPServer(PORT);
                System.out.println("Running! Go to " + initIpAddress());
                serverStatusText.setText(R.string.server_status_online);
                startButton.setText(R.string.button_text_stop);
                serverStarted = true;

            } catch (IOException ioe) {
                System.err.println("Couldn't start server:\n" + ioe);
                serverStatusText.setText(R.string.server_status_error);
                serverStarted = false;
            }
        } else {
            server.stop();
            serverStarted = false;
            serverStatusText.setText(R.string.server_status_offline);
            startButton.setText(R.string.button_text_start);

        }
        data.put("accelerometerX", 0f);
        data.put("accelerometerY", 0f);
        data.put("accelerometerZ", 0f);
        data.put("linearAccelerationX", 0f);
        data.put("linearAccelerationY", 0f);
        data.put("linearAccelerationZ", 0f);
        data.put("gyroscopeX", 0f);
        data.put("gyroscopeY", 0f);
        data.put("gyroscopeZ", 0f);
        data.put("magneticFieldX", 0f);
        data.put("magneticFieldY", 0f);
        data.put("magneticFieldZ", 0f);
        data.put("ambientTemperature", 0f);
        data.put("light", 0f);
        data.put("pressure", 0f);
        data.put("relativeHumidity", 0f);
    }

    private String initIpAddress() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        System.out.println("Server running at: " + ip + ":" + PORT);
        return "http://" + ip + ":" + PORT + "/";
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSensorChanged(SensorEvent event) {

//        for (int i = 0; i < allSensors.size(); i++) {
//            if(event.sensor.getType() == allSensors.get(i).getType()){
//                if(event.values.length > 1){
//                    data.put(allSensors.get(i).getName() + "X", event.values[0]);
//                    data.put(allSensors.get(i).getName() + "Y", event.values[1]);
//                    data.put(allSensors.get(i).getName() + "Z", event.values[2]);
//                    setLabels(event, new TextView[]{allSensorTexts[i], allSensorTexts[i+1], allSensorTexts[i+2]});
//                } else{
//                    data.put(allSensors.get(i).getName(), event.values[0]);
//                    setLabels(event, new TextView[]{allSensorTexts[i + 8]});
//                }
//            }
//        }
//
//        if(gravity != null && geomagnetic != null){
//            SensorManager.getRotationMatrix(rotationMatrix, null, gravity, geomagnetic);
//            System.out.println(rotationMatrix == null);
//            SensorManager.getOrientation(rotationMatrix, orientation);
//            textAzimuth.setText("azimuth: " + orientation[0]);
//            textAzimuth.setText("pitch: " + orientation[1]);
//            textAzimuth.setText("roll: " + orientation[2]);
//        }


        switch(event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
//                gravity = new float[]{event.values[0], event.values[1], event.values[2]};
                data.put("accelerometerX", event.values[0]);
                data.put("accelerometerY", event.values[1]);
                data.put("accelerometerZ", event.values[2]);
                setLabels(event, new TextView[]{textAccelerometerX, textAccelerometerY, textAccelerometerZ});
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                data.put("linearAccelerationX", event.values[0]);
                data.put("linearAccelerationY", event.values[1]);
                data.put("linearAccelerationZ", event.values[2]);
                setLabels(event, new TextView[]{textLinearAccelerometerX, textLinearAccelerometerY, textLinearAccelerometerZ});
                break;
            case Sensor.TYPE_GYROSCOPE:
                data.put("gyroscopeX", event.values[0]);
                data.put("gyroscopeY", event.values[1]);
                data.put("gyroscopeZ", event.values[2]);
                setLabels(event, new TextView[]{textGyroscopeX, textGyroscopeY, textGyroscopeZ});
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
//                geomagnetic = new float[]{event.values[0], event.values[1], event.values[2]};
                data.put("magneticFieldX", event.values[0]);
                data.put("magneticFieldY", event.values[1]);
                data.put("magneticFieldZ", event.values[2]);
                setLabels(event, new TextView[]{textMagnetometerX, textMagnetometerY, textMagnetometerZ});
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                data.put("ambientTemperature", event.values[0]);
                textAmbientTemperature.setText("ambientTemperature: " + event.values[0]);
                break;
            case Sensor.TYPE_LIGHT:
                data.put("light", event.values[0]);
                textLight.setText("light: " + event.values[0]);
                break;
            case Sensor.TYPE_PRESSURE:
                data.put("pressure", event.values[0]);
                textPressure.setText("pressure: " + event.values[0]);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                data.put("relativeHumidity", event.values[0]);
                textRelativeHumidity.setText("relativeHumidity: " + event.values[0]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, linearAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope,           SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometer,        SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, ambientTemperature,  SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, light,               SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pressure,            SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, relativeHumidity,    SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    // kinda scuffed but works
    @SuppressLint("SetTextI18n")
    private void setLabels(SensorEvent event, TextView[] textViews){
        for(int i = 0; i < textViews.length; i++) {
            String label = String.valueOf(textViews[i].getText());
            if(label.contains(":")) {
                textViews[i].setText(label.substring(0, label.indexOf(":")) + ": " + event.values[i]);
            } else {
                textViews[i].setText(label + ": " + event.values[i]);
            }
        }
    }

}
