
import React from 'react';
import { connect } from 'react-redux';
import { shapeLoaded, loadingShape } from '../actions/shapes.js';
import ArenaEditField from './ArenaEditField.js';
import ArenaListContainer from './ArenaListContainer.js';
import ArenaSearchContainer from './ArenaSearchContainer';
import { CONTAINER_MODE, api } from './enums';
import { mapStateToProps } from './constants';
import classNames from 'classnames';


class ArenaContainer extends React.Component {


	constructor(props) {
		super(props);
		console.log(props);
		if (!props.shape && !props.loadingOwnShape) {
			this.props.loadingShape(props.controller);
			fetch(`${api}/${props.controller}/shape`)
				.then(res => {
					console.log(res); res.json()
						.then(
							(response) => {
								this.props.shapeLoaded(props.controller, response);
							}
						)
				}
				);
		}
		this.state = {}
		this.state.mode = props.mode ? props.mode : 'view';
		this.state.templates = props.componentMapper.getTemplates(this.props.controller);
		this.mappedComponent=undefined;
	}

	componentDidMount() {
		this.fetchEntity();
		this.mappedComponent = this.calculateComponent();
	}

	componentDidUpdate(prevProps) {
		if (prevProps.id !== this.props.id)
			this.fetchEntity();
		if (prevProps.controller !== this.props.controller)
			this.fetchTemplates();
	}

	fetchTemplates = () => {
		this.setState(
			{
				templates: this.props.componentMapper.getTemplates(this.props.controller)
			}
		);
	}

	fetchEntity = () => {
		if (this.props.id) {
			fetch(`${api}/${this.props.controller}/${this.props.id}`)
				.then(res => res.json()
					.then(
						(response) => {
							this.setState({ entity: response });
						}
					)
				)
		} else {
			this.setState({ entity: this.props.entity });
		}

	}

	update = (field, newValue) => {
		let entity = {
			id: this.state.entity.id,
		}
		entity[field] = newValue;
		if (this.props.mode === CONTAINER_MODE.dummy) {
			entity = { ...this.state.entity, ...entity };
			this.setState(
				{
					entity: entity
				}
			);
			this.props.updateEntity(entity);
			return;
		}
		fetch(`${api}/${this.props.controller}`, {
			method: 'POST', // or 'PUT'
			body: JSON.stringify(entity), // data can be `string` or {object}!
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(res => res.json()
			.then(
				entity => {
					this.setState({ entity: entity });
				}
			)
		)
	}

	calculateComponent(){
			return this.props.componentMapper.getComponent(
				this.props.name,
				this.props.controller, 
				this.props.parentController,
				this.props.type,
				this.props.superType,
				this.props.parentType,
				this.props.level
			);
	}

	getClassName = () => {
		let prefix='arena-container-';
		let {
			name,
			controller, 
			parentController,
			type,
			superType,
			parentType,
			level,
			mode
		} = this.props;
		return `${prefix}name-${name} ${prefix}controller-${controller} ${prefix}parentController-${parentController} ${prefix}type-${type} ${prefix}superType-${superType} ${prefix}parentType-${parentType} ${prefix}level-${level} ${prefix}mode-${mode}`;
	}

	renderMappedComponent(mappedComponent){
		let containerClassName=this.getClassName();
		return (
			<ContainerLayout
				className={
					classNames(
						containerClassName,
						this.props.className
					)
				}>
				{
					this.props.mode !== CONTAINER_MODE.view && this.props.update &&
					<ArenaSearchContainer {...this.props} />
				}
				{
					mappedComponent.render(this.state.entity, {...this.props, ...mappedComponent.props})
				}
			</ContainerLayout>
		)
	}

	renderChildren(fields) {
		const containerClassName=this.getClassName();
		const { controller, name, componentMapper } = this.props; 
		const textKey =  name ? `${controller}.${name}.label` : `${controller}.label`;
		return (
			<ContainerLayout
				className={
					classNames(
						containerClassName,
						this.props.className
					)
				}
				name={componentMapper.t(textKey)}>
				{
					this.props.mode !== CONTAINER_MODE.view && this.props.update &&
					<ArenaSearchContainer {...this.props} />
				}
				{fields}
			</ContainerLayout>
		)
	}

	calculateField(fieldName){
		let defaultField = this.props.shape.fields[fieldName];
		if(!this.props.shapeConfiguration)
			return defaultField;
		const customShapeConfiguration = this.props.shapeConfiguration[fieldName];
		if (!customShapeConfiguration)
			return defaultField;
		const self = customShapeConfiguration.self ? customShapeConfiguration.self : {};
		const field = { 
			...defaultField, 
			...self
		}
		console.log(field);
		return field;
	}

	calculateChildren() {
		let containerFields = [];
		let simpleFields = [];
		if (this.state.entity) {
			let entity = this.state.entity;
			Object.keys(this.props.shape.fields).forEach(
				(fieldName) => {
					let field = this.calculateField(fieldName);
					if (!field.hidden) {
						if (field.key) {
							if (!field.collection) {
								containerFields.unshift(
									<ConnectedContainer
										name={fieldName}
										type={field.type}
										superType={field.superType}
										id={entity[field.name]}
										mode={this.state.mode}
										level={field.level ? field.level : this.props.level - 1}
										controller={field.controller}
										parentController={this.props.controller}
										parentType={this.props.type}
										shapeConfiguration={this.props.shapeConfiguration[fieldName]}
										componentMapper={this.props.componentMapper}
										update={
											(value) => {
												this.update(fieldName, value);
											}
										}
									/>
								);
							} else {
								const template = this.state.templates[fieldName];
								containerFields.push(
									<ArenaListContainer
										name={fieldName}
										type={field.type}
										superType={field.superType}
										ids={entity[field.name]}
										mode={this.state.mode}
										level={this.props.level - 1}
										controller={field.controller}
										parentController={this.props.controller}
										parentType={this.props.type}
										shapeConfiguration={this.props.shapeConfiguration[fieldName]}
										componentMapper={this.props.componentMapper}
										template={
											template ?
												template(
													this.props,
													this.state
												) :
												undefined
										}
									/>
								);
							}

						} else {
							simpleFields.push(
								<ArenaEditField
									mode={this.state.mode}
									name={fieldName}
									field={field}
									type={field.type}
									value={entity[field.name]}
									enumeration={field.enumeration}
									update={this.update}
									level={this.props.level - 1}
									componentMapper={this.props.componentMapper}
									parentController={this.props.controller}
								/>
							);
						}
					}
				}
			)
		}
		return [...simpleFields, ...containerFields];
	}

	render() {

		if (!this.props.shape) return null;
		let mappedComponent = this.mappedComponent;
		if (this.props.level < 0 || (this.mappedComponent && this.mappedComponent.hidden )) return null;

		
		if (mappedComponent && mappedComponent.render)
			return this.renderMappedComponent(mappedComponent);
		
		let fields = this.calculateChildren();
		return this.renderChildren(fields);
	}
}

ArenaContainer.defaultProps = {
	shapeConfiguration: {}
}



const ContainerLayout = ({ name, className, children }) => {
	return (
		<div className={`${name} ${className}`}>
			<div className="">
				{name && <h2>{name}</h2>}
				{children}
			</div>
		</div>
	)
}


const ConnectedContainer = connect(mapStateToProps, { shapeLoaded, loadingShape })(ArenaContainer);
export default ConnectedContainer;
