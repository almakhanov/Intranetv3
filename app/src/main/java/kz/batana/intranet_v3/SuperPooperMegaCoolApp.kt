package kz.batana.intranet_v3

import android.app.Application
import android.arch.persistence.room.Room
import kz.batana.intranet_v3.SplashActivity.Companion.log
import kz.batana.intranet_v3.data.localDB.database.AppDatabase

class SuperPooperMegaCoolApp: Application() {
    companion object {
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        log("SuperPooperMegaCoolApp is created!")
        appDatabase =  Room.databaseBuilder(this, AppDatabase::class.java, "appDatabase").build()
    }

}