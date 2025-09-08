import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.tablesmanagement.model.OrderSheet
import com.example.tablesmanagement.model.PdvDevice

@Entity(tableName = "check_pads", indices = [Index("title", unique = true)])
data class CheckPadEntity(
    @PrimaryKey val id: Int,
    val status: Boolean,
    val hash: String,
    val title: Int,
    val hasPdv: Boolean,
    val lastOrderCreated: String? = null,
    val hasOrderSheets: Boolean,
    val hasOrder: Boolean,
    val idleTime: Int,
    val activity: String,
    val pdvDevices: List<PdvDevice>,
    val orderSheets: List<OrderSheet>
)