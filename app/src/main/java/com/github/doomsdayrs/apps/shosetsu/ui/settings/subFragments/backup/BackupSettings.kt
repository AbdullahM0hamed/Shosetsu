package com.github.doomsdayrs.apps.shosetsu.ui.settings.subFragments.backup

import android.app.Activity
import android.content.Intent
import android.widget.CompoundButton
import android.widget.Toast.LENGTH_LONG
import com.github.doomsdayrs.apps.shosetsu.R
import com.github.doomsdayrs.apps.shosetsu.backend.Settings
import com.github.doomsdayrs.apps.shosetsu.ui.settings.SettingsSubController
import com.github.doomsdayrs.apps.shosetsu.ui.settings.subFragments.backup.async.BackupProcess
import com.github.doomsdayrs.apps.shosetsu.ui.settings.subFragments.backup.async.RestoreProcess
import com.github.doomsdayrs.apps.shosetsu.ui.settings.viewHolder.SettingsItem.SettingsItemData
import com.github.doomsdayrs.apps.shosetsu.ui.settings.viewHolder.SettingsItem.SettingsItemData.SettingsType
import com.github.doomsdayrs.apps.shosetsu.variables.ext.context
import com.github.doomsdayrs.apps.shosetsu.variables.ext.toast
import com.vincent.filepicker.Constant
import com.vincent.filepicker.activity.NormalFilePickActivity
import com.vincent.filepicker.filter.entity.NormalFile
import java.util.*

/*
 * This file is part of Shosetsu.
 *
 * Shosetsu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Shosetsu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Shosetsu.  If not, see <https://www.gnu.org/licenses/>.
 * ====================================================================
 */

/**
 * Shosetsu
 * 13 / 07 / 2019
 *
 * @author github.com/doomsdayrs
 */
class BackupSettings : SettingsSubController() {
	override val settings by lazy {
		arrayListOf(
				SettingsItemData(SettingsType.CHECKBOX)
						.setTitle(R.string.backup_chapters_option)
						.setDescription(R.string.backup_chapters_option_description)
						.setIsChecked(Settings.backupChapters)
						.setOnCheckedListner(CompoundButton.OnCheckedChangeListener { _, isChecked ->
							Settings.backupChapters = isChecked
						}),
				SettingsItemData(SettingsType.CHECKBOX)
						.setTitle((R.string.backup_settings_option))
						.setDescription(R.string.backup_settings_option_desc)
						.setIsChecked(Settings.backupSettings)
						.setOnCheckedListner(CompoundButton.OnCheckedChangeListener { _, isChecked ->
							Settings.backupSettings = isChecked
						}),
                SettingsItemData(SettingsType.CHECKBOX)
                        .setTitle(R.string.backup_quick_option)
                        .setDescription(R.string.backup_quick_option_desc)
                        .setIsChecked(Settings.backupQuick)
                        .setOnCheckedListner(CompoundButton.OnCheckedChangeListener { _, isChecked ->
                            Settings.backupQuick = isChecked
                        }),
				SettingsItemData(SettingsType.BUTTON)
						.setOnClickListenerButton { it.post { BackupProcess().execute() } }
						.setTitle(R.string.backup_now)
						.setTextViewText(R.string.restore_now),
				SettingsItemData(SettingsType.BUTTON)
						.setOnClickListenerButton {
							it.post { this@BackupSettings.performFileSelection() }
						}
						.setTitle(R.string.restore_now)
						.setTextViewText(R.string.restore_now)
		)
	}

	@Suppress("unused")
	private fun performFileSelection() {
		context?.toast("Please make sure this is on the main storage, " +
				"SD card storage is not functional yet", duration = LENGTH_LONG)
		val intent = Intent(context, NormalFilePickActivity::class.java)
		intent.putExtra(Constant.MAX_NUMBER, 9)
		intent.putExtra(NormalFilePickActivity.SUFFIX, arrayOf("shoback", "json"))
		startActivityForResult(intent, Constant.REQUEST_CODE_PICK_FILE)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (Constant.REQUEST_CODE_PICK_FILE == requestCode && resultCode == Activity.RESULT_OK) {
			if (data != null) {
				val list: ArrayList<NormalFile>? =
						data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE)
				if (list != null && list.size > 0) {
					val normalFile = list[0]
					RestoreProcess(normalFile.path, context!!).execute()
				}
				/*
				String path = data.getData().getPath();
				Log.i("SelectedPath", path);

				int i = path.lastIndexOf(".");
				if (i > -1) {
					String fileEnding = path.substring(i + 1);
					if (fileEnding.equalsIgnoreCase("shoback")) {
						Log.i("Selected Folder", "Uri: " + path);
						//TODO Fix this shit, need's a proper integrated file manager
						new RestoreProcess("/Shosetsu/backup/backup-Mon Oct
						28 20:46:16 EDT 2019.shoback", getContext()).execute();
					} else
						Toast.makeText(getContext(), "Invalid file to use!",
						 Toast.LENGTH_LONG).show();
				}*/
			}
		}
	}
}