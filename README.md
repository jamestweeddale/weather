# weather

<b>Overview</b>
<p>
This project implements two java applications, Weather Station and Weather Server that work together to capture and display weather data from one or more weather stations on a web-based UI. The station captures reading data and sends to the server for storage and reporting. A single server supports receipt and reporting of data from any number of stations.
I have an implementation of this weather station-server running on a Raspberry Pi 2B (station) and an AWS EC2 instance (server) using AWS RDS MySQL for storage.
</p>

<br>

<b>Station</b>
<p>
The weather station application is intended to run on a raspberry pi. It captures readings from the pi’s sensors and sends them to the weather server application’s RESTful interface using HTTP. The station application currently includes support for the following peripherals:
<ul>
<li>SHT20 I2C humidity and temperature sensor</li>
<li>Adafruit SPI anemometer via an MCP3008 A2D converter</li>
<li>Any Raspberry Pi onboard camera that is supported by the raspistill command</li>
</ul>
Configuration settings are provided to enable/disable the available sensors/peripherals as well as customize settings such as reading capture rate.
<br><br>
Technology Stack:
    Java and Pi4J library, 
    Spring Boot 2
</p>

<br>

<b>Server</b>
<p>
The weather server receives, stores, and displays weather data from one or more stations. It provides RESTful interfaces that weather stations can report readings to and a web-based UI that allows user to view the latest readings and images from each station.
<ul>
<li>Reading Storage: The server application provides RESTful interfaces for the receipt of incoming reading data and images. Reading data is persisted to a relational database and served to the UI via RESTful interfaces. 
<li>UI: The server application runs a web-based UI that displays the latest readings and images for each station that reports to it. 
</ul>
<br>
Technology Stack:
    Java, 
    MySQL, 
    Spring Boot 2, 
    Angular 7 w/Bootstrap
</p>

<br>
<p>
<img src="https://i.imgur.com/nTBlvRE.jpg" height="415px">
<img src="https://i.imgur.com/6FL0AFf.png" height="415px">
</p>

<br>

<b>Coming Soon</b>
<p>
<ul>
<li>Historical reading graphs/charts
<li>Barometer support
<li>Improved camera timing/scheduling configuration 
</p>
