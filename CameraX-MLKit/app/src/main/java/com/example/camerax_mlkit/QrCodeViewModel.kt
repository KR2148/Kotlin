/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.camerax_mlkit

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.view.MotionEvent
import android.view.View
import com.google.mlkit.vision.barcode.common.Barcode

/**
 * A ViewModel for encapsulating the data for a QR Code, including the encoded data, the bounding
 * box, and the touch behavior on the QR Code.
 *
 * As is, this class only handles displaying the QR Code data if it's a URL. Other data types
 * can be handled by adding more cases of Barcode.TYPE_URL in the init block.
 */
class QrCodeViewModel(barcode: Barcode) {
    var boundingRect: Rect = barcode.boundingBox!!
    var qrContent: String = ""
    var qrCodeTouchCallback = { v: View, e: MotionEvent -> false} //no-op    lambda function that handles touch events on the QR code.

    init {
        when (barcode.format) { // Use `format` to determine the type of barcode
            Barcode.FORMAT_CODE_128 -> {
                qrContent = barcode.rawValue ?: "No data"
                qrCodeTouchCallback = { _, _ -> false } // Define interaction if needed
            }
            Barcode.TYPE_URL -> {
                qrContent = barcode.url?.url ?: "Invalid URL"
                qrCodeTouchCallback = { v: View, e: MotionEvent ->
                    if (e.action == MotionEvent.ACTION_DOWN && boundingRect.contains(e.x.toInt(), e.y.toInt())) {
                        val openBrowserIntent = Intent(Intent.ACTION_VIEW)
                        openBrowserIntent.data = Uri.parse(qrContent)
                        v.context.startActivity(openBrowserIntent)
                    }
                    true // signify the event was handled
                }
            }
//        when (barcode.valueType) {
//            Barcode.TYPE_URL -> {
//                qrContent = barcode.url!!.url!!
//                qrCodeTouchCallback = { v: View, e: MotionEvent ->  //If the user taps within the bounding rectangle of the QR code, it launches an intent to open the URL in a browser.
//                    if (e.action == MotionEvent.ACTION_DOWN && boundingRect.contains(e.getX().toInt(), e.getY().toInt())) {
//                        val openBrowserIntent = Intent(Intent.ACTION_VIEW)
//                        openBrowserIntent.data = Uri.parse(qrContent)
//                        v.context.startActivity(openBrowserIntent)
//                    }
//                    true // return true from the callback to signify the event was handled
//                }
//            }
            // Add other QR Code types here to handle other types of data,
            // like Wifi credentials.
            else -> {
                qrContent = barcode.rawValue.toString()
//                qrContent = "Unsupported data type: ${barcode.rawValue.toString()}"
            }
        }
    }
}