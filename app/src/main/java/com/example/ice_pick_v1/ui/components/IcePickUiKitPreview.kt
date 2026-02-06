package com.example.ice_pick_v1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ice_pick_v1.ui.theme.Icepickv1Theme

@Preview(name = "IcePickScreenBackground", showBackground = true)
@Composable
private fun Preview_IcePickScreenBackground() {
    Icepickv1Theme {
        IcePickScreenBackground {

        }
    }
}

@Preview(name = "IcePickTopSearchBar", showBackground = true)
@Composable
private fun Preview_IcePickTopSearchBar() {
    Icepickv1Theme {
        IcePickScreenBackground {
            val q = remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp, 14.dp)
            ) {
                IcePickTopSearchBar(
                    query = q.value,
                    onQueryChange = { q.value = it }
                )
            }
        }
    }
}

@Preview(name = "IcePickProfileHeader", showBackground = true)
@Composable
private fun Preview_IcePickProfileHeader() {
    Icepickv1Theme {
        IcePickScreenBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp, 14.dp)
            ) {
                IcePickProfileHeader(
                    name = "Eric Trinque",
                    tags = "#Tags: Coder, CI/CD,\nTech, Startup,\nFounder, Mobile,\nArchery, Disc-Golf,\nMaker, Carpentry"
                )
            }
        }
    }
}

@Preview(name = "IcePickSectionCard", showBackground = true)
@Composable
private fun Preview_IcePickSectionCard() {
    Icepickv1Theme {
        IcePickScreenBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp, 14.dp)
            ) {
                IcePickSectionCard(title = "Friends") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IcePickMiniBubbleCard(label = "J.James")
                        IcePickMiniBubbleCard(label = "P.Jones")
                        IcePickMiniBubbleCard(label = "M.Jules")
                    }
                }
            }
        }
    }
}

@Preview(name = "IcePickMiniBubbleCard", showBackground = true)
@Composable
private fun Preview_IcePickMiniBubbleCard() {
    Icepickv1Theme {
        // Neutral background  for contrast
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IcePickMiniBubbleCard(label = "CodeCrazy")
            }
        }
    }
}

@Preview(name = "IcePickPillButton", showBackground = true)
@Composable
private fun Preview_IcePickPillButton() {
    Icepickv1Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                IcePickPillButton(
                    text = "Account",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                IcePickPillButton(
                    text = "Settings",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(name = "UI Kit - Assembled Example", showBackground = true)
@Composable
private fun Preview_AssembledExample() {
    Icepickv1Theme {
        IcePickScreenBackground {
            val q = remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 14.dp)
            ) {
                IcePickTopSearchBar(
                    query = q.value,
                    onQueryChange = { q.value = it }
                )
                Spacer(Modifier.height(16.dp))

                IcePickProfileHeader(
                    name = "Eric Trinque",
                    tags = "#Tags: Coder, CI/CD,\nTech, Startup,\nFounder, Mobile,\nArchery, Disc-Golf,\nMaker, Carpentry"
                )

                Spacer(Modifier.height(18.dp))

                IcePickSectionCard(title = "Friends") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IcePickMiniBubbleCard("J.James")
                        IcePickMiniBubbleCard("P.Jones")
                        IcePickMiniBubbleCard("M.Jules")
                    }
                }

                Spacer(Modifier.height(14.dp))

                IcePickSectionCard(title = "History") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IcePickMiniBubbleCard("CodeCrazy")
                        IcePickMiniBubbleCard("Sailaway")
                        IcePickMiniBubbleCard("MakerZ")
                    }
                }

                Spacer(Modifier.weight(1f))

                Row(modifier = Modifier.fillMaxWidth()) {
                    IcePickPillButton("Account", modifier = Modifier.weight(1f))
                    Spacer(Modifier.width(12.dp))
                    IcePickPillButton("Settings", modifier = Modifier.weight(1f))
                }
            }
        }
    }
}