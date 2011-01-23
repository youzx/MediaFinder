package net.behoo;


import net.behoo.R.drawable;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 客户端主窗口
 * @author CheesyHobo
 *
 */
public class MediaFinder extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        mClient.LoadDiskMedia();
        
        MediaPlayList.mParent = this;
        
        setContentView(R.layout.main);
        
        // TODO: 显示当前加载的媒体文件信息
        Log.i("MediaFinder", "video count" + mClient.mVideos.size());
        Log.i("MediaFinder", "audio count" + mClient.mAudios.size());
        
        final RelativeLayout rl = ( RelativeLayout )findViewById( R.id.RelativeLayout01 );
        rl.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v) {
        		// TODO: 添加点击视频按钮处理事件
        		for ( int i=0; i<mClient.mVideos.size(); ++i)
        		{
        			MediaPlayList.Add(mClient.mVideos.get(i));	
        		}      
        		MediaPlayList.Play();
			}
        });
    }
    
    public final static MediaClient mClient = new MediaClient();
}