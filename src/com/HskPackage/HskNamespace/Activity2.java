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

import java.io.File;

import com.HskPackage.HskNamespace.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View ;
import android.view.View.OnClickListener ;

public class Activity2 extends Activity{
	private ImageView miaImmagine;
	public String scodice;
	public String scarattere;
	public String sfonetica;
	public String ssignificato;
	
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

	
	 private String getStringSS() 
	 {
return null;
	}


	@Override
	    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        
        /*********** CREATE A DATABASE ******************************************************/
        final String DB_PATH = "/data/data/com.HskPackage.HskNamespace/";
        final String DB_NAME = "chineseX.db";
        File dbFile = new File(DB_PATH + DB_NAME);
        final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        
        //set up sound button
        final MediaPlayer mpButtonClick = MediaPlayer.create(this, R.raw.hangout_ringtone);
        final MediaPlayer chword002 = MediaPlayer.create(this, R.raw.a002);
        
        Integer valore = getIntent().getExtras().getInt("AVANTI");
        final TextView codice      =   (TextView) findViewById(R.id.codice);
        final Button   carattere   =   (Button) findViewById(R.id.carattere);
        final TextView fonetica    =   (TextView) findViewById(R.id.fonetica);
        final TextView significato =   (TextView) findViewById(R.id.significato);
        
        //inizio
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
			scodice = (String)cursor.getString(1);
			codice.setText(scodice);
			System.out.println("Questo è il codice ====>" + scodice);
			scarattere = (String) cursor.getString(2);
			carattere.setText(scarattere);
			System.out.println("Questo è il carattere ====>" + scarattere);
			sfonetica = (String) cursor.getString(3);
			fonetica.setText(sfonetica);
			System.out.println("Questo è il fonet ====>" + sfonetica);
			
			ssignificato = cursor.getString(4);
			significato.setText("点击意思 - Visualizza Significato");
			System.out.println("Questo è il carattere ====>" + ssignificato);
		}
        //fine
        
        
       
        /*
        miaImmagine = (ImageView) findViewById(R.id.Image);
        miaImmagine.setImageResource(R.drawable.i002);
        
        String imageID = "uno" + (i);
        int resID = getResources().getIdentifier(imageID, "drawable", getPackageName());

        
        miaImmagine.setOnClickListener( new OnClickListener() 
        {
                        public void onClick(View arg0) {
                        	 
                          
                        	chword002.start();
                        }       
        }) ;	
*/
        Button b  = (Button) this.findViewById(R.id.button1) ;
        Button b2 = (Button) this.findViewById(R.id.button2) ;
        Button b3 = (Button) this.findViewById(R.id.carattere) ;
        
        final Intent go   = new Intent(this , Activity2.class) ;
        final Intent back = new Intent(this , Activity2.class) ;
        final Intent immagine = new Intent(this , Activity3.class) ;
        
        b.setOnClickListener( new OnClickListener() 
        {
                        

						public void onClick(View arg0) {
							 
							Integer valore = getIntent().getExtras().getInt("AVANTI");
                            valore = valore - 1;
                            if (valore <= 0 )
                            {
                            	valore = 153;
                            }
  
                            
                            System.out.println("AVANTI"+valore);
                            go.putExtra("AVANTI", valore);
                                startActivity(go);
                                finish();
                                mpButtonClick.start();
                        }                            
        }) ;       
        
        b2.setOnClickListener( new OnClickListener() 
        {
                        

						public void onClick(View arg0) {
							Integer valore = getIntent().getExtras().getInt("AVANTI");
                            valore = valore + 1;
                            if (valore > 153 )
                            {
                            	valore = 1;
                            }
                            

                            System.out.println("AVANTI"+valore);
                            go.putExtra("AVANTI", valore);
                                startActivity(go);
                                finish();
                                
                                mpButtonClick.start();
                        }                       
        }) ;            
          
	
    b3.setOnClickListener( new OnClickListener() 
    {
                    

					public void onClick(View arg0) {
						 
						Integer valore = getIntent().getExtras().getInt("AVANTI");
                    
                        System.out.println("AVANTI"+valore);
                        immagine.putExtra("AVANTI", valore);
                            startActivity(immagine);
                            finish();
                            mpButtonClick.start();
                    }                            
    }) ; 
} }
