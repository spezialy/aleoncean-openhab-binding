# item configuration

## configuration parameters

### REMOTEID

The EnOcean ID of the remote device. If not set FF:FF:FF:FF will be used.

### LOCALID

The EnOcean ID of the local side. If not set FF:FF:FF:FF will be used.

You need this for exmaple if you want to send something with a specific sender ID.

### TYPE

The device type that should be used.

The device type identifiers are defined by the aleoncean library.

### PARAMETER

The parameter of the device that should be used by the item.

The parameters of the devices are defined by the aleoncean library.

### CONVPARAM

A parameter that could be used to specify the internal converter if multiple ones are available.

A converter is used to translate between the item and the device parameter.

E.g. a switch could be used to handle a OnOffType (ON or OFF). A rocker switch action could be "up pressed", "up released", "down pressed" or "down released". Here we can define, if how the on / off should be changed.

## Examples

```
// Map a rocker switch to a roller shutter item (up / down / stop) -- see converter documentation
Rollershutter EO_xxyyzz01_ROLLERSHUTTER "Jalousie" {aleoncean="REMOTEID=01:02:03:01,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_A"}

// Motion sensor
Number EO_xxyyzz02_TEMPERATURE "Temperatur [%.1f °C]" {aleoncean="REMOTEID=01:02:03:02,TYPE=RD_A5-08-02,PARAMETER=TEMPERATURE_CELSIUS"}
Number EO_xxyyzz02_ILLUMINANCE "Helligkeit [%.1f lx]" {aleoncean="REMOTEID=01:02:03:02,TYPE=RD_A5-08-02,PARAMETER=ILLUMINATION_LUX"}
Number EO_xxyyzz02_POWER "Power [%.1f Volt]" {aleoncean="REMOTEID=01:02:03:02,TYPE=RD_A5-08-02,PARAMETER=SUPPLY_VOLTAGE_V"}
Switch EO_xxyyzz02_MOVEMENT "Bewegung [%s]" {aleoncean="REMOTEID=01:02:03:02,TYPE=RD_A5-08-02,PARAMETER=MOTION"}

// Climate sensor
Number EO_xxyyzz03_TEMPERATURE "Temperatur [%.1f °C]" {aleoncean="REMOTEID=01:02:03:03,TYPE=RD_A5-04-01,PARAMETER=TEMPERATURE_CELSIUS"}
Number EO_xxyyzz03_HUMIDITY "Feuchtigkeit [%.1f %%]" {aleoncean="REMOTEID=01:02:03:03,TYPE=RD_A5-04-01,PARAMETER=HUMIDITY_PERCENT"}

// EEP F6-02-01 and F6-02-02 differ between up / down direction.
Switch ROCKER_DIM_01_A "R (A) up: pressed / released" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_A,CONVPARAM=UpPressedReleased"}
Switch ROCKER_DIM_02_A "R (A) up: pressed / released" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-02,PARAMETER=BUTTON_DIM_A,CONVPARAM=UpPressedReleased"}

// Test different converters using Switch item and a rocker switch action
Number ROCKER_TEST_A_N "R (A) [%d]" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_A"}
Switch ROCKER_TEST_A_1 "R (A) up: pressed / released" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_A,CONVPARAM=UpPressedReleased"}
Switch ROCKER_TEST_A_2 "R (A) down: pressed / released" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_A,CONVPARAM=DownPressedReleased"}
Switch ROCKER_TEST_A_3 "R (A) pressed: up / down" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_A,CONVPARAM=PressedUpDown"}
Switch ROCKER_TEST_A_4 "R (A) released: up / down" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_A,CONVPARAM=ReleasedUpDown"}
Number ROCKER_TEST_B_N "R (B) [%d]" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_B"}
Switch ROCKER_TEST_B_1 "R (B) up: pressed / released" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_B,CONVPARAM=UpPressedReleased"}
Switch ROCKER_TEST_B_2 "R (B) down: pressed / released" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_B,CONVPARAM=DownPressedReleased"}
Switch ROCKER_TEST_B_3 "R (B) pressed: up / down" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_B,CONVPARAM=PressedUpDown"}
Switch ROCKER_TEST_B_4 "R (B) released: up / down" {aleoncean="REMOTEID=01:02:03:04,TYPE=RD_F6-02-01,PARAMETER=BUTTON_DIM_B,CONVPARAM=ReleasedUpDown"}

// Using a local device to simulate rocker switch action
Switch LOCAL_ROCKER_A_PRESS_UP_DOWN "L (A) up: pressed / released" {aleoncean="LOCALID=FF:80:03:04,TYPE=LD_F6-02-01,PARAMETER=BUTTON_DIM_A,CONVPARAM=UpPressedReleased"}
Switch LOCAL_ROCKER_B_PRESS_UP_DOWN "L (B) up: pressed / released" {aleoncean="LOCALID=FF:80:03:04,TYPE=LD_F6-02-01,PARAMETER=BUTTON_DIM_B,CONVPARAM=UpPressedReleased"}
```
