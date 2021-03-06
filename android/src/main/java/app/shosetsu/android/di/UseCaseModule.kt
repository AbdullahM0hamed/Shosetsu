package app.shosetsu.android.di

import app.shosetsu.android.domain.ReportExceptionUseCase
import app.shosetsu.android.domain.usecases.*
import app.shosetsu.android.domain.usecases.delete.DeleteChapterPassageUseCase
import app.shosetsu.android.domain.usecases.delete.DeleteDownloadUseCase
import app.shosetsu.android.domain.usecases.get.*
import app.shosetsu.android.domain.usecases.load.*
import app.shosetsu.android.domain.usecases.open.OpenInBrowserUseCase
import app.shosetsu.android.domain.usecases.open.OpenInWebviewUseCase
import app.shosetsu.android.domain.usecases.settings.LoadChaptersResumeFirstUnreadUseCase
import app.shosetsu.android.domain.usecases.settings.LoadNavigationStyleUseCase
import app.shosetsu.android.domain.usecases.settings.SetNovelUITypeUseCase
import app.shosetsu.android.domain.usecases.toast.StringToastUseCase
import app.shosetsu.android.domain.usecases.toast.ToastErrorUseCase
import app.shosetsu.android.domain.usecases.update.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

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
 * shosetsu
 * 01 / 05 / 2020
 */
