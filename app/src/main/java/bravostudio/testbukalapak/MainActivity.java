package bravostudio.testbukalapak;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.text.Html.escapeHtml;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    GridAdapter gridAdapter;
    TextView labelText;
    ArrayList<BidakModel> arrayChessPos = new ArrayList<>();
    ArrayList<BidakModel> fixedPosition = new ArrayList<>();

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grid_view);
        labelText = (TextView) findViewById(R.id.text_target);

        gridView.setAdapter(gridAdapter);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        timer.cancel();
                        break;
                    case MotionEvent.ACTION_UP:
                        runTimer();
                        break;
                }
                return false;
            }
        });

        runTimer();
    }

    private void runTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateChessBoard();
            }
        },1500,3000);
    }

    public void updateChessBoard(){
        arrayChessPos.clear();
        fixedPosition.clear();

        GetChessUpdate getChessUpdate = new GetChessUpdate(this);
        getChessUpdate.execute((Void) null);
    }

    public class GetChessUpdate extends AsyncTask<Void, Void, Boolean>{

        Context context;
        String chessPosition;

        public GetChessUpdate(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            String url = "http://mobile.suitmedia.com/bl/chess.php";

            try {
                chessPosition = NetworkUtility.getJsonFromServer(url);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success){
                labelText.setText(chessPosition);
                String[] chessPositionSplited = chessPosition.split("<br/>");
                for(int i = 0; i < chessPositionSplited.length; i++){
                    String splited = chessPositionSplited[i];
                    int posX = 0;
                    switch (splited.charAt(2)){
                        case 'a' : posX = 0;
                            break;
                        case 'b' : posX = 1;
                            break;
                        case 'c' : posX = 2;
                            break;
                        case 'd' : posX = 3;
                            break;
                        case 'e' : posX = 4;
                            break;
                        case 'f' : posX = 5;
                            break;
                        case 'g' : posX = 6;
                            break;
                        case 'h' : posX = 7;
                            break;
                    }
                    int posY = splited.charAt(4) - '0';
                    posY--;
                    switch (posY){
                        case 0 : posY = 7;
                            break;
                        case 1 : posY = 6;
                            break;
                        case 2 : posY = 5;
                            break;
                        case 3 : posY = 4;
                            break;
                        case 4 : posY = 3;
                            break;
                        case 5 : posY = 2;
                            break;
                        case 6 : posY = 1;
                            break;
                        case 7 : posY = 0;
                            break;
                    }
                    BidakModel temp = new BidakModel("" + splited.charAt(0), posY, posX);
                    arrayChessPos.add(temp);
                }


                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        boolean flag = false;
                        for(int k = 0; k < arrayChessPos.size(); k++){
                            if(arrayChessPos.get(k).x_pos == i && arrayChessPos.get(k).y_pos == j){
//                                fixedPosition.add(arrayChessPos.get(k).getType());
                                fixedPosition.add(new BidakModel(arrayChessPos.get(k).getType(), i, j));
                                flag = true;
                                break;
                            }
                        }
                        if(!flag){
                            fixedPosition.add(new BidakModel("-", i, j));
                        }
                    }
                }

                gridAdapter = new GridAdapter(context, fixedPosition);
                gridAdapter.notifyDataSetChanged();
                gridView.setAdapter(gridAdapter);
            } else{
                labelText.setText("Failed to retrieve data");
            }
        }
    }
}

//TODO: hasan@bukalapak.com
