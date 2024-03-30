package com.example.compose.unit1.codelab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Call
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.Share
import com.example.compose.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview("BusinessCardScreen")
@Composable
fun BusinessCardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        NameSegment(Modifier.weight(1f))
        ShareSegment()
        Spacer(modifier = Modifier.padding(bottom = 24.dp))
    }
}


/**
 * プロフィール領域
 */
@Composable
fun NameSegment(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier.size(120.dp),
        )
        Text(
            text = "Full Name",
            fontSize = 50.sp,
            color = Color.White
        )
        Text(
            text = "Android Developer",
            color = Color(0xFF3ddc84),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

/**
 * 各種連携領域
 */
@Composable
fun ShareSegment() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DelimiterLine()
        ShareSegment(
            icon = Icons.Sharp.Call,
            info = "+11 (123) 444 555 666",
            modifier = Modifier.weight(1f)
        )
        DelimiterLine()
        ShareSegment(
            icon = Icons.Sharp.Share,
            info = "@AndroidDev",
            modifier = Modifier.weight(1f)
        )
        DelimiterLine()
        ShareSegment(
            icon = Icons.Sharp.Email,
            info = "jen.doe@android.com",
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * 連携領域
 */
@Composable
fun ShareSegment(icon: ImageVector, info: String, modifier:Modifier) {
    Row(Modifier.padding(4.dp)) {
        Spacer(modifier = Modifier.width(24.dp))
        Box(modifier = Modifier.padding(8.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF3ddc84),
            )
        }

        Box(modifier = modifier
            .align(Alignment.CenterVertically)) {
            Text(
                text = info,
                modifier = Modifier
                    .padding(8.dp),
                color = Color.White
            )

        }
    }
}

/**
 * 区切り線
 */
@Composable
fun DelimiterLine() {
    Spacer(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .height(1.dp)
    )
}