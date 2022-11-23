package algonquin.cst2335.jian0076;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import algonquin.cst2335.jian0076.databinding.ActivityMainBinding;

/**
 * This is a program to validate the password
 * @author wenjiejiang
 * @version1.0
 */
public class MainActivity extends AppCompatActivity {


   protected String cityName;
    RequestQueue queue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate( getLayoutInflater() );

        binding.forecastbutton.setOnClickListener(click -> {
            cityName = binding.editText.getText().toString();
            String url = null;
            try {
                url = "https://api.openweathermap.org/data/2.5/weather?q="
                        + URLEncoder.encode(cityName,"UTF-8")
                        +"&appid=7e943c97096a9784391a981c4d878b22&Units=Metric";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    (response)->{

                    },
                    (error)->{

                    });
            queue.add(request);
        });


    }}

