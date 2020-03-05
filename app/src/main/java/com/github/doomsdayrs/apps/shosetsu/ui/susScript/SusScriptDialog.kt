package com.github.doomsdayrs.apps.shosetsu.ui.susScript

import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.util.Log
import com.github.doomsdayrs.api.shosetsu.services.core.LuaFormatter
import com.github.doomsdayrs.apps.shosetsu.R
import com.github.doomsdayrs.apps.shosetsu.backend.FormatterUtils
import com.github.doomsdayrs.apps.shosetsu.ui.susScript.objects.DialogBody
import com.github.doomsdayrs.apps.shosetsu.ui.susScript.objects.FileObject
import com.github.doomsdayrs.apps.shosetsu.variables.obj.DefaultScrapers
import com.github.doomsdayrs.apps.shosetsu.backend.Settings
import org.json.JSONObject
import java.io.File

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
 * ====================================================================
 */

/**
 * shosetsu
 * 19 / 01 / 2020
 *
 * @author github.com/doomsdayrs
 */
class SusScriptDialog(val activity: Activity, fileList: ArrayList<File>) {


    val files = ArrayList<FileObject>()

    init {
        for (file in fileList) {
            files.add(FileObject(file))
        }
    }

    fun execute(finalAction: () -> Unit) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle(activity.getString(R.string.sus_script_title))
        builder.setMessage(R.string.sus_scripts)

        val dialog = DialogBody(activity.layoutInflater, builder, this)
        builder.setView(dialog.view)

        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            processActions()
            finalAction()
        }

        builder.setNegativeButton(android.R.string.cancel) { _, _ ->
            for (file in files) {
                Log.i("SusScriptDialog", "Deleting\t${file.file.name}")
                file.delete()
            }
        }
        dialog.dialog = builder.show()
    }

    fun processActions() {
        for (file in files) {
            Log.i("SusScriptDialog", "File confirmed Action\t${file.file.name}\t${file.action}")
            when (file.action) {
                0 -> {
                    FormatterUtils.trustScript(file.file)
                    DefaultScrapers.formatters.add(LuaFormatter(file.file))
                }
                1 -> {
                    DefaultScrapers.formatters.add(LuaFormatter(file.file))
                }
                2 -> {
                    val meta = FormatterUtils.getMetaData(file.file)
                    val js = JSONObject()
                    js.put("name", meta!!.getString("name"))
                    js.put("id", meta.getInt("id"))
                    js.put("imageUrl", "")

                    val a = Settings.disabledFormatters
                    a.put(js)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        DefaultScrapers.formatters.removeIf { it.formatterID == meta.getInt("id") }
                    } else {
                        var point = -1
                        for (i in 0 until DefaultScrapers.formatters.size)
                            if (DefaultScrapers.formatters[i].formatterID == meta.getInt("id"))
                                point = i
                        if (point != -1)
                            DefaultScrapers.formatters.removeAt(point)
                    }
                    Settings.disabledFormatters = a
                }
                3 -> {
                    file.delete()
                }
            }
        }
    }

    fun setAll(int: Int) {
        for (file in files)
            file.action = int
    }
}