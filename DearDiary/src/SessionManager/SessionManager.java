package SessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.deardiary.DearDiary;

public class SessionManager {
	// Shared Preferences
    SharedPreferences pref;
     
    // Editor for Shared preferences
    Editor editor;
     
    // Context
    Context _context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "AndroidSharedPref";
     
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
     
     
    // Username (make variable public to access from outside)
    public static final String KEY_UNAME = "uname";
     
    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    
    /**
     * Create login session
     * 
     **/
    
    public void createLoginSession(String uname){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
         
        // Storing uname in pref
        editor.putString(KEY_UNAME, uname);
         
        // commit changes
        editor.commit();
    } 
    
    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, DearDiary.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             
            // Staring Login Activity
            _context.startActivity(i);
        }
         
    }
    
    /**
     * Get stored session data
     * */
    
    public String getUserDetails(){
         
        // user email id
        String key= pref.getString(KEY_UNAME, null);
         
        // return user
        return key;
    }
     
    /**
     * Clear session details
     * */
    
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
         
        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, DearDiary.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         
        // Staring Login Activity
        _context.startActivity(i);
    }
     
    /**
     * Quick check for login
     * **/
    
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
