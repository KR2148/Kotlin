package com.example.dice_roller

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dice_roller.ui.theme.Dice_rollerTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dice_rollerTheme {
//                DiceRollerApp()
                DiceWithButtonAndImage(modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center))
            }
        }
    }
}


//@Preview
//@Composable
//fun DiceRollerApp() {
//    DiceWithButtonAndImage()
//}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier =Modifier) {
    var result by remember { mutableStateOf(1) }
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageResource = when (result) {
            1 -> com.example.dice_roller.R.drawable.dice_1
            2 -> com.example.dice_roller.R.drawable.dice_2
            3 -> com.example.dice_roller.R.drawable.dice_3
            4 -> com.example.dice_roller.R.drawable.dice_4
            5 -> com.example.dice_roller.R.drawable.dice_5
            else -> com.example.dice_roller.R.drawable.dice_6
        }
        Image(
            painter =  painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(
                text = stringResource(com.example.dice_roller.R.string.roll_name)
            )
        }
    }
}
