package com.example.ice_pick_v1.ui.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.ice_pick_v1.ui.theme.Green20
import com.example.ice_pick_v1.ui.theme.Grey60
import com.example.ice_pick_v1.ui.theme.Grey80
import com.example.ice_pick_v1.ui.theme.Purple40
import com.example.ice_pick_v1.ui.theme.Purple80

// NOTE: "Screens" should be the composition of components/elements and smaller UI compositions.
//  They should be primarily concerned with the organization and layout of child items.
//  Data State should ideally be managed externally in a "ViewModel"
@Composable
fun DashboardScreen(modifier: Modifier, navController: NavController?) {

    // NOTE: Main Container for Screen
    Box(modifier = Modifier) {
        TopLevelSearchBar(
            modifier = Modifier,
            textFieldState = TextFieldState(""),
            onSearch = { },
        )

        // NOTE: Whole Page Layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // TODO: Figure out how to add ProfileImageCard Overlaying QuickProfileCard
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 50.dp),
//                verticalAlignment = Alignment.Bottom,

            ) {

                Box() {
                    // TODO: ProfileImage.... Card?
                    Card(
                        modifier = Modifier
                            .clip(CircleShape)
                            .widthIn(100.dp)
                            .heightIn(100.dp)
                            .zIndex(1f)
                            .offset(y = (-0).dp),
                        colors = CardDefaults.cardColors(containerColor =Grey60 )

                    ) {}
                    //TODO: QuickProfileCard
                    OutlinedCard(
                        onClick = {},
                        modifier = Modifier
                            .offset(y = 55.dp)
                            .widthIn(165.dp)
                            .heightIn(80.dp),
                        colors = CardDefaults.cardColors(Grey80)
                    ) {}
                }

                Spacer(Modifier.width(75.dp))

                // TODO: QuickLinkCard
                OutlinedCard(
                    onClick = {},
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFF4EBFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .widthIn(min = 135.dp)
                        .heightIn(135.dp),
                    colors = CardDefaults.cardColors(Grey80)
                ) {}
            }

            // NOTE: Layout for Radar/Event
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.BottomCenter,

                ) {

                // TODO: RadarMap component
                OutlinedCard(
                    onClick = {},
                    modifier = Modifier
                        .clip(CircleShape)
                        .widthIn(min = 360.dp)
                        .heightIn(min = 340.dp)
                        .zIndex(1f)
                        .alpha(0.80f),

                    colors = CardDefaults.cardColors(
                        containerColor = Grey60,
                        contentColor = Purple40
                    )
                ) {}

                // TODO: DashEventCard component
                Card(
                    onClick = {},
                    modifier = Modifier
                        .width(360.dp)
                        .height(275.dp)
                        .offset(y = 190.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Green20
                    )


                ) {}
//                Card(
//                    onClick = {},
//                    modifier = Modifier
//                        .width(width = 275.dp)
//                        .height(height = 45.dp)
//                     .offset(y = 50.dp),
//
//                ){}
//                Card(
//                    onClick = {},
//                    modifier = Modifier
//                        .width(width = 275.dp)
//                        .height(height = 45.dp)
//                        .offset(y = 110.dp),
//
//                    ){}
//                Card(
//                    onClick = {},
//                    modifier = Modifier
//                        .width(width = 275.dp)
//                        .height(height = 45.dp)
//                        .offset(y = 170.dp),
//
//
//                    ){}

            }
        }
    }

}


// TODO: TopLevelSearchBar can be moved into ui/components and imported in each file
//  that will host it. It could also be a part of a bigger component like TopAppBar/TopBar/etc.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelSearchBar(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String> = mutableListOf<String>(),

) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxSize()
    )
    {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 12.dp, bottom = 12.dp)
                .heightIn(35.dp)
                .width(250.dp),
            colors = SearchBarDefaults.colors(
                containerColor = Color(193, 193, 197, 255),   // background
                dividerColor = Color.Black,    // bottom divider
                inputFieldColors = SearchBarDefaults.inputFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.White,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedPlaceholderColor = Color.Gray
                )
            ),



            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                    onSearch = {
                        onSearch(textFieldState.text.toString())
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search") }

                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { Text(result) },
                        modifier = Modifier
                            .clickable {
                                textFieldState.edit { replace(0, length, result) }
                                expanded = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardScreen(modifier = Modifier, null)
}