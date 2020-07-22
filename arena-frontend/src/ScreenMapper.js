
export const setScreenConfigurationMap = (map) => {
	global.screenConfigurationMap =	map;
}
export const getScreenConfiguration = (name) => {
	if(!name)
		return global.screenConfigurationMap.default;
	return {...global.screenConfigurationMap.default, ...global.screenConfigurationMap[name]};
}

