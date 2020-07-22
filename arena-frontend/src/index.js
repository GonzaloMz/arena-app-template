import React from 'react';
import App from './App';
import * as serviceWorker from './serviceWorker';
import "./custom.scss";
//redux
import rootReducer from './reducers';
import { Provider } from 'react-redux';

import Mapper from './mapper.js';
import Container from './arena/ArenaContainer.js';
import ListContainer from './arena/ArenaListContainer.js';
import { CONTAINER_MODE } from './arena/enums.js';

export const ArenaMapper = Mapper;

export const ArenaApp= (props) => (
	<Provider store={props.store}>
		<App componentMapper={props.componentMapper} location={props.location}/>
	</Provider>
);
export const arenaReducer = rootReducer;
export const ArenaContainer = Container;
export const ArenaListContainer = ListContainer;
export const ContainerMode = CONTAINER_MODE;
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
