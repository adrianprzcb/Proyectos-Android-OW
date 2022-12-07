package com.example.aplicacion1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.FloatingActionButtonDefaults.elevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.aplicacion1.ui.theme.Aplicacion1Theme
import java.nio.file.Files.size

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@Composable
fun MessageList(messages: List<String>) {
    LazyColumn {
        items(messages) {
            message -> MessageInfo(message = message)
        }
    }
}

@Composable
fun MessageInfo(message: String) {
    Text(text = message)
}


@Composable
fun CardItem() {
    Card(
        Modifier.fillMaxWidth().wrapContentSize()
                ,elevation = 8.dp,
    ){
        Column(
            Modifier.padding(16.dp)
                ){
            Text(text = "hOLA",
                style = TextStyle(
                    color = Color.Red,
                    
                fontWeight = FontWeight.Bold
            )
            )
            Text(text = "esto es una prueba")
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()) {
 /*   val nameState = viewModel.textFieldState.observeAsState("")
    Surface(
        color = Color.Gray,
        modifier = Modifier.fillMaxSize()
    ){
        MainLayout(
            name = nameState.value,
        ){
            newName -> viewModel.onTextField(newName)
        }
    }
    Surface(
        color = Color.Gray,
    ){
        MessageList(messages = listOf("hola" , "como estas" , "bien y tu"))

    }*/


    Surface (
        color = Color.Gray,
        modifier = Modifier.fillMaxSize()){
        CardItem()

    }

}



@Composable
fun MainLayout(
    name: String,
    onTextFieldChange: (String) -> Unit,
){

        Column(
            modifier = Modifier.fillMaxWidth(),
        ){
            TextField(
                value = name,
                onValueChange = onTextFieldChange,
            )
            Text(
                text = name
            )
        }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

/*
@Composable
fun GreetingText(name: String) {
    Text(text = name ,
        modifier = Modifier
            .padding(16.dp),

        )style = TextStyle(
            color = Color.Red,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold

    )
}
*/

/*
@Composable
fun MainScreen(){
    Surface(
        color = Color.Gray,
        modifier = Modifier.fillMaxSize()
    ){
       Surface(
           color = Color.Green,
              modifier = Modifier
                .wrapContentSize(Alignment.TopCenter)

       ){
           Text(
               text = "Hola mundo",
               color = Color.Black,
               style = MaterialTheme.typography.h5,
               modifier = Modifier.wrapContentSize()
                   .padding(20.dp)
           )
       }
    }
}



 */
/*
@Composable
fun MainScreen(){
    Surface(
        color = Color.Gray,
        modifier = Modifier.fillMaxSize()
    ){
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            mySquare(Color.Red)
            mySquare(Color.Blue)
        }
       }
    }

@Composable
fun mySquare(color: Color){
    Surface(
        color = color,
        modifier = Modifier.size(80.dp))
    {

    }
}


 */
/*
@Composable
fun StudentList(){

    val students = remember{mutableStateListOf("mIGUESL" , "jfuesfse" , "joseñlit" ,"mnaria")}
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        for(student in students){
            Text(text= student)
        }
        Button(onClick = { students.add("Jaume") }) {
            Text(text="Add student")
        }
    }
}


 */
/*



 */
/*



 */