package app.shosetsu.common.domain.repositories.base

import app.shosetsu.common.dto.HResult
import app.shosetsu.common.enums.DownloadStatus
import kotlinx.coroutines.flow.Flow

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

/**
 * Shosetsu
 *
 * This repository handles queuing of extension installs.
 *
 * This repository's data should be volatile.
 * If the application is stopped for any reason, all data is lost.
 *
 * @since 30 / 06 / 2021
 * @author Doomsdayrs
 */
interface IExtensionDownloadRepository {
	/**
	 * How many entries are pending
	 */
	val size: Int

	/**
	 * Provides the first extension to install
	 */
	val first: HResult<Int>

	suspend fun add(extension: Int): HResult<*>

	suspend fun remove(extension: Int): HResult<*>

	suspend fun getStatus(extension: Int): HResult<DownloadStatus>

	suspend fun getStatusFlow(extension: Int): Flow<HResult<DownloadStatus>>

	suspend fun updateStatus(extension: Int, status: DownloadStatus): HResult<*>
}