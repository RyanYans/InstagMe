package com.paisheng.instagme.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import com.paisheng.instagme.InstagmeApp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * 基本数据存储
 *
 * @author longluliu
 * @ClassName: SharedPreference
 * @Description: TODO
 * @date 2014-7-22 上午10:37:34
 */
public class SharedPreference {
    private static final String FILENAME = "SharedPreference.xml";
    private static SharedPreference instance;
    private SharedPreferences mSp;
    /**
     * 渠道
     */
    public static final String SHARED_PRE_CHANNEL= "channel";

    private SharedPreference() {
        mSp = InstagmeApp.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }

    public static SharedPreference getInstance() {
        if (instance == null) {
            instance = new SharedPreference();
        }
        return instance;
    }

    /**
     * 获取值
     *
     * @return String
     * @author longluliu
     * @date 2015-1-8 下午5:06:26
     */
    public String getStringValue(String key) {
        return mSp.getString(key, "");
    }

    /**
     * 获取值
     *
     * @return String
     * @author longluliu
     * @date 2015-1-8 下午5:06:26
     */
    public float getFloatValue(String key) {
        return mSp.getFloat(key, 0);
    }

    /**
     * 获取值
     *
     * @return String
     * @author longluliu
     * @date 2015-1-8 下午5:06:26
     */
    public int getIntValue(String key) {
        return mSp.getInt(key, 0);
    }

    /**
     * 获取值,带默认值
     *
     * @return String
     * @author longluliu
     * @date 2015-1-8 下午5:06:26
     */
    public int getIntValue(String key, int defaultValue) {
        return mSp.getInt(key, defaultValue);
    }

    /**
     * 获取值
     *
     * @return String
     * @author longluliu
     * @date 2015-1-8 下午5:06:26
     */
    public long getLongValue(String key, long def) {
        return mSp.getLong(key, def);
    }

    /**
     * 获取值
     *
     * @return String
     * @author longluliu
     * @date 2015-1-8 下午5:06:26
     */
    public long getLongValue(String key) {
        return mSp.getLong(key, 0);
    }

    /**
     * 获取值
     *
     * @return String
     * @author longluliu
     * @date 2015-1-8 下午5:06:26
     */
    public boolean getBooleanValue(String key) {
        return mSp.getBoolean(key, false);
    }

    /**
     * 获取值
     *
     * @return String
     * @author longluliu
     * @date 2015-1-8 下午5:06:26
     */
    public boolean getBooleanValue(String key, boolean defaultValue) {
        return mSp.getBoolean(key, defaultValue);
    }

    /**
     * 设置 值
     *
     * @return void
     * @author longluliu
     * @date 2015-1-8 下午5:06:36
     */
    public void putValue(String key, String value) {
        Editor editor = mSp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 设置 值
     *
     * @return void
     * @author longluliu
     * @date 2015-1-8 下午5:06:36
     */
    public void putValue(String key, int value) {
        Editor editor = mSp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 设置 值
     *
     * @return void
     * @author longluliu
     * @date 2015-1-8 下午5:06:36
     */
    public void putValue(String key, long value) {
        Editor editor = mSp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 设置 值
     *
     * @return void
     * @author longluliu
     * @date 2015-1-8 下午5:06:36
     */
    public void putValue(String key, boolean value) {
        Editor editor = mSp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 设置 值
     *
     * @return void
     * @author longluliu
     * @date 2015-1-8 下午5:06:36
     */
    public void putValue(String key, float value) {
        Editor editor = mSp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 本地存储中是否包涵该key的值
     *
     * @param key 键
     * @return 是否包涵
     */
    public boolean contains(String key) {
        return mSp.contains(key);
    }

    public void putObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        Editor editor = mSp.edit();
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectVal);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (mSp.contains(key)) {
            String objectVal = mSp.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
