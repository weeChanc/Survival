package weechan.com.survival;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView contractsRecyclerView;
    private SharedPreferences preference;
    private ContractAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preference = getSharedPreferences("gfs",MODE_PRIVATE);
        final List<String> contracts = new Gson().fromJson(preference.getString("gfs","[]"),
                new TypeToken<List<String>>(){}.getType());

        contractsRecyclerView = findViewById(R.id.contract_list);
        contractsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContractAdapter(contracts);
        contractsRecyclerView.setAdapter(adapter);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog,null,false);
                new AlertDialog.Builder(MainActivity.this).setView(dialogView)
                        .setTitle("输入昵称")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = dialogView.findViewById(R.id.input_name);
                                contracts.add(editText.getText().toString());
                                preference.edit().putString("gfs",new Gson().toJson(contracts)).apply();
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });
    }
}
