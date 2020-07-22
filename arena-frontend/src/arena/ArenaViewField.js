import React from 'react';

export default class ArenaViewField extends React.Component {

	render(){
		return (
			<div className="view-field">
				<div className="label">{this.props.name}</div>
				<div className="value">{this.props.value}</div>
			</div>
		)
	}
}
