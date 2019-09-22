package de.richargh.buggetfx

import tornadofx.*

fun main(args: Array<String>) {
    launch<BuggetApp>(args)
}

class BuggetApp: App(MyView::class)


