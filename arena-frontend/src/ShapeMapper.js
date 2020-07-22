export const setShapeConfigurationMap =(map) => {
	global.shapeConfigurationMap = map;
}

export const getShapeConfiguration = (name) => {
    return global.shapeConfigurationMap[name];
}

