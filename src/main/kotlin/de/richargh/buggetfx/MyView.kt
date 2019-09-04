package de.richargh.buggetfx

import tornadofx.*
import java.time.LocalDate

class MyView: View() {

    private val persons = listOf(
            PersonVM(1, "Samantha Stuart", LocalDate.of(1981, 12, 4)),
            PersonVM(2, "Tom Marks", LocalDate.of(2001, 1, 23)),
            PersonVM(3, "Stuart Gills", LocalDate.of(1989, 5, 23)),
            PersonVM(3, "Nicole Williams", LocalDate.of(1998, 8, 11))).asObservable()

    override val root = vbox {
        button("Press me")
        label("Waiting")
        tableview(persons) {
            isEditable = true
            column("ID", PersonVM::idProperty).makeEditable()
            column("Name", PersonVM::nameProperty).makeEditable()
            column("Birthday", PersonVM::birthdayProperty).makeEditable()
            readonlyColumn("Age", PersonVM::age)
        }
    }
}
