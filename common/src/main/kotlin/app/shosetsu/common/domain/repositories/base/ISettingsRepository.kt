package app.shosetsu.common.domain.repositories.base

import app.shosetsu.common.consts.settings.SettingKey
import app.shosetsu.common.dto.HResult
import app.shosetsu.common.dto.unwrap
import kotlinx.coroutines.flow.Flow

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
 * shosetsu
 * 17 / 09 / 2020
 */
interface ISettingsRepository {

	fun getLongFlow(key: SettingKey<Long>): Flow<Long>

	fun getStringFlow(key: SettingKey<String>): Flow<String>

	fun getIntFlow(key: SettingKey<Int>): Flow<Int>

	fun getBooleanFlow(key: SettingKey<Boolean>): Flow<Boolean>

	fun getFloatFlow(key: SettingKey<Float>): Flow<Float>

	fun getStringSetFlow(key: SettingKey<Set<String>>): Flow<Set<String>>


	suspend fun getLong(key: SettingKey<Long>): HResult<Long>

	suspend fun getString(key: SettingKey<String>): HResult<String>

	suspend fun getInt(key: SettingKey<Int>): HResult<Int>

	suspend fun getBoolean(key: SettingKey<Boolean>): HResult<Boolean>

	suspend fun getStringSet(key: SettingKey<Set<String>>): HResult<Set<String>>

	suspend fun getFloat(key: SettingKey<Float>): HResult<Float>


	suspend fun setLong(key: SettingKey<Long>, value: Long): HResult<*>

	suspend fun setString(key: SettingKey<String>, value: String): HResult<*>

	suspend fun setInt(key: SettingKey<Int>, value: Int): HResult<*>

	suspend fun setBoolean(key: SettingKey<Boolean>, value: Boolean): HResult<*>

	suspend fun setStringSet(key: SettingKey<Set<String>>, value: Set<String>): HResult<*>

	suspend fun setFloat(key: SettingKey<Float>, value: Float): HResult<*>
}

/**
 * Integrates with [unwrap] for an [HResult]
 * If [unwrap] returns a null, will return to the [key]s [SettingKey.default]
 */
suspend fun ISettingsRepository.getLongOrDefault(key: SettingKey<Long>) =
	getLong(key).unwrap() ?: key.default

/**
 * Integrates with [unwrap] for an [HResult]
 * If [unwrap] returns a null, will return to the [key]s [SettingKey.default]
 */
suspend fun ISettingsRepository.getStringOrDefault(key: SettingKey<String>) =
	getString(key).unwrap() ?: key.default

/**
 * Integrates with [unwrap] for an [HResult]
 * If [unwrap] returns a null, will return to the [key]s [SettingKey.default]
 */
suspend fun ISettingsRepository.getIntOrDefault(key: SettingKey<Int>) =
	getInt(key).unwrap() ?: key.default

/**
 * Integrates with [unwrap] for an [HResult]
 * If [unwrap] returns a null, will return to the [key]s [SettingKey.default]
 */
suspend fun ISettingsRepository.getBooleanOrDefault(key: SettingKey<Boolean>) =
	getBoolean(key).unwrap() ?: key.default

/**
 * Integrates with [unwrap] for an [HResult]
 * If [unwrap] returns a null, will return to the [key]s [SettingKey.default]
 */
suspend fun ISettingsRepository.getStringSetOrDefault(key: SettingKey<Set<String>>) =
	getStringSet(key).unwrap() ?: key.default

/**
 * Integrates with [unwrap] for an [HResult]
 * If [unwrap] returns a null, will return to the [key]s [SettingKey.default]
 */
suspend fun ISettingsRepository.getFloatOrDefault(key: SettingKey<Float>) =
	getFloat(key).unwrap() ?: key.default