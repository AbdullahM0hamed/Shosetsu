package app.shosetsu.common.domain.model.local

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
 * 17 / 01 / 2021
 *
 * @param content Content of the file
 */
data class BackupEntity(
	val content: ByteArray
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is BackupEntity) return false

		if (!content.contentEquals(other.content)) return false

		return true
	}

	override fun hashCode(): Int {
		return content.contentHashCode()
	}
}
