import React from 'react';
import { connect } from 'react-redux';
import { shapeLoaded, loadingShape } from '../actions/shapes.js';
import ArenaContainer from './ArenaContainer.js';
import { CONTAINER_MODE, api } from './enums';
import classNames from 'classnames';
import { mapStateToProps } from './constants';
import ArenaCreateContainer from './ArenaCreateContainer';
import { findEntities } from './api';
import { getClassNameBasic } from './ArenaClassNames.js'; 


class ArenaListContainer extends React.Component {

	constructor(props) {
		super(props);
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
		this.state.example = props.example;
		this.state.mode = props.mode ? props.mode : 'view';
	}

	componentDidMount() {
		if (this.props.ids && this.props.ids.length > 0) {
			fetch(`${api}/${this.props.controller}?ids=${this.props.ids}`)
				.then(res => res.json()
					.then(
						(response) => {
							this.setState({ entities: response });
						}
					)
				)
		} else {
			if (this.state.example) {
				findEntities(this.props.controller, this.state.example)
					.then(res => res.json()
						.then(
							entities => {
								this.setState({ entities: entities });
							}
						)
					)
			} else {
				this.setState({ entities: this.props.entities });
			}
		}
	}

	componentDidUpdate(prevProps) {
		if (prevProps.entities !== this.props.entities)
			this.componentDidMount();
	}

	addItem = (item) => {
		this.setState(
			{
				entities:
					[
						item,
						...this.state.entities,
					]
			}
		);
	}

	getCreateContainer = () => {
		if (!this.props.template) return null;
		return (
			<ArenaCreateContainer
				controller={this.props.controller}
				onCreate={this.addItem}
				template={this.props.template}
				componentMapper={this.props.componentMapper}
			></ArenaCreateContainer>
		);
	}

	isSelected = (item) => {
		const result = this.props.selected.find(id => id === item.id);
		return result !== undefined;
	}

	onItemClick = (entity) => {
		if (this.props.onItemClick)
			this.props.onItemClick(entity);
	}

	getClassName = (prefix) => {
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

	render() {
		if (!this.state.entities) {
			if (this.props.mode === CONTAINER_MODE.edit)
				return this.getCreateContainer();
			else
				return null;
		}
		let mappedComponent = this.props.componentMapper.getComponent(
			this.props.name,
			this.props.controller,
			this.props.parentController,
			this.props.type,
			this.props.superType,
			this.props.parentType,
			this.props.level
		);
		if (this.props.level < 0) return null;
		let containerClassName=this.getClassName('arena-list-item-');
		const t = this.props.componentMapper.t;
		const rows = this.state.entities.map(
			entity => {
				let selectedClass = "";
				if (this.props.mode === CONTAINER_MODE.select)
					selectedClass = this.isSelected(entity) ? "selected" : "not-selected";
				const href = this.props.shapeConfiguration.itemHref ? 
					this.props.shapeConfiguration.itemHref(entity) :
					undefined;
				return (
					<ArenaListItemWrapper 
					className={classNames(containerClassName, selectedClass)}
					onClick={() => this.onItemClick(entity)}
					href={href}>
						<ArenaContainer 
							shapeConfiguration={this.props.shapeConfiguration}
							className={getClassNameBasic("arena-list-container-",this.props,"-item")} controller={this.props.controller}
							name={this.props.name}
							type={this.props.superType}
							mode={this.state.mode}
							level={this.props.level}
							entity={entity}
							componentMapper={this.props.componentMapper}
							{...mappedComponent.props} />
					</ArenaListItemWrapper>
				);
			}
		);
		return (
			<div className={this.getClassName('arena-list-')}>
				<h2>
					{
						t(`${this.props.controller}.listLabel`)	
					}
				</h2>
				{
					this.props.template && 
					this.props.shapeConfiguration && 
					this.props.shapeConfiguration.showCreate &&
					this.getCreateContainer()
				}
				<div className={this.getClassName('arena-list-rows-')}>
					{[...rows]}
				</div>
				{
					this.props.mode === CONTAINER_MODE.select &&
					this.props.entities.length > 0 &&
					<button 
						type="button" 
						className={getClassNameBasic('arena-end-selection-',this.props,'-button')} 
						onClick={this.props.onSelectFinish}>
						{
							t(`${this.props.controller}.select`)
						}
					</button>
				}
			</div>
		)
	}

}

const ArenaListItemWrapper = ({className, onClick, href, children}) =>{
	if (href)
		return (
			<a className='arena-link' href={href}>
				<div className={className} onClick={onClick}>
					{children}
				</div>
			</a>
		)
	else
		return (
			<div className={className} onClick={onClick}>
				{children}
			</div>
		)
}

const ConnectedContainer = connect(mapStateToProps, { shapeLoaded, loadingShape })(ArenaListContainer);
export default ConnectedContainer;
