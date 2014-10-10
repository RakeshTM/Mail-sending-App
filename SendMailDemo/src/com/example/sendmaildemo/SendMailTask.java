package com.example.sendmaildemo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class SendMailTask extends AsyncTask<String, Integer, String>
{
	 private Activity activity;
	private ProgressDialog statusDialog;
	
	 SendMailTask(Activity activity)
	{
		this.activity=activity;
	}
	@Override
	protected void onPreExecute() 
	{
		statusDialog = new ProgressDialog(activity);
		statusDialog.setMessage("Sending mail...");
		statusDialog.setIndeterminate(false);
		statusDialog.setCancelable(false);
		statusDialog.show();
	}

	@Override
	protected String doInBackground(String... params) 
	{
		try {
			Log.i("SendMailTask", "About to instantiate GMail...");
			
			
			
			Gmail gmail=new Gmail(params[0],params[1],params[2]);
			
			
			Log.i("SendMailTask", "sending Mail");
			gmail.sendEmail();
			
			Log.i("SendMailTask", "Mail Sent.");
		} catch (Exception e) {
			
			Log.e("SendMailTask", e.getMessage(), e);
		}
		return null;
	}
	
	
	@Override
	protected void onPostExecute(String result) 
	{
		statusDialog.setMessage("Mail sent Successfully");
		statusDialog.dismiss();
	
		
	}
	
}