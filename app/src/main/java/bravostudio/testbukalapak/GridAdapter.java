package bravostudio.testbukalapak;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jouvyap on 8/11/16.
 */
public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<BidakModel> mData;

    public GridAdapter(Context mContext, ArrayList<BidakModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        View tilesView;
        ImageView bidakGambar;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        gridView = inflater.inflate(R.layout.grid_item, null);

        //MUST CHANGE THIS TO USE RECYCLED VIEW RATHER THAN CREATING VIEW
//        if (convertView == null){
//            Log.d("JOUVY", "Recycled: " + position);
//            gridView = inflater.inflate(R.layout.grid_item, null);
//        } else{
//            Log.d("JOUVY", "Not Recycled: " + position);
//            gridView = convertView;
//        }

        tilesView = gridView.findViewById(R.id.tiles_colour);
        bidakGambar = (ImageView) gridView.findViewById(R.id.bidak_type);

        int posX = mData.get(position).getX_pos();
        int posY = mData.get(position).getY_pos();
        if (((posY % 2 == 1 && posX % 2 == 1) || (posY % 2 == 0 && posX % 2 == 0))) {
            tilesView.setBackground(mContext.getResources().getDrawable(R.color.tiles_white));
        }

        switch (mData.get(position).getType()) {
            case ChessConstant.CHESS_TYPE.BLACK_BISHOP:
                bidakGambar.setImageResource(R.drawable.black_bishop);
                break;
            case ChessConstant.CHESS_TYPE.BLACK_KING:
                bidakGambar.setImageResource(R.drawable.black_king);
                break;
            case ChessConstant.CHESS_TYPE.BLACK_KNIGHT:
                bidakGambar.setImageResource(R.drawable.black_knight);
                break;
            case ChessConstant.CHESS_TYPE.BLACK_QUEEN:
                bidakGambar.setImageResource(R.drawable.black_queen);
                break;
            case ChessConstant.CHESS_TYPE.BLACK_ROOK:
                bidakGambar.setImageResource(R.drawable.black_rook);
                break;
            case ChessConstant.CHESS_TYPE.WHITE_BISHOP:
                bidakGambar.setImageResource(R.drawable.white_bishop);
                break;
            case ChessConstant.CHESS_TYPE.WHITE_KING:
                bidakGambar.setImageResource(R.drawable.white_king);
                break;
            case ChessConstant.CHESS_TYPE.WHITE_KNIGHT:
                bidakGambar.setImageResource(R.drawable.white_knight);
                break;
            case ChessConstant.CHESS_TYPE.WHITE_QUEEN:
                bidakGambar.setImageResource(R.drawable.white_queen);
                break;
            case ChessConstant.CHESS_TYPE.WHITE_ROOK:
                bidakGambar.setImageResource(R.drawable.white_rook);
                break;
        }

        return gridView;
    }
}
