* REMOTEID: The EnOcean ID of the remote device. If not set FF:FF:FF:FF will be used.

* LOCALID: The EnOcean ID of the local side. If not set FF:FF:FF:FF will be used.

 You need this for exmaple if you want to send something with a specific sender ID.

* TYPE: The device type that should be used.

 The device type identifiers are defined by the aleoncean library.

* PARAMETER: The parameter of the device that should be used by the item.

 The parameters of the devices are defined by the aleoncean library.

* CONVPARAM: A parameter that could be used to specify the internal converter if multiple ones are available.

 A converter is used to translate between the item and the device parameter.

 E.g. a switch could be used to handle a OnOffType (ON or OFF). A rocker switch action could be "up pressed", "up released", "down pressed" or "down released". Here we can define, if how the on / off should be changed.
