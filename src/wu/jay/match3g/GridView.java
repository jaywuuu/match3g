package wu.jay.match3g;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class GridView extends View {
	// display dimensions
	private static DisplayMetrics mDisplayMetrics;
	private static int mScreenWidth;
	private static int mScreenHeight;

	// playable area dimensions and stuff
	private int mGridOrbsX;
	private int mGridOrbsY;
	private int mGridStartX;
	private int mGridStartY;
	private int mGridWidth;
	private int mGridHeight;
	private int mOrbWidth;
	private final int mPixelsBetween = 10;
	
	// game related
	//private GameGrid mGrid;
	//private OrbView mOrbView;
	
	// Android stuff
	private Context context;
	
	// graphic stuff
	private Paint mPaint = new Paint();
	private Paint mGridPaint = new Paint();
	private Bitmap mGridBgBitmap;
	private Bitmap mGridTileBitmap;
	
	public GridView(DisplayMetrics disp,
			int gridOrbsX, int gridOrbsY, 
			int gridStartX, int gridStartY, 
			int gridWidth, int gridHeight, Context context) {
		super(context);
		
		this.context = context;
		mPaint.setColor(Color.GRAY);
		mGridPaint.setColor(Color.CYAN);
		
		mDisplayMetrics = disp;
		initDisplay();
		//mGrid = grid;
		mGridOrbsX = gridOrbsX;
		mGridOrbsY= gridOrbsY;
		mGridStartX = gridStartX;
		mGridStartY = gridStartY;
		mGridWidth = gridWidth;
		mGridHeight = gridHeight;
		mOrbWidth = calcOrbWidth();
		
	}
	
	/*
     * We are assuming the app is being run in portrait mode so the
     * width is the shorter dimension, so we're going to scale to the width.
     */
    private int calcOrbWidth() {
    	int pixelsLeft = mGridWidth - (mGridOrbsX + 1) * mPixelsBetween;
    	return (pixelsLeft / mGridOrbsX);
    }
    
    public int getOrbWidth() {
    	return mOrbWidth;
    }
	
	private void initDisplay() {
    	mScreenWidth = mDisplayMetrics.widthPixels;
    	mScreenHeight = mDisplayMetrics.heightPixels;
    }
	
	void setGridWidth(int gridWidth) {
		mGridWidth = gridWidth;
	}
	
	void setGridHeight(int gridHeight) {
		mGridHeight = gridHeight;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// draw main background
		canvas.drawRect(mGridStartX, mGridStartY, mGridStartX + mGridWidth, mGridStartY + mGridHeight, mPaint);
		
		// draw grids
		drawGrid(canvas);
	}
	
	private void drawGrid(Canvas canvas) {
		int gridX = mGridStartX + mPixelsBetween;
		int gridY = mGridStartY + mPixelsBetween;
		
		for (int i = 0; i < mGridOrbsY; i++) {
			for (int j = 0; j < mGridOrbsX; j++) {
				canvas.drawRect(gridX, gridY, gridX + mOrbWidth, gridY + mOrbWidth, mGridPaint);
				gridX += mOrbWidth + mPixelsBetween;
			}
			gridY += mOrbWidth + mPixelsBetween;
			gridX = mGridStartX + mPixelsBetween;
		}
	}
}