val useCaseModule: Kodein.Module = Kodein.Module("useCase") {

	bind<LoadDownloadsUseCase>() with provider { LoadDownloadsUseCase(instance()) }

	bind<LoadLibraryUseCase>() with provider { LoadLibraryUseCase(instance(), instance()) }

	bind<SearchBookMarkedNovelsUseCase>() with provider { SearchBookMarkedNovelsUseCase(instance()) }


	bind<LoadExtensionsUIUseCase>() with provider { LoadExtensionsUIUseCase(instance()) }

	bind<LoadUpdatesUseCase>() with provider { LoadUpdatesUseCase(instance()) }

	bind<RefreshRepositoryUseCase>() with provider { RefreshRepositoryUseCase(instance()) }

	bind<InitializeExtensionsUseCase>() with provider {
		InitializeExtensionsUseCase(instance(), instance(), instance(), instance())
	}

	bind<InstallExtensionUIUseCase>() with provider { InstallExtensionUIUseCase(instance()) }

	bind<UpdateNovelUseCase>() with provider { UpdateNovelUseCase(instance()) }

	bind<GetExtensionUseCase>() with provider { GetExtensionUseCase(instance()) }

	bind<NovelBackgroundAddUseCase>() with provider {
		NovelBackgroundAddUseCase(instance(), instance())
	}

	bind<GetNovelUIUseCase>() with provider { GetNovelUIUseCase(instance(), instance()) }

	bind<GetNovelUseCase>() with provider {
		GetNovelUseCase(instance(), instance(), instance(), instance(), instance(), instance())
	}

	bind<GetCatalogueListingDataUseCase>() with provider {
		GetCatalogueListingDataUseCase(instance(), instance(), instance(), instance())
	}

	bind<GetChapterUIsUseCase>() with provider { GetChapterUIsUseCase(instance()) }

	bind<UpdateChapterUseCase>() with provider { UpdateChapterUseCase(instance()) }

	bind<UpdateReaderChapterUseCase>() with provider { UpdateReaderChapterUseCase(instance()) }
	bind<GetReaderChaptersUseCase>() with provider { GetReaderChaptersUseCase(instance()) }

	bind<GetChapterPassageUseCase>() with provider {
		GetChapterPassageUseCase(instance(), instance())
	}

	bind<DownloadChapterPassageUseCase>() with provider {
		DownloadChapterPassageUseCase(instance(), instance(), instance())
	}
	bind<DeleteChapterPassageUseCase>() with provider { DeleteChapterPassageUseCase(instance()) }

	bind<StartDownloadWorkerUseCase>() with provider {
		StartDownloadWorkerUseCase(instance())
	}
	bind<StartUpdateWorkerUseCase>() with provider {
		StartUpdateWorkerUseCase(instance())
	}

	bind<LoadRemoteAppUpdateUseCase>() with provider { LoadRemoteAppUpdateUseCase(instance()) }
	bind<UpdateDownloadUseCase>() with provider { UpdateDownloadUseCase(instance()) }
	bind<DeleteDownloadUseCase>() with provider { DeleteDownloadUseCase(instance()) }

	bind<UpdateExtensionEntityUseCase>() with provider { UpdateExtensionEntityUseCase(instance()) }

	bind<UpdateBookmarkedNovelUseCase>() with provider { UpdateBookmarkedNovelUseCase(instance()) }

	bind<UninstallExtensionUIUseCase>() with provider { UninstallExtensionUIUseCase(instance()) }

	bind<StringToastUseCase>() with provider { StringToastUseCase(instance()) }

	bind<OpenInWebviewUseCase>() with provider {
		OpenInWebviewUseCase(
			instance(),
			instance(),
			instance()
		)
	}
	bind<OpenInBrowserUseCase>() with provider {
		OpenInBrowserUseCase(
			instance(),
			instance(),
			instance()
		)
	}
	bind<ShareUseCase>() with provider { ShareUseCase(instance(), instance(), instance()) }
	bind<ToastErrorUseCase>() with provider { ToastErrorUseCase(instance()) }
	bind<IsOnlineUseCase>() with provider { IsOnlineUseCase(instance()) }

	bind<LoadAppUpdateFlowLiveUseCase>() with provider { LoadAppUpdateFlowLiveUseCase(instance()) }

	bind<GetCatalogueQueryDataUseCase>() with provider {
		GetCatalogueQueryDataUseCase(instance(), instance(), instance(), instance())
	}
	bind<ConvertNCToCNUIUseCase>() with provider { ConvertNCToCNUIUseCase() }
	bind<LoadSearchRowUIUseCase>() with provider { LoadSearchRowUIUseCase((instance())) }
	bind<GetExtensionSettingsUseCase>() with provider { GetExtensionSettingsUseCase(instance()) }
	bind<GetExtensionUIUseCase>() with provider { GetExtensionUIUseCase(instance()) }
	bind<LoadRepositoriesUseCase>() with provider { LoadRepositoriesUseCase(instance()) }
	bind<LoadReaderThemes>() with provider {
		LoadReaderThemes(instance(), instance())
	}
	bind<LoadChaptersResumeFirstUnreadUseCase>() with provider {
		LoadChaptersResumeFirstUnreadUseCase(instance())
	}

	bind<ReportExceptionUseCase>() with provider { ReportExceptionUseCase(instance(), instance()) }
	bind<LoadNavigationStyleUseCase>() with provider { LoadNavigationStyleUseCase(instance()) }

	bind<LoadLiveAppThemeUseCase>() with provider { LoadLiveAppThemeUseCase(instance()) }

	bind<LoadNovelUIColumnsPUseCase>() with provider { LoadNovelUIColumnsPUseCase(instance()) }
	bind<LoadNovelUIColumnsHUseCase>() with provider { LoadNovelUIColumnsHUseCase(instance()) }
	bind<LoadNovelUITypeUseCase>() with provider { LoadNovelUITypeUseCase(instance()) }
	bind<StartAppUpdateInstallWorkerUseCase>() with provider {
		StartAppUpdateInstallWorkerUseCase(instance())
	}
	bind<CanAppSelfUpdateUseCase>() with provider {
		CanAppSelfUpdateUseCase(instance())
	}
	bind<LoadAppUpdateUseCase>() with provider {
		LoadAppUpdateUseCase(instance())
	}
	bind<SetNovelUITypeUseCase>() with provider {
		SetNovelUITypeUseCase(instance())
	}

	bind<GetNovelSettingFlowUseCase>() with provider {
		GetNovelSettingFlowUseCase(instance(), instance(), instance())
	}
	bind<UpdateNovelSettingUseCase>() with provider {
		UpdateNovelSettingUseCase(instance())
	}
	bind<LoadDeletePreviousChapterUseCase>() with provider {
		LoadDeletePreviousChapterUseCase(instance())
	}


}