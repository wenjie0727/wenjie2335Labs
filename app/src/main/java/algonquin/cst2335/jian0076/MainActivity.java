package algonquin.cst2335.jian0076;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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
        setContentView(binding.getRoot());

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
                        try {
                            JSONObject coord= response.getJSONObject("coord");
                            JSONObject mainObject = response.getJSONObject("main");
                            double temp = mainObject.getDouble("temp");
                            double max  = mainObject.getDouble("temp_max");
                            double min = mainObject.getDouble("temp_min");
                            int humidity = mainObject.getInt("humidity");
                            JSONArray weather = response.getJSONArray("weather");
                            JSONObject first = weather.getJSONObject(0);
                            String description = first.getString("description");
                            String iconName = first.getString("icon");
                        String pathname = getFilesDir()+"/" + iconName +".png";
                        File file = new File(pathname);
                        if(file.exists()){
                            image = BitmapFactory.decodeFile(pathname);
                        }
                        else{
                            ImageRequest imgReq = new ImageRequest(imageUrl, new Response.Listener>Bitmap () {
                                @Override
                                public void onResponse(Bitmap bitmap) {
                                    // Do something with loaded bitmap...

                                }
                            }, 1024, 1024, iconName.ScaleType.CENTER, null, (error ) -> {

                            });
                        }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    (error)->{

                    });
            queue.add(request);
        });


    }}

