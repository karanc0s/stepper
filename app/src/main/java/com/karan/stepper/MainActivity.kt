package com.karan.stepper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.karan.stepper.stepper.StepperComponent
import com.karan.stepper.stepper.WizardStepAction
import com.karan.stepper.stepper.WizardStepState
import com.karan.stepper.ui.theme.StepperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    StepperComponent(
                        icon = Icons.Default.LocationOn,
                        title = "Step 1", state = WizardStepState.COMPLETED,
                        showVerticalDivider = false ,
                        decoration = WizardStepAction.Action(
                            text = "This is text",
                            onClick = {

                            },
                        )
                    ) {
                        Column {
                            Text(text = "this is text 1")
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StepperTheme {
        Greeting("Android")
    }
}