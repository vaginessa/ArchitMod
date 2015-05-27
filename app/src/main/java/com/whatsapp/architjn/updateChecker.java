package com.whatsapp.architjn;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by architjn on 21/12/14.
 */
public class updateChecker extends AsyncTask<Void, Void, Void> {

    private String url = "http://thepsycraft.com/update.php", jsonResult;
    private Context context;
    private SharedPreferences shp;

    updateChecker(Context context) {
        this.context = context;
        this.shp = context.getSharedPreferences("architMod", Context.MODE_PRIVATE);
    }

    @Override
    protected Void doInBackground(Void a[]) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpclient.execute(httppost);
            jsonResult = inputStreamToString(response.getEntity().getContent())
                    .toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void res) {
        if (jsonResult != null) {
            try {
                final JSONObject jsonResponse = new JSONObject(jsonResult);
                if (jsonResponse.optInt("vno") > Integer.parseInt(context.getResources().getString(R.string.wmavno))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false);
                    builder.setMessage("Yah! A new update is available")
                            .setPositiveButton("Let's Download", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    shp.edit().putBoolean("updateAvil", true).apply();
                                    shp.edit().putString("updateUrl", jsonResponse.optString("url")).apply();
//                                    dialog.dismiss();
                                    downloader();
                                }
                            })
                            .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    shp.edit().putBoolean("updateAvil", true).apply();
                                    shp.edit().putString("updateUrl", jsonResponse.optString("url")).apply();
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false);
                    builder.setMessage("No update for now")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }

    private void downloader() {
        Log.v("d", shp.getString("updateUrl", ""));
        DownloadManager.Request r = new DownloadManager.Request(Uri.parse(shp.getString("updateUrl", "")));
        r.setTitle("WhatsMapp Downloading")
                .setDescription("Update for WhatsMapp is Downloading..");
        r.setDestinationInExternalPublicDir(File.separator + "WhatsApp/ArchitMod/", "update.apk");
        //r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        final DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        final long enqueue = dm.enqueue(r);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(
                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(enqueue);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int columnIndex = c
                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c
                                .getInt(columnIndex)) {
                            Intent i = new Intent(Intent.ACTION_VIEW);
//                            if ((new File(Environment.getExternalStorageDirectory() + File.separator + "WhatsApp/ArchitMod/update.apk")).isFile())
//                                (new File(Environment.getExternalStorageDirectory() + File.separator + "WhatsApp/ArchitMod/update.apk")).delete();
                            i.setDataAndType(Uri.fromFile(new File(File.separator + "WhatsApp/ArchitMod/update.apk")), "application/vnd.android.package-archive");
                            context.startActivity(i);
                        }
                    }
                }
            }
        };

        context.registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

}
