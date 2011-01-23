package net.behoo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

/**
 * 监视本地目录
 * @author CheesyHobo
 *
 */
public class MediaMonitor extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediabrowser);
		load();
	}
	
	protected void load() {
		IntentFilter intentFilter = new IntentFilter(
				Intent.ACTION_MEDIA_SCANNER_STARTED);
		intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		intentFilter.addDataScheme("file");

		registerReceiver(mReceiver, intentFilter);

		Log.i("load", Environment.getExternalStorageDirectory().toString());
		
		sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri
				.parse("file://" + Environment.getExternalStorageDirectory())));
	}

	protected void unload() {
		unregisterReceiver(mReceiver);
	}

	public void systemScan() {
		sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri
				.parse("file://" + Environment.getExternalStorageDirectory())));
	}
	
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_MEDIA_SCANNER_STARTED)) 
			{
				Log.i("ACTION_MEDIA_SCANNER_STARTED", intent.getData().getPath());
			} else if (intent.getAction().equals(
					Intent.ACTION_MEDIA_SCANNER_FINISHED)) 
			{
				
				Log.i("ACTION_MEDIA_SCANNER_FINISHED", intent.getData().getPath());
			} 
			else if (intent.getAction().equals(intent.ACTION_MEDIA_MOUNTED))
			{
				Log.i("ACTION_MEDIA_MOUNTED", intent.getData().getPath());
			}
		}
	};

}
