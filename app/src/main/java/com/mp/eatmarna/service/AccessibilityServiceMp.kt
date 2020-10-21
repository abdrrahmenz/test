package com.mp.eatmarna.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat


//import com.qodrbee.woowandroid.db.DatabaseHelper

class AccessibilityServiceMp : AccessibilityService() {

    private lateinit var handler: Handler
//    private var mDb: DatabaseHelper? = null

    @SuppressLint("SwitchIntDef")
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (rootInActiveWindow == null) {
            return
        }

//        logd("is any event sending >>${App.SENDING_TO_WA_EVENT}<<")
//        if (!App.SENDING_TO_WA_EVENT){
//            return
//        }

//        logd("is this running?")

        val rootInActiveWindow =
            AccessibilityNodeInfoCompat.wrap(rootInActiveWindow)
        val eatmarna = "com.sejel.eatamrna"
        val whatsappMessenger = "com.whatsapp"

        when (event.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                if (event.packageName == null) {
                    return
                }
                val packageName = event.packageName.toString()
                if (packageName.contains(eatmarna)) {
                    if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                        return
                    }
                    val buttonWelcome =
                        getButtonWelcome(rootInActiveWindow)
                    if (!(buttonWelcome == null || buttonWelcome.isEmpty())) {

                        val buttonWelcomeNotFound =
                            buttonWelcome[0]
                        if (!buttonWelcomeNotFound.isVisibleToUser) {
                            return
                        }

                        // Now fire a click on the send button
                        if (buttonWelcomeNotFound.isEnabled) {
                            buttonWelcomeNotFound.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        }
                    }


                    val buttonConfirmTawakalna =
                        getButtonConfirmTawakalna(rootInActiveWindow)
                    if (!(buttonConfirmTawakalna == null || buttonConfirmTawakalna.isEmpty())) {

                        val buttonConfirm =
                            buttonConfirmTawakalna[0]
                        if (!buttonConfirm.isVisibleToUser) {
                            return
                        }

                        // Now fire a click on the send button
                        if (buttonConfirm.isEnabled) {
                            buttonConfirm.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        }
                    }

                    val buttonTabExternal =
                        getTabExternal(rootInActiveWindow)
                    if (!(buttonTabExternal == null || buttonTabExternal.isEmpty())) {

                        val buttonTab =
                            buttonTabExternal[0]
                        if (!buttonTab.isVisibleToUser) {
                            return
                        }

                        // Now fire a click on the send button
                        if (buttonTab.isEnabled) {
                            buttonTab.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        }
                    }

                    val entryNationality =
                        findEntryNationalityButton(rootInActiveWindow, eatmarna)

                    if (!(entryNationality == null || entryNationality.isEmpty())) {
                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
                        val messageField = entryNationality[0]
                        if (messageField.text == null || messageField.text.isEmpty()) { // So your service doesn't process any message, but the ones ending your apps suffix
                            return
                        }
//                        val arguments = Bundle()
//                        arguments.putCharSequence(
//                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//                            "Indonesia"
//                        )
//                        entryNationality[0].performAction(
                        entryNationality[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
//                            AccessibilityNodeInfo.ACTION_SET_TEXT,
//                            arguments
//                        )
                    }

//                    Thread.sleep(1000)

//                    val buttonIndoNationality =
//                        getIDNational(rootInActiveWindow)
//                    if (!(buttonIndoNationality == null || buttonIndoNationality.isEmpty())) {
//
//                            if (buttonIndoNationality.isNotEmpty()){
//                                for (node in buttonIndoNationality){
//                                    val parent = node.parent
//                                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
//                                }
//                            }
//                    }

                    val buttonListNationality =
                        getListNationality(rootInActiveWindow)
                    if (!(buttonListNationality == null || buttonListNationality.isEmpty())) {

                        val buttonList =
                            buttonListNationality[0]

                        buttonList.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
                    }

                    val entryPassport =
                        findEntryPassportButton(rootInActiveWindow, eatmarna)

                    if (!(entryPassport == null || entryPassport.isEmpty())) {
                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
                        val messageField = entryPassport[0]
                        if (messageField.text == null || messageField.text.isEmpty() || !messageField.text.toString()
                                .endsWith(
                                    ""
                                )
                        ) { // So your service doesn't process any message, but the ones ending your apps suffix
                            return
                        }
                        val arguments = Bundle()
                        arguments.putCharSequence(
                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//                            "2353521558"
                            "2353521558"
                        )
//                        entryPassport[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        entryPassport[0].performAction(
                            AccessibilityNodeInfo.ACTION_SET_TEXT,
                            arguments
                        )
                    }

                    val entryCountryPhone =
                        findEntryCountryPhoneButton(rootInActiveWindow, eatmarna)

                    if (!(entryCountryPhone == null || entryCountryPhone.isEmpty())) {
                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
                        val messageField = entryCountryPhone[0]
                        if (messageField.text == null || messageField.text.isEmpty()) { // So your service doesn't process any message, but the ones ending your apps suffix
                            return
                        }
                        val arguments = Bundle()
                        arguments.putCharSequence(
                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                            "+62"
                        )
//                        entryCountryPhone[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        entryCountryPhone[0].performAction(
                            AccessibilityNodeInfo.ACTION_SET_TEXT,
                            arguments
                        )
                    }

