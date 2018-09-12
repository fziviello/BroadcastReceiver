package com.ziviello.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private BroadcastReceiver receiver;
    private static final String TAG ="broadcastreceiver";

    public static final String BASE_PACKAGE = "com.ziviello.broadcastreceiver";
    public static final String CMD_HELLO = "HELLO";
    public static final String CMD_CIAO = "CIAO";

    private Button btn_hello;
    private Button btn_ciao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_ciao= (Button) findViewById(R.id.id_btn_ciao);
        btn_hello= (Button) findViewById(R.id.id_btn_hello);

        btn_ciao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BASE_PACKAGE+".CMD");
                intent.putExtra("Command",CMD_CIAO);
                sendBroadcast(intent);
            }
        });

        btn_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BASE_PACKAGE+".CMD");
                intent.putExtra("Command",CMD_HELLO);
                sendBroadcast(intent);
            }
        });

        receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                if(intent.getAction().contentEquals(BASE_PACKAGE+".CMD"))
                {
                    String cmd = intent.getStringExtra("Command");

                    if(cmd != null)
                    {
                        if (cmd.contentEquals(CMD_HELLO))
                        {
                            Log.e(TAG,"CMD_HELLO");
                            Toast.makeText(context, "CMD_HELLO",  Toast.LENGTH_SHORT).show();

                        }
                        else if (cmd.contentEquals(CMD_CIAO))
                        {
                            Log.e(TAG,"CMD_CIAO");
                            Toast.makeText(context, "CMD_CIAO",  Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        };

        this.registerReceiver(receiver, new IntentFilter(BASE_PACKAGE+".CMD"));

    }

    @Override
    protected void onDestroy()
    {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

}
