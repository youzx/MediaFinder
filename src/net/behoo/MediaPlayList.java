package net.behoo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import behoo.providers.BehooProvider;
import behoo.providers.PlaylistDb;

public class MediaPlayList {
	public MediaPlayList()
	{
	
	}
	
	public static Activity mParent;
	/**
	 * 添加到播放列表
	 * @param meidaPath
	 */
	public static void Add(String meidaPath)
	{
		ContentValues fields = new ContentValues();
		fields.put(PlaylistDb.FIELD_PROGRAM_URI, meidaPath);
		fields.put(PlaylistDb.FIELD_TYPE, PlaylistDb.SourceType.Local.name());
		mParent.getContentResolver().insert(BehooProvider.PLAYLIST_CONTENT_URI, fields);
	}
	/**
	 * 播放最后一个文件
	 */
	public static void Play()
	{
		Intent media = new Intent();
		media.setAction("behoo.intent.action.PLAY");
		media.putExtra("backfrom", 1);
		mParent.startActivity(media);
	}
}
