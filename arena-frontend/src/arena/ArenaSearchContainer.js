import React from 'react';
import Modal from 'react-responsive-modal';
import { CONTAINER_MODE } from './enums';
import ArenaContainer from './ArenaContainer';
import ArenaListContainer from './ArenaListContainer';
import { findEntities } from './api';
import { getClassNameBasic, modalClassNames } from './ArenaClassNames.js'; 
const ArenaSearchContainerInitialState = {
    open: false,
    example: {},
    entities: [],
    items: []
}
export default class ArenaSearchContainer extends React.Component {

    state = ArenaSearchContainerInitialState;

    find = () => {
        findEntities(this.props.controller, this.state.example)
        .then(res => res.json()
            .then(
                entities => {
                    this.setState({ entities: entities });
                }
            )
        )

    }

    select = (item) => {
        console.log("select");
        this.setState(
            {
                items: [item.id]
            }
        )
    }

	getClassName = (prefix) => {
		let {
			name,
			type
		} = this.props;
		return `${prefix}name-${name} ${prefix}type-${type} `;
	}

    render() {
        const { open } = this.state;
	const { componentMapper, controller } = this.props;
	const buttonText=componentMapper.t(`${controller}.search`);	
        return (
            <div>
                <button type="button" 
			className={getClassNameBasic("arena-open-search-modal-",this.props,"-button")} 
			onClick={() => this.setState({ open: true })}>
			{buttonText}
		</button>

                <Modal
                    open={open}
                    onClose={() => this.setState(ArenaSearchContainerInitialState)}
                    center
                    classNames={modalClassNames('search')}
                >
                    <div className="pt-4">
                        <ArenaContainer
                            {...this.props}
                            update={undefined}
                            level={1}
                            id={undefined}
                            entity={this.state.example}
                            updateEntity={(entity) => this.setState({ example: entity })}
                            mode={CONTAINER_MODE.dummy}
                        />
                        <button type="button" className={getClassNameBasic('arena-search-', this.props,'-button')} onClick={this.find}>
				{buttonText}
			</button>
                        <ArenaListContainer
                            {...this.props}
                            level={0}
                            entities={this.state.entities}
                            mode={CONTAINER_MODE.select}
                            onItemClick={this.select}
                            selected={this.state.items}
                            onSelectFinish={
                                () => {
                                    this.props.update(this.state.items[0]);
                                    this.setState(ArenaSearchContainerInitialState);
                                }
                            }
                        />
                    </div>
                </Modal>
            </div>
        )
    }
}
