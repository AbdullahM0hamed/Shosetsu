package app.shosetsu.android.ui.browse

/*
 * This file is part of shosetsu.
 *
 * shosetsu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * shosetsu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with shosetsu.  If not, see <https://www.gnu.org/licenses/>.
 */

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import app.shosetsu.android.common.consts.BundleKeys.BUNDLE_EXTENSION
import app.shosetsu.android.common.ext.*
import app.shosetsu.android.ui.catalogue.CatalogController
import app.shosetsu.android.ui.extensionsConfigure.ConfigureExtension
import app.shosetsu.android.view.controller.FastAdapterRefreshableRecyclerController
import app.shosetsu.android.view.controller.base.PushCapableController
import app.shosetsu.android.view.uimodels.model.ExtensionUI
import app.shosetsu.android.viewmodel.abstracted.IExtensionsViewModel
import app.shosetsu.common.dto.HResult
import com.bluelinelabs.conductor.Controller
import com.github.doomsdayrs.apps.shosetsu.R
import com.mikepenz.fastadapter.FastAdapter

/**
 * shosetsu
 * 18 / 01 / 2020
 *
 * @author github.com/doomsdayrs
 */
class BrowseController : FastAdapterRefreshableRecyclerController<ExtensionUI>(),
	PushCapableController {
	override val viewTitleRes: Int = R.string.browse

	init {
		setHasOptionsMenu(true)
	}

	override var pushController: (Controller) -> Unit = {}


	/***/
	val viewModel: IExtensionsViewModel by viewModel()

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.toolbar_extensions, menu)
		(menu.findItem(R.id.catalogues_search).actionView as SearchView)
			.setOnQueryTextListener(BrowseSearchQuery(pushController))
	}

	override fun FastAdapter<ExtensionUI>.setupFastAdapter() {
		setOnClickListener { _, _, item, _ ->
			if (item.installed)
				if (viewModel.isOnline()) {
					pushController(
						CatalogController(
							bundleOf(
								BUNDLE_EXTENSION to item.id
							)
						)
					)
				} else context?.toast(R.string.you_not_online)
			else toast(R.string.ext_not_installed)
			true
		}


		hookClickEvent(
			bind = { it: ExtensionUI.ViewHolder -> it.binding.button }
		) { _, _, _, item ->
			var installed = false
			var update = false
			if (item.installed && item.isExtEnabled) {
				installed = true
				if (item.updateState() == ExtensionUI.State.UPDATE) update = true
			}

			if (!installed || update) {
				it.binding.button.isVisible = false
				it.binding.progress.isVisible = true
				viewModel.installExtension(item)
			}
		}

		hookClickEvent(
			bind = { it: ExtensionUI.ViewHolder -> it.binding.settings }
		) { _, _, _, item ->
			pushController(ConfigureExtension(bundleOf(BUNDLE_EXTENSION to item.id)))
		}
	}


	override fun showEmpty() {
		super.showEmpty()
		binding.emptyDataView.show("No extensions installed, Press the refresh button on the top right")
	}

	override fun handleErrorResult(e: HResult.Error) {
		super.handleErrorResult(e)
		viewModel.reportError(e)
	}

	override fun onViewCreated(view: View) {
		super.onViewCreated(view)
		showEmpty()
		viewModel.liveData.observe(this) { handleRecyclerUpdate(it) }
	}

	override fun updateUI(newList: List<ExtensionUI>) {
		launchIO {
			val list = newList
				.sortedBy { it.name }
				.sortedBy { it.lang }
				.sortedBy { !it.installed }
				.sortedBy { it.updateState() != ExtensionUI.State.UPDATE }
			launchUI { super.updateUI(list) }
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
		R.id.refresh -> {
			refreshExtensions()
			true
		}
		R.id.catalogues_search -> true
		else -> false
	}

	private fun refreshExtensions() {
		if (viewModel.isOnline())
			viewModel.refreshRepository()
		else toast(R.string.you_not_online)
	}

	override fun onRefresh() = refreshExtensions()
}
