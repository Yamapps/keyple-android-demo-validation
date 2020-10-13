/********************************************************************************
 * Copyright (c) 2020 Calypso Networks Association https://www.calypsonet-asso.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/
package org.cna.keyple.famoco.validator.ui.activities

import android.app.ProgressDialog
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import dagger.android.support.DaggerAppCompatActivity
import org.cna.keyple.famoco.validator.BuildConfig
import org.cna.keyple.famoco.validator.R
import org.cna.keyple.famoco.validator.ui.fragments.CardReaderFragment
import org.cna.keyple.famoco.validator.util.ActivityUtils.addFragmentToActivity
import javax.inject.Inject

@VisibleForTesting
class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mInjectedFragment: CardReaderFragment

    private lateinit var progress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup custom toolbar as main action bar
        val myToolbar =
            findViewById<Toolbar>(R.id.my_toolbar)
        myToolbar.title = ""
        setSupportActionBar(myToolbar)

        // Set up fragment
        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) ?: mInjectedFragment
        addFragmentToActivity(supportFragmentManager, fragment, R.id.contentFrame)

        progress = ProgressDialog(this)
        progress.setMessage(getString(R.string.please_wait))
        progress.setCancelable(false)
    }

    fun showProgress() {
        if (!progress.isShowing) {
            progress.show()
        }
    }

    fun dismissProgress() {
        if (progress.isShowing) {
            progress.dismiss()
        }
    }

    fun showNoProxyReaderDialog(t: Throwable) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error_title)
        builder.setMessage(t.message)
        builder.setNegativeButton(R.string.quit) { _, _ ->
            finish()
        }
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    fun getCurrentFlavor(): String{
        return BuildConfig.FLAVOR
    }
}
