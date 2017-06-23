package info.smartlife360.facebookprofilepicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//https://graph.facebook.com/{facebookId}/picture?type=large&w‌​idth=720&height=720
public class MainActivity extends AppCompatActivity {

    TextView userId;
    ImageView profilePic;
    ProgressBar pb;

    public void getPicture(View view){
//        pb.setVisibility(View.VISIBLE);
        String id=userId.getText().toString();
        String full_url="https://graph.facebook.com/"+id+"/picture?type=large&width=720&height=720";
        Download_profile_pic dpp=new Download_profile_pic();
        try{
            //
            Bitmap bm=dpp.execute(full_url).get();
            //pb.setVisibility(View.INVISIBLE);
            profilePic.setImageBitmap(bm);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    public class Download_profile_pic extends AsyncTask<String,Void,Bitmap>{

        URL url;
        HttpURLConnection connection;
        InputStream is;
        Bitmap profile_pic;

        @Override
        protected Bitmap doInBackground(String... urls) {
            try
            {
             url=new URL(urls[0]);
             connection=(HttpURLConnection) url.openConnection();
             connection.connect();
             is=connection.getInputStream();
             profile_pic= BitmapFactory.decodeStream(is);
             return profile_pic;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId=(TextView)findViewById(R.id.userId);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        profilePic=(ImageView)findViewById(R.id.profilePic);

    }
}
