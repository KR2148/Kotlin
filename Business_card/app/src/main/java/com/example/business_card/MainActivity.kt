package com.example.business_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.business_card.ui.theme.Business_cardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Business_cardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    First_block(name= stringResource(R.string.name), title = stringResource(R.string.title), number = stringResource(
                        R.string.num
                    ),socmedia = stringResource(R.string.socialmedia) , email = stringResource(R.string.email),modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}


@Composable
fun First_block(
    name: String,
    title: String,
    number: String,
    socmedia: String,
    email: String,
    modifier: Modifier = Modifier
) {
    val profilePic = painterResource(R.drawable.profile)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(200.dp))
                Image(
                    painter = profilePic,
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 100.dp, height = 100.dp)
                )
                Text(
                    text = name,
                    fontSize = 50.sp
                )
                Text(
                    text = title,
                    fontSize = 30.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp)) // Add space between sections

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = number,
                fontSize = 25.sp // Adjust font size as needed
            )
            Text(
                text = socmedia,
                fontSize = 25.sp
            )
            Text(
                text = email,
                fontSize = 25.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Business_cardTheme {
        First_block(name= stringResource(R.string.name), title = stringResource(R.string.title), number = stringResource(
            R.string.num
        ),socmedia = stringResource(R.string.socialmedia) , email = stringResource(R.string.email))
    }
}