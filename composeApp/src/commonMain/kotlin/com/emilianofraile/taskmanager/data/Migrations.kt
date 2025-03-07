package com.emilianofraile.taskmanager.data

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL("ALTER TABLE task ADD COLUMN is_completed INTEGER NOT NULL DEFAULT 0")
    }
} 