package com.shengsiyuan.kotlin.translate

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import kotlin.test.Test

/**
 * Created by andy
 * 2019-11-28.
 * Email: 1239604859@qq.com
 *
 * https://docs.google.com/spreadsheets/d/1fVPwGUzo10esRJckB97SkJuqlepEwjTSkhhNsytcyqQ/edit#gid=979116593
 */
class Ticpod2Translate {
    companion object {
        private const val ROW_TITLE = 1
        private const val INDEX_TITLE_FIRST = 2 // 一列就是一个国家语言文件
        private const val INDEX_TITLE_LAST = 9

        private const val ROW_CONTENT = 2

        private const val INDEX_KEY_H5 = 300
    }

    private val out = File("/Users/andy/Desktop/out")

    /**
     * All column
     */
    private val content = ArrayList<ArrayList<String>>()

    private fun generateFiles() {
        if (!out.exists()) {
            out.mkdirs()
        }
        for (i in INDEX_TITLE_FIRST..INDEX_TITLE_LAST) {
            val fileName = "${content[i][ROW_TITLE]}.xml"
            println("fileName: $fileName")
            val file = File(out, fileName)
            if (!file.exists()) {
                file.createNewFile()
            }
            writeAndroidValueStringXml(file, content[i])
            println("finish this file")
        }
    }

