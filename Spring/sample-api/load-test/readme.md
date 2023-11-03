Run: 

./jmetter-link -Dusers=1000 -Dusers.rampup=10 -Dusers.time=60 -t Simple-gate-system.jmx -n

Generate graph:
./JMeterPluginsCMD.sh --generate-png test.png --input-jtl /media/alexferreira/SSD/Documentos/Repositorios_git/Testes_conceitos_tecnologias/tecnologias_java/Spring/sample-api/load-test/response-time-1.jtl --plugin-type ResponseTimesOverTime --width 1024 --height 800

Refs:
https://jmeter-plugins.org/wiki/JMeterPluginsCMD/
https://jmeter-plugins.org/wiki/PluginsManager/
