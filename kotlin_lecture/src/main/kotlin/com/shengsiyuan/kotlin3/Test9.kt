package com.shengsiyuan.kotlin3

import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JButton
import javax.swing.JFrame

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 */

fun main() {
    val jFrame = JFrame("JFrame")

    val jButton = JButton("button")

    jFrame.addWindowListener(object : WindowListener {
        override fun windowDeiconified(e: WindowEvent?) {
        }

        override fun windowClosing(e: WindowEvent?) {
            println("closing")
        }

        override fun windowClosed(e: WindowEvent?) {
        }

        override fun windowActivated(e: WindowEvent?) {
            println("activated")
        }

        override fun windowDeactivated(e: WindowEvent?) {
        }

        override fun windowOpened(e: WindowEvent?) {
            println("opened")
        }

        override fun windowIconified(e: WindowEvent?) {
        }
    })

    jButton.addActionListener { println(it.actionCommand) }

    jFrame.add(jButton)
    jFrame.pack()
    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}