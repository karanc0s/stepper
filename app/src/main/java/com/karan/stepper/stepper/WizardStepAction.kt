package com.karan.stepper.stepper

sealed class WizardStepAction {
    /**
     * A button to perform an action.
     * @property text The title.
     * @property onClick The action to perform when the button is clicked.
     * @property enabled Whether the button is enabled.
     * @property dangerous Whether the button is dangerous (red), or not (normal).
     */

    class Action(
        val text: String,
        val onClick: () -> Unit = {},
        val enabled: Boolean = true,
        val dangerous: Boolean = false,
    ) : WizardStepAction()

    object ProgressIndicator : WizardStepAction()
}