package com.emilianofraile.taskmanager.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emilianofraile.taskmanager.data.AppDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("task_db.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}