package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Call
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun ComposeArticleScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.jetpack_compose_tutorial),
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = stringResource(id = R.string.jetpack_compose_is_a_modern_toolkit_for_building_native_android_ui_compose_simplifies_and_accelerates_ui_development_on_android_with_less_code_powerful_tools_and_intuitive_kotlin_apis),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = stringResource(id = R.string.in_this_tutorial_you_build_a_simple_ui_component_with_declarative_functions_you_call_compose_functions_to_say_what_elements_you_want_and_the_compose_compiler_does_the_rest_compose_is_built_around_composable_functions_these_functions_let_you_define_your_app_s_ui_programmatically_because_they_let_you_describe_how_it_should_look_and_provide_data_dependencies_rather_than_focus_on_the_process_of_the_ui_s_construction_such_as_initializing_an_element_and_then_attaching_it_to_a_parent_to_create_a_composable_function_you_add_the_composable_annotation_to_the_function_name),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun TaskManagerScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.all_tasks_completed),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.nice_work),
            fontSize = 16.sp
        )
    }
}

@Composable
fun ComposeQuadrant(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.weight(weight = 1.0f, true)) {
            ComposableInfoCard(
                title = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.\n",
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f, false)
            )
            ComposableInfoCard(
                title = "Image composable\n",
                description = "Creates a composable that lays out and draws a given Painter class object.\n",
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f, false)
            )
        }
        Row(modifier = Modifier.weight(weight = 1.0f, true)) {
            ComposableInfoCard(
                title = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.\n",
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f, false)
            )
            ComposableInfoCard(
                title = "Image composable\n",
                description = "Creates a composable that lays out and draws a given Painter class object.\n",
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f, false)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Gray),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /// なるほどね。weight をかけると、その要素と別の要素との間をとってくれて、その間に配置できるわけだね。
        NameCard(Modifier.weight(1f))
        PrivateInfo()
        Spacer(modifier = Modifier.padding(bottom = 24.dp))
    }
}

@Composable
private fun NameCard(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
        ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            null,
            Modifier.size(120.dp).background(Color(R.color.black))
        )
        Text(text = "Jennifer Doe", fontSize = 56.sp)
        Text(text = "Android Developer Extraordinaire", fontSize = 16.sp, color = Color(0xFF3ddc84))
    }
}

@Composable
private fun PrivateInfo() {
    Column(
        modifier = Modifier,
    ) {
        PrivateInfoCard(icon = Icons.Sharp.Call, info = "+11 (123) 444 555 666", modifier = Modifier.weight(1f))
        PrivateInfoCard(icon = Icons.Sharp.Share, info = "@AndroidDev", modifier = Modifier.weight(1f))
        PrivateInfoCard(icon = Icons.Sharp.Email, info = "jen.doe@android.com", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun PrivateInfoCard(icon: ImageVector, info: String, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 24.dp)
        )
        Text(text = info)
    }
}


@Preview(showBackground = true)
@Composable
private fun ComposeArticlePreview() {
    MaterialTheme {
        BusinessCard()
    }
}