                    Thread.sleep(1000)

                    val entryNumberPhone =
                        findEntryPhoneButton(rootInActiveWindow, eatmarna)

                    if (!(entryNumberPhone == null || entryNumberPhone.isEmpty())) {
                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
                        val messageField = entryNumberPhone[0]
                        if (messageField.text == null || messageField.text.isEmpty() || !messageField.text.toString()
                                .endsWith(
                                    ""
                                )
                        ) { // So your service doesn't process any message, but the ones ending your apps suffix
                            return
                        }
                        val arguments = Bundle()
                        arguments.putCharSequence(
                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                            "89634518222"
                        )
//                        entryNumberPhone[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        entryNumberPhone[0].performAction(
                            AccessibilityNodeInfo.ACTION_SET_TEXT,
                            arguments
                        )
                    }

                    Thread.sleep(1000)

                    val entryEmail =
                        findEntryEmailButton(rootInActiveWindow, eatmarna)

                    if (!(entryEmail == null || entryEmail.isEmpty())) {
                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
                        val messageField = entryEmail[0]
                        if (messageField.text == null || messageField.text.isEmpty()) { // So your service doesn't process any message, but the ones ending your apps suffix
                            return
                        }
                        val arguments = Bundle()
                        arguments.putCharSequence(
                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                            "abdrrahmenz@gmail.com"
                        )
//                        entryEmail[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        entryEmail[0].performAction(
                            AccessibilityNodeInfo.ACTION_SET_TEXT,
                            arguments
                        )
                    }

                    Thread.sleep(1000)

                    val entryPassword =
                        findEntryPassButton(rootInActiveWindow, eatmarna)

                    if (!(entryPassword == null || entryPassword.isEmpty())) {
                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
                        val messageField = entryPassword[0]
                        if (messageField.text == null || messageField.text.isEmpty()) { // So your service doesn't process any message, but the ones ending your apps suffix
                            return
                        }
                        val arguments = Bundle()
                        arguments.putCharSequence(
                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                            "qweasd123"
                        )
//                        entryPassword[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        entryPassword[0].performAction(
                            AccessibilityNodeInfo.ACTION_SET_TEXT,
                            arguments
                        )
                    }

                    Thread.sleep(1000)

                    val entryRePassword =
                        findEntryRePassButton(rootInActiveWindow, eatmarna)

                    if (!(entryRePassword == null || entryRePassword.isEmpty())) {
                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
                        val messageField = entryRePassword[0]
                        if (messageField.text == null || messageField.text.isEmpty()) { // So your service doesn't process any message, but the ones ending your apps suffix
                            return
                        }
                        val arguments = Bundle()
                        arguments.putCharSequence(
                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                            "qweasd123"
                        )
//                        entryRePassword[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        entryRePassword[0].performAction(
                            AccessibilityNodeInfo.ACTION_SET_TEXT,
                            arguments
                        )
