package com.example.interviewface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.interviewface.ui.theme.InterviewfaceTheme

class MainActivity : ComponentActivity() {

    data class PracticeInterview(
        val title: String,
        val description: String,
        val imageUrl: String
    )

    private val practiceInterviews = listOf(
        PracticeInterview(
            "Entrevista de trabajo de marketing",
            "Prepárate para tu puesto de marketing entrevista",
            "https://img.freepik.com/vector-gratis/ilustracion-concepto-analisis-marketing-digital_114360-1931.jpg"
        ),
        PracticeInterview(
            "Entrevista de ingeniero de software",
            "Perfecciona tus habilidades para tu entrevista de ingeniería de software",
            "https://img.freepik.com/foto-gratis/teclado-computadora_53876-94047.jpg"
        ),
        PracticeInterview(
            "Entrevista de análisis de datos",
            "Prepárate para tu entrevista de análisis de datos",
            "https://img.freepik.com/vector-gratis/ilustracion-concepto-analisis-datos_114360-1933.jpg"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterviewfaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(practiceInterviews)
                }
            }
        }
    }
}

@Composable
fun MainScreen(practiceInterviews: List<MainActivity.PracticeInterview>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        MainCard()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Entrevistas de práctica",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        PracticeInterviewsCarousel(practiceInterviews)
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar()
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Interviewface",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun MainCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C3E50))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(120.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://img.freepik.com/vector-gratis/cabeza-humana-concepto-ideas_24877-60194.jpg"),
                contentDescription = "Main Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = "Entrevistas de comportamiento:\nPráctica técnica",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Domina las preguntas comunes de entrevista sobre código, diseño y arquitectura",
                    color = Color(0xFFB0BEC5),
                    fontSize = 14.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { /* TODO: Handle click */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
                ) {
                    Text(text = "Inicio", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun PracticeInterviewsCarousel(practiceInterviews: List<MainActivity.PracticeInterview>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(practiceInterviews) { interview ->
            PracticeInterviewCard(interview)
        }
    }
}

@Composable
fun PracticeInterviewCard(interview: MainActivity.PracticeInterview) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(220.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C3E50))
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(interview.imageUrl),
                contentDescription = interview.title,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = interview.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = interview.description,
                color = Color(0xFFB0BEC5),
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color(0xFF212121)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = true,
            onClick = { /* TODO: Handle navigation */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.VideoLibrary, contentDescription = "Entrevistas") },
            label = { Text("Entrevistas") },
            selected = false,
            onClick = { /* TODO: Handle navigation */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ChatBubble, contentDescription = "Comentarios") },
            label = { Text("Comentarios") },
            selected = false,
            onClick = { /* TODO: Handle navigation */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = false,
            onClick = { /* TODO: Handle navigation */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Menu, contentDescription = "Más") },
            label = { Text("Más") },
            selected = false,
            onClick = { /* TODO: Handle navigation */ }
        )
    }
}
