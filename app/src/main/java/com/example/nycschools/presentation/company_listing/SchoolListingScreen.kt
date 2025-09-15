package com.example.nycschools.presentation.company_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SchoolListingScreen(
    navigator: NavHostController,
    modifier: Modifier,
    viewModel: SchoolListingVM = hiltViewModel()
){
    val swipeRefershState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getSchoolListing(context = context)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SwipeRefresh(
            state = swipeRefershState,
            onRefresh = {
                viewModel.getSchoolListing(context)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.schools.size){ i ->
                    val school = state.schools[i]
                    SchoolItem(
                        school = school,
                        modifier = Modifier.fillMaxWidth()
                            .clickable{
//                                navigator.navigate(Screen.SchoolDetailScreen.route)
                            }
                    )
                    if (i < state.schools.size){
                        HorizontalDivider(
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            ),
                        )
                    }
                }
            }
        }
    }
}