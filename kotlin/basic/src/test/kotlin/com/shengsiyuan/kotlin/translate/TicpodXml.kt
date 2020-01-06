package com.shengsiyuan.kotlin.translate

import org.dom4j.Element
import org.dom4j.io.OutputFormat
import org.dom4j.io.SAXReader
import org.dom4j.io.XMLWriter
import java.io.File
import kotlin.test.Test

/**
 * Created by andy
 * 2019-11-28.
 * Email: 1239604859@qq.com
 */
class TicpodXml {
    @Test
    fun readXml() {
        val dataFile = File("/Users/andy/Desktop/data.xml")
        val srcFile = File("/Users/andy/Desktop/src.xml")

        val saxReader = SAXReader()
        val dataXml = saxReader.read(dataFile)
        val srcXml = saxReader.read(srcFile)
        val dataMap = HashMap<String, String>()

        val dataRoot = dataXml.rootElement
        println(dataRoot.namespace)
        println(dataRoot.name)

        dataRoot.elementIterator().forEach {
            val element = it as Element
            if (element.name == "string") {
                val key = element.attribute("name").text
                val value = element.text
                dataMap[key] = value
                println("$key = $value")
            }
        }

        val srcRoot = srcXml.rootElement
        println(srcRoot.namespace)
        println(srcRoot.name)

        dataMap.forEach { (k, v) ->
            srcRoot.elementIterator().forEach {
                val element = it as Element
                if (element.name == "string") {
                    val key = element.attribute("name").text
                    if (key == k) {
                        element.text = v
                    }
                }
            }
        }
        val format = OutputFormat.createPrettyPrint()
        val writer = XMLWriter(srcFile.outputStream(), format)
        writer.write(srcRoot)
        writer.close()
    }
}
