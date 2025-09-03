package com.example.tablesmanagement.view.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tablesmanagement.view.screens.components.FindBar
import com.example.tablesmanagement.view.screens.components.TableCard


@Composable
fun MapScreen(navController: NavController){

    Column (
        modifier = Modifier
            .fillMaxSize(1f)

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {

            IconButton( onClick = { navController.popBackStack() } ){
                Icon( tint = Color(0xFFFF5E1A), painter = painterResource(id = com.example.tablesmanagement.R.drawable.vector),
                    contentDescription = "Voltar",
                )
            }

            Spacer(Modifier.width(16.dp))

            Text(
                text = "Mapeamento de atendimento",
                style = MaterialTheme.typography.titleLarge
            )

        }
        HorizontalDivider(
            thickness = 2.dp,
            color = Color(0x0F383737),
        )

        FindBar(modifier = Modifier.padding(8.dp))

        HorizontalDivider(
            thickness = 2.dp,
            color = Color(0x0F383737),
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0x0F383737))
            .padding(16.dp),
            ) {

           



        }





        }

    }