//                        performGlobalAction(GLOBAL_ACTION_BACK)
                    }

                    Thread.sleep(1000)

                    val entryDateOfBirth =
                        findEntryDateButton(rootInActiveWindow, eatmarna)

                    if (!(entryDateOfBirth == null || entryDateOfBirth.isEmpty())) {
                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
                        val messageField = entryDateOfBirth[0]
                        if (messageField.text == null || messageField.text.isEmpty()) { // So your service doesn't process any message, but the ones ending your apps suffix
                            return
                        }
                        val arguments = Bundle()
                        arguments.putCharSequence(
                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                            "21/12/1995"
                        )
//                        entryDateOfBirth[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        entryDateOfBirth[0].performAction(
                            AccessibilityNodeInfo.ACTION_SET_TEXT,
                            arguments
                        )
                    }

                    Thread.sleep(1000)

                    val buttonCheckbox =
                        getButtonCheckbox(rootInActiveWindow)
                    if (!(buttonCheckbox == null || buttonCheckbox.isEmpty())) {

                        val buttonCh =
                            buttonCheckbox[0]
                        if (!buttonCh.isVisibleToUser) {
                            return
                        }

                        // Now fire a click on the send button
                        if (buttonCh.isEnabled) {
                            buttonCh.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                            buttonCh.performAction(
                                AccessibilityNodeInfo.ACTION_CLEAR_ACCESSIBILITY_FOCUS)
                        }
                    }

//                    AccessibilityNodeInfo.ACTION_CLEAR_ACCESSIBILITY_FOCUS

//                    Thread.sleep(1000)
//
//                    val entryPassword =
//                        findEntryPasswordButton(rootInActiveWindow, eatmarna)
//
//                    if (!(entryPassword == null || entryPassword.isEmpty())) {
//                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
//                        val messageField = entryPassword[0]
//                        if (messageField.text == null || messageField.text.isEmpty() || !messageField.text.toString()
//                                .endsWith(
//                                    ""
//                                )
//                        ) { // So your service doesn't process any message, but the ones ending your apps suffix
//                            return
//                        }
//                        val arguments = Bundle()
//                        arguments.putCharSequence(
//                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//                            "Aan711353"
////                            "Aan71135903"
//                        )
//                        entryPassword[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
//                        entryPassword[0].performAction(
//                            AccessibilityNodeInfo.ACTION_SET_TEXT,
//                            arguments
//                        )
//                    }
//
//                    Thread.sleep(1000)
//
//                    val buttonLoginNodeInfoList =
//                        getButtonLogin(rootInActiveWindow)
//                    if (!(buttonLoginNodeInfoList == null || buttonLoginNodeInfoList.isEmpty())) {
//
//                        val goLogin =
//                            buttonLoginNodeInfoList[0]
//                        if (!goLogin.isVisibleToUser) {
//                            return
//                        }
//
//                        // Now fire a click on the send button
//
//                        if (goLogin.isEnabled) {
//                            goLogin.performAction(AccessibilityNodeInfo.ACTION_CLICK)
//                        }
//                    }

                    Thread.sleep(5000)
                }

