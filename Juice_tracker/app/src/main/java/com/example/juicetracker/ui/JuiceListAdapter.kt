/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.juicetracker.ui

import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.juicetracker.data.Juice
import com.example.juicetracker.data.JuiceColor
import com.example.juicetracker.databinding.ListItemBinding
import com.google.accompanist.themeadapter.material3.Mdc3Theme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.juicetracker.R
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material.MaterialTheme

class JuiceListAdapter(
    private var onEdit: (Juice) -> Unit,
    private var onDelete: (Juice) -> Unit
) : ListAdapter<Juice, JuiceListAdapter.JuiceListViewHolder>(JuiceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JuiceListViewHolder {
        return JuiceListViewHolder(
            ComposeView(parent.context),
            onEdit,
            onDelete
        )
    }

    override fun onBindViewHolder(holder: JuiceListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class JuiceListViewHolder(
        private val composeView: ComposeView,
        private val onEdit: (Juice) -> Unit,
        private val onDelete: (Juice) -> Unit

    ) : RecyclerView.ViewHolder(composeView) {

        fun bind(input: Juice) {
            composeView.setContent {
                ListItem(
                    input,
                    onDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEdit(input)
                        }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                )
            }
        }
//        RecyclerView.ViewHolder(binding.root) {
//        private val nameView = binding.name
//        private val description = binding.description
//        private val drinkImage = binding.drinkColorOverlay
//        private val ratingBar = binding.ratingBar
//
//        fun bind(juice: Juice) {
//            nameView.text = juice.name
//            description.text = juice.description
//            drinkImage.setColorFilter(
//                JuiceColor.valueOf(juice.color).color,
//                android.graphics.PorterDuff.Mode.SRC_IN
//            )
//            ratingBar.rating = juice.rating.toFloat()
//            binding.deleteButton.setOnClickListener {
//                onDelete(juice)
//            }
//            binding.root.setOnClickListener {
//                onEdit(juice)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JuiceListViewHolder(
//        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
//        onEdit,
//        onDelete
//    )
//
//    override fun onBindViewHolder(holder: JuiceListViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
    }
}
class JuiceDiffCallback : DiffUtil.ItemCallback<Juice>() {
    override fun areItemsTheSame(oldItem: Juice, newItem: Juice): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Juice, newItem: Juice): Boolean {
        return oldItem == newItem
    }
}
@Composable
fun ListItem(
    input: Juice,
    onDelete: (Juice) -> Unit,
    modifier: Modifier = Modifier
) {
    Mdc3Theme {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            JuiceIcon(input.color)
            JuiceDetails(input, Modifier.weight(1f))
            DeleteButton(
                onDelete = {
                    onDelete(input)
                },
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}
@Composable
fun JuiceIcon(color: String, modifier: Modifier = Modifier) {
    val colorLabelMap = JuiceColor.values().associateBy { stringResource(it.label) }
    val selectedColor = colorLabelMap[color]?.let { Color(it.color) }
    val juiceIconContentDescription = stringResource(R.string.juice_color, color)
    Box(
        modifier.semantics {
            contentDescription = juiceIconContentDescription
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_juice_color),
            contentDescription = null,
            tint = selectedColor ?: Color.Red,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(painter = painterResource(R.drawable.ic_juice_clear), contentDescription = null)
    }
}
//@Preview
//@Composable
//fun PreviewJuiceIcon() {
//    JuiceIcon("Yellow")
//}

@Composable
fun JuiceDetails(juice: Juice, modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.Top) {
        Text(
            text = juice.name,
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
        )
        Text(juice.description)
        RatingDisplay(rating = juice.rating, modifier = Modifier.padding(top = 8.dp))
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingDisplay(rating: Int, modifier: Modifier = Modifier) {
    val displayDescription = pluralStringResource(R.plurals.number_of_stars, count = rating)
    Row(
        // Content description is added here to support accessibility
        modifier.semantics {
            contentDescription = displayDescription
        }
    ) {
        repeat(rating) {
            // Star [contentDescription] is null as the image is for illustrative purpose
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.baseline_star_24),
                contentDescription = null
            )
        }
    }
}
//@Preview
//@Composable
//fun PreviewJuiceDetails() {
//    JuiceDetails(Juice(1, "Sweet Beet", "Apple, carrot, beet, and lemon", "Red", 4))
//}
@Composable
fun DeleteButton(onDelete: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { onDelete() },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_delete),
            contentDescription = stringResource(R.string.delete)
        )
    }
}

//@Preview
//@Composable
//fun PreviewDeleteIcon() {
//    DeleteButton({})
//}
//@Preview
//@Composable
//fun PreviewListItem() {
//    ListItem(Juice(1, "Sweet Beet", "Apple, carrot, beet, and lemon", "Red", 4), {})
//}