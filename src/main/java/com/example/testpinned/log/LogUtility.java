package com.example.testpinned.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * a log helper class，will print the position where the log method was called as the prefix of log
 * info.
 */
public class LogUtility {
    
    private static final boolean WARN = true;
    
    public static final boolean IS_LOG_CTRL_OPEN = true;
    
    private static final boolean ERROR = true;
    
    private static final boolean INFO = true;
    
    private static final int LOG_UNIT_LENGTH = 8;

    private static final int STACK_LENGHT_2 = 2;

    /**
     * get log position info
     * 
     * @return
     */
    private static String getCallerInfo() {
    
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        StackTraceElement ele = null;
        String className = "";
        String methodName = "";
        int lineNO = 0;
        if (stack.length > STACK_LENGHT_2) {
            ele = stack[2];
            try {
                className = Class.forName(ele.getClassName()).getSimpleName();
                methodName = ele.getMethodName();
                lineNO = ele.getLineNumber();
            } catch (ClassNotFoundException e) {
            }
        }
        
        String callerInfo = className + ":" + methodName + ":" + lineNO + "=>";
        return callerInfo;
    }

    public static void d(String tag, String msg) {
    
        if (IS_LOG_CTRL_OPEN) {
            Log.d(tag, getCallerInfo() + String.valueOf(msg));
        }
    }
    
    public static void d(String tag, String msg, Exception e) {
    
        if (IS_LOG_CTRL_OPEN) {
            Log.d(tag, getCallerInfo() + String.valueOf(msg), e);
        }
    }
    
    public static void d(String tag, String msg, Throwable throwable) {
    
        if (IS_LOG_CTRL_OPEN) {
            Log.d(tag, getCallerInfo() + String.valueOf(msg), throwable);
        }
    }

    public static void w(String tag, String msg) {
    
        if (WARN) {
            Log.w(tag, getCallerInfo() + String.valueOf(msg));
        }
    }
    
    public static void w(String tag, String msg, Exception e) {
    
        if (WARN) {
            Log.w(tag, getCallerInfo() + String.valueOf(msg), e);
        }
    }
    
    public static void v(String tag, String msg) {
    
        if (IS_LOG_CTRL_OPEN) {
            Log.v(tag, getCallerInfo() + String.valueOf(msg));
        }
    }

    public static void i(String tag, String msg) {
    
        if (INFO) {
            Log.i(tag, getCallerInfo() + String.valueOf(msg));
        }
    }

    public static void i(String tag, Object...obj) {
        if (!INFO) {
            return;
        }
        if (obj == null || obj.length <= 0) {
            Log.i(tag, "null");
            return;
        }
        try {
            StringBuilder sb = new StringBuilder(LOG_UNIT_LENGTH * obj.length);
            sb.append(getCallerInfo());
            for (Object o : obj) {
                sb.append(o).append("  ");
            }
            Log.i(tag, sb.toString());
        } catch (OutOfMemoryError oomError) {
            Log.e("OutOfMemoryError",  printStack(tag));
        }
    }

    public static void i(String tag, String msg, Throwable throwable) {
        
        if (INFO) {
            Log.i(tag, getCallerInfo() + String.valueOf(msg), throwable);
        }
    }

    public static String printStack(String tag) {
        if (TextUtils.isEmpty(tag)) {
            tag = "callStackLine";
        }
        StringBuilder sb = new StringBuilder(1024);
        Thread thread = Thread.currentThread();
        StackTraceElement[] ele = thread.getStackTrace();
        for (StackTraceElement s:ele) {
            sb.append(tag).append(s).append("\n");
        }
        Log.i("stack:", sb.toString());
        return sb.toString();
    }

    public static void e(String tag, String msg) {
    
        if (ERROR) {
            Log.e(tag, getCallerInfo() + String.valueOf(msg));
        }
    }
    
    public static void e(String tag, String msg, Exception e) {
    
        if (ERROR) {
            Log.e(tag, getCallerInfo() + String.valueOf(msg), e);
        }
    }
    
    public static void e(String tag, String msg, Throwable throwable) {
    
        if (ERROR) {
            Log.e(tag, getCallerInfo() + String.valueOf(msg), throwable);
        }
    }
    
}