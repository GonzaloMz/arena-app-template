const toDeep = (obj, defaultReturn, path) =>{
	if (!obj)
		return defaultReturn;
	if(!path || path.length===0)
		if(typeof obj === 'string')
			return obj;
		else
			return defaultReturn;
	return toDeep(obj[path[0]], defaultReturn, path.slice(1));	
}


export default class Mapper{
	
	constructor(
		parentComponentsMap={},
		templatesMap={},
		componentsMap={},
		screenConfigurationMap={default:{}},
		shapeConfigurationMap={},
		filtersMap={},
		componentsTypeMap={},
		textMap={}){
			this.parentComponentsMap=parentComponentsMap;
			this.templatesMap=templatesMap;
			this.componentsMap=componentsMap;
			this.screenConfigurationMap=screenConfigurationMap;
			this.shapeConfigurationMap=shapeConfigurationMap;
			this.filtersMap=filtersMap;
			this.componentsTypeMap=componentsTypeMap;
			this.textMap=textMap;
		}

	getComponent = (name, controller, parentController, type, superType, parentType, level) => {
		let components = this.getComponents(controller)[level];
		if (components)
			return components;
		if (parentType && name) {
			let parentComponents = this.getFieldComponents(parentType, name)[level];
			if (parentComponents) {

				return components ? { ...components, ...parentComponents } : parentComponents;
			}
		}
		if (components)
			return components;
		components = this.getComponentsByType(type)[level];
		if (components)
			return components;
		if (superType)
			components = this.getComponentsByType(superType)[level];

		return components ? components : {};
	}

	getFieldComponents = (parentController, name) => {
		let parentComponentArray = this.parentComponentsMap[parentController];
		let fieldComponentArray = parentComponentArray ? parentComponentArray[name] : {};
		return fieldComponentArray;
	}

	getComponents = (repository) => {
		let componentArray = this.componentsMap[repository];
		return componentArray ? componentArray : {};
	}

	getComponentsByType = (type) => {
		let componentArray = this.componentsTypeMap[type];
		return componentArray ? componentArray : {};
	}
	getScreenConfiguration = (name) => {
		if(!name)
			return this.screenConfigurationMap.default;
		return {...this.screenConfigurationMap.default, ...this.screenConfigurationMap[name]};
	}
	getShapeConfiguration = (name) => {
	    return this.shapeConfigurationMap[name];
	}
	getTemplates = (controller) => {
		const templates = this.templatesMap[controller];
		return templates ? templates : {};
	}

	t = (key) => {
		return toDeep(this.textMap, key, key.split('.'));
	}

}
