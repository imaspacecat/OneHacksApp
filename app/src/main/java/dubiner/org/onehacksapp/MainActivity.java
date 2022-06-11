package dubiner.org.onehacksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private HTTPServer server;
    private boolean serverStarted = false;
    private TextView serverStatusText;
    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverStatusText = (TextView) findViewById(R.id.serverStatusText);
        startButton = (Button) findViewById(R.id.startButton);
    }

    public void startServer(View view){
        if(!serverStarted) {
            try {
                server = new HTTPServer(8080); // http://localhost:8080/
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
    }
}