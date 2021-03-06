package go.goskate.goskate.customizedviews

import android.app.DatePickerDialog.OnDateSetListener
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.datepicker_layout.*
import java.text.DateFormatSymbols
import java.util.*

/**
 * Control personalizado para la selección de fecha usando spinners
 *
 */
class DatePickerDialogView : DialogFragment() {

    private val daysByMont = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private val monthsArray =
        arrayOf("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic")
    private lateinit var listener: OnDateSetListener
    private val actualDate = Calendar.getInstance()
    private var windowTitle = "FECHA"
    var maxYear = 2050
    var minYear = 1900

    fun setListener(listener: OnDateSetListener, windowTitle: String) {
        this.listener = listener
        this.windowTitle = windowTitle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.datepicker_layout, container)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * Función que inicializa el contenido de los spinners
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView() {

        yearPicker.minValue = 0
        yearPicker.maxValue = 59
        dayPicker.minValue = 1
        dayPicker.maxValue = 24
        val months = DateFormatSymbols(context?.resources?.configuration?.locales?.get(0)).months
        for (monthNumber in 0 until 12) {
            monthsArray[monthNumber] = months[monthNumber].toUpperCase(Locale.getDefault())
        }
        setListeners()
        yearPicker.value =
            if (maxYear < actualDate.get(Calendar.YEAR)) maxYear else actualDate.get(Calendar.YEAR)
        dayPicker.value = actualDate.get(Calendar.DAY_OF_MONTH)
        yearPicker.wrapSelectorWheel = false
        titleTextView.text = this.windowTitle
    }

    /**
     * Establece los cambios al seleecionar un valor de un spinner.
     */
    private fun setListeners() {
        yearPicker.setOnValueChangedListener { _, _, i2 ->
            daysByMont[1] = if (i2.rem(4) == 0 && i2.rem(100) != 0) 29 else 28
        }


        datepicker_ok.setOnClickListener {
            dismiss()
        }
        datepicker_cancel.setOnClickListener {
            dismiss()
        }
    }

    /**
     * Actualiza la cantidad de dias segun el mes
     */
    private fun updateMonth(i2: Int) {
        if (dayPicker.value >= daysByMont[i2]) dayPicker.value = daysByMont[i2]
        dayPicker.maxValue = daysByMont[i2]
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setDate(formatedString: String) {
        if ("[0-3][0-9]/[0-1][0-9]/[0-9]{4}".toRegex().matches(formatedString)) {
            actualDate.apply {
                time = SimpleDateFormat("dd/MM/yyyy").parse(formatedString)
            }
        }
    }


}