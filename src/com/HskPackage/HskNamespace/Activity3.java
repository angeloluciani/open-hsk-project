/*Copyright 2012 Angelo Luciani
 * 
    This file is part of OPEN HSK-PROJECT.

    OPEN HSK-PROJECT is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    OPEN HSK-PROJECT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with OPEN HSK-PROJECT.  If not, see <http://www.gnu.org/licenses/>.*/

package com.HskPackage.HskNamespace;

import java.io.File;

import com.HskPackage.HskNamespace.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View ;
import android.view.View.OnClickListener ;

public class Activity3 extends Activity{
	private ImageView miaImmagine;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        
        //set up sound button
        final MediaPlayer mpButtonClick = MediaPlayer.create(this, R.raw.hangout_ringtone);
        final MediaPlayer chword002 = MediaPlayer.create(this, R.raw.a002);
        
        Integer valore = getIntent().getExtras().getInt("AVANTI");
        String number = valore.toString();
       
        String imageID = "uno" + number;
        System.out.print("IMAGEID:"+ imageID);
        int resID = getResources().getIdentifier(imageID, "drawable", getPackageName());
        miaImmagine = (ImageView) findViewById(R.id.Image);
        // dichiaro  l'oggetto image view
        miaImmagine.setImageResource(resID);
        // associo l'immagine alla  figura uno

        
        miaImmagine.setOnClickListener( new OnClickListener() 
        {
                        public void onClick(View arg0) {
                        	 
                          
                        	chword002.start();
                        }       
        }) ;	

        Button b  = (Button) this.findViewById(R.id.button1) ;

        final Intent back = new Intent(this , Activity2.class) ;
        
        b.setOnClickListener( new OnClickListener() 
        {
                        

						public void onClick(View arg0) {
							 
							Integer valore = getIntent().getExtras().getInt("AVANTI");

                            
                            System.out.println("AVANTI"+valore);
                            back.putExtra("AVANTI", valore);
                                startActivity(back);
                                finish();
                                mpButtonClick.start();
                        }                            
        }) ;       

}}
