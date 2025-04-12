import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.podejscie1.CartViewModel
import com.example.podejscie1.R
import com.example.podejscie1.db.CartItem

class CartAdapter(
    private val viewModel: CartViewModel
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var cartItems: List<CartItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.bind(item, position)
    }

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvIndex: TextView = view.findViewById(R.id.tvIndex)
        private val tvItemName: TextView = view.findViewById(R.id.tvItemName)
        private val tvMeat: TextView = view.findViewById(R.id.tvMeat)
        private val tvCheese: TextView = view.findViewById(R.id.tvCheese)
        private val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        private val ivMinus: ImageView = view.findViewById(R.id.ivMinus)
        private val tvAmount: TextView = view.findViewById(R.id.tvAmount)

        fun bind(item: CartItem, position: Int) {
            tvIndex.text = (position + 1).toString()
            tvItemName.text = item.name
            tvMeat.visibility = if (item.extraMeat) View.VISIBLE else View.GONE
            tvCheese.visibility = if (item.extraCheese) View.VISIBLE else View.GONE
            tvPrice.text = "42.00zł"
            tvAmount.text = item.amount.toString()

            ivMinus.setOnClickListener {
                viewModel.removeItem(item)
                cartItems = cartItems.filter { it != item }
                notifyDataSetChanged()
            }
        }
    }
}