//                    try {
//                        Thread.sleep(500)
//                        // Whatsapp Message EditText id
//                        val messageNodeList =
//                            findEntryButton(rootInActiveWindow, whatsappBusiness)
//                        if (!(messageNodeList == null || messageNodeList.isEmpty())) {
//
//                            // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
//                            val messageField = messageNodeList[0]
//                            if (messageField.text == null || messageField.text.isEmpty() || !messageField.text.toString().endsWith(
//                                    "         "
//                                )
//                            ) { // So your service doesn't process any message, but the ones ending your apps suffix
//                                return
//                            }
//
//                            Log.d("onAccessibilityText", messageField.text.toString())
//                            // Whatsapp send button id
//                            val sendMessageNodeInfoList =
//                                findButtonSend(rootInActiveWindow, whatsappBusiness)
//                            if (sendMessageNodeInfoList == null || sendMessageNodeInfoList.isEmpty()) {
//                                return
//                            }
//                            val sendMessageButton =
//                                sendMessageNodeInfoList[0]
//                            if (!sendMessageButton.isVisibleToUser) {
//                                return
//                            }
//                            // Now fire a click on the send button
//                            sendMessageButton.performAction(AccessibilityNodeInfo.ACTION_CLICK)
//
//                            Log.d("onAccessibility enable", sendMessageButton.isEnabled.toString())
//
//                            if (sendMessageButton.isEnabled) {
////                                updateWaDataInDb("success", true)
//                            }else{
////                                App.appendLog("e: send button not enable", this@AccessibilityServiceMp.javaClass)
//                                Log.d("onAccessibility enable", "send button not enable")
//                            }
//
//                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
//                                if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//                                    return
//                                }
//                                performGlobalAction(GLOBAL_ACTION_BACK)
//                            } else {
//                                if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//                                    return
//                                }
//                                performGlobalAction(GLOBAL_ACTION_BACK)
//                            }
//                        }
//                    } catch (e: NullPointerException) {
//                        e.printStackTrace()
//                        addLogError(e)
////                        Log.d("onAccessibilityEvent", e.message)
//                    } catch (e: InterruptedException) {
//                        e.printStackTrace()
//                        addLogError(e)
//                    }
//                }
//                else if (packageName.contains(whatsappMessenger)) {
//                    if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//                        return
//                    }
//                    val dialogMessageNodeInfoList =
//                        getButton1(rootInActiveWindow)
//                    if (!(dialogMessageNodeInfoList == null || dialogMessageNodeInfoList.isEmpty())) {
//
//                        val dialogPhoneWaNotFound =
//                            dialogMessageNodeInfoList[0]
//                        if (!dialogPhoneWaNotFound.isVisibleToUser) {
//                            return
//                        }
//
//                        // Now fire a click on the send button
//
////                        Log.d("onAccessibilityButton", dialogPhoneWaNotFound.isEnabled.toString())
//                        if (dialogPhoneWaNotFound.isEnabled) {
//                            updateWaDataInDb("wa number not registered", false)
////                            Log.d("dialogPhoneWa", "Not Found")
//                        }
//                    }
//
//                    try {
//                        Thread.sleep(500)
//                        // Whatsapp Message EditText id
//                        // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
////                        requireNotNull(rootInActiveWindow.findAccessibilityNodeInfosByViewId("$whatsappMessenger:id/entry")) { "whatsappMessenger object is null and cannot be processed." }
//                        // Whatsapp Message EditText id
//                        val messageNodeList =
//                            findEntryButton(rootInActiveWindow, whatsappMessenger)
//                        if (!(messageNodeList == null || messageNodeList.isEmpty())) {
//
//                            // check if the whatsapp message EditText field is filled with text and ending with your suffix (explanation above)
//                            val messageField = messageNodeList[0]
//                            if (messageField.text == null || messageField.text.isEmpty() || !messageField.text.toString().endsWith(
//                                    "         "
//                                )
//                            ) { // So your service doesn't process any message, but the ones ending your apps suffix
//                                return
//                            }
//
////                            Log.d("onAccessibilityText", messageField.text.toString())
//                            // Whatsapp send button id
//                            val sendMessageNodeInfoList =
//                                findButtonSend(rootInActiveWindow, whatsappMessenger)
//                            if (sendMessageNodeInfoList == null || sendMessageNodeInfoList.isEmpty()) {
//                                return
//                            }
//                            val sendMessageButton =
//                                sendMessageNodeInfoList[0]
//                            if (!sendMessageButton.isVisibleToUser) {
//                                return
//                            }
//                            // Now fire a click on the send button
//                            sendMessageButton.performAction(AccessibilityNodeInfo.ACTION_CLICK)
//                            if (sendMessageButton.isEnabled) {
//                                updateWaDataInDb("success", true)
//                            }else{
//                                App.appendLog("e: send button not enable", this@WhatsappAccessibilityService.javaClass)
//                            }
//
//                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
//                                if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//                                    return
//                                }
//                                performGlobalAction(GLOBAL_ACTION_BACK)
//                            } else {
//                                if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//                                    return
//                                }
//                                performGlobalAction(GLOBAL_ACTION_BACK)
//                            }
//                        }
//                    } catch (e: NullPointerException) {
//                        e.printStackTrace()
////                        Log.d("onAccessibilityEvent", e.message)
//                        addLogError(e)
//                    } catch (e: InterruptedException) {
//                        e.printStackTrace()
//                        addLogError(e)
//                    }
//                }
            }
        }
    }

    private fun findButtonSend(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        whatsappBusiness: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$whatsappBusiness:id/send"
            )
        } catch (e: java.lang.Exception) {
            addLogError(e)
            null
        }
    }


    private fun findEntryIDNumberButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_idCitizen"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryPasswordButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_passCitizen"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryNationalityButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_natVisa_newUser"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryPassportButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_passportVisa_newUser"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryCountryPhoneButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/btn_mobileVisa_newUser"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryPhoneButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_mobileVisa_newUser"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryEmailButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_emailVisa_newUser"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryPassButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_passVisa_newUser"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryRePassButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_repassVisa_newUser"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun findEntryDateButton(
        rootInActiveWindow: AccessibilityNodeInfoCompat,
        packageMp: String
    ): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId(
                "$packageMp:id/ed_dob_newUser"
            )
        } catch (e: Exception) {
            addLogError(e)
            null
        }
    }

    private fun getListNationality(rootInActiveWindow: AccessibilityNodeInfoCompat): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.sejel.eatamrna:id/listViewBtmSheet")
        } catch (e: java.lang.NullPointerException) {
            addLogError(e)
            null
        }
    }

    private fun getIDNational(rootInActiveWindow: AccessibilityNodeInfoCompat): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByText("Afghanistan")
        } catch (e: java.lang.NullPointerException) {
            addLogError(e)
            null
        }
    }

    private fun getButtonCheckbox(rootInActiveWindow: AccessibilityNodeInfoCompat): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.sejel.eatamrna:id/ch_newUser")
        } catch (e: java.lang.NullPointerException) {
            addLogError(e)
            null
        }
    }

    private fun getButtonWelcome(rootInActiveWindow: AccessibilityNodeInfoCompat): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.sejel.eatamrna:id/btn_welcome_newUser")
        } catch (e: java.lang.NullPointerException) {
            addLogError(e)
            null
        }
    }

    private fun getButtonConfirmTawakalna(rootInActiveWindow: AccessibilityNodeInfoCompat): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.sejel.eatamrna:id/btn_conf_newUser")
        } catch (e: java.lang.NullPointerException) {
            addLogError(e)
            null
        }
    }

    private fun getTabExternal(rootInActiveWindow: AccessibilityNodeInfoCompat): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.sejel.eatamrna:id/seg_visa_newUser")
        } catch (e: java.lang.NullPointerException) {
            addLogError(e)
            null
        }
    }

    private fun getCountryCode(rootInActiveWindow: AccessibilityNodeInfoCompat): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.sejel.eatamrna:id/btn_mobileVisa_newUser")
        } catch (e: java.lang.NullPointerException) {
            addLogError(e)
            null
        }
    }

    private fun getButtonLogin(rootInActiveWindow: AccessibilityNodeInfoCompat): List<AccessibilityNodeInfoCompat>? {
        return try {
            rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.sejel.eatamrna:id/btn_login")
        } catch (e: java.lang.NullPointerException) {
            addLogError(e)
            null
        }
    }

    private fun addLogError(e: java.lang.Exception) {
//        App.appendLog(
//            "error: ${Log.getStackTraceString(e)}",
//            this@AccessibilityServiceMp.javaClass
//        )
        Log.d("onAccessibility enable", "Error")
    }

    override fun onCreate() {
        super.onCreate()
//        val handlerThread = HandlerThread("accessibility-thread")
//        handlerThread.start()
//
//        val looper: Looper = handlerThread.looper
//        handler = Handler(looper)

//        mDb = DatabaseHelper.getInstance(this)
    }

//    private fun updateWaDataInDb(status_message: String, status: Boolean) {
//        val lastId = SharedPref().isLastRowId
//        when {
//            lastId.isNullOrBlank() -> { }
//            else -> {
//                val tasks = Runnable {
//                    mDb?.waDataDao()?.updateData(
//                        status, status_message, App.getCurrentTime(), lastId.toLong()
//                    )
//                }
//                handler.post(tasks)
//            }
//        }
//        App.appendLog("update ID $lastId -> $status_message", this@WhatsappAccessibilityService.javaClass)
//        Log.d("WaAccessibilityService","update /$lastId/$status/$status_message/")
//        App.SENDING_TO_WA_EVENT = false
//    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK
        info.notificationTimeout = 100
        info.packageNames = null
        serviceInfo = info
//        Log.i(TAG, "ACC::onServiceConnected: ")
    }

    private fun logd(message: String) {
        Log.e(TAG, message)
    }

    override fun onInterrupt() {}

    companion object {
        private const val TAG = "v2-Accessibility"
    }
}