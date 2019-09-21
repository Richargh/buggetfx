package de.richargh.buggetfx.imports.ynab_builder

import de.richargh.buggetfx.imports.ynab.model.entity.toYAutofillCategoryId
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityId
import de.richargh.buggetfx.imports.ynab.model.base.toYEntityVersion
import de.richargh.buggetfx.imports.ynab.model.entity.YPayee

class YPayeeBuilder {

    private var entityVersion = "A-5".toYEntityVersion()

    fun build(): YPayee {
        return YPayee(
                "no memo",
                "no name",
                true,
                entityVersion,
                "C45B535E-4AAD-C2FD-7A91-46F1AE68A6ED".toYEntityId(),
                "3A471795-F8F2-7727-F36C-D0D1A4E8C4F9".toYAutofillCategoryId(),
                -5.19)
    }
}