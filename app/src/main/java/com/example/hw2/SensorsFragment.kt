package com.example.hw2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SensorsFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private var proximitySensor: Sensor? = null
    private var magneticSensor: Sensor? = null

    private lateinit var lightTextView: TextView
    private lateinit var proximityTextView: TextView
    private lateinit var magneticTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sensors, container, false)
        lightTextView = view.findViewById(R.id.light_sensor_text)
        proximityTextView = view.findViewById(R.id.proximity_sensor_text)
        magneticTextView = view.findViewById(R.id.magnetic_sensor_text)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (lightSensor == null) {
            lightTextView.text = "Light Sensor not available"
            Toast.makeText(context, "Light Sensor not available", Toast.LENGTH_SHORT).show()
        }

        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if (proximitySensor == null) {
            proximityTextView.text = "Proximity Sensor not available"
            Toast.makeText(context, "Proximity Sensor not available", Toast.LENGTH_SHORT).show()
        }

        magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        if (magneticSensor == null) {
            magneticTextView.text = "Magnetic Sensor not available"
            Toast.makeText(context, "Magnetic Sensor not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        lightSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
        proximitySensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
        magneticSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        when (event.sensor.type) {
            Sensor.TYPE_LIGHT -> {
                lightTextView.text = "Light: ${event.values[0]} lx"
            }
            Sensor.TYPE_PROXIMITY -> {
                proximityTextView.text = "Proximity: ${event.values[0]} cm"
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                magneticTextView.text = "Magnetic: x=${event.values[0]}, y=${event.values[1]}, z=${event.values[2]}"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
