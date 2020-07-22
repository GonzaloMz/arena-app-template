import React from 'react';
import ArenaContainer from './arena/ArenaContainer.js';
import Fullscreen from "react-full-screen";
import classNames from 'classnames';
import { CONTAINER_MODE } from './arena/enums.js';

export const setParentComponentsMap= (map) => {
	global.parentComponentsMap = map;
}

export const setTemplatesMap= (map) => {
	global.templatesMap = map;
}

export const setComponentsMap= (map) => {
	global.componentsMap = map;
}
	
export const getComponent = (name, controller, parentController, type, superType, parentType, level) => {
	let components = getComponents(controller)[level];
	console.log(parentType);
	if (components)
		return components;
	if (parentType && name) {
		let parentComponents = getFieldComponents(parentType, name)[level];
		if (parentComponents) {

			return components ? { ...components, ...parentComponents } : parentComponents;
		}
	}
	if (components)
		return components;
	components = getComponentsByType(type)[level];
	if (components)
		return components;
	if (superType)
		components = getComponentsByType(superType)[level];

	return components ? components : {};
}

const getFieldComponents = (parentController, name) => {
	let parentComponentArray = global.parentComponentsMap[parentController];
	let fieldComponentArray = parentComponentArray ? parentComponentArray[name] : {};
	return fieldComponentArray;
}

const getComponents = (repository) => {
	let componentArray = global.componentsMap[repository];
	return componentArray ? componentArray : {};
}

const getComponentsByType = (type) => {
	let componentArray = componentsTypeMap[type];
	return componentArray ? componentArray : {};
}

const componentsTypeMap = {

	"arena.backend.model.Attach": {
		"0": {
			className: "h-100",
			colClassNames: "col-12 col-md-2",
			render: (attach) => (
				<Attach attach={attach} />
			)
		}
	}
}

class Attach extends React.Component {

	state = {
		fullscreen: false
	}
	render() {
		const { attach = {} } = this.props;
		const { fullscreen } = this.state;
		return (
			<Fullscreen enabled={fullscreen}>
				<div onClick={() => this.setState({ fullscreen: !fullscreen })}>
					<strong className={
						classNames(
							{
								"d-block bg-white h1 text-dark": fullscreen
							}
						)
					} >
						{attach.name}
					</strong>
					<img
						className={
							classNames(
								{
									"w-100": !fullscreen,
								}
								, "m-auto"
							)
						}
						alt={attach.name}
						style={{ overflowY: "scroll" }}
						src={attach.url}
					/>
				</div>
			</Fullscreen>
		);
	}
}


export const getTemplates = (controller) => {
	const templates = global.templatesMap[controller];
	return templates ? templates : {};
}

