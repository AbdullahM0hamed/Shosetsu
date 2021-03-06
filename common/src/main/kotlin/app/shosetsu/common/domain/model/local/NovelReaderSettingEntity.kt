package app.shosetsu.common.domain.model.local

import app.shosetsu.common.enums.ReaderType
import app.shosetsu.common.enums.TextSizes

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
 * 03 / 01 / 2021
 *
 * Defines the reader settings for a novel
 */
data class NovelReaderSettingEntity(
	val novelID: Int,

	// how the reader is set-up
	var type: ReaderType,

	var themeChoice: Int,

	var textSize: TextSizes,

	var paragraphIndentSize: Int,

	var paragraphSpacingSize: Int,

	var volumeScrolling: Boolean,

	var tapToScroll: Boolean
)
