package wu.jay.match3g;

import wu.jay.match3g.Orb.OrbType;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class OrbView extends SurfaceView implements SurfaceHolder.Callback {

	private Context mContext;
	private GameGrid mGrid;
	private int mNumOrbs;
	private int mOrbWidth;
	private int mGridStartX;
	private int mGridStartY;
	private final int mNumOrbTypes = OrbType.values().length;
	
	// drawing and stuff
	private Paint [] mPaint = new Paint[mNumOrbTypes];
	
	// SurfaceView stuff
	private SurfaceHolder mSurfaceHolder;
	private Thread mDrawThread;
	
	
	public OrbView(Context context, GameGrid grid, int orbWidth, int startx, int starty) {
		super(context);

		mContext = context;
		mGrid = grid;
		mOrbWidth = orbWidth;
		mGridStartX = startx;
		mGridStartY = starty;
		
		// instantiate paint
		for (OrbType o : OrbType.values()) {
			mPaint[o.getId()] = new Paint();
			if (o == OrbType.NONE) continue;
			else if (o == OrbType.RED) mPaint[o.getId()].setColor(Color.RED);
			else if (o == OrbType.GREEN) mPaint[o.getId()].setColor(Color.GREEN);
			else if (o == OrbType.BLUE) mPaint[o.getId()].setColor(Color.BLUE); 
			else if (o == OrbType.YELLOW) mPaint[o.getId()].setColor(Color.YELLOW);
			else if (o == OrbType.BLACK) mPaint[o.getId()].setColor(Color.BLACK);
		}
		
		// positions
		final int r = mOrbWidth/2;
		int cx = mGridStartX + r + 10;
		int cy = mGridStartY + r + 10;
		
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {
				mGrid.mGrid[i][j].cx = cx;
				mGrid.mGrid[i][j].cy = cy;
				mGrid.mGrid[i][j].r = r;
				cx += mOrbWidth + 10;
			}
			cy += mOrbWidth + 10;
			cx = mGridStartX + r + 10;
		}
		
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
	}
	
	public void drawOrbs(Canvas canvas) {
		int orbPaint;
		for (int i = 0; i < mGrid.getHeight(); i++) {
			for (int j = 0; j < mGrid.getWidth(); j++) {
				orbPaint = mGrid.getOrb(i, j).type.getId();
				canvas.drawCircle(mGrid.getOrb(i, j).cx,  mGrid.getOrb(i, j).cy,  mGrid.getOrb(i, j).r, mPaint[orbPaint]);
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mDrawThread = new Thread(new Runnable() {
			public void run() {
				Canvas canvas = null;
				while (!Thread.currentThread().isInterrupted()) {
					canvas = mSurfaceHolder.lockCanvas();
					if (canvas != null) {
						// draw
						drawOrbs(canvas);
						mSurfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
			}
		});
		mDrawThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mDrawThread != null) {
			mDrawThread.interrupt();
		}
	}
	
}
