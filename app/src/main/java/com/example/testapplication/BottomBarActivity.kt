package com.example.testapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.ui.home.AddPostBottomSheet
import com.example.testapplication.ui.home.ChatScreen
import com.example.testapplication.ui.home.GetACoachScreen
import com.example.testapplication.ui.home.HomeScreen
import com.example.testapplication.ui.home.MyDrawerScreen
import com.example.testapplication.ui.home.NotificationScreen
import com.example.testapplication.ui.home.ToolsScreen
import com.example.testapplication.ui.theme.TestApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BottomBarActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainBottomScreen()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainBottomScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    //val drawerState = rememberDrawerState(DrawerValue.Closed)
    Box {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                Toolbar(coroutineScope, scaffoldState)
            },
            bottomBar = {
                /*BottomAppBar(
                    cutoutShape = MaterialTheme.shapes.small.copy(
                        CornerSize(percent = 30)
                    )
                ) {
                    BottomBar(navController)
                }*/
                BottomBar(navController)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(onClick = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                }) {
                    Icon(Icons.Filled.Add, contentDescription = null, tint = Color.Black)
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = false,
            drawerContent = {
                MyDrawerScreen()
            },
            drawerShape = CutCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
            content = {
                NavHost(
                    navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.padding(it)
                ) {
                    composable(Screen.Home.route) { HomeScreen() }
                    composable(Screen.Chat.route) { ChatScreen() }
                    composable(Screen.GetACoach.route) { GetACoachScreen() }
                    composable(Screen.Notification.route) { NotificationScreen() }
                    composable(Screen.Tools.route) { ToolsScreen() }
                }
            }
        )
        AddPostBottomSheet(bottomSheetState)
    }
}

@Composable
fun Toolbar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(0) }

    TopAppBar(
        modifier = Modifier.zIndex(1f),
        title = { Text("My App") },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "On Home Click", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Filled.Home, contentDescription = "Menu")
            }
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = {
                    selectedOption = 0
                    expanded = false
                }) {
                    Text("Option 1")
                }
                DropdownMenuItem(onClick = {
                    selectedOption = 1
                    expanded = false
                }) {
                    Text("Option 2")
                }
                DropdownMenuItem(onClick = {
                    selectedOption = 2
                    expanded = false
                }) {
                    Text("Option 3")
                }
            }
        },
        backgroundColor = Color.White,
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "",
                modifier = Modifier.clickable {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                })
        }
    )
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Chat,
        Screen.GetACoach,
        Screen.Notification,
        Screen.Tools
    )

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        var selectedItem by remember { mutableStateOf(1) }

        items.forEachIndexed { index, screen ->

            BottomNavigationItem(
                icon = {
                    if (index == 3) {
                        Box() {
                            Icon(screen.icon, contentDescription = null)
                            Text(
                                text = "9+",
                                color = Color.White,
                                fontSize = 8.sp,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .background(Color.Red, shape = CircleShape)
                                    .padding(top = 1.dp, bottom = 1.dp, start = 2.dp, end = 2.dp)
                            )
                        }
                    } else {
                        Icon(screen.icon, contentDescription = null)
                    }
                },
                label = {
                    Text(
                        screen.title, maxLines = 1, fontSize = 10.sp, color = when {
                            currentRoute == screen.route -> Color.Black
                            else -> Color.LightGray
                        }
                    )
                },
                selected = currentRoute == screen.route,
                alwaysShowLabel = true,
                onClick = {
                    selectedItem = index
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    TestApplicationTheme {
        MainBottomScreen()
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Chat : Screen("chat", "Chat", Icons.Default.MailOutline)
    object GetACoach : Screen("getacoach", "Coach", Icons.Default.Person)
    object Notification : Screen("notification", "Notification", Icons.Sharp.Notifications)
    object Tools : Screen("tools", "Tools", Icons.Default.Settings)
}

data class DrawerItem(var name: String, var icon: ImageVector)