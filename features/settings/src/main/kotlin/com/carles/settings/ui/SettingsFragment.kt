package com.carles.settings.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.carles.common.databinding.FragmentSettingsBinding
import com.carles.settings.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentSettingsBinding.bind(view)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val cacheListPreference = findPreference<ListPreference>(getString(R.string.preferences_cache_key))
        cacheListPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
        cacheListPreference?.setOnPreferenceChangeListener { preference, _ ->
            when (preference.key) {
                getString(R.string.preferences_cache_key) -> viewModel.onPreferenceCacheChanged()
            }
            true
        }
    }
}