# Reglas para Room
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

# Reglas para la aplicación
-keep class com.emilianofraile.taskmanager.** { *; }
-keepclassmembers class com.emilianofraile.taskmanager.** { *; }

# Reglas para SQLite
-keep class org.sqlite.** { *; }
-keep class org.sqlite.database.** { *; } 