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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class MyDatabaseHelper extends SQLiteOpenHelper {
private static SQLiteDatabase database = null;
private static final String DB_PATH = "/data/data/com.HskPackage.HskNamespace/databases/";
private static final String DB_NAME = "chineseX.db";
private static final int DB_VERSION = 1;
private static final String ct = "create table chineseX ("
        +" _id INTEGER PRIMARY KEY,"
        + "codice varchar,"
        + "carattere varchar,"
        + "fonetica varchar,"
        + "significato varchar)";

public static SQLiteDatabase loadDatabase(AssetManager assetManager)
{
        if(database == null)
        {
                try
                {
                        InputStream in = assetManager.open(DB_NAME);
                        OutputStream out = new FileOutputStream(DB_PATH + DB_NAME);
                        copyFile(in, out);
                        in.close();
                        out.flush();
                        out.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                
        }
		return database;
}

private static void copyFile(InputStream in, OutputStream out) throws IOException
{
        byte[] buffer = new byte[1024];
    int read;
    while((read = in.read(buffer)) != -1)
            out.write(buffer, 0, read);
}


public MyDatabaseHelper(Context context) 
{
super(context, DB_NAME, null, DB_VERSION);
}
 
@Override
public void onCreate(SQLiteDatabase db)
{   
db.execSQL(ct);
}
 
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Aggiornamento delle tabelle
}
}

