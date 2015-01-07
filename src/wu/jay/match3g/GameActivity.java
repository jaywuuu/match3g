package wu.jay.match3g;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class GameActivity extends Activity {
	// display dimensions
	private static Display mDisplay;
	private static DisplayMetrics mDisplayMetrics;
	
	// playable area dimensions and stuff
	private static final int GRID_DIM_X = 6;
	private static final int GRID_DIM_Y = 9;
	
	// layout
	private RelativeLayout rLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // go full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_game);
        
        // init
        rLayout = (RelativeLayout) findViewById(R.id.main_layout);
        mDisplay = getWindowManager().getDefaultDisplay();
        mDisplayMetrics = new DisplayMetrics();
        mDisplay.getMetrics(mDisplayMetrics);
        GameGrid game = new GameGrid(GRID_DIM_X, GRID_DIM_Y);
        GridView gridView = new GridView(mDisplayMetrics,
        		GRID_DIM_X, GRID_DIM_Y, 
        		0, 0, 
        		mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels,
        		getApplicationContext());
        rLayout.addView(gridView);
        OrbView orbView = new OrbView(getApplicationContext(), game, gridView.getOrbWidth(), 0, 0);
        // make orbView background transparent
        orbView.setZOrderOnTop(true);
        orbView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        rLayout.addView(orbView);
    }
}
