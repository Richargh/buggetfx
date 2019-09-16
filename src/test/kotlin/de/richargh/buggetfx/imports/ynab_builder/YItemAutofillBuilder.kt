package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.base.YEntityType
import de.richargh.buggetfx.imports.ynab.diff.toYAutofillCategoryId
import de.richargh.buggetfx.imports.ynab.base.toYEntityId
import de.richargh.buggetfx.imports.ynab.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.diff.YItemAutofill

class YItemAutofillBuilder {

    fun build() = YItemAutofill(
            "no memo",
            "no name",
            true,
            "A-5".toYEntityVersion(),
            "C45B535E-4AAD-C2FD-7A91-46F1AE68A6ED".toYEntityId(),
            "3A471795-F8F2-7727-F36C-D0D1A4E8C4F9".toYAutofillCategoryId(),
            YEntityType("payee"),
            -5.19)
}