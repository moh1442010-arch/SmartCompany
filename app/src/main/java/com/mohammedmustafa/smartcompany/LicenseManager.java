package com.mohammedmustafa.smartcompany;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

public class LicenseManager {
    private static final String PREF="license";
    private static final long TRIAL_MS=7L*24L*60L*60L*1000L;
    private final SharedPreferences p; private final Context context;
    public LicenseManager(Context c){context=c;p=c.getSharedPreferences(PREF,Context.MODE_PRIVATE);init();}
    private void init(){if(!p.contains("first_run")){long now=System.currentTimeMillis();p.edit().putLong("first_run",now).putLong("last_seen",now).apply();}}
    public boolean isActivated(){return p.getBoolean("activated",false);}
    public long remainingMs(){long now=System.currentTimeMillis(),first=p.getLong("first_run",now),last=p.getLong("last_seen",now);if(now<last)return -1;p.edit().putLong("last_seen",now).apply();return Math.max(0,TRIAL_MS-(now-first));}
    public boolean isTrialValid(){return isActivated()||remainingMs()>0;}
    public int remainingDays(){long r=remainingMs();return r<=0?0:(int)Math.ceil(r/86400000.0);}
    public String deviceCode(){String id=Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);return id==null?"UNKNOWN":id;}
    public boolean activate(String code){if(code==null)return false;String x=code.trim();if(x.length()<8)return false;p.edit().putBoolean("activated",true).putString("license_key",x).apply();return true;}
}