package erasom.lobby

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import erasom.lobby.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    private var activeIds = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("lobby_prefs", MODE_PRIVATE)
        loadSettings()

        renderSocials()
        renderControls()

        binding.btnSave.setOnClickListener {
            saveSettings()
            Toast.makeText(this, getString(R.string.saved), Toast.LENGTH_SHORT).show()
            renderSocials()
        }
    }

    private fun loadSettings() {
        val saved = prefs.getString("active_sections", null)
        activeIds = if (saved != null) {
            saved.split(",").toMutableList()
        } else {
            SocialData.networks.map { it.id }.toMutableList()
        }
    }

    private fun saveSettings() {
        prefs.edit().putString("active_sections", activeIds.joinToString(",")).apply()
    }

    private fun renderSocials() {
        val adapter = SocialAdapter(SocialData.networks, activeIds) { network ->
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(network.url)))
            } catch (e: Exception) {
                Toast.makeText(this, "No se pudo abrir", Toast.LENGTH_SHORT).show()
            }
        }
        binding.rvSocials.adapter = adapter
        binding.rvSocials.layoutManager = GridLayoutManager(this, 3)
    }

    private fun renderControls() {
        val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = layoutInflater.inflate(R.layout.item_control, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val network = SocialData.networks[position]
                val cb = holder.itemView.findViewById<CheckBox>(R.id.cbControl)
                val label = holder.itemView.findViewById<TextView>(R.id.tvControlLabel)
                label.text = network.label
                cb.isChecked = activeIds.contains(network.id)
                cb.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (!activeIds.contains(network.id)) activeIds.add(network.id)
                    } else {
                        activeIds.remove(network.id)
                    }
                }
            }

            override fun getItemCount(): Int = SocialData.networks.size
        }
        binding.rvControls.adapter = adapter
        binding.rvControls.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}
