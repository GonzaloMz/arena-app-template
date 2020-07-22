import React from 'react';

export default class ArenaField extends React.Component {

	constructor(props){
		super(props);
		const type=props.type;
		this.state = 
			{
				value:props.value,
				id:`${type}.${props.name}`,
			}
	}

	labelClasses = () => {
		if (this.state.value)
			return 'bmd-label-static';
		else
			return 'bmd-label-placeholder';
	}

	componentDidUpdate(prevProps) {
		// Typical usage (don't forget to compare props):
		if (this.props.value !== prevProps.value) {
			this.setState({value:this.props.value});
		}
	}

	/*
	 * Event handlers
	 * --------------
	 */
	onChange = (e)=>{    
		this.setState({value: e.target.value});
	}

	onFocus = 	(e)=>{    
		this.setState({previous: this.state.value});
	}

	onBlur = (e)=>{    
		if(this.state.previous!==e.target.value){
			this.props.update(this.props.name, this.state.value);
		}
	}

	input = () => {
		let disabled;
		switch (this.props.mode) {
			case 'view':
				disabled=true;
				break;
			case 'edit':
				disabled=false;
				break;
			default:
				break;

		}
		const { componentMapper, type, parentController, name } = this.props;
		const commonProps = {
			id:this.state.id,
			className:`arena-form-control arena-form-control-${this.props.name}` , 
			value:this.state.value,
			onChange:this.onChange,
			onFocus:this.onFocus,
			onBlur:this.onBlur,
			disabled: disabled,
			placeholder: componentMapper.t(`${parentController}.${name}.placeholder`)

		}
		if (this.props.enumeration){
			return (
				<select {...commonProps} style={{height:'unset'}}>
					<option value="" disabled selected></option>
					{
						this.props.enumeration.map(
							enumeration =>(
								<option value={enumeration}>{componentMapper.t(`${type}.${enumeration}`)}</option>
							)
						)
					}
				</select>
			);
		}
		else {
			return (
				<input 
					type="text"
					{...commonProps}
				/>

			);
		}
	}

	getClassName = (prefix) => {
		let {
			name,
			parentController,
			type,
			superType,
			parentType,
			level,
			mode
		} = this.props;
		return `${prefix}name-${name} ${prefix}parentController-${parentController} ${prefix}type-${type} ${prefix}superType-${superType} ${prefix}parentType-${parentType} ${prefix}level-${level} ${prefix}mode-${mode}`;
	}

	render(){
		if (this.props.level<0) return null;
		if (this.props.name === 'reference' || this.props.name === 'id') return null;
		if (this.props.mode==='edit' && !this.props.field.editable) return null;
		const { componentMapper, parentController, name } = this.props;
		const textKey =  `${parentController}.${name}.label`;
		return (
			<div className={this.getClassName('arena-field-')}>
				<fieldset className={this.getClassName('arena-fieldset-')}>
					<label htmlFor={this.state.id} className={this.getClassName('arena-label-')}>
						{componentMapper.t(textKey)}
					</label>
					{{...this.input()}}
				</fieldset>
			</div>
		)
	}
}
