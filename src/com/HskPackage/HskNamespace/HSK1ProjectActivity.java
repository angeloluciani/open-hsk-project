/*Copyright 2012 Angelo Luciani
 * 
    This file is part of HSK-PROJECT.

    HSK-PROJECT is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    HSK-PROJECT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with HSK-PROJECT.  If not, see <http://www.gnu.org/licenses/>.*/

package com.HskPackage.HskNamespace;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import com.HskPackage.HskNamespace.R;
import com.HskPackage.HskNamespace.MyDatabaseHelper;

import android.R.integer;
import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;
import android.view.KeyEvent;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class HSK1ProjectActivity extends Activity {
	private static final String TAG = null;

	private MyDatabaseHelper mioDatabaseHelper;
	/* VARIABLES */
	
	private ImageView miaImmagine;
	
	public String scodice ;
	public String scarattere ;
	public String sfonetica ;
	public String ssignificato ; 
	
	public void showDef(View v) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage(ssignificato)
	        .setCancelable(false)
	        .setNegativeButton("Clear", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	        });

	AlertDialog alert = builder.create();
	alert.show();}
	
	private static void copyFile(InputStream in, OutputStream out) throws IOException
    {
            byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1)
                out.write(buffer, 0, read);
    }

	
	
	@Override
	 public boolean onKeyUp(int keyCode, KeyEvent event) 
	     {
	     if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	            finish();
	            return true;
	     }
	     return super.onKeyUp(keyCode, event);
	 }

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        
   
     final TextView codice      =   (TextView) findViewById(R.id.codice);
     final Button   carattere   =   (Button) findViewById(R.id.carattere);
     final TextView fonetica    =   (TextView) findViewById(R.id.fonetica);
     final TextView significato =   (TextView) findViewById(R.id.significato);
     
	/*********** CREATE A DATABASE ******************************************************/
        final String DB_PATH = "/data/data/com.HskPackage.HskNamespace/";
        final String DB_NAME = "chineseX.db";
        SQLiteDatabase db = null;
        
        boolean exists = (new File(DB_PATH+DB_NAME)).exists();
        
        AssetManager assetManager = getAssets();
        
        if(!exists)
        {
                try
                {
                        InputStream in = assetManager.open(DB_NAME);
                        OutputStream out = new FileOutputStream(DB_PATH+DB_NAME);
                        copyFile(in, out);
                        in.close();
                        out.flush();
                        out.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                File dbFile = new File(DB_PATH+DB_NAME);
                db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        }
        else 
        {
            File dbFile = new File(DB_PATH+DB_NAME);
            db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);	 	
        }
               
        final Integer valore = 1;
        //String query = "SELECT * FROM chineseX";
		String query = "SELECT * FROM chineseX where _id = ? ";                 	
		String[] selectionArgs = {valore.toString()};                   		
		Cursor cursor = null;                  		
		cursor = db.rawQuery(query, selectionArgs);
		//cursor = db.rawQuery(query, null);
		int count = cursor.getCount();
		System.out.println("il numero di dati contenuti nel database " + count);
		while (cursor.moveToNext()) {
			long id = cursor.getLong(0);
			System.out.println("Questo è l'ID ====>" + id);
			scodice = cursor.getString(1);
			codice.setText(scodice);
			System.out.println("Questo è il codice ====>" + codice);
			scarattere = cursor.getString(2);
			carattere.setText(scarattere);
			System.out.println("Questo è il carattere ====>" + carattere);
			sfonetica = cursor.getString(3);
			fonetica.setText(sfonetica);
			System.out.println("Questo è il fonet ====>" + fonetica);
			
			ssignificato = cursor.getString(4);
			significato.setText("点击意思 - Visualizza Significato");
			System.out.println("Questo è il carattere ====>" + ssignificato);
		}
        //fine
        db.close(); 
       
        //set up sound button
        final MediaPlayer mpButtonClick = MediaPlayer.create(this, R.raw.hangout_ringtone);
        
        //final MediaPlayer chword001 = MediaPlayer.create(this, R.raw.ayi001);
        
/*
        
        // set up change Images 
        
        miaImmagine = (ImageView) findViewById(R.id.Image); 
        // dichiaro  l'oggetto image view
     
        miaImmagine.setImageResource(R.drawable.uno1);
        // associo l'immagine alla  figura uno
   
        // setto un evento di cattura del click sull'immagine
        miaImmagine.setOnClickListener( new OnClickListener() 
        {
                        public void onClick(View arg0) {
                        	//chword001.start();
                        }       
        }) ;
      */  
        
        final Intent first = new Intent(this , Activity2.class) ;
        final Intent immagine = new Intent(this , Activity3.class) ;
        
        /*
         * Un intent è definito nella javadoc della classe android.content.Intent come una 
         * "descrizione astratta dell'operazione da eseguire".
         * E un intent ESPLICITO perchè cosciamo il destinatario.
         * Passiamo come parametri il context attuale ed la classe che identifica l'activity di destinazione.
         * E' importante che la classe sia registrata nell'AndroidManifest.xml
         * */
        
        
        
        Button b =  (Button) this.findViewById(R.id.button1) ;
        Button b2 = (Button) this.findViewById(R.id.button2) ;
        Button b3 = (Button) this.findViewById(R.id.carattere) ;
        
        b.setOnClickListener( new OnClickListener() 
        {
                        public void onClick(View arg0) {
                        	Integer valore = 1;                     
                        	valore = valore + 1;                      	
                            if (valore >= 153 )
                            {
                            	valore = 1;
                            }                           
                         
                            System.out.println("AVANTI"+valore);
                            first.putExtra("AVANTI", valore);
                                startActivity(first);
                                finish();
                                mpButtonClick.start();
                        }       
        }) ;
        
        
        b2.setOnClickListener( new OnClickListener() 
        {
                        public void onClick(View arg0) {
                        	
                            Integer	valore=153;
                           
                   
                            System.out.println("AVANTI == >"+valore);
                            first.putExtra("AVANTI", valore);
                                startActivity(first);
                                finish();
                                mpButtonClick.start();
                        }       
        }) ;
      
    
    b3.setOnClickListener( new OnClickListener() 
    {
                    

					public void onClick(View arg0) {
						 
						Integer valore = 1;
                    
                        System.out.println("AVANTI"+valore);
                        
                        immagine.putExtra("AVANTI", valore);
                        
                            startActivity(immagine);
                            finish();
                            mpButtonClick.start();
                    }                            
    }) ; 
}
}