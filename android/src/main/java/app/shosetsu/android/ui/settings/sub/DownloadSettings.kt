package app.shosetsu.android.ui.settings.sub

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import app.shosetsu.android.common.consts.ActivityRequestCodes.REQUEST_CODE_DIRECTORY
import app.shosetsu.android.common.ext.*
import app.shosetsu.android.ui.settings.SettingsSubController
import app.shosetsu.android.view.uimodels.settings.TextSettingData
import app.shosetsu.android.view.uimodels.settings.base.SettingsItemData
import app.shosetsu.android.view.uimodels.settings.dsl.onClicked
import app.shosetsu.android.viewmodel.abstracted.settings.ADownloadSettingsViewModel
import app.shosetsu.android.viewmodel.base.IWorkerUpdatingViewModel
import app.shosetsu.common.dto.handle
import com.github.doomsdayrs.apps.shosetsu.R
import com.google.android.material.snackbar.Snackbar

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
 */

/**
 * Shosetsu
 * 13 / 07 / 2019
 */
class DownloadSettings : SettingsSubController() {
	override val viewTitleRes: Int = R.string.settings_download
	override val viewModel: ADownloadSettingsViewModel by viewModel()

	override val adjustments: List<SettingsItemData>.() -> Unit = {
		find<TextSettingData>(1)?.onClicked {
			performFileSearch()
		}
	}

	private fun setDownloadDirectory(dir: String) {
		//s.downloadDirectory = dir
		recyclerView.post { adapter?.notifyItemChanged(0) }
	}

	private fun performFileSearch() {
		context?.toast(
			"Please make sure this is on the main storage, " +
					"SD card storage is not functional yet", duration = Toast.LENGTH_LONG
		)
		val i = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
		i.addCategory(Intent.CATEGORY_DEFAULT)
		activity?.startActivityForResult(Intent.createChooser(i, "Choose directory"), 42)
	}

	private var workersToRestart: IWorkerUpdatingViewModel.WorkerIdentifier? = null

	override fun onViewCreated(view: View) {
		super.onViewCreated(view)
		viewModel.workerSettingsChanged.observe { hResult ->
			hResult.handle {
				workersToRestart = it
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		launchUI {
			val workersToRestart = workersToRestart
			workersToRestart?.let { identifier ->
				makeSnackBar(
					getString(
						R.string.controller_settings_restart_worker,
						getString(identifier.nameRes)
					),
					Snackbar.LENGTH_LONG
				)?.setAction(R.string.restart) {
					viewModel.restartManager(identifier.id)
				}?.show()
			}
		}

	}


	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == REQUEST_CODE_DIRECTORY && resultCode == Activity.RESULT_OK) {
			if (data != null) {
				val path = data.data?.path
				Log.i("Selected Folder", "Uri: $path")
				if (path != null)
					setDownloadDirectory(path.substring(path.indexOf(":") + 1))
				else context?.toast("Path is null")
			}
		}
	}
}