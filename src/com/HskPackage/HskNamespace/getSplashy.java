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

import com.HskPackage.HskNamespace.R;

import android.app.Activity;
import android.view.KeyEvent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;

public class getSplashy extends Activity //extend an activity
{
    /** Called when the activity is first created. */
	
	boolean m_bSplashActive;
	boolean m_bPaused;	
	long m_dwSplashTime = 6000;
	
    @Override
    public void onCreate(Bundle icicle) 
    {
        super.onCreate(icicle);
        //Music Sound
        final MediaPlayer mpButtonClick = MediaPlayer.create(this, R.raw.china);
        //Main Activity
        final Intent first = new Intent(this ,HSK1ProjectActivity.class) ;
        //Variables
        m_bPaused = false;
        m_bSplashActive = true;
        
        //Very simple timer thread
        Thread splashTimer = new Thread() 
        {
        	public void run()
        	{
        		try
        		{
        			//Wait loop
        			long ms = 0;
        			while(m_bSplashActive && ms < m_dwSplashTime)
        			{
        				sleep(300);
        				//Only advance the timer if we're running.
        				if(!m_bPaused)
        					ms += 300;
        			}
        			//Advance to the next screen.
        			mpButtonClick.stop();
        			startActivity(first);
        			finish();
        		}
        		catch(Exception e)
        		{
        			//Thread exception
        			System.out.println(e.toString());
        		}
        	}
        };
        splashTimer.start();
      
        setContentView(R.layout.splash);
       
       
       
        mpButtonClick.start();
        
        return;
    }
    //If we're stopped, make sure the splash timer stops as well. 
    protected void onStop()
    {
    	super.onStop();
    }
    protected void onPause()// Splash timer when there is an incoming call or SMS
    {
    	super.onPause();
    	m_bPaused = true;
    }
    protected void onResume() // When the application resume , the timer thread re-start
    {
    	super.onResume();
    	m_bPaused = false;
    }
    protected void onDestroy()
    {
    	super.onDestroy();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
    	//if we get any key, clear the Splash Screen
    	super.onKeyDown(keyCode, event);
    	m_bSplashActive = false;
    	return true;
    }

}