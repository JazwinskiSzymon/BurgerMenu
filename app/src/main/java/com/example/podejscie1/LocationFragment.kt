import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.podejscie1.R

class LocationFragment : Fragment(R.layout.fragment_location) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("MYTAG", "Location Fragment")

        return inflater.inflate(R.layout.fragment_location, container, false)
    }
}