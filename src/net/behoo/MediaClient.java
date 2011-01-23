package net.behoo;

import java.io.File;
import java.util.Vector;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import behoo.providers.BehooProvider;
import behoo.providers.PlaylistDb;

/*
 * 客户端类，主要是扫描本地文件
 */
public class MediaClient {
	
	public MediaClient()
	{		
		
	}
	
	public void LoadDiskMedia()
	{
		folderScan("/sdcard");
		folderScan("/nfs");
		folderScan("/scsi");
		Log.i("MediaClient", "load finish");
	}
	
	public Vector<String> mAudios = new Vector<String>();
	public Vector<String> mVideos = new Vector<String>();
	
	public final static String AUDIO_MEDIA_TYPE = "*.mp3;";
	public final static String VIDEO_MEDIA_TYPE = "*.mp4;*.rmvb;";

	protected static String getExtension(String filename, String defExt) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i > 0) && (i < (filename.length() - 1))) {
                return filename.substring(i + 1);
            }
        }
        return defExt;
    }

	protected void fileScan(String file) {
		String ext = new String();
		ext = getExtension(file, ext);
		if (AUDIO_MEDIA_TYPE.indexOf("*."+ext+";") != -1)
		{
			mAudios.add(file);
			Log.i("MediaClient", "find audio file:" + file);
		}
		else if (VIDEO_MEDIA_TYPE.indexOf("*."+ext+";") != -1)
		{
			mVideos.add(file);
			Log.i("MediaClient", "find video file:" + file);
		}
	}

	protected void folderScan(String path) {
		File file = new File(path);

		if (file.isDirectory()) {
			File[] array = file.listFiles();
			if (array == null)
				return;
			
			for (int i = 0; i < array.length; i++) {
				File f = array[i];

				if (f.isFile()) {// FILE TYPE
					//String name = f.getName();
					fileScan(f.getAbsolutePath());
				} else {// FOLDER TYPE
					folderScan(f.getAbsolutePath());
				}
			}
		}
	}
}
