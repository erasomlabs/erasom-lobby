package erasom.lobby

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SocialAdapter(
    private val networks: List<SocialNetwork>,
    private val activeIds: List<String>,
    private val onClick: (SocialNetwork) -> Unit
) : RecyclerView.Adapter<SocialAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val icon: TextView = view.findViewById(R.id.tvIcon)
        val label: TextView = view.findViewById(R.id.tvLabel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_social, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val network = networks[position]
        holder.icon.text = network.icon
        holder.label.text = network.label
        holder.itemView.visibility = if (activeIds.contains(network.id)) View.VISIBLE else View.GONE
        holder.itemView.setOnClickListener { onClick(network) }
    }

    override fun getItemCount(): Int = networks.size
}