    private fun writeAndroidValueStringXml(file: File, list: ArrayList<String>) {
        file.writeText("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
        file.appendText("\n")
        file.appendText("<resources>")
        file.appendText("\n")

        val keys = content[0]
        println(keys)
        println(list)
        keys.forEachIndexed { index, it ->
            if (index >= ROW_CONTENT) {
                if (it.isNotBlank() && it.isNotEmpty()) {
                    // key = it
                    // value = list[index]
                    file.appendText("    ") // \t
                    file.appendText("<string name=\"$it\">${list[index]}</string>")
                    file.appendText("\n")
                }
            }
        }
        file.appendText("</resources>")
    }

    /**
     * @param files 文件顺序要和文档保持一致: en fr de it ja es ru pt th
     */
    private fun appendFiles(files: List<File>, keys: List<String>, expectKeys: List<String>) {
        var columns: List<String>? = null
        files.forEach { file ->
            val name = file.absoluteFile.parentFile.name
            println(name)
            if (name.length > 2) {
                when (name.substring(name.length - 2, name.length)) {
                    "en" -> {
                        columns = content[INDEX_TITLE_FIRST]
                    }
                    "fr" -> {
                        columns = content[INDEX_TITLE_FIRST + 1]
                    }
                    "de" -> {
                        columns = content[INDEX_TITLE_FIRST + 2]
                    }
                    "it" -> {
                        columns = content[INDEX_TITLE_FIRST + 3]
                    }
                    "ja" -> {
                        columns = content[INDEX_TITLE_FIRST + 4]
                    }
                    "es" -> {
                        columns = content[INDEX_TITLE_FIRST + 5]
                    }
                    "ru" -> {
                        columns = content[INDEX_TITLE_FIRST + 6]
                    }
                    "pt" -> {
                        columns = content[INDEX_TITLE_FIRST + 7]
                    }
                    "th" -> {
                        columns = content[INDEX_TITLE_FIRST + 8]
                    }
                }
                columns?.let {
                    appendKeys(file, it, keys, expectKeys)
                }
            }
        }
    }

    private fun appendKeys(
        file: File,
        columns: List<String>,
        keys: List<String>,
        expectKeys: List<String>
    ) {
        val endLineStr = "</resources>"
        var endLine = 0
        val fileData = file.readLines()
        fileData.forEachIndexed { index, s ->
            s == endLineStr
            endLine = index
        }
        if (endLine <= 0) {
            println("file is not string android res")
            return
        }

        val appendKeys = ArrayList<String>()
        val appendValues = ArrayList<String>()
        val iosKeys = content[1]
        iosKeys.forEachIndexed { index, it ->
            if (index >= ROW_CONTENT) {
                if (it.isNotBlank() && it.isNotEmpty()) {
                    // key = it
                    // value = columns[index]
                    keys.forEachIndexed { i, iosKey ->
                        if (it == iosKey) {
                            expectKeys[i].let { key ->
                                appendKeys.add(key)
                                appendValues.add(columns[index])
                            }
                        }
                    }
                }
            }
        }

        fileData.forEachIndexed { index, s ->
            when (index) {
                0 -> {
                    file.writeText(s)
                    file.appendText("\n")
                }
                endLine -> {
                    appendValues.forEachIndexed { i, it ->
                        file.appendText("    <string name=\"${expectKeys[i]}\">$it</string>")
                        file.appendText("\n")
                    }
                    file.appendText(s)
                    file.appendText("\n")
                }
                else -> {
                    file.appendText(s)
                    file.appendText("\n")
                }
            }
        }
    }

    /**
     * xlsx -- [XSSFWorkbook]
     * xls  -- [HSSFWorkbook]
     */
    private fun readXLSX(file: File, sheetName: String) {
        val book = if (file.name.endsWith("xlsx")) {
            XSSFWorkbook(file.inputStream().buffered())
        } else {
            HSSFWorkbook(file.inputStream().buffered())
        }

        val sheet = book.getSheet(sheetName)
        val columns = sheet.getRow(0).lastCellNum
        println("All columns: $columns")
        for (i in 0 until columns) {
            content.add(ArrayList())
        }

        sheet.forEach { row ->
            row.forEachIndexed { columnIndex, cell ->
                val rowIndex = cell.rowIndex
                val value = cell.toString()

                println("$rowIndex, $columnIndex: $value")

                if (rowIndex < INDEX_KEY_H5) {
                    content[columnIndex].add(value)
                }
            }
        }
        book.close()
    }

    @Test
    fun studentIdCard() {
        val file = File("/Users/andy/Desktop/7.6视力筛查学生导入模板.xls")
        val sheetName = "Sheet1"

        val book = if (file.name.endsWith("xlsx")) {
            XSSFWorkbook(file.inputStream().buffered())
        } else {
            HSSFWorkbook(file.inputStream().buffered())
        }

        val sheet = book.getSheet(sheetName)
        val columns = sheet.getRow(0).lastCellNum
        println("All columns: $columns")

        sheet.forEachIndexed { rowIndex, row ->

            print("$rowIndex -> ")

            row.forEachIndexed { columnIndex, cell ->
                val value = cell.toString()
                // 身份证号
                if (rowIndex > 0 && columnIndex == 4) {
                    val sex = value[16].toInt()
                    val isEvent = sex.rem(2) == 0
                    val gender = if (isEvent) "女" else "男"

                    val year = value.substring(6, 10)
                    val month = value.substring(10, 12)
                    val day = value.substring(12, 14)
                    val birth = "$year/$month/$day"

                    val genderCell = row.getCell(2)
                    val birthCell = row.getCell(3)

                    genderCell.setCellValue(gender)
                    birthCell.setCellValue(birth)
                    print("$value : $gender : $birth")
                }
            }

            print("\n")
        }
        val ops = file.outputStream().buffered()
        book.write(ops)

        ops.flush()
        ops.close()
        book.close()
    }

    @Test
    fun androidKeys() {
        val file = File("/Users/andy/Downloads/TicApps多语言翻译.xlsx")
        val ticpodSheetName = "二代耳机191213"
        val ticpod2Translate = Ticpod2Translate()
        ticpod2Translate.readXLSX(file, ticpodSheetName)
        ticpod2Translate.generateFiles()
    }

    @Test
    fun appendIosKey2Android() {
        val file = File("/Users/andy/Downloads/TicApps多语言翻译.xlsx")
        val ticpodSheetName = "二代耳机191213"
        val ticpod2Translate = Ticpod2Translate()
        ticpod2Translate.readXLSX(file, ticpodSheetName)
        val files = ArrayList<File>()
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-de/string_ticpod2_translate.xml"))
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-en/string_ticpod2_translate.xml"))
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-es/string_ticpod2_translate.xml"))
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-fr/string_ticpod2_translate.xml"))
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-it/string_ticpod2_translate.xml"))
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-ja/string_ticpod2_translate.xml"))
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-pt/string_ticpod2_translate.xml"))
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-ru/string_ticpod2_translate.xml"))
        files.add(File("/Volumes/aosp/work/android.apps/wenwen_oversea/TicpodOversea/src/main/res/values-th/string_ticpod2_translate.xml"))
        val keys = ArrayList<String>()
        keys.add("ticpod_translate_cant_dialog_text")
        val expectKeys = ArrayList<String>()
        expectKeys.add("ticpod_translate_cant_all_mode_dialog")
        ticpod2Translate.appendFiles(files, keys, expectKeys)
    }

    @Test
    fun mobvoiVpa() {
        val file = File("/Users/andy/Downloads/TicApps多语言翻译.xlsx")
        val ticpodSheetName = "VPA提示文案"
        val ticpod2Translate = Ticpod2Translate()
        ticpod2Translate.readXLSX(file, ticpodSheetName)
        ticpod2Translate.generateFiles()
    }
}
