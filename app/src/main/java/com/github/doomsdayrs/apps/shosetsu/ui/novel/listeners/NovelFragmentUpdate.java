package com.github.doomsdayrs.apps.shosetsu.ui.novel.listeners;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.doomsdayrs.apps.shosetsu.ui.novel.async.NovelLoader;
import com.github.doomsdayrs.apps.shosetsu.ui.novel.pages.NovelFragmentInfo;

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
 * Shosetsu
 * 06 / 07 / 2019
 *
 * @author github.com/doomsdayrs
 */

public class NovelFragmentUpdate implements SwipeRefreshLayout.OnRefreshListener {
    private NovelFragmentInfo novelFragmentInfo;

    public NovelFragmentUpdate(NovelFragmentInfo novelFragmentInfo) {
        this.novelFragmentInfo = novelFragmentInfo;
    }

    @Override
    public void onRefresh() {
        new NovelLoader(novelFragmentInfo, false).execute(novelFragmentInfo.getActivity());
    }
}
