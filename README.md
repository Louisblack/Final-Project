Smart-Irrigator
=============

This is my final project for my Open University Computing degree.

This is the software for a smart garden irrigation system. One of the components runs on a Raspberry Pi connected to a solenoid water valve. 

The Server component has a scheduled task that runs each morning and schedules times for watering. At the designated watering time the system checks the a weather API (Forecast.io) to find out whether it has rained recently and whether it is forcast to rain in future. If there has been no rain and no rain is forecast then the server to send a request to the Raspberry Pi to open the water valve.
