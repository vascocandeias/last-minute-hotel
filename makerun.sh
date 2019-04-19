rm src/bookings/*.class &> /dev/null
rm src/houses/*.class &> /dev/null
rm src/users/*.class &> /dev/null
rm LastMinuteHotel.class &> /dev/null
javac LastMinuteHotel.java
if (( $? )); then exit 1
else
	java LastMinuteHotel
fi
exit 0
