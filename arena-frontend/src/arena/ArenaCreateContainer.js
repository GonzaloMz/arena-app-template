import React from 'react';
import Modal from 'react-responsive-modal';
import { CONTAINER_MODE, api } from './enums';
import ArenaContainer from './ArenaContainer.js';
import { getClassNameBasic, modalClassNames } from './ArenaClassNames.js'; 

const ArenaCreateContainerInitialState = {
	open: false
}

export default class ArenaCreateContainer extends React.Component {

	constructor(props) {
		super(props);
		this.state = ArenaCreateContainerInitialState;
		this.state.entity = props.template.entity;
		console.log(props.template);
	}

	create = () => {
		fetch(`${api}/${this.props.controller}`, {
			method: 'POST', // or 'PUT'
			body: JSON.stringify(this.state.entity), // data can be `string` or {object}!
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(res => res.json()
			.then(this.props.onCreate)
		)

	}

	componentDidMount = () => {
		this.setState(
			{
				entity: this.props.template.entity
			}
		)
	}


	render() {
		const { open } = this.state;
		const containerClass=`arena-create-${this.props.controller}`;
		const { componentMapper, controller } = this.props;
		return (
			<div className={containerClass}>
				<button 
					type="button" 
					className={getClassNameBasic("arena-open-create-modal-",this.props,"-button")}
					onClick={() => this.setState({ open: true })}>
					{
						componentMapper.t(`${controller}.create`)
					}
				</button>

				<Modal
					open={open}
					onClose={
						() => this.setState(
							{
								...ArenaCreateContainerInitialState,
								entity: this.props.template.entity
							}
						)
					}
					center
					classNames={modalClassNames('create')}
				>
					<div className="pt-4">
						{open &&
							<ArenaContainer
								{...this.props}
								update={undefined}
								level={1}
								id={undefined}
								entity={this.state.entity}
								updateEntity={(entity) => this.setState({ entity: entity })}
								mode={CONTAINER_MODE.dummy}
								shapeConfiguration={this.props.template.shapeConfiguration}
								componentMapper={this.props.componentMapper}
							/>
						}
						<button 
							type="button" 
							className={getClassNameBasic('arena-create-', this.props,'-button')} 
							onClick={this.create}>
							{
								componentMapper.t(`${controller}.create`)
							}
						</button>
					</div>
				</Modal>
			</div>
		)
	}
}
