package com.fabian.schengenvisacalculator

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.fabian.schengenvisacalculator.data.SettingsRepository
import timber.log.Timber

class SchengenCalculatorApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppCompatDelegate.setDefaultNightMode(SettingsRepository().nightMode)
    }
}