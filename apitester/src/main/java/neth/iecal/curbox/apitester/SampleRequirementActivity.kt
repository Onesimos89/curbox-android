package neth.iecal.curbox.apitester

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/** Demonstrates the protocol only. Real providers must verify their condition before approving. */
class SampleRequirementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val requestId = intent.getStringExtra("request_id")
        if (callingPackage == null || intent.action != "neth.iecal.curbox.api.CHECK_UNLOCK_CONDITION" ||
            intent.getStringExtra("condition_id") != "practice_goal" || requestId.isNullOrEmpty()) {
            finish()
            return
        }
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.api_tester_condition_title)
            .setMessage(R.string.api_tester_condition_message)
            .setPositiveButton(R.string.api_tester_condition_approve) { _, _ ->
                setResult(RESULT_OK, Intent().putExtra("request_id", requestId))
                finish()
            }
            .setNegativeButton(R.string.api_tester_condition_deny) { _, _ ->
                setResult(RESULT_CANCELED)
                finish()
            }
            .setOnCancelListener { finish() }
            .show()
    }
}
