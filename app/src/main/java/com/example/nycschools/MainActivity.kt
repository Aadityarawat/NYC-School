package com.example.nycschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nycschools.domain.model.SchoolListing
import com.example.nycschools.presentation.company_info.SchoolInfoScreen
import com.example.nycschools.presentation.company_listing.SchoolListingScreen
import com.example.nycschools.ui.theme.NYCSchoolsTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NYCSchoolsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            SchoolListingScreen(
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable(
                            route = "school_info_screen/{schoolJson}",
                            arguments = listOf(navArgument("schoolJson") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val schoolJson = backStackEntry.arguments?.getString("schoolJson")
                            val decodedSchoolJson = URLDecoder.decode(schoolJson, StandardCharsets.UTF_8.toString())
                            val school = Gson().fromJson(decodedSchoolJson, SchoolListing::class.java)
                            SchoolInfoScreen(school = school,
                                modifier = Modifier.padding(innerPadding)
                            )
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
    NYCSchoolsTheme {
        Greeting("Android")
    }
}