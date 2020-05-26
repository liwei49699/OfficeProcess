package com.chengzhen.kotlinappother

import android.app.Activity
import android.os.Process

import java.util.Stack


class ActivityManager private constructor() {

    private val activityStack = Stack<Activity>()

    /**
     * 静态内部类的单例模式（最优）
     */
    private object ActivityManagerHolder {

        private val INSTANCE = ActivityManager()
    }


    /**
     * 将Activity加入Stack
     * @param activity
     */
    fun addActivity(activity: Activity) {

        if (!activityStack.contains(activity)) {

            //            activityStack.push(activity);
            activityStack.add(activity)
        }
    }

    /**
     * 结束Activity并移除Stack
     * @param activity
     */
    fun removeActivity(activity: Activity) {

        if (activityStack.contains(activity)) {

            activityStack.remove(activity)

            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    /**
     * 关闭所有的Activity
     */
    fun finishAllActivitys() {

        for (Activity in activityStack) {
            if (!Activity.isFinishing) {
                Activity.finish()
            }
        }
        activityStack.clear()
    }

    /**
     * 退出应用
     */
    fun exitApp() {

        for (activity in activityStack) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activityStack.clear()
        //杀死进程    android.os.Process;
        Process.killProcess(Process.myPid())
        //其他数字非正常退出
        System.exit(0)
    }

    companion object {

        val instance: ActivityManager
            get() = ActivityManagerHolder.INSTANCE
    }
}
