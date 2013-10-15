package com.example.soapapplication2;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import org.ksoap2.transport.HttpsTransportSE;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SOAPMain2 extends Activity {

	private final String NAMESPACE = "https://webservice.aspsms.com/aspsmsx2.asmx";
	private final String SOAP_ACTION = "https://webservice.aspsms.com/aspsmsx2.asmx/CheckCredits";
	private final String METHOD_NAME = "CheckCredits";
	private static final String TAG = "Aplikacja2 SOAP";
	private static String userKey,haslo;
	private static String wynik;
	Button b;
	TextView tv;
	EditText et1,et2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soapmain2);
		 et1 = (EditText) findViewById(R.id.editText1);
		 et2= (EditText) findViewById(R.id.editText2);
		 tv = (TextView) findViewById(R.id.tv_wynik);
		 b = (Button) findViewById(R.id.button1);
		 b.setOnClickListener(new OnClickListener() {
		    	@Override
				public void onClick(View arg0) {
		                userKey = et1.getText().toString();
		                haslo = et2.getText().toString();
		                if(isOnline())
		                {
		                	WebService task = new WebService();
			                task.execute();
		                }
		                else
		                {
		                	Toast.makeText(getApplicationContext(), "Brak po³¹czenia z internetem", Toast.LENGTH_SHORT).show();
		                }
		           
					
				}
		    });
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.soapmain2, menu);
			return true;
		}
		
		public boolean isOnline() {
		    ConnectivityManager cm =
		        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
		        return true;
		    }
		    return false;
		}
		
		private class WebService extends AsyncTask<String, Void, Void> {
	        @Override
	        protected Void doInBackground(String... params) {
	            Log.i(TAG, "doInBackground");
	            getCredits(userKey,haslo);
	            return null;
	        }
	 
	        @Override
	        protected void onPostExecute(Void result) {
	            Log.i(TAG, "onPostExecute");
	            if(wynik.equals("StatusCode:3") || wynik.equals("StatusCode:8") || wynik.equals("StatusCode:9"))
	            {
	            	tv.setText("Niepoprawne dane");
	            }
	            else if(wynik.equals("StatusCode:2"))
	            {
	            	tv.setText("B³¹d po³¹czenia");
	            }
	            else
	            {
	            	tv.setText(wynik);
	            }
	        }
	 
	        @Override
	        protected void onPreExecute() {
	            Log.i(TAG, "onPreExecute");
	            tv.setText("Pobieranie...");
	        }
	 
	        @Override
	        protected void onProgressUpdate(Void... values) {
	            Log.i(TAG, "onProgressUpdate");
	        }
	 
	    }
		
		public void getCredits(String uk,String h) {

		    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		    PropertyInfo userKey = new PropertyInfo();
		    PropertyInfo haslo = new PropertyInfo();

		    userKey.setName("UserKey");
		    userKey.setValue(uk); 

		    haslo.setName("Password");
		    haslo.setValue(h); 

		    request.addProperty(userKey);
		    request.addProperty(haslo);

		    
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		            SoapEnvelope.VER11);
		    envelope.dotNet = true;
		    envelope.setOutputSoapObject(request);

		    HttpsTransportSE httpsTransport = new HttpsTransportSE("webservice.aspsms.com",443, "aspsmsx2.asmx", 1000);
		    
		    try {
		    	httpsTransport.call(SOAP_ACTION, envelope);
		        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
		        wynik = response.toString();
		 
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
	
	}
